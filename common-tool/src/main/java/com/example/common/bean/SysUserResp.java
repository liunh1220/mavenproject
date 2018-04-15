package com.example.common.bean;

import com.example.common.base.BasePageReq;

public class SysUserResp extends BasePageReq implements java.io.Serializable
{
    /**
     * 
     * 注释内容
     */
    private static final long serialVersionUID = -5221306893774322151L;
    
    /**
     *用户ID
     */
    private String userId;
    
    /**
     *用户登录账号
     */
    private String userName;
    
    /**
     *用户登录账号
     */
    private String realName;
    
    /**
     *最后登录时间
     */
    private String dateLogin;
    
    /**
     *创建时间
     */
    private String dateCreate;
    
    /**
     *创建人
     */
    private String creatorId;
    
    /**
     *用户状态
     */
    private String userStatus;
    
    /**
     *用户组
     */
    private String userGrop;
    
    /**
     *创建人名称
     */
    private String name;
    
    /**
     *性别 1:男 2：女
     */
    private String gender;
    
    /**
     *手机号码
     */
    private String phone;
    
    /**
     *最后登录ip
     */
    private String lastLoginIp;
    
    /**
     *角色ID
     */
    private String roleId;
    
    /**
     *角色名称
     */
    private String roleName;
    
    /**
     *角色描述
     */
    private String roleDesc;
    
    /**
     *0：启用，1：停用
     */
    private String status;
    
    /**
     *创建人id
     */
    private String creator;
    
    /**
     *修改时间
     */
    private String dateUpdate;
    
    /**
     *令牌
     */
    private String token;
    
    /**
     *修改人id
     */
    private String updator;
    
    public String getGender()
    {
        return "1".equals(gender) ? "男" : "2".equals(gender) ? "女" : "";
    }
    
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public String getUserStatus()
    {
        return "0".equals(userStatus) ? "启用" : "1".equals(userStatus) ? "停用" : "";
    }
    
    public void setUserStatus(String userStatus)
    {
        this.userStatus = userStatus;
    }
    
    public String getUserGrop()
    {
        return userGrop;
    }
    
    public void setUserGrop(String userGrop)
    {
        this.userGrop = userGrop;
    }
    
    /**
     *用户ID
     */
    public String getUserId()
    {
        return userId;
    }
    
    /**
     *用户ID
     */
    public void setUserId(String userId)
    {
        this.userId = userId == null ? null : userId.trim();
    }
    
    /**
     *用户登录账号
     */
    public String getUserName()
    {
        return userName;
    }
    
    /**
     *用户登录账号
     */
    public void setUserName(String userName)
    {
        this.userName = userName == null ? null : userName.trim();
    }
    
    /**
     *最后登录时间
     */
    public String getDateLogin()
    {
        return dateLogin;
    }
    
    /**
     *最后登录时间
     */
    public void setDateLogin(String dateLogin)
    {
        this.dateLogin = dateLogin;
    }
    
    /**
     *创建人
     */
    public String getCreatorId()
    {
        return creatorId;
    }
    
    /**
     *创建人
     */
    public void setCreatorId(String creatorId)
    {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }
    
    /**
     *创建人名称
     */
    public String getName()
    {
        return name;
    }
    
    /**
     *创建人名称
     */
    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }
    
    /**
     *最后登录ip
     */
    public String getLastLoginIp()
    {
        return lastLoginIp;
    }
    
    /**
     *最后登录ip
     */
    public void setLastLoginIp(String lastLoginIp)
    {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }
    
    /**
     *角色ID
     */
    public String getRoleId()
    {
        return roleId;
    }
    
    /**
     *角色ID
     */
    public void setRoleId(String roleId)
    {
        this.roleId = roleId == null ? null : roleId.trim();
    }
    
    /**
     *角色名称
     */
    public String getRoleName()
    {
        return roleName;
    }
    
    /**
     *角色名称
     */
    public void setRoleName(String roleName)
    {
        this.roleName = roleName == null ? null : roleName.trim();
    }
    
    /**
     *角色描述
     */
    public String getRoleDesc()
    {
        return roleDesc;
    }
    
    /**
     *角色描述
     */
    public void setRoleDesc(String roleDesc)
    {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }
    
    /**
     *0：启用，1：停用
     */
    public String getStatus()
    {
        return userStatus;
    }
    
    /**
     *0：启用，1：停用
     */
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    /**
     *创建人id
     */
    public String getCreator()
    {
        return creator;
    }
    
    /**
     *创建人id
     */
    public void setCreator(String creator)
    {
        this.creator = creator == null ? null : creator.trim();
    }
    
    /**
     *创建时间
     */
    public String getDateCreate()
    {
        return dateCreate;
    }
    
    /**
     *创建时间
     */
    public void setDateCreate(String dateCreate)
    {
        this.dateCreate = dateCreate;
    }
    
    /**
     *修改时间
     */
    public String getDateUpdate()
    {
        return dateUpdate;
    }
    
    /**
     *修改时间
     */
    public void setDateUpdate(String dateUpdate)
    {
        this.dateUpdate = dateUpdate;
    }
    
    /**
     *修改人id
     */
    public String getUpdator()
    {
        return updator;
    }
    
    /**
     *修改人id
     */
    public void setUpdator(String updator)
    {
        this.updator = updator == null ? null : updator.trim();
    }
    
    public String getRealName()
    {
        return realName;
    }
    
    public void setRealName(String realName)
    {
        this.realName = realName;
    }
    
    public String getToken()
    {
        return token;
    }
    
    public void setToken(String token)
    {
        this.token = token;
    }
    
}

