/**
 * date: 2021/08/18
 * description: 用户管理js
 * require:
 * author: 庞海
 * version: 1.0
 *
 */

//搜索用户
function searchUser(table) {
    if($('#login_name').val()===''&&$('#phone').val()===''&&$('#user_status').val()===''){
        table.reload('layui-table',{
            url:crx + '/user/getPageUser',
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
            url:crx + '/user/searchUser',
            where: {
                "searchKey": "login_name;phone;user.status",
                "searchValue": $('#login_name').val()+";"+ $('#phone').val()+";"+ $('#user_status').val(),
            },
            headers: {
                token: token,
                login_name: login_name,
            },
        });
    }
}

//添加用户
function insertUser(form,table) {
    var body;
    var iframeWindow;
    var options={
        title:"添加用户",
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
            if(v.status==="0"){
                body.contents().find("#dept_id").append("<option value=\""+v.dept_id+"\">"+v.dept_name+"</option>");
            }
        });
        $.each($.cache.get('roles'),function(i,v){
            if(v.status==="0"&&v.role_id!==1){
                body.contents().find("#role_id").append("<option value=\""+v.role_id+"\">"+v.role_name+"</option>");
            }
        });
        iframeWindow.layui.form.render();
    }
    $.modal.open(options);
}

//批量删除用户
function deleteCombineUser(table,checkStatus) {
    var data = checkStatus.data;
    if (data.length>1){
        var options={
            content:'确定要删除吗？',
            callback:function (index) {
                var str_user_id="";
                $.each(data,function (i,v) {
                    str_user_id+=v.user_id+";";
                });
                var sendData = {
                    "user_id": str_user_id,
                    "updater": $.cache.get('user').user.user_id,
                };
                $.operate.jsonPost(crx+'/user/deleteUser',JSON.stringify(sendData),function (result) {
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

//导出用户数据
function exportUserFile(excel){
    $.operate.get(crx+'/user/getAllUser',exportExcel);
    function exportExcel(result){
        result.data.unshift({
            login_name: '登录名称',
            dept_name: '部门名称',
            role_name: '角色名称',
            user_name: '用户名称',
            real_name: '姓名',
            sex: '性别',
            age: '年龄',
            phone_number: '电话',
            email: '邮箱',
            status: '状态',
            create_time: '创建时间',
            remark: '备注',
        });
        var data = excel.filterExportData(result.data, {
            login_name: 'login_name',
            dept_name: 'dept_name',
            role_name: 'role_name',
            user_name: 'user_name',
            real_name: 'real_name',
            sex: function(value, line, data) {
                if(line.sex !== '性别'){
                    var sex;
                    if(value==="0")
                        sex='未知';
                    else if(value==="1")
                        sex='女';
                    else
                        sex='男';
                    return {
                        v: sex
                    };
                }
                return {
                    v: value
                };
            },
            age: 'age',
            phone_number: 'phone_number',
            email: 'email',
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
        }, '用户数据.xlsx', 'xlsx');

    }
}

//编辑用户
function updateUser(form,data){
    var body;
    var iframeWindow;
    var options={
        title:"编辑用户",
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
            $.operate.jsonPost(crx+'/user/updateUser',JSON.stringify(data),function (result) {
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
            if(v.status==="0"){
                body.contents().find("#dept_id").append("<option value=\""+v.dept_id+"\">"+v.dept_name+"</option>");
            }
            if(v.dept_id===data.dept_id){
                body.contents().find("#dept_id").find("option[value="+v.dept_id+"]").prop("selected",true);
            }
        });
        $.each($.cache.get('roles'),function(i,v){
            if(v.status==="0"&&v.role_id!==1){
                body.contents().find("#role_id").append("<option value=\""+v.role_id+"\">"+v.role_name+"</option>");
            }
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

//重置密码
function resetPassword(data,table) {
    var body;
    var iframeWindow;
    var options={
        title:"重置密码",
        area: ['30%', '30%'],
        btn:['确认','取消'],
        content:'reset',
        success: function(layero, index){
            body=layer.getChildFrame('body',index);
            iframeWindow = window[layero.find('iframe')[0]['name']];
        },
        yes: function (index, layero){
            var user_id = data.user_id;
            var login_name = data.login_name;
            var password = $.common.getFormValue(body,"#password").val();
            var sendData = {
                "user_id": user_id,
                "login_name": login_name,
                "password": hex_md5(password),
                "updater": $.cache.get('user').user.user_id,
            };
            console.log(sendData);
            $.operate.jsonPost(crx+'/user/resetUserPassword',JSON.stringify(sendData),function (result) {
                if(result.code===0){
                    $.modal.msgSuccess(result.msg,function () {
                        layer.close(index);
                    })
                }else {
                    $.modal.msgError(result.msg,function () {})
                }
            });
        },
        end:function () {
            $('#resetPassword')[0].reset();
        }
    };
    $.modal.open(options);
}

//删除用户
function deleteUser(data,table) {
    var options={
      content:'确定要删除吗？',
        callback:function (index) {
            var user_id = data.user_id;
            var sendData = {
                "user_id": user_id+"",
                "updater": $.cache.get('user').user.user_id,
            };
            $.operate.jsonPost(crx+'/user/deleteUser',JSON.stringify(sendData),function (result) {
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

//修改用户状态
function changeUserStatus(form,user_id,status) {
    var sendData = {
        "user_id": user_id+"",
        "status": status,
        "updater":$.cache.get('user').user.user_id,
    };
    $.operate.jsonPost(crx+'/user/changeUserStatus',JSON.stringify(sendData),function (result) {
        if(result.code===0){
            $.modal.msgSuccess(result.msg,function () {})
        }else {
            $.modal.msgError(result.msg,function () {})
        }
    });
    form.render();//渲染开关
}
