/**
 * date: 2021/08/18
 * description: 角色管理js
 * require:
 * author: 庞海
 * version: 1.0
 *
 */

//搜索角色
function searchRole(table) {
    if($('#role_name').val()===''&&$('#role_key').val()===''&&$('#role_status').val()===''){
        table.reload('layui-table',{
            url:crx + '/role/getPageRole',
            page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    }else{
        var user = $.cache.get("user");
        var token=null;
        var login_name=null;
        if ($.common.isExist(user)){
            token = user.token;
            login_name = user.user.login_name;
        }
        table.reload('layui-table',{
            url:crx + '/role/searchRole',
            where: {
                "searchKey": "role_name;role_key;status",
                "searchValue": $('#role_name').val()+";"+ $('#role_key').val()+";"+ $('#role_status').val(),
            },
            headers: {
                token: token,
                login_name: login_name,
            },
        });
    }
}

//添加角色
function insertRole(form,table) {
    var body;
    var iframeWindow;
    var options={
        title:"添加角色",
        area: ['30%', '90%'],
        btn:['确认','取消'],
        content:'insert',
        success: function(layero, index){
            body=layer.getChildFrame('body',index);
            iframeWindow = window[layero.find('iframe')[0]['name']];
            valuation(iframeWindow);
        },
        yes: function (index, layero){
            var login_name = $.common.getFormValue(body,"#login_name").val();
            var password = $.common.getFormValue(body,"#password").val();
            var role_id = $.common.getFormValue(body,"#role_id").val();
            var dept_id = $.common.getFormValue(body,"#dept_id").val();
            var status = $.common.getFormValue(body,"input[name='status']:checked").val();

            if(login_name===''){
                $.modal.msgWarning('登录名不为空',function () {});
                return;
            }
            if(password===''){
                $.modal.msgWarning('密码不为空',function () {});
                return;
            }
            if(role_id===''){
                $.modal.msgWarning('角色不为空',function () {});
                return;
            }

            var data = {
                "user_id": 0,
                "login_name": login_name,
                "password": hex_md5(password),
                "dept_id": dept_id,
                "user_name": $.common.getFormValue(body,"#user_name").val(),
                "real_name": $.common.getFormValue(body,"#real_name").val(),
                "role_id": role_id,
                "phone_number": $.common.getFormValue(body,"#phone_number").val(),
                "email": $.common.getFormValue(body,"#email").val(),
                "status": status==='on'?'0':'1',
                "sex": $.common.getFormValue(body,"#sex").val(),
                "age": $.common.getFormValue(body,"#age").val(),
                "remark": $.common.getFormValue(body,"#remark").val(),
                "creater": $.cache.get('user').user.user_id,
            };
            if(dept_id===''){
                data.dept_id=undefined;
                delete data.dept_id;
            }
            $.operate.jsonPost(crx+'/user/insertUser',JSON.stringify(data),function (result) {
                if(result.code===0){
                    $.modal.msgSuccess(result.msg,function () {
                        layer.close(index);
                        location.reload();
                    })
                }else {
                    $.modal.msgError(result.msg,function () {})
                }
            });
        },
        end:function () {
            $('#insertUser')[0].reset();
        }
    };
    //赋值
    function valuation(iframeWindow){
        $.each($.cache.get('depts'),function(i,v){
            body.contents().find("#dept_id").append("<option value=\""+v.dept_id+"\">"+v.dept_name+"</option>");
        });
        $.each($.cache.get('roles'),function(i,v){
            body.contents().find("#role_id").append("<option value=\""+v.role_id+"\">"+v.role_name+"</option>");
        });
        iframeWindow.layui.form.render();
    }
    $.modal.open(options);
}

//批量删除角色
function deleteCombineRole(table,checkStatus) {
    var data = checkStatus.data;
    if (data.length>1){
        var options={
            content:'确定要删除吗？',
            callback:function (index) {
                var str_role_id="";
                $.each(data,function (i,v) {
                    str_role_id+=v.role_id+";";
                });
                var sendData = {
                    "role_id": str_role_id,
                    "updater": $.cache.get('user').user.user_id,
                };
                $.operate.jsonPost(crx+'/role/deleteRole',JSON.stringify(sendData),function (result) {
                    if(result.code===0){
                        $.modal.msgSuccess(result.msg,function () {
                            layer.close(index);
                            location.reload();
                        })
                    }else {
                        $.modal.msgError(result.msg,function () {})
                    }
                });
            }
        };
        $.modal.confirm(options);
    }else{
        $.modal.msgWarning('至少选择两条数据',function () {});
    }
}

//导出角色数据
function exportRoleFile(excel){
    $.operate.get(crx+'/role/getAllRole',exportExcel);
    function exportExcel(result){
        result.data.unshift({
            role_name: '角色名称',
            role_key: '权限字符',
            status: '状态',
            create_time: '创建时间',
            remark: '备注',
        });
        var data = excel.filterExportData(result.data, {
            role_name: 'role_name',
            role_key: 'role_key',
            status: function(value, line, data) {
                if(line.status !== '状态'){
                    return {
                        v: value==="0"?'正常':'停用'
                    };
                }
                return {
                    v: value
                };
            },
            create_time: function(value, line, data) {
                if(line.create_time !== '创建时间'){
                    return {
                        v: value
                    };
                }
                return {
                    v: value
                };
            },
            remark: 'remark'
        });
        excel.exportExcel({
            sheet1: data
        }, '角色数据.xlsx', 'xlsx');

    }
}

//编辑角色
function updateRole(form,data){
    var body;
    var iframeWindow;
    var options={
        title:"编辑角色",
        area: ['30%', '95%'],
        btn:['确认','取消'],
        content:'update',
        success: function(layero, index){
            body=layer.getChildFrame('body',index);
            iframeWindow = window[layero.find('iframe')[0]['name']];
            valuation(iframeWindow);
        },
        yes: function (index, layero){
            var data = {
                "user_id": $.common.getFormValue(body,"#user_id").val(),
                "dept_id": $.common.getFormValue(body,"#dept_id").val(),
                "role_id": $.common.getFormValue(body,"#role_id").val(),
                "phone_number": $.common.getFormValue(body,"#phone_number").val(),
                "email": $.common.getFormValue(body,"#email").val(),
                "sex": $.common.getFormValue(body,"#sex").val(),
                "age": $.common.getFormValue(body,"#age").val(),
                "remark": $.common.getFormValue(body,"#remark").val(),
                "updater": $.cache.get('user').user.user_id,
            };
            if(data.dept_id===''){
                data.dept_id=null;
            }
            if(data.age===''){
                data.age=null;
            }
            $.operate.jsonPost(crx+'/role/updateRole',JSON.stringify(data),function (result) {
                if(result.code===0){
                    $.modal.msgSuccess(result.msg,function () {
                        layer.close(index);
                        location.reload();
                    })
                }else {
                    $.modal.msgError(result.msg,function () {})
                }
            });
        },
        end:function () {
            $('#updateUser')[0].reset();
        }
    };
    //赋值
    function valuation(iframeWindow){
        $.each($.cache.get('depts'),function(i,v){
            body.contents().find("#dept_id").append("<option value=\""+v.dept_id+"\">"+v.dept_name+"</option>");
            if(v.dept_id===data.dept_id){
                body.contents().find("#dept_id").find("option[value="+v.dept_id+"]").prop("selected",true);
            }
        });
        $.each($.cache.get('roles'),function(i,v){
            body.contents().find("#role_id").append("<option value=\""+v.role_id+"\">"+v.role_name+"</option>");
            if(v.role_id===data.role_id){
                body.contents().find("#role_id").find("option[value="+v.role_id+"]").prop("selected",true);
            }
        });
        $.common.setFormValuate(body,"#user_id",data.user_id);
        $.common.setFormValuate(body,"#login_name",data.login_name);
        $.common.setFormValuate(body,"#dept_id",data.dept_id);
        $.common.setFormValuate(body,"#role_id",data.role_id);
        $.common.setFormValuate(body,"#phone_number",data.phone_number);
        $.common.setFormValuate(body,"#email",data.email);
        $.common.setFormValuate(body,"#age",data.age);
        $.common.setFormValuate(body,"#remark",data.remark);
        body.contents().find("#sex").find("option[value="+data.sex+"]").prop("selected",true);
        iframeWindow.layui.form.render();
    }
    $.modal.open(options);
}

//删除角色
function deleteRole(data,table) {
    var options={
        content:'确定要删除吗？',
        callback:function (index) {
            var role_id = data.role_id;
            var sendData = {
                "role_id": role_id+"",
                "updater": $.cache.get('user').user.user_id,
            };
            $.operate.jsonPost(crx+'/role/deleteRole',JSON.stringify(sendData),function (result) {
                if(result.code===0){
                    $.modal.msgSuccess(result.msg,function () {
                        layer.close(index);
                        location.reload();
                    })
                }else {
                    $.modal.msgError(result.msg,function () {})
                }
            });
        }
    };
    $.modal.confirm(options);
}

//修改角色状态
function changeRoleStatus(form,role_id,status) {
    var sendData = {
        "role_id": role_id+"",
        "status": status,
        "updater":$.cache.get('user').user.user_id,
    };
    $.operate.jsonPost(crx+'/role/changeRoleStatus',JSON.stringify(sendData),function (result) {
        if(result.code===0){
            $.modal.msgSuccess(result.msg,function () {})
        }else {
            $.modal.msgError(result.msg,function () {})
        }
    });
    form.render();//渲染开关
}
