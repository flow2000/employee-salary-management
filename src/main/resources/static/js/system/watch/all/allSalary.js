//搜索部门薪资
function searchAllSalary(table) {
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

//导出部门薪资
function  exportAllSalaryFile(excel){
    $.operate.get(crx+'/salary/getAllSalary',exportExcel);
    function exportExcel(result){
        result.data.unshift({
            login_name: '用户账号',
            dept_name: '部门名称',
            create_time: '创建时间',
            base_salary: '基本薪资',
            week_time: '周末加班费',
            festival_time: '节日加班费',
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
            dept_name: 'dept_name',
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
            bonus: function(value, line, data) {
                if(line.bonus !== '奖金'&&value!==''&&$.common.isExist(value)){
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
        }, '公司薪资数据.xlsx', 'xlsx');

    }
}

//部门薪资详细信息
function moreSalary(form,data) {
    var body;
    var iframeWindow;
    var options={
        title:"薪资详细信息",
        area: ['30%', '95%'],
        btn:['了解'],
        content:'more',
        success: function(layero, index){
            body=layer.getChildFrame('body',index);
            iframeWindow = window[layero.find('iframe')[0]['name']];
            valuation(iframeWindow);
        },
        end:function () {
            $('#moreSalary')[0].reset();
        }
    };
    //赋值
    function valuation(iframeWindow){
        $.common.setFormValuate(body,"#login_name",data.login_name);
        $.common.setFormValuate(body,"#base_salary",data.base_salary);
        $.common.setFormValuate(body,"#week_time",data.week_time*data.overtime*2);
        $.common.setFormValuate(body,"#festival_time",data.festival_time*data.overtime*3);
        $.common.setFormValuate(body,"#percentage",data.percentage);
        $.common.setFormValuate(body,"#bonus",data.bonus);
        $.common.setFormValuate(body,"#absence",data.absence_count*data.absence);
        $.common.setFormValuate(body,"#late",data.late_count*data.late);
        $.common.setFormValuate(body,"#leave",data.leave_count*data.leave);
        iframeWindow.layui.form.render();
    }
    $.modal.open(options);
}