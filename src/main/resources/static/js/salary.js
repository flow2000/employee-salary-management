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

let user = {};
let role = {};
let dept = {};
let menu = {};

const crx="http://localhost:8080/salary/api";

(function ($) {
    $.extend({
        _tree: {},
        bttTable: {},
        //缓存封装处理
        cache : {

            //存进对象(会覆盖key相同的对象)
            set : function (key,value,expire) {
                let obj = {
                    data: value,
                    time: Date.now(),
                    expire: expire
                };
                window.localStorage.setItem(key,JSON.stringify(obj));
            },

            //取出对象 不存在或者过期返回null
            get : function (key) {
                let val = localStorage.getItem(key);
                if (!$.common.isExist(val)) {
                    return null;
                }
                val = JSON.parse(val);
                if (Date.now() - val.time > val.expire) {
                    localStorage.removeItem(key);
                    return null;
                }
                return val.data;
            },

            //项目初始化
            init : function () {
                $.operate.post(crx+'/init',callback);
                function callback(result) {

                }
            },
        },
        // 表格封装处理
        table:{},
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
            // 弹出提示
            alert : function(content, type) {
                layer.alert(content, {
                    icon: $.modal.icon(type),
                    title: "系统提示",
                    btn: ['确认'],
                    btnclass: ['btn btn-primary'],
                });
            },
            // 消息提示
            msg : function(content, type) {
                if (type !== undefined) {
                    layer.msg(content, { icon: $.modal.icon(type), time: 1000, shift: 5 });
                } else {
                    layer.msg(content);
                }
            },
            // 错误消息
            msgError : function(content) {
                $.modal.msg(content, modal_status.FAIL);
            },

            // 成功消息
            msgSuccess : function(content) {
                $.modal.msg(content, modal_status.SUCCESS);
            },

            // 警告消息
            msgWarning : function(content) {
                $.modal.msg(content, modal_status.WARNING);
            },

            // 错误提示
            alertError : function(content) {
                $.modal.alert(content, modal_status.FAIL);
            },
            // 成功提示
            alertSuccess : function(content) {
                $.modal.alert(content, modal_status.SUCCESS);
            },
            // 警告提示
            alertWarning : function(content) {
                $.modal.alert(content, modal_status.WARNING);
            },

            // 打开遮罩层
            loading : function (message) {
                $.blockUI({ message: '<div class="loaderbox"><div class="loading-activity"></div> ' + message + '</div>' });
            },
            // 关闭遮罩层
            closeLoading : function () {
                setTimeout(function(){
                    $.unblockUI();
                }, 50);
            },
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
                        $.modal.alertError("系统错误");
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
                $.operate.submit(url, "post", "json","application/x-www-form-urlencoded", data, callback);
            },

            //get请求
            get : function(url, callback) {
                $.operate.submit(url, "get", "json", "application/x-www-form-urlencoded", "", callback);
            },

            //post传输json数据格式请求
            jsonPost : function(url, data, callback) {
                $.operate.submit(url, "post", "json","application/json", data, callback);
            },

            //get传输json数据格式请求
            jsonGet : function(url, callback) {
                $.operate.submit(url, "get", "json", "application/json", "", callback);
            },
        },
        //选择器封装处理
        selector: {
            layer : function(){
                var layer = null;
                layui.use(['layer'],function () {
                    layer = layui.layer;
                });
                return layer;
            },

            table : function(){
                var table = null;
                layui.use(['table'],function () {
                    table = layui.table;
                });
                return table;
            },

            form : function(){
                var form = null;
                layui.use(['form'],function () {
                    form = layui.form;
                });
                return form;
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