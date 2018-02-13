package com.aml.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

public class RedisPwdEncryption extends JedisConnectionFactory{

    @Override
    public void setPassword(String password) {
    	if(StringUtils.isNotBlank(password)) {
    		password = CodeUtils.Decrypt(password, 6, 9);
    	}
		super.setPassword(password);
    }
}
