/**
 * date: 2021/08/7
 * description: 主页
 * require:
 * author: 庞海
 * version: 1.0
 *
 */
console.log($.common.isExist(localStorage.dasyiuy));
user = $.cache.get("user");
console.log(user);
if($.common.isExist(user)){
    $.modal.alertError("会话已过期，请重新登录");
}



//添加右上角用户名
document.getElementById("username").innerText = "欢迎 "+$.cache.get('user').user.real_name;

layui.use(['jquery', 'layer', 'miniAdmin'], function () {
	
    var $ = layui.jquery,
        layer = layui.layer,
        miniAdmin = layui.miniAdmin;
    var options = {
        iniUrl: "/salary/js/init.json",    // 初始化接口
        clearUrl: "", // 缓存清理接口
        urlHashLocation: true,      // 是否打开hash定位
        bgColorDefault: false,      // 主题默认配置
        multiModule: true,          // 是否开启多模块
        menuChildOpen: false,       // 是否默认展开菜单
        loadingTime: 0,             // 初始化加载时间
        pageAnim: true,             // iframe窗口动画
        maxTabNum: 20,              // 最大的tab打开数量
    };
    miniAdmin.render(options);
    $('.login-out').on("click", function () {
        layer.msg('退出登录成功', {
        	  icon: 1,
        	  time: 1000 
        	}, function () {
            window.location = 'login';
        });
    });
});
