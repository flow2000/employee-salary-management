//搜索奖惩录入
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

//导出奖惩录入数据
function  exportSalaryFile(excel){
    $.operate.get(crx+'/salary/getAllSalary',exportExcel);
    function exportExcel(result){
        result.data.unshift({
            login_name: '用户账号',
            create_time: '创建时间',
            base_salary: '基本薪资',
            week_time: '周末加班时长',
            festival_time: '节日加班时长',
            percentage: '提成',
            bonus: '奖金',
            absence_count: '旷工次数',
            late_count: '迟到次数',
            leave_count: '请假次数',
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
                if(line.week_time !== '周末加班时长'&&value!==''&&$.common.isExist(value)){
                    return {
                        v: value+"时"
                    };
                }
                return {
                    v: value
                };
            },
            festival_time: function(value, line, data) {
                if(line.festival_time !== '节日加班时长'&&value!==''&&$.common.isExist(value)){
                    return {
                        v: value+"时"
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
            absence_count: function(value, line, data) {
                if(line.absence_count !== '旷工次数'&&value!==''&&$.common.isExist(value)){
                    return {
                        v: value+"次"
                    };
                }
                return {
                    v: value
                };
            },
            late_count: function(value, line, data) {
                if(line.late_count !== '迟到次数'&&value!==''&&$.common.isExist(value)){
                    return {
                        v: value+"次"
                    };
                }
                return {
                    v: value
                };
            },
            leave_count: function(value, line, data) {
                if(line.leave_count !== '请假次数'&&value!==''&&$.common.isExist(value)){
                    return {
                        v: value+"次"
                    };
                }
                return {
                    v: value
                };
            },
            remark: 'remark',
        });
        excel.exportExcel({
            sheet1: data
        }, '奖惩录入数据.xlsx', 'xlsx');

    }
}

//编辑奖惩录入
function updateSalary(form,data) {
    var body;
    var iframeWindow;
    var options={
        title:"编辑奖惩录入",
        area: ['30%', '95%'],
        btn:['确认','取消'],
        content:'update',
        success: function(layero, index){
            body=layer.getChildFrame('body',index);
            iframeWindow = window[layero.find('iframe')[0]['name']];
            valuation(iframeWindow);
        },
        yes: function (index, layero){
            var week_time = $.common.getFormValue(body,"#week_time");
            var festival_time = $.common.getFormValue(body,"#festival_time");
            var percentage = $.common.getFormValue(body,"#percentage");
            var bonus = $.common.getFormValue(body,"#bonus");
            var absence_count = $.common.getFormValue(body,"#absence_count");
            var late_count = $.common.getFormValue(body,"#late_count");
            var leave_count = $.common.getFormValue(body,"#leave_count");

            var sendData = {
                "salary_id":data.salary_id,
                "user_id":data.user_id,
                "week_time": week_time.val(),
                "festival_time": festival_time.val(),
                "percentage": percentage.val(),
                "bonus":bonus.val(),
                "absence_count": absence_count.val(),
                "late_count": late_count.val(),
                "leave_count": leave_count.val(),
                "updater": $.cache.get('user').user.user_id,
            };
            $.operate.jsonPost(crx+'/salary/updateSalaryInput',JSON.stringify(sendData),function (result) {
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
        $.common.setFormValuate(body,"#week_time",data.week_time);
        $.common.setFormValuate(body,"#festival_time",data.festival_time);
        $.common.setFormValuate(body,"#percentage",data.percentage);
        $.common.setFormValuate(body,"#bonus",data.bonus);
        $.common.setFormValuate(body,"#absence_count",data.absence_count);
        $.common.setFormValuate(body,"#late_count",data.late_count);
        $.common.setFormValuate(body,"#leave_count",data.leave_count);
        iframeWindow.layui.form.render();
    }
    $.modal.open(options);
}