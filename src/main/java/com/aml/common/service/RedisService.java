package com.aml.common.service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import com.aml.common.core.Constants;
import com.aml.common.util.RedisSerializeUtils;


/**
 * 这里类好少直接用
 * 
 * @author Ding
 * @since 2014-10-25
 * @version 1.0
 * 
 */
@SuppressWarnings("unchecked")
public class RedisService {

	// 注入redis 基本的操作类
	@Resource(name = "redisTemplate")
	public RedisTemplate<String, String> redisTemplate;
	// 注入redis keyvalue集合的操作类
	@Resource(name = "redisTemplate")
	public SetOperations<String, String> setOperations;
	// 注入redis List集合的操作类
	@Resource(name = "redisTemplate")
	public ListOperations<String, String> listOperations;
	// 注入redis keyvalue集合的操作类
	@Resource(name = "redisTemplate")
	public ValueOperations<String, String> valueOperations;
	@Resource(name = "redisTemplate")
	public HashOperations<String, String, String> hashOperations;

	/**
	 * 删除一个key
	 * 
	 * @param key
	 */
	public void delete(String key) {
		this.redisTemplate.delete(key);
	}

	// 通过key拿到value
	public String getValue(String key) {
		return valueOperations.get(key);
	}

	// 保存key + 保存时间
	public int setValueAndExpire(String key, String val, int time) {
		valueOperations.set(key, val, time, TimeUnit.SECONDS);
		return 0;
	}

	// 保存key + 保存时间
	public int setExpire(String key, int time) {
		redisTemplate.expire(key, time, TimeUnit.SECONDS);
		return 0;
	}

	// 保存key
	public int setVa(String key, String val) {
		valueOperations.set(key, val);
		return 0;
	}

	// 判断是否有对应的key
	public boolean hasKey(String key, long userId) {
		boolean flag = redisTemplate.hasKey(key);
		if (flag) { 
			String kval = valueOperations.get(key);
			if (Long.valueOf(kval) != userId) {
				flag = false;
			}
		}
		return flag;
	}

	// 没有userId的情况 ，这些个接口是会被黑的，注意
	public boolean hasKeyNoUser(String key) {
		boolean flag = redisTemplate.hasKey(key);
		return flag;
	}

	// 保存优惠卷列表
	public void saveTicketList(final String key, final Map<String, Object> map1) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(key.getBytes(), RedisSerializeUtils.serialize(map1));
				return null;
			}
		});
	}

	// 拿到优惠卷列表
	public Map<String, Object> getTicketList(final String key) {
		return redisTemplate.execute(new RedisCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] value = connection.get(key.getBytes());
				Map<String, Object> returnMap = (Map<String, Object>) RedisSerializeUtils.unserialize(value);
				return returnMap;
			}
		});
	}
	
	/**
	 *  登陆验证
	 * @param val
	 * @param userId
	 * @return
	 */
	public boolean verifyLanding(String val, long userId) {
		if(StringUtils.isEmpty(val)){
			return false;
		}
		String prefix = val.split(":")[0] + ":";
		if(prefix.equals(Constants.REDIS_KEY_TIMER)) {
			//AML定时器
			boolean flag = redisTemplate.hasKey(val);
			if (flag) { 
				String kval = valueOperations.get(val);
				if (!kval.equals(String.valueOf(userId))) {
					flag = false;
				}
				this.delete(val);
			}
			return flag;
		}
		String key = prefix + userId;
		boolean flag = redisTemplate.hasKey(key);
		if (flag) { 
			String kval = valueOperations.get(key);
			if (!val.equals(kval)) {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * 验证登录其他地方key和value
	 * @param key
	 * @param value
	 * @return
	 * @date 2017年12月27日
	 */
	public boolean verifyLoginElsewhere(String key,String value){
		if(StringUtils.isEmpty(key) || value == null){
			return false;
		}
		if(!value.equals(valueOperations.get(key))){
			return false;
		}
		this.setExpire(key, 60);
		return true;
	}
}
