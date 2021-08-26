/**
 * date: 2021/08/8
 * description: 通用js方法封装处理
 * require:
 * author: 庞海
 * version: 2.0
 *
 */

/** 消息状态码 */
const web_status = {
    SUCCESS: 0,
    FAIL: 500,
    WARNING: 301
};

/** 弹窗状态码 */
const modal_status = {
    SUCCESS: "success",
    FAIL: "error",
    WARNING: "warning"
};

/** 用户信息过期时间60分钟 */
const expire_user = 60 * 60 * 1000;

let user = {};
let roles = {};
let depts = {};
let menus = {};
let personSalary = {};
let totalSalary = {};

const crx="http://localhost:8080/salary/api";

// 当前table相关信息
var table = {
    options:{},
};

//提交方式
var submitType={
    form:'application/x-www-form-urlencoded',
    json:'application/json'
};

(function ($) {
    $.extend({
        _tree: {},
        bttTable: {},
        //缓存封装处理
        cache : {
            //存进对象(会覆盖key相同的对象)，并设置过期时间，过期时间为-1表示无期限
            set : function (key,value,expire) {
                let obj = {
                    data: value,
                    time: Date.now(),
                    expire: expire
                };
                window.localStorage.setItem(key,JSON.stringify(obj));
            },

            //取出对象 不存在或者过期返回null，存在刷新时间并返回结果
            get : function (key) {
                var val = localStorage.getItem(key);
                if (!$.common.isExist(val)) {
                    return null;
                }
                val = JSON.parse(val);
                if(val.expire===-1){  //过期时间为-1直接返回
                    return val.data;
                }
                if (Date.now() - val.time > val.expire) {//判断是否过期
                    localStorage.removeItem(key);
                    return null;
                }
                $.cache.set(key,val.data,expire_user); //刷新过期时间
                return val.data;
            },

            //项目初始化
            init : function () {
                $.operate.post(crx+'/init',{role_id:user.user.role_id},function (result) {
                    if(result.code===0){
                        $.cache.set("roles",result.data.roles, -1); //将返回的数据存到localStorage并设置过期时间为永久
                        $.cache.set("menus",result.data.menus, -1);
                        $.cache.set("depts",result.data.depts, -1);
                        $.cache.set("personSalary",result.data.personSalary, -1);
                        $.cache.set("totalSalary",result.data.totalSalary, -1);
                    }
                });

            },
        },
        // 表格封装处理
        table:{
            init : function (options) {
                table.options = options;
                layui.use('table',function () {
                    var table = layui.table;
                    //渲染表格
                    table.render({
                        elem: options.elem,
                        url: options.url,
                        toolbar: options.toolbar,
                        headers:options.headers,
                        defaultToolbar: options.defaultToolbar,
                        cols: [options.cols],
                        parseData: options.parseData,
                        done:function(res){
                            $('th').each(function(index,element){
                                $(element).attr('title',$(element).text());
                            });
                            $('td').each(function(index,element){
                                $(element).attr('title',$(element).text());
                            });
                        },
                        limits: [10, 15, 20, 25, 50, 100],
                        limit: 10,
                        page: true,
                        skin: 'row',
                        even: true
                    });
                });

            },
        },
        tree:  {
            _option: {},
            _lastValue: {},
            // 初始化树结构
            init: function(options) {
                var defaults = {
                    id: "tree",                    // 属性ID
                    expandLevel: 0,                // 展开等级节点
                    view: {
                        selectedMulti: false,      // 设置是否允许同时选中多个节点
                        nameIsHTML: true           // 设置 name 属性是否支持 HTML 脚本
                    },
                    check: {
                        enable: false,             // 置 zTree 的节点上是否显示 checkbox / radio
                        nocheckInherit: true,      // 设置子节点是否自动继承
                        chkboxType: { "Y": "ps", "N": "ps" } // 父子节点的关联关系
                    },
                    data: {
                        key: {
                            title: "title"         // 节点数据保存节点提示信息的属性名称
                        },
                        simpleData: {
                            enable: true           // true / false 分别表示 使用 / 不使用 简单数据模式
                        }
                    },
                };
                var options = $.extend(defaults, options);
                $.tree._option = options;
                // 树结构初始化加载
                var setting = {
                    callback: {
                        onClick: options.onClick,                      // 用于捕获节点被点击的事件回调函数
                        onCheck: options.onCheck,                      // 用于捕获 checkbox / radio 被勾选 或 取消勾选的事件回调函数
                        onDblClick: options.onDblClick                 // 用于捕获鼠标双击之后的事件回调函数
                    },
                    check: options.check,
                    view: options.view,
                    data: options.data
                };
                $.get(options.url, function(data) {
                    var treeId = $("#treeId").val();
                    tree = $.fn.zTree.init($("#" + options.id), setting, data);
                    $._tree = tree;
                    for (var i = 0; i < options.expandLevel; i++) {
                        var nodes = tree.getNodesByParam("level", i);
                        for (var j = 0; j < nodes.length; j++) {
                            tree.expandNode(nodes[j], true, false, false);
                        }
                    }
                    var node = tree.getNodesByParam("id", treeId, null)[0];
                    $.tree.selectByIdName(treeId, node);
                    // 回调tree方法
                    if(typeof(options.callBack) === "function"){
                        options.callBack(tree);
                    }
                });
            },
            // 搜索节点
            searchNode: function() {
                // 取得输入的关键字的值
                var value = $.common.trim($("#keyword").val());
                if ($.tree._lastValue == value) {
                    return;
                }
                // 保存最后一次搜索名称
                $.tree._lastValue = value;
                var nodes = $._tree.getNodes();
                // 如果要查空字串，就退出不查了。
                if (value == "") {
                    $.tree.showAllNode(nodes);
                    return;
                }
                $.tree.hideAllNode(nodes);
                // 根据搜索值模糊匹配
                $.tree.updateNodes($._tree.getNodesByParamFuzzy("name", value));
            },
            // 根据Id和Name选中指定节点
            selectByIdName: function(treeId, node) {
                if ($.common.isNotEmpty(treeId) && node && treeId == node.id) {
                    $._tree.selectNode(node, true);
                }
            },
            // 显示所有节点
            showAllNode: function(nodes) {
                nodes = $._tree.transformToArray(nodes);
                for (var i = nodes.length - 1; i >= 0; i--) {
                    if (nodes[i].getParentNode() != null) {
                        $._tree.expandNode(nodes[i], true, false, false, false);
                    } else {
                        $._tree.expandNode(nodes[i], true, true, false, false);
                    }
                    $._tree.showNode(nodes[i]);
                    $.tree.showAllNode(nodes[i].children);
                }
            },
            // 隐藏所有节点
            hideAllNode: function(nodes) {
                var tree = $.fn.zTree.getZTreeObj("tree");
                var nodes = $._tree.transformToArray(nodes);
                for (var i = nodes.length - 1; i >= 0; i--) {
                    $._tree.hideNode(nodes[i]);
                }
            },
            // 显示所有父节点
            showParent: function(treeNode) {
                var parentNode;
                while ((parentNode = treeNode.getParentNode()) != null) {
                    $._tree.showNode(parentNode);
                    $._tree.expandNode(parentNode, true, false, false);
                    treeNode = parentNode;
                }
            },
            // 显示所有孩子节点
            showChildren: function(treeNode) {
                if (treeNode.isParent) {
                    for (var idx in treeNode.children) {
                        var node = treeNode.children[idx];
                        $._tree.showNode(node);
                        $.tree.showChildren(node);
                    }
                }
            },
            // 更新节点状态
            updateNodes: function(nodeList) {
                $._tree.showNodes(nodeList);
                for (var i = 0, l = nodeList.length; i < l; i++) {
                    var treeNode = nodeList[i];
                    $.tree.showChildren(treeNode);
                    $.tree.showParent(treeNode)
                }
            },
            // 获取当前被勾选集合
            getCheckedNodes: function(column) {
                var _column = $.common.isEmpty(column) ? "id" : column;
                var nodes = $._tree.getCheckedNodes(true);
                return $.map(nodes, function (row) {
                    return row[_column];
                }).join();
            },
            // 不允许根父节点选择
            notAllowParents: function(_tree) {
                var nodes = _tree.getSelectedNodes();
                if(nodes.length == 0){
                    $.modal.msgError("请选择节点后提交");
                    return false;
                }
                for (var i = 0; i < nodes.length; i++) {
                    if (nodes[i].level == 0) {
                        $.modal.msgError("不能选择根节点（" + nodes[i].name + "）");
                        return false;
                    }
                    if (nodes[i].isParent) {
                        $.modal.msgError("不能选择父节点（" + nodes[i].name + "）");
                        return false;
                    }
                }
                return true;
            },
            // 不允许最后层级节点选择
            notAllowLastLevel: function(_tree) {
                var nodes = _tree.getSelectedNodes();
                for (var i = 0; i < nodes.length; i++) {
                    if (!nodes[i].isParent) {
                        $.modal.msgError("不能选择最后层级节点（" + nodes[i].name + "）");
                        return false;
                    }
                }
                return true;
            },
            // 隐藏/显示搜索栏
            toggleSearch: function() {
                $('#search').slideToggle(200);
                $('#btnShow').toggle();
                $('#btnHide').toggle();
                $('#keyword').focus();
            },
            // 折叠
            collapse: function() {
                $._tree.expandAll(false);
            },
            // 展开
            expand: function() {
                $._tree.expandAll(true);
            }
        },
        // 弹出层封装处理
        modal: {
            // 显示图标
            icon : function(type) {
                var icon = 1;
                if (type === modal_status.WARNING) {
                    icon = 0;
                } else if (type === modal_status.SUCCESS) {
                    icon = 1;
                } else if (type === modal_status.FAIL) {
                    icon = 2;
                } else {
                    icon = 3;
                }
                return icon;
            },
            // 弹出提示,默认刷新子页面
            alert : function(content, type, a) {
                layer.alert(content, {
                    icon: $.modal.icon(type),
                    title: "系统提示",
                    btn: ['确认'],
                    btnclass: ['btn btn-primary'],
                },function () {
                    if( typeof a==='function')
                        a();
                    else
                        location.reload();
                });
            },
            // 消息提示
            msg : function(content, type, a) {
                if( typeof a==='function'){
                    layer.msg(content, { icon: $.modal.icon(type), time: 1500, shift: 5 }, function () {
                        if( typeof a==='function'){
                            a();
                        }
                    });
                }
            },
            // 错误消息
            msgError : function(content,a) {
                $.modal.msg(content, modal_status.FAIL,a);
            },

            // 成功消息
            msgSuccess : function(content,a) {
                $.modal.msg(content, modal_status.SUCCESS,a);
            },

            // 警告消息
            msgWarning : function(content,a) {
                $.modal.msg(content, modal_status.WARNING,a);
            },

            // 错误提示
            alertError : function(content,a) {
                $.modal.alert(content, modal_status.FAIL,a);
            },
            // 成功提示
            alertSuccess : function(content,a) {
                $.modal.alert(content, modal_status.SUCCESS,a);
            },
            // 警告提示
            alertWarning : function(content,a) {
                $.modal.alert(content, modal_status.WARNING,a);
            },

            // 打开遮罩层
            loading : function (message) {
                $.blockUI({ message:
                        '<div class="loaderbox"><div class="loading-activity"></div> ' + message + '</div>' });
            },
            // 关闭遮罩层
            closeLoading : function () {
                setTimeout(function(){
                    $.unblockUI();
                }, 50);
            },
            //弹出一个编辑界面
            open:function (options) {
                layer.open({
                    type: 2,
                    title:options.title,
                    area: options.area,
                    btn:options.btn,
                    maxmin: true,
                    shadeClose: true,
                    content: options.content,
                    success: options.success,
                    yes: options.yes,
                });
            },
            confirm:function (options) {
                layer.confirm(options.content,{
                    btn: ['确认','取消'],
                    icon: 3,
                    title:'提示'
                }, options.callback)
            }
        },
        // 操作封装处理
        operate: {
            // 提交数据
            submit : function(url, type, dataType, contentType, data, callback) {
                var user = $.cache.get("user");
                var token=null;
                var login_name=null;
                if ($.common.isExist(user)){
                    token = user.token;
                    login_name = user.user.login_name;
                }
                var config = {
                    url: url,
                    type: type,
                    headers: {
                        token: token,
                        login_name: login_name,
                    },
                    dataType: dataType,
                    contentType: contentType,
                    data: data,
                    beforeSend: function () {
                        $.modal.loading("正在处理中，请稍后...");
                    },
                    success: function(result) {
                        if (typeof callback == "function") {
                            callback(result);
                            $.modal.closeLoading();
                        }else {
                            $.operate.ajaxSuccess(result);
                        }
                    },
                    error:function (result) {
                        $.modal.msgError("系统错误");
                        $.modal.closeLoading();
                    }
                };
                $.ajax(config);
            },

            //请求成功
            ajaxSuccess : function(result) {
                if (result.code === web_status.SUCCESS) {
                    $.modal.msgSuccess(result.msg);
                    // $.table.refresh();
                }  else if (result.code === web_status.WARNING) {
                    $.modal.alertWarning(result.msg)
                }  else {
                    $.modal.alertError(result.msg);
                }
                $.modal.closeLoading();
            },

            //post请求
            post : function(url, data, callback) {
                $.operate.submit(url, "post", "json",submitType.form, data, callback);
            },

            //get请求
            get : function(url, callback) {
                $.operate.submit(url, "get", "json", submitType.form, "", callback);
            },

            //post传输json数据格式请求
            jsonPost : function(url, data, callback) {
                $.operate.submit(url, "post", "json",submitType.json, data, callback);
            },

            //get传输json数据格式请求
            jsonGet : function(url, callback) {
                $.operate.submit(url, "get", "json", submitType.json, "", callback);
            },

            //input赋值
            setInput : function (name,value) {
                $('#'+name).val(value);
            },

            //单选框赋值
            setRadio : function (name,value) {
                $("input[name="+name+"][value=0]").attr("checked",value === '0');
                $("input[name="+name+"][value=1]").attr("checked",value === '1');
            },

        },
        //通用方法封装处理
        common: {
            //判断值是否存在,存在返回true,不存在返回false
            isExist :function (value) {
                return value!==undefined&&value!==null;
            },

            // 判断字符串是否为空
            isEmpty : function (value) {
                return value == null || this.trim(value) === "";
            },

            // 判断一个字符串是否为非空串
            isNotEmpty : function (value) {
                return $.common.isEmpty(value);
            },

            // 是否显示数据 为空默认为显示
            visible : function (value) {
                return $.common.isEmpty(value) || value === true;
            },

            // 空对象转字符串
            nullToStr : function(value) {
                if ($.common.isEmpty(value)) {
                    return "-";
                }
                return value;
            },

            // 比较两个字符串（大小写敏感）
            equals : function (str, that) {
                return str === that;
            },

            // 空格截取
            trim : function (value) {
                if (value == null) {
                    return "";
                }
                return value.toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "");
            },

            // 指定随机数返回
            random : function (min, max) {
                return Math.floor((Math.random() * max) + min);
            },

            //将中国标准时间转化为标准时间
            time : function(date) {
                if(isNaN(date)){
                    return "";
                }
                if(!isNaN(Date.parse(date))){
                    return $.common.datetimeFormat(date);
                }
                var y = date.getFullYear();
                var m = date.getMonth() + 1;
                m = m < 10 ? '0' + m : m;
                var d = date.getDate();
                d = d < 10 ? '0' + d : d;
                var h = date.getHours();
                h = h < 10 ? '0' + h : h;
                var minute = date.getMinutes();
                minute = minute < 10 ? '0' + minute : minute;
                var second = date.getSeconds();
                second = second < 10 ? '0' + second : second;
                return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second
            },

            // 把Long类型的1527672756454日期还原yyyy-MM-dd 00:00:00格式日期
            datetimeFormat : function(longTypeDate){
                var dateTypeDate = "";
                var date = new Date();
                date.setTime(longTypeDate);
                dateTypeDate += date.getFullYear(); //年
                dateTypeDate += "-" + $.common.getMonth(date); //月
                dateTypeDate += "-" + $.common.getDay(date); //日
                dateTypeDate += " " + $.common.getHours(date); //时
                dateTypeDate += ":" + $.common.getMinutes(date);  //分
                dateTypeDate += ":" + $.common.getSeconds(date);  //秒
                return dateTypeDate;
            },

             //  把Long类型的1527672756454日期还原yyyy-MM-dd格式日期
            dateFormat : function(longTypeDate){
                var dateTypeDate = "";
                var date = new Date();
                date.setTime(longTypeDate);
                dateTypeDate += date.getFullYear(); //年
                dateTypeDate += "-" + $.common.getMonth(date); //月
                dateTypeDate += "-" + $.common.getDay(date); //日
                return dateTypeDate;
            },

            //返回 01-12 的月份值
            getMonth : function (date){
                var month = "";
                month = date.getMonth() + 1; //getMonth()得到的月份是0-11
                if(month<10){
                    month = "0" + month;
                }
                return month;
            },
            //返回01-30的日期
            getDay : function (date){
                var day = "";
                day = date.getDate();
                if(day<10){
                    day = "0" + day;
                }
                return day;
            },
            //小时
            getHours : function (date){
                var hours = "";
                hours = date.getHours();
                if(hours<10){
                    hours = "0" + hours;
                }
                return hours;
            },
            //分
            getMinutes : function (date){
                var minute = "";
                minute = date.getMinutes();
                if(minute<10){
                    minute = "0" + minute;
                }
                return minute;
            },
            //秒
            getSeconds : function (date){
                var second = "";
                second = date.getSeconds();
                if(second<10){
                    second = "0" + second;
                }
                return second;
            },
            //设置子页面的值
             setFormValuate:function(body,id,value){
                body.contents().find(id).val(value);
            },

            //获取子页面的值
            getFormValue:function (body,id){
                return body.contents().find(id);
            }
        },
    })
})(jQuery);

Storage.prototype.setExpire = (key, value, expire) => {
    let obj = {
        data: value,
        time: Date.now(),
        expire: expire
    };
    //localStorage 设置的值不能为对象,转为json字符串
    localStorage.setItem(key, JSON.stringify(obj));
};

Storage.prototype.getExpire = key => {
    let val = localStorage.getItem(key);
    if (!val) {
        return val;
    }
    val = JSON.parse(val);
    if (Date.now() - val.time > val.expire) {
        localStorage.removeItem(key);
        return null;
    }
    return val.data;
};