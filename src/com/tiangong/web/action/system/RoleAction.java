package com.tiangong.web.action.system;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.tiangong.service.system.imp.RoleServiceImp;
import com.tiangong.service.system.imp.UserServiceImp;
import com.tiangong.web.action.BaseAction;
import com.tiangong.bean.Userrole;
import com.tiangong.bean.User;
import com.tiangong.bean.Role;
import com.tiangong.bean.Rolefunction;
import com.tiangong.comm.OperateTab;

public class RoleAction extends BaseAction implements ModelDriven<Role>{

    private Role role=new Role();
    private RoleServiceImp roleService;
    private UserServiceImp userService;
    private String roleid;
    private String empid;
    private String funs;
    
    SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String data =format.format(new Date());

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRoleService(RoleServiceImp roleService) {
        this.roleService = roleService;
    }

    public void setUserService(UserServiceImp userService) {
        this.userService = userService;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getEmpid() {
        return empid;
        
        
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getFuns() {
        return funs;
    }

    public void setFuns(String funs) {
        this.funs = funs;
    }

    /**
     * 创建人：赵井桂
     * 创建时间：2011-11-15 下午05:44:59
     * 方法描述：分页显示角色信息
     * 参数名称：@throws IOException
     * 返回值：无
     *
     */
    public void showroletab() throws IOException
    {
        this.getHttpServletResponse().setContentType("text/html;charset=utf-8");
        List<Role> list =roleService.selrole(getCurrentPage(),getPageSize());
        OperateTab.initDatagrid(list, list.size());
    }

    /**
     * 创建人：赵井桂
     * 创建时间：2011-11-15 下午05:56:00
     * 方法描述：保存角色信息
     * 参数名称：@throws Exception
     * 返回值：无
     */
    public void saverole() throws Exception
    {
        if(roleService.save(role))
        {
            this.getHttpServletResponse().getWriter().write("Y");
        }else {
            this.getHttpServletResponse().getWriter().write("N");
        }
        
    }
    
    /**
     * 创建人：赵井桂
     * 创建时间：2011-11-16 上午09:55:34
     * 方法描述：删除角色信息
     * 返回值：无
     *
     */
    public void delrole()
    {
        System.out.println("ID:"+roleid);
        this.getHttpServletResponse().setContentType("text/html;charset=utf-8");
        int count =0;
        String roleids[]=roleid.split(",");
        for(int i=0;i<roleids.length;i++)
        {
            role=roleService.getrole(roleids[i]);
            if(roleService.delrole(role))
            {
                count ++;
            }
        }
        if(count>0)
        {
            try {
                this.getHttpServletResponse().getWriter().write("Y");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
    
    /**
     * 创建人：赵井桂
     * 创建时间：2011-11-16 上午09:56:02
     * 方法描述：将角色信息回显到修改页面
     * 参数名称：@return
     * 返回值：修改页面
     *
     */
    public String showrole()
    {
        role=roleService.getrole(roleid);
        return "roleedit";
    }
    
    /**
     * 创建人：赵井桂
     * 创建时间：2011-11-16 上午10:09:13
     * 方法描述：更新角色信息
     * 参数名称：@throws Exception
     * 返回值：true 更新成功　/ false  更新失败
     * @throws IOException 
     *
     */
    public void updaterole() throws IOException
    {
        try {
         this.getHttpServletResponse().setContentType("text/html;charset=utf-8");
         if(roleService.updaterole(role))
         {
             this.getHttpServletResponse().getWriter().write("Y");
         }else {
             this.getHttpServletResponse().getWriter().write("N");
        }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    
    }
    
    /**
     * 创建人：赵井桂
     * 创建时间：2011-11-17 上午10:34:53
     * 方法描述：查询所有员工id 和姓名　并以checkbox形式显示在对话框中
     * 返回值：无（异步调用）
     */
    public void selemps()
    {
        this.getHttpServletResponse().setContentType("text/html;charset=utf-8");
        List<User> empList =userService.list();
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for(int i=0;i<empList.size();i++)
        {
            //sb.append("<input type='checkbox' id='add' name='opers' value='"+empList.get(i).getUserId()+"'>"+empList.get(i).getName());
            sb.append("{");
            sb.append("\"id\":\""+empList.get(i).getUserId()+"\",");
            sb.append("\"text\":\""+empList.get(i).getName()+"("+empList.get(i).getUserId()+")"+"\"");
            sb.append("},");
        }
        try {
            if(sb.toString().length()>2)
            {
                sb.deleteCharAt(sb.toString().length()-1);
            }
            sb.append("]");
            //System.out.println(sb.toString());
            this.getHttpServletResponse().getWriter().write(sb.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
     
    /**
     * 创建人：赵井桂
     * 创建时间：2011-11-17 上午10:34:53
     * 方法描述：查询所有员工id 和姓名　并以checkbox形式显示在对话框中
     * 返回值：无（异步调用）
     */
    public void selRoles()
    {
        this.getHttpServletResponse().setContentType("text/html;charset=utf-8");
        List<Role> roleList =roleService.selroleinfo();
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for(int i=0;i<roleList.size();i++)
        {
            //sb.append("<input type='checkbox' id='add' name='opers' value='"+empList.get(i).getUserId()+"'>"+empList.get(i).getName());
            sb.append("{");
            sb.append("\"id\":\""+roleList.get(i).getId()+"\",");
            sb.append("\"text\":\""+roleList.get(i).getName()+"\"");
            sb.append("},");
        }
        try {
            if(sb.toString().length()>2)
            {
                sb.deleteCharAt(sb.toString().length()-1);
            }
            sb.append("]");
            //System.out.println(sb.toString());
            this.getHttpServletResponse().getWriter().write(sb.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void getRoleId() throws IOException
    {
    	String empid=this.getHttpServletRequest().getParameter("id");
    	List list =roleService.selroleidbyempid(empid);
    	if(list.size()>0 && list.get(0)!=null)
    	{
    		this.getHttpServletResponse().getWriter().print(list.get(0));
    	}else{
    		this.getHttpServletResponse().getWriter().print("N");
    	}
         
    }
    /**
     * 
     * 创建人：赵井桂
     * 创建时间：2011-11-16 上午11:27:42
     * 方法描述：保存员工角色信息
     * 返回值：true 保存成功　/ false 保存失败
     * @throws IOException 
     */
    public void saveemp_role() throws IOException
    {
    	this.getHttpServletResponse().setContentType("html/text;charset=utf-8");
        int count =0;
        String msg="";
        Userrole empRole=new Userrole();
        empRole.setRoleId(roleid);
        String empids[]=empid.split(",");
        for(int i=0;i<empids.length;i++)
        {
        	if(roleService.selemprolebyempid(empids[i])==null || roleService.selemprolebyempid(empids[i]).isEmpty())
        	{
        		empRole.setUserId(empids[i]);
        		roleService.saveemp_role(empRole);
        	}else{
        		count++;
        		msg+=empids[i]+",";
        	}
        }
        if(count==0)
        {           
            this.getHttpServletResponse().getWriter().write("Y");
        }else{
        	this.getHttpServletResponse().getWriter().write(msg+"已分配角色!");
        }
    }
    
    /**
     * 创建人：赵井桂
     * 创建时间：2011-11-17 上午10:36:36
     * 方法描述：根据角色id查询员工id 用于判断该角色是否已授权用户
     * 返回值：无(异步调用)
     */
    public void selempids()
    {
       this.getHttpServletResponse().setContentType("text/html;charset=utf-8"); 
       List list =roleService.selempidbyroleid(roleid);
       if(list.isEmpty())
       {
           try {
            this.getHttpServletResponse().getWriter().write("Y");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       }else {
        
           try {
              
               String operid=list.toString().substring(1,list.toString().length()-1).replaceAll(" ", "");
               this.getHttpServletResponse().getWriter().write(operid);
               
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }
        
    }
    
    /**
     * 创建人：赵井桂
     * 创建时间：2011-11-17 上午10:38:07
     * 方法描述：用户角色授权信息修改
     * 返回值：无(异步调用)
     * @throws IOException 
     */
    public void saveorupdateeemprole() throws IOException
    {
    	this.getHttpServletResponse().setContentType("text/html;charset=utf-8");
        int count =0;
        String msg ="";
        String [] empids=empid.split(",");
        for(int i=0;i<empids.length;i++)
        {
            if(roleService.selCountByRoleIdUserId(roleid, empids[i])!=null)
            {
                count ++;
                msg+=empids[i]+",";
            }
        }
        if(count>0)
        {
        	 this.getHttpServletResponse().getWriter().write(msg+"已分配角色");
        }else{
        	 if(roleService.delerbyroleid(roleid))
             {
                 Userrole re=new Userrole();
                 re.setRoleId(roleid);
                 for(int i=0;i<empids.length;i++)
                 {
                     re.setUserId(empids[i]);
                     roleService.saveemp_role(re);
                 }
             }
        	 this.getHttpServletResponse().getWriter().write("Y");
        }
        
    }
    
    public void saveUserRole() throws IOException
    {
    	List<Userrole> list =roleService.selemprolebyempid(empid);
    	if(list.size()>0)
    	{
    		if(roleService.delerbyroleid(list.get(0).getRoleId()))
            {
                Userrole re=new Userrole();
                re.setRoleId(roleid);
                re.setUserId(empid);
                roleService.saveemp_role(re);
            }
    	}else{
    		  Userrole re=new Userrole();
              re.setRoleId(roleid);
              re.setUserId(empid);
              roleService.saveemp_role(re);
    	}
    	 this.getHttpServletResponse().getWriter().write("Y");
    }
    /**
     * 创建人：赵井桂
     * 创建时间：2011-11-17 上午11:01:33
     * 方法描述：判断该角色是否赋予权限
     * 返回值：　如果该角色已赋予权限返回所有权限进行修改，如果没有则返回“Ｙ”进行添加（异步调用）；
     * @throws IOException 
     */
    public void ispurview() throws IOException
    {
        List funlist =roleService.selfunbyroleid(roleid);
        
        if(funlist.isEmpty())
        {
            this.getHttpServletResponse().getWriter().write("Y");
        }else {
            String funs=funlist.get(0).toString();
            this.getHttpServletResponse().getWriter().write(funs);
        }
    }
    
    /**
     * 创建人：赵井桂
     * 创建时间：2011-11-17 上午11:45:56
     * 方法描述：保存角色权限信息
     * 返回值：异步调用，返回"Y"表示保存成功
     */
    public void saverolefun()
    {
        System.out.println(roleid+funs);
        Rolefunction rf=new Rolefunction();
        rf.setRoleId(roleid);
        rf.setFunctionIds(funs);
        if(roleService.saverole_fun(rf))
        {
            try {
                this.getHttpServletResponse().getWriter().write("Y");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 创建人：赵井桂
     * 创建时间：2011-11-17 下午01:11:56
     * 方法描述：更新角色权限信息
     * 返回值：true 更新成功　/ false 更新失败
     * @throws IOException 
     */
    public void updaterolefun() throws IOException
    {
        String id =roleService.getrole_fun_id(roleid);
        Rolefunction rf =roleService.getrf(id);
        rf.setFunctionIds(funs);
        if(roleService.updaterole_fun(rf))
        {
            this.getHttpServletResponse().getWriter().write("Y");
        }
    }
    
    public void checkrolename() throws IOException
    {
       String rolename=this.getHttpServletRequest().getParameter("rolename");
       List list =roleService.selectroIdByroname(rolename);
       if(list.isEmpty())
       {
           this.getHttpServletResponse().getWriter().write("Y");
           
       }else{
           this.getHttpServletResponse().getWriter().write("N");
       }
    }
    /**
     * 创建人：赵井桂
     * 创建时间：2012-8-14 下午02:25:46
     * 方法描述：判断是否为超级管理员
     * 参数名称：@throws IOException
     */
    public void isadmin() throws IOException
    {
        List list =roleService.selrolelevel(true);
        if(list.isEmpty())
        {
            this.getHttpServletResponse().getWriter().write("Y");
        }else{
            this.getHttpServletResponse().getWriter().write("N");
        }
    }
    /**
     * 创建人：赵井桂
     * 创建时间：2011-11-15 下午17:43:42
     * 方法描述：实现modelDriven接口重写getModel()方法
     * 返回值：角色对象
     */
    public Role getModel() {
        // TODO Auto-generated method stub
        return role;
    }
    
}
