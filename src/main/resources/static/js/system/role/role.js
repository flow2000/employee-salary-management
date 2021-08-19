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
    let body;
    let iframeWindow;
    var user_id=$.cache.get("user").user.user_id;
    var options={
        title:"添加角色",
        area: ['50%', '95%'],
        btn:['确认','取消'],
        content:'insert',
        success: function(layero, index){
            body=layer.getChildFrame('body',index);
            iframeWindow = window[layero.find('iframe')[0]['name']];
        },
        yes: function (index, layero){
            var menuIds = $.map(iframeWindow.tree.getCheckedNodes(), function (row) {
                return row["id"];
            }).join();
            var data = {
                "user_id": user_id,
                "role_name": $.common.getFormValue(body,"#role_name").val(),
                "role_id": 0,
                "role_key": $.common.getFormValue(body,"#role_key").val(),
                "remark": $.common.getFormValue(body,"#remark").val(),
                "creater": user_id,
                "menuIds" : menuIds,
            };
            $.operate.jsonPost(crx+'/role/insertRole',JSON.stringify(data),function (result) {
                if(result.code===0){
                    $.modal.msgSuccess(result.msg,function () {
                        layer.close(index);
                        refresh();
                        location.reload();
                    })
                }else {
                    $.modal.msgError(result.msg,function () {})
                }
            });
        },
        end:function () {
            $('#insertRole')[0].reset();
        }
    };
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
                            refresh();
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
    let body;
    let iframeWindow;
    var user_id=$.cache.get("user").user.user_id;
    var options={
        title:"编辑角色",
        area: ['50%', '95%'],
        btn:['确认','取消'],
        content:'update?role_id='+data.role_id,
        success: function(layero, index){
            body=layer.getChildFrame('body',index);
            iframeWindow = window[layero.find('iframe')[0]['name']];
            valuation(iframeWindow);
        },
        yes: function (index, layero){
            var menuIds = $.map(iframeWindow.tree.getCheckedNodes(), function (row) {
                return row["id"];
            }).join();
            var data = {
                "user_id": user_id,
                "role_name": $.common.getFormValue(body,"#role_name").val(),
                "role_id": $.common.getFormValue(body,"#role_id").val(),
                "role_key": $.common.getFormValue(body,"#role_key").val(),
                "remark": $.common.getFormValue(body,"#remark").val(),
                "updater": $.cache.get('user').user.user_id,
                "menuIds" : menuIds,
            };
            $.operate.jsonPost(crx+'/role/updateRole',JSON.stringify(data),function (result) {
                if(result.code===0){
                    $.modal.msgSuccess(result.msg,function () {
                        layer.close(index);
                        refresh();
                        location.reload();
                    })
                }else {
                    $.modal.msgError(result.msg,function () {})
                }
            });
        },
        end:function () {
            $('#updateRole')[0].reset();
        }
    };
    //赋值
    function valuation(iframeWindow){
        $.common.setFormValuate(body,"#role_name",data.role_name);
        $.common.setFormValuate(body,"#role_id",data.role_id);
        $.common.setFormValuate(body,"#role_key",data.role_key);
        $.common.setFormValuate(body,"#remark",data.remark);
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
                        refresh();
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
            $.modal.msgSuccess(result.msg,function () {
                refresh();
                form.render();//渲染开关
            })
        }else {
            $.modal.msgError(result.msg,function () {
                location.reload();
            })
        }
    });

}

//刷新角色缓存
function refresh() {
    $.operate.get(crx+'/role/getAllRole',function (result) {
        if (result.code===0){
            $.cache.set("roles",result.data, -1);
        }
    })
}
