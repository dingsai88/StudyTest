package com.ding.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class ZMainRedisHash {

	/**
	 * @author daniel
	 * @time 2016-5-9 上午11:08:13
	 * @param args
	 */
	public static void main(String[] args) {
		// 请求限制key
		String requestKey = "/xxx/password:" + mobile;

		// 删除integralValidationSms.deleteUserSendSmsCount(requestKey);
		// this.delKeyFromJedis(RedisSetKeyConstant.USER_SEND_SMS_COUNT + ":" +
		// uid);

		Map<String, String> interfaceCount = new HashMap<String, String>();

		// 查hash
		interfaceCount = this.getHashMapFromJedis(requestKey);
		int requestCount = Integer.parseInt(interfaceCount.get("USERCOUNT"));
		Long.parseLong(interfaceCount.get("COUNTTIME"));

		// set设置可以调用的次数
		interfaceCount.put("USERCOUNT", REQUEST_COUNT);
		// 设置首次调用时间
		interfaceCount.put("COUNTTIME", this.getTime());
		// set
		this.addHashMapToJedis(requestKey, interfaceCount, seconds, false);

	}

	/**
	 * 批量修改hash的值
	 * 
	 * @param key
	 * @param map
	 * @param cacheSeconds
	 * @param isModified
	 * @param methodName
	 */
	public boolean addHashMapToJedis(String key, Map<String, String> map, int cacheSeconds, boolean isModified) {
		boolean isBroken = true;
		boolean status = true;
		Jedis jedis = null;
		if (map != null && map.size() > 0) {
			try {
				jedis = this.getJedis();
				jedis.select(getDBIndex());
				jedis.hmset(key, map);
				if (cacheSeconds > 0) {
					jedis.expire(key, cacheSeconds);
				}
			} catch (Exception e) {
				e.printStackTrace();
				isBroken = true;
				status = false;
			} finally {
				release(jedis, isBroken);
			}
		}
		return status;
	}
}
