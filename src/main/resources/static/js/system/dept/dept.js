/**
 * date: 2021/08/19
 * description: 部门管理js
 * require:
 * author: 庞海
 * version: 1.0
 *
 */

//搜索部门
function searchDept(table) {
    if($('#dept_name').val()===''&&$('#leader').val()===''&&$('#dept_status').val()===''){
        table.reload('layui-table',{
            url:crx + '/dept/getPageDept',
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
            url:crx + '/dept/searchDept',
            where: {
                "searchKey": "dept_name;leader;status",
                "searchValue": $('#dept_name').val()+";"+ $('#leader').val()+";"+ $('#dept_status').val(),
            },
            headers: {
                token: token,
                login_name: login_name,
            },
        });
    }
}

//添加部门
function insertDept(form,table) {
    var body;
    var iframeWindow;
    var options={
        title:"添加部门",
        area: ['30%', '60%'],
        btn:['确认','取消'],
        content:'insert',
        success: function(layero, index){
            body=layer.getChildFrame('body',index);
            iframeWindow = window[layero.find('iframe')[0]['name']];
        },
        yes: function (index, layero){
            var dept_name = $.common.getFormValue(body,"#dept_name").val();
            var leader = $.common.getFormValue(body,"#leader").val();
            var phone = $.common.getFormValue(body,"#phone").val();
            var email = $.common.getFormValue(body,"#email").val();

            if(dept_name===''){
                $.modal.msgWarning('部门名不为空',function () {});
                return;
            }
            if(leader===''){
                $.modal.msgWarning('负责人不为空',function () {});
                return;
            }
            if(phone===''){
                $.modal.msgWarning('联系电话不为空',function () {});
                return;
            }

            var data = {
                "dept_id": 0,
                "dept_name": dept_name,
                "leader": leader,
                "phone": phone,
                "email": email,
                "status": '0',
                "creater": $.cache.get('user').user.user_id,
            };
            $.operate.jsonPost(crx+'/dept/insertDept',JSON.stringify(data),function (result) {
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
            $('#insertDept')[0].reset();
        }
    };
    $.modal.open(options);
}

//批量删除部门
function deleteCombineDept(table,checkStatus) {
    var data = checkStatus.data;
    if (data.length>1){
        var options={
            content:'确定要删除吗？',
            callback:function (index) {
                var str_dept_id="";
                $.each(data,function (i,v) {
                    str_dept_id+=v.dept_id+";";
                });
                var sendData = {
                    "dept_id": str_dept_id,
                    "updater": $.cache.get('user').user.user_id,
                };
                $.operate.jsonPost(crx+'/dept/deleteDept',JSON.stringify(sendData),function (result) {
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

//合并部门
function mergeDept(table,checkStatus) {
    var data = checkStatus.data;
    var body;
    var iframeWindow;
    if (data.length>1){
        var options={
            title:"合并部门",
            area: ['30%', '60%'],
            btn:['确认','取消'],
            content:'merge',
            success: function(layero, index){
                body=layer.getChildFrame('body',index);
                iframeWindow = window[layero.find('iframe')[0]['name']];
            },
            yes:function (index) {
                var str_dept_id="";
                $.each(data,function (i,v) {
                    str_dept_id+=v.dept_id+";";
                });
                var dept_name = $.common.getFormValue(body,"#dept_name").val();
                var leader = $.common.getFormValue(body,"#leader").val();
                var phone = $.common.getFormValue(body,"#phone").val();
                var email = $.common.getFormValue(body,"#email").val();
                var sendData = {
                    "dept_id": str_dept_id,
                    "dept_name": dept_name,
                    "leader": leader,
                    "phone": phone,
                    "email": email,
                    "status": '0',
                    "creater": $.cache.get('user').user.user_id,
                };
                $.operate.jsonPost(crx+'/dept/mergeDept',JSON.stringify(sendData),function (result) {
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
                $('#mergeDept')[0].reset();
            }
        };
        $.modal.open(options);
    }else{
        $.modal.msgWarning('至少选择两条数据',function () {});
    }
}

//导出部门数据
function exportDeptFile(excel){
    $.operate.get(crx+'/dept/getAllDept',exportExcel);
    function exportExcel(result){
        result.data.unshift({
            dept_name: '部门名称',
            leader: '负责人',
            phone: '联系电话',
            email: '部门邮箱',
            status: '部门状态',
            create_time: '创建时间',
        });
        var data = excel.filterExportData(result.data, {
            dept_name: 'dept_name',
            leader: 'leader',
            phone: 'phone',
            email: 'email',
            status: function(value, line, data) {
                if(line.status !== '部门状态'){
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
        });
        excel.exportExcel({
            sheet1: data
        }, '部门数据.xlsx', 'xlsx');

    }
}

//编辑部门
function updateDept(form,data){
    var body;
    var iframeWindow;
    var options={
        title:"编辑部门",
        area: ['30%', '60%'],
        btn:['确认','取消'],
        content:'update',
        success: function(layero, index){
            body=layer.getChildFrame('body',index);
            iframeWindow = window[layero.find('iframe')[0]['name']];
            valuation(iframeWindow);
        },
        yes: function (index, layero){
            var dept_name = $.common.getFormValue(body,"#dept_name").val();
            var leader = $.common.getFormValue(body,"#leader").val();
            var phone = $.common.getFormValue(body,"#phone").val();
            var email = $.common.getFormValue(body,"#email").val();
            var sendData = {
                "dept_id": data.dept_id,
                "dept_name": dept_name,
                "leader": leader,
                "phone": phone,
                "email": email,
                "updater": $.cache.get('user').user.user_id,
            };
            $.operate.jsonPost(crx+'/dept/updateDept',JSON.stringify(sendData),function (result) {
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
            $('#updateDept')[0].reset();
        }
    };
    //赋值
    function valuation(iframeWindow){
        $.common.setFormValuate(body,"#dept_id",data.dept_id);
        $.common.setFormValuate(body,"#dept_name",data.dept_name);
        $.common.setFormValuate(body,"#leader",data.leader);
        $.common.setFormValuate(body,"#phone",data.phone);
        $.common.setFormValuate(body,"#email",data.email);
        iframeWindow.layui.form.render();
    }
    $.modal.open(options);
}

//删除部门
function deleteDept(data,table) {
    var options={
        content:'确定要删除吗？',
        callback:function (index) {
            var dept_id = data.dept_id;
            var sendData = {
                "dept_id": dept_id+"",
            };
            $.operate.jsonPost(crx+'/dept/deleteDept',JSON.stringify(sendData),function (result) {
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

//修改部门状态
function changeDeptStatus(form,dept_id,status) {
    var sendData = {
        "dept_id": dept_id+"",
        "status": status,
        "updater":$.cache.get('user').user.user_id,
    };
    $.operate.jsonPost(crx+'/dept/changeDeptStatus',JSON.stringify(sendData),function (result) {
        if(result.code===0){
            $.modal.msgSuccess(result.msg,function () {})
        }else {
            $.modal.msgError(result.msg,function () {
                location.reload();
            })
        }
    });
    form.render();//渲染开关
}
