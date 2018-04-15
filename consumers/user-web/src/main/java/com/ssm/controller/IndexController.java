package com.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.entity.table.User;
import com.ssm.service.IUserService;
import com.ssm.util.ConfigurationUtil;
import com.ssm.util.Constant;
import com.ssm.util.Context;
import com.ssm.util.CookieUtil;
import com.ssm.util.KeyGenerator;
import com.ssm.util.LoginInfo;
import com.ssm.util.RemoteCacheUtil;
import com.ssm.util.TokenProcessor;

@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
    private IUserService userService; 
	
	@RequestMapping("index")
	public ModelAndView indxe(HttpServletRequest request, HttpServletResponse response){
		String ticket = CookieUtil.getCookieValue(request, Constant.TICKET);
		if(ticket == null){
			ticket = KeyGenerator.getUUID();
			// 初次访问，发一个饼干
			CookieUtil.setCookie(response, Constant.TICKET, ticket, -1);
		}
		// 单点登陆域设置  所有系统需在同一个域名,如:.baidu.com
		String domain = ConfigurationUtil.getStringValue("sso.domain");
		if(domain != null && !"".equals(domain)){
			CookieUtil.setCookie(response, Constant.TICKET, ticket, -1, "/", domain);
		}
		return new ModelAndView("index");
	}
	
	private String getTekon(HttpServletRequest request){
		String token = TokenProcessor.getInstance().generateToken();// 防止重复提交
		request.getSession().setAttribute("token", TokenProcessor.getInstance().generateToken());//添加一个令牌属性
		return token;
	}
	
	@RequestMapping("registerPage")
	public ModelAndView registerPage(HttpServletRequest request){
		String token = getTekon(request);
		return new ModelAndView("register").addObject("token", token);
	}
	
	@RequestMapping("register")
	public ModelAndView register(User userReq, ModelMap map, HttpServletRequest request){
		String serverToken = (String) request.getSession().getAttribute("token");  
        String clientToken = request.getParameter("token");  
        if(serverToken!=null && serverToken.equals(clientToken)){   //比对令牌值是否相等  
            request.getSession().removeAttribute("token");  
            return new ModelAndView("main").addObject("msg", "注册成功");
        }  
        String token = getTekon(request);
		return new ModelAndView("redirect: registerPage").addObject("token", token).addObject("msg", "注册失败");
	}
	
	@RequestMapping("loginPage")
	public ModelAndView loginPage(HttpServletRequest request){
		String token = getTekon(request);
		return new ModelAndView("login").addObject("token", token);
	}
	
	@RequestMapping("login")
	public ModelAndView login(User userReq, ModelMap map, HttpServletRequest request){
		String token = getTekon(request);
		try{
			String userName = userReq.getName();
			String password = userReq.getPassword();
			
			userReq.setPassword("");
			User user = userService.selectUser(userReq);
			userReq.setPassword(password);
			map.put("obj", userReq);

			String ticket = CookieUtil.getCookieValue(request,Constant.TICKET);
			LoginInfo li = (LoginInfo)RemoteCacheUtil.getObject(ticket);
			if(StringUtils.isBlank(userName)){
				return new ModelAndView("login").addObject("token", token).addObject("msg", "用户名不能为空");
			}
			if(li != null && "get".equalsIgnoreCase(request.getMethod())){
				if(li.isLogin()){
					return new ModelAndView("main").addObject("msg", "success");
				}
				return new ModelAndView("login").addObject("token", token);
			}
			/*String validationCode = userReq.getValidateCode();
			String orgValidationCode = li.getValidationCode();
			if(!orgValidationCode.equalsIgnoreCase(validationCode)){
				return new ModelAndView("login").addObject("msg", "验证码失效");
			}*/
			if(user == null){
				return new ModelAndView("login").addObject("token", token).addObject("msg", "用户名不正确");
			}else{
				if("N".equalsIgnoreCase(user.getEnabledFlag())){
					return new ModelAndView("login").addObject("token", token).addObject("msg", "失效用户");
				}
				if(user.getPassword().equals(DigestUtils.md5Hex(password))) {
					li.setLogin(true);
					li.setUserId(user.getId());
					li.setUserName(userName);
					li.setPassword(DigestUtils.md5Hex(password));
					li.setTicket(ticket);
					if("admin".equals(userName)){
						li.setSuperManager(true);
					}else{
						li.setSuperManager(false);
					}
					Context.setRequest(request);
					Context.setLoginInfo(li);
					return new ModelAndView("main").addObject("msg", "登录成功");
				}else{
					return new ModelAndView("login").addObject("token", token).addObject("msg", "密码不对");
				}
			}
		}catch(Exception e){
			logger.error("",e);
			return new ModelAndView("login").addObject("token", token).addObject("msg", "登录异常");
		}
	}
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest request){
		Context.setRequest(request);
		Context.logout(); // 剔除远程缓存
		return new ModelAndView("redirect:index.do").addObject("msg", "登出成功");
	}
	
	@RequestMapping("changPassword")
	@ResponseBody
	public String changPassword(String userName, String oldPassword, String newPassword, HttpServletRequest request, HttpServletResponse response){
		Context.setRequest(request);
		LoginInfo loginInfo = Context.getLoginInfo();
		oldPassword = DigestUtils.md5Hex(oldPassword);
		newPassword = DigestUtils.md5Hex(newPassword);
		if(loginInfo != null && !loginInfo.isLogin() && (StringUtils.isBlank(userName) || !userName.equals(loginInfo.getUserName()))){
			return "用户名不对。";
		}
		if(oldPassword != null && oldPassword.equals(loginInfo.getPassword())){
			User currentUser = new User();
			currentUser.setId(loginInfo.getUserId());
			currentUser.setPassword(newPassword);
			userService.updateByPrimaryKeySelective(currentUser);
		}else{
			return "原密码不对。";
		}
		loginInfo.setPassword(newPassword);
		Context.setRequest(request);
		Context.setLoginInfo(loginInfo);
		return "密码修改成功。";
	}
	
}
