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
            $.operate.jsonPost(crx+'/user/updateUser',JSON.stringify(data),function (result) {
                if(result.code===0){
                    $.modal.msgSuccess(result.msg,function () {
                        layer.close(index);
                        table.reload('layui-table');
                    })
                }else {
                    $.modal.msgError(result.msg,function () {})
                }
            });

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

function resetPassword(data,table) {

}

function deleteUser(data,table) {

}

function changeUserStatus() {

}
