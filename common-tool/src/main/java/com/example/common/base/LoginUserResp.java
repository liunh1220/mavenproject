package com.example.common.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class LoginUserResp extends BaseReq implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    /**
     *用户ID
     */
    private String userId;
    
    /**
     *用户登录用户名
     */
    private String userName;
    
    /**
     *用户登录密码
     */
    private String password;
    
    /**
     *0：前台注册
            1：运营后台添加
            2：苹果端注册
            3：安卓端注册
            4：微信端注册
     */
    private String registerSource;
    
    /**
     *1：正常 0：锁定
     */
    private String userStatus;
    
    /**
     *0：不是第一次，1：第一次
     */
    private String firstLogin;
    
    /**
     *最后一次登录时间
     */
    private String dateLastLogin;
    
    /**
     *最后一次登录ip
     */
    private String lastLoginIp;
    
    /**
     *姓名
     */
    private String name;
    
    /**
     *身份证号,3-18位星号替换
     */
    private String idCard;
    
    /**
     *0：男，1：女
     */
    private Integer sex;
    
    /**
     *手机号码
     */
    private String mobile;
    
    /**
     *邮箱
     */
    private String email;
    
    /**
     *用户头像ID
     */
    private String imageId;
    
    /**
     *所在城市ID
     */
    private String cityId;
    
    /**
     *住址
     */
    private String address;
    
    /**
     *1：个人投资人
            2：机构投资人
            3：融资方
     */
    private String roleType;
    
    /**
     *0:准融资人（没有经过安全认证的融资方，不能建项目）
            1:准投资人（没有通过投资资格认证的投资方，不能投资）
            2:普通投资人(只能投资实体店铺的项目)
            3:专业投资人（能投资所有项目）
            4:领投人（能投资实体店铺/创投版的项目）
            6:融资人
            
     */
    private String userQualification;
    
    /**
     * 当日登录错误次数
     */
    private String failLoginCount;
    
    /**
     * 身份证认证状态
     */
    private String idcardCertStatus;
    
    /**
     * 手机认证状态
     */
    private String mobileCertStatus;
    
    /**
     * 邮箱认证状态
     */
    private String emailCertStatus;
    
    /**
     * 第三方账号  如果为null，则表示未第三方注册
     */
    private String thirdPartyAccount;
    
    /**
     * 银行卡绑定状态
     */
    private String bandcardBind;
    
    private String token;
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getRegisterSource()
    {
        return registerSource;
    }
    
    public void setRegisterSource(String registerSource)
    {
        this.registerSource = registerSource;
    }
    
    public String getUserStatus()
    {
        return userStatus;
    }
    
    public void setUserStatus(String userStatus)
    {
        this.userStatus = userStatus;
    }
    
    public String getFirstLogin()
    {
        return firstLogin;
    }
    
    public void setFirstLogin(String firstLogin)
    {
        this.firstLogin = firstLogin;
    }
    
    public Integer getSex()
    {
        return sex;
    }
    
    public void setSex(Integer sex)
    {
        this.sex = sex;
    }
    
    public String getDateLastLogin()
        throws Exception
    {
        if (dateLastLogin != null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(sdf.parse(dateLastLogin));
        }
        else
        {
            return dateLastLogin;
        }
    }
    
    public void setDateLastLogin(String dateLastLogin)
    {
        this.dateLastLogin = dateLastLogin;
    }
    
    public String getLastLoginIp()
    {
        return lastLoginIp;
    }
    
    public void setLastLoginIp(String lastLoginIp)
    {
        this.lastLoginIp = lastLoginIp;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getIdCard()
    {
        return idCard;
    }
    
    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getImageId()
    {
        return imageId;
    }
    
    public void setImageId(String imageId)
    {
        this.imageId = imageId;
    }
    
    public String getCityId()
    {
        return cityId;
    }
    
    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getRoleType()
    {
        return roleType;
    }
    
    public void setRoleType(String roleType)
    {
        this.roleType = roleType;
    }
    
    public String getUserQualification()
    {
        return userQualification;
    }
    
    public void setUserQualification(String userQualification)
    {
        this.userQualification = userQualification;
    }
    
    public String getFailLoginCount()
    {
        return failLoginCount;
    }
    
    public void setFailLoginCount(String failLoginCount)
    {
        this.failLoginCount = failLoginCount;
    }
    
    public String getIdcardCertStatus()
    {
        return idcardCertStatus;
    }
    
    public void setIdcardCertStatus(String idcardCertStatus)
    {
        this.idcardCertStatus = idcardCertStatus;
    }
    
    public String getMobileCertStatus()
    {
        return mobileCertStatus;
    }
    
    public void setMobileCertStatus(String mobileCertStatus)
    {
        this.mobileCertStatus = mobileCertStatus;
    }
    
    public String getEmailCertStatus()
    {
        return emailCertStatus;
    }
    
    public void setEmailCertStatus(String emailCertStatus)
    {
        this.emailCertStatus = emailCertStatus;
    }
    
    public String getThirdPartyAccount()
    {
        return thirdPartyAccount;
    }
    
    public void setThirdPartyAccount(String thirdPartyAccount)
    {
        this.thirdPartyAccount = thirdPartyAccount;
    }
    
    public String getBandcardBind()
    {
        return bandcardBind;
    }
    
    public void setBandcardBind(String bandcardBind)
    {
        this.bandcardBind = bandcardBind;
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

