package com.salary.service.imp;

import com.salary.dao.MenuDao;
import com.salary.entity.Menu;
import com.salary.entity.Ztree;
import com.salary.service.MenuService;
import com.salary.util.AjaxResult;
import com.salary.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MenuServiceImp implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getAllMenu() {
        return menuDao.getAllMenu();
    }

    @Override
    public AjaxResult getRoleMenu(int role_id) {
        return AjaxResult.returnMessage(menuDao.getRoleMenu(role_id));
    }

    /**
     * 获取菜单信息
     *  @param role_id 角色id
     * @return 菜单信息
     */
    @Override
    public Map<String, Object> getMenu(int role_id) {
        Map<String,Object> resultMap = new HashMap<>();
        List<Menu> menuList = loadMenuTree(menuDao.getRoleMenu(role_id));

        Menu menu = null;

        List<Object> list = new ArrayList<>();
        menu = new Menu();
        menu.setTitle(" ");
        menu.setIcon("fa fa-address-book");
        menu.setHref("#");
        menu.setTarget("_self");
        menu.setChild(menuList);
        list.add(menu);
        resultMap.put("menuInfo", list);

        menu = new Menu();
        menu.setTitle("首页");
        menu.setHref("system/welcome/welcome");
        menu.setTarget("_self");
        resultMap.put("homeInfo", menu);

        menu = new Menu();
        menu.setTitle("工资管理系统");
        menu.setImage("img/logo.png");
        menu.setHref("index");
        resultMap.put("logoInfo", menu);

        return resultMap;
    }

    private List<Menu> loadMenuTree(List<Menu> allMenus) {
        //存返回数据
        List<Menu> totaltype = new ArrayList<>();
        //使用map来装前面查到的所有数据
        Map<BigInteger, Menu> map = new HashMap<>();

        for (Menu p:allMenus){
            map.put(p.getMenu_id(),p);
        }

        //遍历所有类型，如果是最顶级父类型就直接装, 然后用这个父类型的children集合取装取当前数据

        for(Menu p:allMenus){
            if(p.getParent_id().intValue()==0){
                totaltype.add(p);
            }else{
                Menu parents=map.get(p.getParent_id());
                parents.getChild().add(p);
            }
        }
        return totaltype;
    }

    /**
     * 根据角色ID和用户id查询菜单树
     *
     * @param role_id 角色id
     * @param user_id 用户id
     * @return 菜单树
     */
    @Override
    public List<Ztree> getRoleMenuTree(int role_id,int user_id)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<Menu> menuList = menuDao.getAllUserMenu(user_id);//获取用户对应得到的菜单
        if (role_id!=0)
        {
            List<String> roleMenuList = menuDao.getRoleMenuPerms(role_id);//获取角色对应的菜单权限
            ztrees = initZtree(menuList, roleMenuList, true);
        }
        else
        {
            ztrees = initZtree(menuList, null, true);
        }
        return ztrees;
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Menu> menuList)
    {
        return initZtree(menuList, null, false);
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Menu> menuList, List<String> roleMenuList, boolean permsFlag)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (Menu menu : menuList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getMenu_id());
            ztree.setPId(menu.getParent_id());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getTitle());
            if (roleMenuList!=null)
            {
                ztree.setChecked(roleMenuList.contains(menu.getMenu_id() + menu.getPerms()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(Menu menu, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getTitle());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }
}
