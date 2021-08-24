//搜索薪资
function searchSalary(table) {
    if($('#login_name').val()===''&&$('#create_time').val()===''){
        table.reload('layui-table',{
            url:crx + '/salary/getPageSalary',
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
            url:crx + '/salary/searchSalary',
            where: {
                "login_name": $('#login_name').val(),
                "create_time":$('#create_time').val(),
            },
            headers: {
                token: token,
                login_name: login_name,
            },
        });
    }
    layui.use(['laydate'],function () {
        var laydate = layui.laydate;
        //渲染时间选择器
        laydate.render({
            elem: '#create_time'
            ,type: 'month'
            ,trigger: 'click'
        });
    })
}

//导出薪资
function  exportSalaryFile(excel){
    $.operate.get(crx+'/salary/getAllSalary',exportExcel);
    function exportExcel(result){
        result.data.unshift({
            login_name: '用户账号',
            create_time: '创建时间',
            base_salary: '基本薪资',
            week: '周末加班费',
            festival: '节日加班费',
            percentage: '提成',
            bonus: '奖金',
            absence: '旷工扣薪',
            late: '迟到扣薪',
            leave: '请假扣薪',
            total_salary: '总计',
            remark: '备注',
        });
        var data = excel.filterExportData(result.data, {
            login_name: 'login_name',
            create_time: function(value, line, data) {
                if(line.create_time !== '创建时间'&&value!==''&&$.common.isExist(value)){
                    return {
                        v: value.substr(0, 7)
                    };
                }
                return {
                    v: value===null?'':value
                };
            },
            base_salary: function(value, line, data) {
                if(line.base_salary !== '基本薪资'&&value!==''&&$.common.isExist(value)){
                    return {
                        v: value+"元"
                    };
                }
                return {
                    v: value
                };
            },

            week_time: function(value, line, data) {
                if(line.week_time !== '周末加班费'&&value!==''&&$.common.isExist(value)){
                    return {
                        v: value*line.overtime*2+"元"
                    };
                }
                return {
                    v: value
                };
            },
            festival_time: function(value, line, data) {
                if(line.festival_time !== '节日加班费'&&value!==''&&$.common.isExist(value)){
                    return {
                        v: value*line.overtime*3+"元"
                    };
                }
                return {
                    v: value
                };
            },
            percentage: function(value, line, data) {
                if(line.percentage !== '提成'&&value!==''&&$.common.isExist(value)){
                    return {
                        v: value+"元"
                    };
                }
                return {
                    v: value
                };
            },
            absence: function(value, line, data) {
                if(line.absence !== '旷工扣薪'&&value!==''&&$.common.isExist(value)){
                    return {
                        v: value*line.absence_count+"元"
                    };
                }
                return {
                    v: value
                };
            },
            late: function(value, line, data) {
                if(line.late !== '迟到扣薪'&&value!==''&&$.common.isExist(value)){
                    return {
                        v: value*line.late_count+"元"
                    };
                }
                return {
                    v: value
                };
            },
            leave: function(value, line, data) {
                if(line.leave !== '请假扣薪'&&value!==''&&$.common.isExist(value)){
                    return {
                        v: value*line.leave_count+"元"
                    };
                }
                return {
                    v: value
                };
            },
            total_salary: 'total_salary',
            remark: 'remark',
        });
        excel.exportExcel({
            sheet1: data
        }, '奖惩录入数据.xlsx', 'xlsx');

    }
}

//审核薪资
function updateSalary(form,data) {
    var body;
    var iframeWindow;
    var options={
        title:"审核薪资",
        area: ['30%', '95%'],
        btn:['确认','取消'],
        content:'update',
        success: function(layero, index){
            body=layer.getChildFrame('body',index);
            iframeWindow = window[layero.find('iframe')[0]['name']];
            valuation(iframeWindow);
        },
        yes: function (index, layero){
            var remark = $.common.getFormValue(body,"#remark");
            var check_result = $.common.getFormValue(body,"#check_result");
            var fail_cause = $.common.getFormValue(body,"#fail_cause");
            var sendData = {
                "salary_id":data.salary_id,
                "user_id":data.user_id,
                "checked":check_result.val()===''?"0":"1",
                "check_result":check_result.val(),
                "fail_cause":fail_cause.val(),
                "remark": remark.val(),
                "updater": $.cache.get('user').user.user_id,
            };
            $.operate.jsonPost(crx+'/salary/updateSalaryChecked',JSON.stringify(sendData),function (result) {
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
            $('#updateSalary')[0].reset();
        }
    };
    //赋值
    function valuation(iframeWindow){
        $.common.setFormValuate(body,"#login_name",data.login_name);
        $.common.setFormValuate(body,"#base_salary",data.base_salary);
        $.common.setFormValuate(body,"#week_time",data.week_time*data.overtime);
        $.common.setFormValuate(body,"#festival_time",data.festival_time*data.overtime);
        $.common.setFormValuate(body,"#percentage",data.percentage);
        $.common.setFormValuate(body,"#bonus",data.bonus);
        $.common.setFormValuate(body,"#absence_count",data.absence_count*data.absence);
        $.common.setFormValuate(body,"#late_count",data.late_count*data.late);
        $.common.setFormValuate(body,"#leave_count",data.leave_count*data.leave);
        $.common.setFormValuate(body,"#total_salary",data.total_salary);
        $.common.setFormValuate(body,"#fail_cause",data.fail_cause);
        $.common.setFormValuate(body,"#remark",data.remark);
        iframeWindow.layui.form.render();
    }
    $.modal.open(options);
}