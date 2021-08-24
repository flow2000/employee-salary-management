//搜索薪资参数
function searchSalaryConfig(table) {
    if($('#login_name').val()===''&&$('#create_time').val()===''){
        table.reload('layui-table',{
            url:crx + '/salary/getPageSalaryConfig',
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
            url:crx + '/salary/searchSalaryConfig',
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

//导出薪资基本参数数据数据
function exportSalaryConfigFile(excel) {
    $.operate.get(crx+'/salary/getAllSalaryConfig',exportExcel);
    function exportExcel(result){
        result.data.unshift({
            login_name: '用户账号',
            create_time: '创建时间',
            base_salary: '基本薪资',
            leave: '请假扣薪标准',
            late: '迟到扣薪标准',
            absence: '旷工扣薪标准',
            overtime: '加班扣薪标准',
            updater: '更新者',
            update_time: '更新时间',
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
                if(line.base_salary !== '基本薪资'){
                    return {
                        v: value+"元"
                    };
                }
                return {
                    v: value
                };
            },
            leave: function(value, line, data) {
                if(line.leave !== '请假扣薪标准'){
                    return {
                        v: value+"元"
                    };
                }
                return {
                    v: value
                };
            },
            late: function(value, line, data) {
                if(line.late !== '迟到扣薪标准'){
                    return {
                        v: value+"元"
                    };
                }
                return {
                    v: value
                };
            },
            absence: function(value, line, data) {
                if(line.absence !== '旷工扣薪标准'){
                    return {
                        v: value+"元"
                    };
                }
                return {
                    v: value
                };
            },
            overtime: function(value, line, data) {
                if(line.overtime !== '加班扣薪标准'){
                    return {
                        v: value+"元"
                    };
                }
                return {
                    v: value
                };
            },
            updater: 'updater',
            update_time: function(value, line, data) {
                value=value===null?'':value;
                if(line.update_time !== '更新时间'){
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
        }, '薪资基本参数数据.xlsx', 'xlsx');

    }
}

//编辑薪资参数
function updateSalaryConfig(form,data) {
    var body;
    var iframeWindow;
    var options={
        title:"编辑薪资参数",
        area: ['30%', '75%'],
        btn:['确认','取消'],
        content:'update',
        success: function(layero, index){
            body=layer.getChildFrame('body',index);
            iframeWindow = window[layero.find('iframe')[0]['name']];
            valuation(iframeWindow);
        },
        yes: function (index, layero){
            var login_name = $.common.getFormValue(body,"#login_name");
            var base_salary = $.common.getFormValue(body,"#base_salary");
            var leave = $.common.getFormValue(body,"#leave");
            var late = $.common.getFormValue(body,"#late");
            var absence = $.common.getFormValue(body,"#absence");
            var overtime = $.common.getFormValue(body,"#overtime");

            var sendData = {
                "salary_id":data.salary_id,
                "user_id":data.user_id,
                "login_name": login_name.val(),
                "base_salary": base_salary.val(),
                "leave": leave.val(),
                "late": late.val(),
                "absence":absence.val(),
                "overtime": overtime.val(),
                "updater": $.cache.get('user').user.user_id,
            };
            $.operate.jsonPost(crx+'/salary/updateSalaryConfig',JSON.stringify(sendData),function (result) {
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
            $('#updateSalaryConfig')[0].reset();
        }
    };
    //赋值
    function valuation(iframeWindow){
        $.common.setFormValuate(body,"#login_name",data.login_name);
        $.common.setFormValuate(body,"#base_salary",data.base_salary);
        $.common.setFormValuate(body,"#leave",data.leave);
        $.common.setFormValuate(body,"#late",data.late);
        $.common.setFormValuate(body,"#absence",data.absence);
        $.common.setFormValuate(body,"#overtime",data.overtime);
        iframeWindow.layui.form.render();
    }
    $.modal.open(options);

}