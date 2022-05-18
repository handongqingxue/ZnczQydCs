package com.znczQydCs.util.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;

public class MyRealm extends AuthorizingRealm{
	@Autowired
	private YongHuMapper yongHuMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 对账号登录进行验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		YongHu yh=new YongHu(token.getUsername(),String.valueOf(token.getPassword()));
		YongHu resultMsg=yongHuMapper.getYongHu(yh);
		if(token.getUsername().equals(resultMsg.getYhm())
				&&
				String.valueOf(token.getPassword()).equals(resultMsg.getMm())){
			return new SimpleAuthenticationInfo(resultMsg,resultMsg.getMm(),resultMsg.getYhm());
		}else{
			throw new AuthenticationException();  
		}
	}

}
