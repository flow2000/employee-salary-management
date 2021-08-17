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
            body.contents().find("#dept_id").append("<option value=\""+v.dept_id+"\">"+v.dept_name+"</option>");
        });
        $.each($.cache.get('roles'),function(i,v){
            body.contents().find("#role_id").append("<option value=\""+v.role_id+"\">"+v.role_name+"</option>");
        });
        iframeWindow.layui.form.render();
    }
    $.modal.open(options);
}

function deleteCombineUser(table,checkStatus) {

}

function exportUserFile(excel){

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
        rederSelect();
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

function deleteUser(data,table) {
    layer.confirm('确认删除用户吗？', {
        btn: ['确认','取消'],
        icon: 3,
        title:'提示'
    }, function(index){
        var sendData={
          "user_id":data.user_id,
        };
        layer.close(index);
    })
}

function changeUserStatus() {

}
