/**
 * date: 2021/08/7
 * description: 登录
 * require:
 * author: 庞海
 * version: 1.0
 *
 */


layui.use(['layer'],function () {
    var layer = layui.layer;
});

$(function () {
    // 页面初始化生成验证码
    window.onload = createCode();
    // 验证码切换
    $('#loginCode').click(function () {
        createCode();
    });
    // 登陆事件
    $('#loginBtn').click(function () {
        login();
    });
});

// 生成验证码
function createCode() {
	document.getElementById("loginCode").src=crx+'/getVerify'+"?math="+Math.random();
}
// 校验用户名、密码
function validateCode() {
    var loginUsername = $('#loginUsername').val();
    var loginPassword = $('#loginPassword').val();
    var loginImgCode = $('#loginCard').val();
    if ($.trim(loginUsername) === '' || $.trim(loginUsername).length<=0){
        layer.msg('帐号不能为空', {icon: 2,time: 1000});
        return false;
    }
    if ($.trim(loginPassword) === '' || $.trim(loginPassword).length<=0){
        layer.msg("密码不能为空", {icon: 2,time: 1000});
        return false;
    }
    if (loginImgCode.length<=0){
    	layer.msg("验证码不能为空", {icon: 2,time: 1000});
        return false;
    }
    return true;
}
// 登录流程
function login() {
    if (!validateCode()){
        //阻断提示
    }else {
        var loginUsername = $('#loginUsername').val();
        var loginPassword = $('#loginPassword').val();
        var loginImgCode = $('#loginCard').val();

        $('#loginBtn').val("正在登录...");
        var data={
            "login_name":loginUsername,
            "password":hex_md5(loginPassword),
            "captcha":loginImgCode,
        };
        $.operate.jsonPost(crx+'/login',JSON.stringify(data),callback);
        $('#loginBtn').val("登录");
        resetAll();
    }
}

//登录回调
function callback(result) {
    if(result.code===web_status.FAIL){
        $.modal.msgError(result.msg);
    }else{
        $.cache.set("user",result.data, 60 * 60 * 1000); //将返回的数据存到localStorage并设置过期时间60分钟
        $.modal.msgSuccess(result.msg,a);
        $.modal.closeLoading();
    }
    //消息提示回调
    function a() {
        window.location='index';
    }
}

//重置输入框
function resetAll(){
	$('#registerUsername').val("");
    $('#registerPassword').val("");
    $('#registerWellPassword').val("");
    $('#roleSelect').val("");
}
