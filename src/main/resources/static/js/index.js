/**
 * date: 2021/08/7
 * description: 主页
 * require:
 * author: 庞海
 * version: 1.0
 *
 */

if(localStorage.user===undefined){
    window.top.location="login";
}

$.operate.post(crx+'/init',{role_id:1},callback);
function callback(result) {
    if(result.code===0){
        $.cache.set("roles",result.data.roles, -1); //将返回的数据存到localStorage并设置过期时间为永久
        $.cache.set("menus",result.data.menus, -1);
        $.cache.set("depts",result.data.depts, -1);
    }
}

console.log($.cache.get("menus"));

user = $.cache.get('user');

//添加右上角用户名
if(user!==null){
    document.getElementById("username").innerText = "欢迎 "+user.real_name;
}

layui.use(['jquery', 'layer', 'miniAdmin'], function () {
	
    var $ = layui.jquery,
        layer = layui.layer,
        miniAdmin = layui.miniAdmin;
    var options = {
        iniUrl: crx+"/menu/getMenu?role_id="+user.user.role_id,    // 初始化接口
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
