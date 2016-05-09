package com.ding.redis;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.wandafilm.api.util.JedisConstant;
import com.wandafilm.api.util.JedisTools;
import com.wandafilm.api.util.JedisToolsSelDB;
import com.wandafilm.api.util.redis.RedisSetKeyConstant;

@Repository("integralValidationSms")
public class IntegralValidationSms extends JedisTools {
	protected static final Log log = LogFactory.getLog(ShareWrite.class);

	@Override
	public int getDBIndex() {
		return JedisConstant.DYNC_DB2;
	}

	public IntegralValidationSms() {
		this.jedisPool = JedisToolsSelDB.getJedisPool(JedisConstant.INTEGRAL_SMS_POOL);
	}

	public boolean setSendSmsCode(String uid, Map<String, String> map, int seconds) {
		return this.addHashMapToJedis(RedisSetKeyConstant.SMS_CHECK_CODE + ":" + uid, map, seconds, false);
	}

	public Map<String, String> getSendSmsCode(String uid) {
		return this.getHashMapFromJedis(RedisSetKeyConstant.SMS_CHECK_CODE + ":" + uid);
	}

	public boolean setUserSendSmsCount(String uid, Map<String, String> map, int seconds) {
		return this.addHashMapToJedis(RedisSetKeyConstant.USER_SEND_SMS_COUNT + ":" + uid, map, seconds, false);
	}

	public Map<String, String> getUserSendSmsCount(String uid) {
		return this.getHashMapFromJedis(RedisSetKeyConstant.USER_SEND_SMS_COUNT + ":" + uid);
	}

	public void deleteUserSendSmsCount(String uid) {
		this.delKeyFromJedis(RedisSetKeyConstant.USER_SEND_SMS_COUNT + ":" + uid);
	}
}
