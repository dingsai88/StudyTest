package com.ding.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisException;

import com.hp.wandafilm.util.SerializeUtil;

public abstract class JedisTools {
	public abstract int getDBIndex();

	/**
	 * 默认日志打印logger_default
	 */
	public static Logger logger_default = Logger.getLogger("logger_jCache_default");
	/**
	 * 失败日志logger，用于定期del指定的key
	 */
	public static Logger logger_failure = Logger.getLogger("logger_jCache_failure");

	public JedisPool jedisPool;

	public Jedis getJedis() throws JedisException {
		Jedis jedis = null;
		try {
			synchronized (jedisPool) {
				jedis = jedisPool.getResource();
			}
		} catch (JedisException e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		}
		return jedis;
	}

	public Jedis getJedis(int databaseIndex) {
		Jedis jedis = null;
		try {
			synchronized (jedisPool) {
				jedis = jedisPool.getResource();
				jedis.select(databaseIndex);
			}
		} catch (JedisException e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		}
		return jedis;
	}

	/**
	 * 得到索引处的得分和元�?
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public Tuple zrangeOneMemberScores(String key, long index) {
		Jedis jedis = null;
		boolean isBroken = false;
		Set<Tuple> set = null;
		try {
			jedis = getJedis(getDBIndex());
			set = jedis.zrangeWithScores(key, index, index);
			if (null != set && set.size() > 0) {
				Iterator<Tuple> it = set.iterator();
				if (it.hasNext())
					return it.next();
			}

		} catch (Exception e) {
			isBroken = true;
			e.printStackTrace();
		} finally {
			release(jedis, isBroken);
		}

		return null;
	}

	/**
	 * �?有序集合 添加元素
	 * 
	 * @param key
	 * @param score
	 * @param value
	 * @return
	 */
	public long zadd(String key, long score, String value) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis(getDBIndex());
			return jedis.zadd(key, score, value);
		} catch (Exception e) {
			isBroken = true;
			e.printStackTrace();
		} finally {
			release(jedis, isBroken);
		}

		return 0L;
	}

	/**
	 * 获得有序集合中得分在 score1 �?score2 之间的元�?
	 * 
	 * @param key
	 * @param score1
	 * @param score2
	 * @return
	 */
	public Set<String> zrangeByScore(String key, String score1, String score2) {
		Jedis jedis = null;
		Set<String> set = new HashSet<String>();
		boolean isBroken = false;
		try {
			jedis = getJedis(getDBIndex());
			set = jedis.zrangeByScore(key, score1, score2);
		} catch (Exception e) {
			isBroken = true;
			e.printStackTrace();
		} finally {
			release(jedis, isBroken);
		}

		return set;
	}

	/**
	 * 获得有序集合中得分在 score1 �?score2 之间的元素的个数
	 * 
	 * @param key
	 * @param score1
	 * @param score2
	 * @return
	 */
	public long zcountByScore(String key, String score1, String score2) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis(getDBIndex());
			return jedis.zcount(key, score1, score2);
		} catch (Exception e) {
			isBroken = true;
			e.printStackTrace();
		} finally {
			release(jedis, isBroken);
		}

		return 0;
	}

	/**
	 * 删除list
	 * 
	 * @param key
	 * @param databaseIndex
	 * @return
	 */
	public long deleteListFromRedis(String key) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis(getDBIndex());
			return jedis.del(key);
		} catch (Exception e) {
			isBroken = true;
			e.printStackTrace();
		} finally {
			release(jedis, isBroken);
		}

		return 0;
	}

	/**
	 * 获取list的长�?
	 * 
	 * @param key
	 * @param databaseIndex
	 * @return
	 */
	public long getQueueLength(String key) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis(getDBIndex());
			return jedis.llen(key);
		} catch (Exception e) {
			isBroken = true;
			e.printStackTrace();
		} finally {
			release(jedis, isBroken);
		}

		return 0;
	}

	/**
	 * 从list尾部取�?
	 * 
	 * @param key
	 * @param databaseIndex
	 * @return
	 */
	public String getRpop(String key) {
		Jedis jedis = null;
		boolean isBroken = false;
		String popValue = null;
		try {
			jedis = getJedis(getDBIndex());
			if (jedis.exists(key))
				popValue = jedis.rpop(key);
			return StringUtils.isNotBlank(popValue) && !"nil".equals(popValue) ? popValue : null;
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}

		return null;
	}

	/**
	 * 从list头部取�?
	 * 
	 * @param key
	 * @param databaseIndex
	 * @return
	 */
	public String getLpop(String key) {
		Jedis jedis = null;
		boolean isBroken = false;
		String popValue = null;
		try {
			jedis = getJedis(getDBIndex());
			if (jedis.exists(key))
				popValue = jedis.lpop(key);
			return StringUtils.isNotBlank(popValue) && !"nil".equals(popValue) ? popValue : null;
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}

		return null;
	}

	/**
	 * 从list头部将对象集合按顺序写入
	 * 
	 * @param key
	 * @param list
	 * @param cacheSeconds
	 */
	public void pushObjectListToQueueHead(String key, List<Object> list, int cacheSeconds) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			Pipeline pipeLine = jedis.pipelined();
			for (Object object : list) {
				pipeLine.lpush(key.getBytes(), SerializeUtil.serialize(object));
				if (cacheSeconds > 0)
					jedis.expire(key.getBytes(), cacheSeconds);
			}
			pipeLine.sync();
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 将对象加入到list的头�?
	 * 
	 * @param key
	 * @param obj
	 * @param cacheSeconds
	 */
	public void pushObjectToQueueHead(String key, Object obj, int cacheSeconds) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			jedis.lpush(key.getBytes(), SerializeUtil.serialize(obj));
			if (cacheSeconds > 0) {
				jedis.expire(key.getBytes(), cacheSeconds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 从list尾部获取对象�?进制转化�?
	 * 
	 * @return
	 */
	public Object getObjectFromQueueHead(String key) {
		Jedis jedis = null;
		boolean isBroken = false;
		byte[] bs = null;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			if (jedis.llen(key.getBytes()) > 0)
				bs = jedis.rpop(key.getBytes());
			if (null != bs)
				return SerializeUtil.unserialize(bs);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}

		return null;
	}

	/**
	 * 从list阻塞队列取�?
	 * 
	 * @param key
	 * @param timeout
	 * @return
	 */
	public List<byte[]> getListByBlpop(String key, int timeout) {
		boolean isBroken = false;
		Jedis jedis = null;
		List<byte[]> list = null;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			list = jedis.blpop(timeout, key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}

		return list;
	}

	/**
	 * 获取list队列区间之内的字符串集合
	 * 
	 * @param key
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<String> getQueueData(String key, Long startIndex, Long endIndex) {
		Jedis jedis = null;
		boolean isBroken = false;
		List<String> list = null;
		try {
			jedis = getJedis(getDBIndex());
			list = jedis.lrange(key, startIndex, endIndex);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = false;
		} finally {
			release(jedis, isBroken);
		}

		return list;
	}

	/**
	 * 分页查询数据
	 * 
	 * @param key
	 * @param pageNum
	 *            页数
	 * @param perPage
	 *            每页显示条数
	 * @return
	 */
	public List<String> getPageListFromJedis(String key, int pageNum, int perPage) {
		List<String> list = null;
		boolean isBroken = false;
		long totalPages = 0;// 实际页数
		int startIndex = pageNum * perPage - perPage;
		int endIndex = pageNum * perPage - 1;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			if (jedis.exists(key)) {
				Long max = jedis.llen(key);
				if ((max % perPage) == 0) {
					totalPages = max / perPage;
				} else {
					totalPages = max / perPage + 1;
				}
				if (pageNum > totalPages) {
					return null;
				} else {
					list = jedis.lrange(key, startIndex, endIndex);
				}
			}
		} catch (JedisException e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return list;
	}

	/**
	 * 生成主键
	 * 
	 * @return
	 */
	public String getPrimaryKey() {
		boolean isBroken = false;
		Jedis jedis = null;
		Long suffix = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			jedis = this.getJedis();
			jedis.select(JedisConstant.BASIC_DB0);
			suffix = jedis.incr(JedisConstant.PRIMARY_KEY);
		} catch (Exception e) {
			isBroken = true;
			e.printStackTrace();
		} finally {
			release(jedis, isBroken);
		}

		if (null == suffix)
			throw new RuntimeException("get primary key suffix error");

		return sdf.format(new Date()) + suffix;
	}

	/**
	 * 设置字符�?
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	public String addStringToJedis(String key, String value, int cacheSeconds) {
		Jedis jedis = null;
		boolean isBroken = false;
		String lastVal = null;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			lastVal = jedis.set(key, value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return lastVal;
	}

	/**
	 * 获取字符串的值，先获取在设置
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	public String getSetStringToJedis(String key, String value, int cacheSeconds) {
		Jedis jedis = null;
		boolean isBroken = false;
		String lastVal = null;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			lastVal = jedis.getSet(key, value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return lastVal;
	}

	/**
	 * 设置字符串的值，pipelined
	 * 
	 * @param batchData
	 * @param cacheSeconds
	 */
	public void addStringToJedis(Map<String, String> batchData, int cacheSeconds) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			Pipeline pipeline = jedis.pipelined();
			for (Map.Entry<String, String> element : batchData.entrySet()) {
				if (cacheSeconds != 0) {
					pipeline.setex(element.getKey(), cacheSeconds, element.getValue());
				} else {
					pipeline.set(element.getKey(), element.getValue());
				}
			}
			pipeline.sync();
		} catch (Exception e) {
			isBroken = true;
			e.printStackTrace();
		} finally {
			release(jedis, isBroken);
		}
	}

	public boolean checkToSetJedis() {

		return false;
	}

	public void addToSetJedis(String key, String[] value) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			jedis.sadd(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}

	public void removeSetJedis(String key, String value) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			jedis.srem(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 将指定的值插入到list尾部
	 * 
	 * @param key
	 * @param data
	 * @param cacheSeconds
	 */
	public void pushDataToListJedis(String key, String data, int cacheSeconds) {
		if (data != null && data.length() > 0) {
			Jedis jedis = null;
			boolean isBroken = false;
			try {
				jedis = this.getJedis();
				jedis.select(getDBIndex());
				jedis.rpush(key, data);
				if (cacheSeconds != 0) {
					jedis.expire(key, cacheSeconds);
				}
			} catch (Exception e) {
				e.printStackTrace();
				isBroken = true;
			} finally {
				release(jedis, isBroken);
			}
		}
	}

	/**
	 * 将集合写入list
	 * 
	 * @param key
	 * @param list
	 * @param cacheSeconds
	 */
	public void addListToJedis(String key, List<String> list, int cacheSeconds) {
		if (list != null && list.size() > 0) {
			Jedis jedis = null;
			boolean isBroken = false;
			try {
				jedis = this.getJedis();
				jedis.select(getDBIndex());
				if (jedis.exists(key)) {
					jedis.del(key);
				}
				for (String aList : list) {
					jedis.rpush(key, aList);
				}
				if (cacheSeconds != 0) {
					jedis.expire(key, cacheSeconds);
				}
			} catch (JedisException e) {
				e.printStackTrace();
				isBroken = true;
			} catch (Exception e) {
				e.printStackTrace();
				isBroken = true;
			} finally {
				release(jedis, isBroken);
			}
		}
	}

	/**
	 * 将指定的字符串插入到list尾部
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @param methodName
	 */
	public boolean addStringToListJedis(String key, String value[], int cacheSeconds) {
		boolean returnStatus = false;
		if (value != null && value.length > 0) {
			Jedis jedis = null;
			boolean isBroken = false;
			try {
				jedis = this.getJedis();
				jedis.select(getDBIndex());
				long pushlength = jedis.rpush(key, value);
				if (cacheSeconds != 0) {
					jedis.expire(key, cacheSeconds);
				}
				if (pushlength > 0l) {
					returnStatus = true;
				}
			} catch (JedisException e) {
				e.printStackTrace();
				isBroken = true;
			} catch (Exception e) {
				e.printStackTrace();
				isBroken = true;
			} finally {
				release(jedis, isBroken);
			}
		}
		return returnStatus;
	}

	/**
	 * 将指定的字符串插入到list头部
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @param methodName
	 */
	public boolean addStringToListBylpushJedis(String key, String value[], int cacheSeconds) {
		boolean returnStatus = false;
		if (value != null && value.length > 0) {
			Jedis jedis = null;
			boolean isBroken = false;
			try {
				jedis = this.getJedis();
				jedis.select(getDBIndex());
				long pushlength = jedis.lpush(key, value);
				if (cacheSeconds > 0) {
					jedis.expire(key, cacheSeconds);
				}
				if (pushlength > 0l) {
					returnStatus = true;
				}
			} catch (JedisException e) {
				e.printStackTrace();
				isBroken = true;
			} catch (Exception e) {
				e.printStackTrace();
				isBroken = true;
			} finally {
				release(jedis, isBroken);
			}
		}
		return returnStatus;
	}

	/**
	 * 将指定的字符串集合插入到list头部
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @param methodName
	 */
	public boolean addStringToListBylpushJedis(String key, String value, int cacheSeconds) {
		boolean returnStatus = false;
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			long pushlength = jedis.lpush(key, value);
			if (cacheSeconds > 0) {
				jedis.expire(key, cacheSeconds);
			}
			if (pushlength > 0l) {
				returnStatus = true;
			}
		} catch (JedisException e) {
			e.printStackTrace();
			isBroken = true;
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}

		return returnStatus;
	}

	/**
	 * 从list头部获取数据
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @param methodName
	 */
	public String getStringFromListByLpopJedis(String key) {
		String returnvalue = null;
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			if (jedis.exists(key))
				returnvalue = jedis.lpop(key);
			return StringUtils.isNotBlank(returnvalue) && !"nil".equals(returnvalue) ? returnvalue : null;
		} catch (JedisException e) {
			e.printStackTrace();
			isBroken = true;
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return null;
	}

	public void pushDataToListJedis(String key, List<String> batchData, int cacheSeconds) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			jedis.del(key);
			jedis.lpush(key, batchData.toArray(new String[batchData.size()]));
			if (cacheSeconds != 0)
				jedis.expire(key, cacheSeconds);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 删除list中的元素
	 * 
	 * @param key
	 * @param values
	 * @param methodName
	 */
	public void deleteDataFromListJedis(String key, List<String> values) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			Pipeline pipeline = jedis.pipelined();
			if (values != null && !values.isEmpty()) {
				for (String val : values) {
					pipeline.lrem(key, 0, val);
				}
			}
			pipeline.sync();
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 获取list全部
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<String> getListFromJedis(String key) {
		List<String> list = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			if (jedis.exists(key)) {
				list = jedis.lrange(key, 0, -1);
			}
		} catch (JedisException e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return list;
	}

	/**
	 * 获取指定区间的list�?
	 * 
	 * @param key
	 * @param startIndex
	 * @param endIndex
	 * @return
	 * @throws Exception
	 */
	public List<String> getListFromJedis(String key, int startIndex, int endIndex) throws Exception {
		List<String> list = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			if (jedis.exists(key)) {
				list = jedis.lrange(key, startIndex, endIndex);
			}
		} catch (JedisException e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return list;
	}

	/**
	 * 将对象写入jedis
	 * 
	 * @param key
	 * @param object
	 * @param cacheSeconds
	 */
	public void pushObjectToListJedis(String key, Object object, int cacheSeconds) {
		Jedis jedis = null;
		boolean isBroken = false;
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			// 序列�?
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.flush();
			byte[] bytes = baos.toByteArray();
			jedis.lpush(key.getBytes(), bytes);
			if (cacheSeconds != 0)
				jedis.expire(key, cacheSeconds);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
			try {
				if (null != oos)
					oos.close();
				if (null != baos)
					baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 从redis读取对象
	 * 
	 * @param key
	 * @param object
	 * @param cacheSeconds
	 * @return
	 */
	public Object getObjectFromListJedis(String key) {
		Jedis jedis = null;
		boolean isBroken = false;
		Object obj = null;
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			byte[] bytes = jedis.rpop(key.getBytes());
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			obj = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
			try {
				if (ois != null)
					ois.close();
				if (bais != null)
					bais.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	/**
	 * 批量修改hash的�?
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
				if (cacheSeconds > 0)
					jedis.expire(key, cacheSeconds);
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

	/**
	 * 单条修改hash的�?
	 * 
	 * @param key
	 * @param map
	 * @param cacheSeconds
	 * @param isModified
	 * @param methodName
	 */
	public boolean addHashMapToJedis(String key, String field, String value, int cacheSeconds) {
		boolean isBroken = false;
		boolean status = true;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (jedis != null) {
				jedis.select(getDBIndex());
				jedis.hset(key, field, value);
				if (cacheSeconds != 0) {
					jedis.expire(key, cacheSeconds);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return status;
	}

	/**
	 * 为哈希表 key 中的指定字段的整数�?加上增量 increment
	 * 
	 * @param key
	 * @param incrementField
	 * @param incrementValue
	 * @param methodName
	 */
	public boolean updateHashMapToJedis(String key, String incrementField, long incrementValue) {
		boolean isBroken = false;
		boolean status = true;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			jedis.hincrBy(key, incrementField, incrementValue);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
			status = false;
		} finally {
			release(jedis, isBroken);
		}
		return status;
	}

	/**
	 * 为哈希表 key 中的指定字段的整数�?加上增量 increment
	 * 
	 * @param key
	 * @param incrementField
	 * @param incrementValue
	 * @param methodName
	 */
	public Long autoIncretmentHash(String key, String incrementField, long incrementValue) {
		boolean isBroken = false;
		Long returnVlue = 0l;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			returnVlue = jedis.hincrBy(key, incrementField, incrementValue);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return returnVlue;
	}

	/**
	 * 获取单个hash字段�?
	 * 
	 * @param key
	 * @param field
	 * @param methodName
	 * @return
	 */
	public String getHashMapValueFromJedis(String key, String field) {
		String value = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (jedis != null) {
				jedis.select(getDBIndex());
				if (jedis.exists(key)) {
					value = jedis.hget(key, field);
					value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}

		return value;
	}

	/**
	 * 获取多个hash字段�?
	 * 
	 * @param key
	 * @param field
	 * @param methodName
	 * @return
	 */
	public List<String> getHashMapValueFromJedis(String key, String[] fields) {
		List<String> value = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (jedis != null) {
				jedis.select(getDBIndex());
				if (jedis.exists(key)) {
					value = jedis.hmget(key, fields);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return value;
	}

	/**
	 * 删除hash中的元素
	 * 
	 * @param key
	 * @param values
	 * @param methodName
	 */
	public void deleteDataFromHashJedis(String key, String fields[]) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			jedis.hdel(key, fields);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 获取hash中所有的元素
	 * 
	 * @param key
	 * @param values
	 * @param methodName
	 */
	public Map<String, String> getHashMapFromJedis(String key) {
		Map<String, String> hashMap = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			hashMap = jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return hashMap;
	}

	/**
	 * 按域 删除 hash中的元素
	 * 
	 * @param key
	 * @param fields
	 */
	public void deleteDataFromHashJedisByField(String key, String fields) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			jedis.hdel(key, fields);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 获取单个字符串的�?
	 * 
	 * @param key
	 * @return
	 */
	public String getStringFromJedis(String key) {
		String value = null;
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			if (jedis.exists(key)) {
				value = jedis.get(key);
				value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return value;
	}

	/**
	 * 获取多个字符串的�?
	 * 
	 * @param key
	 * @return
	 */
	public List<String> getStringFromJedis(String[] keys) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			return jedis.mget(keys);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return null;
	}

	public Set<String> getSetFromJedis(String key) throws Exception {
		Set<String> list = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			if (jedis.exists(key)) {
				list = jedis.smembers(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return list;
	}

	/**
	 * 删除某db的某个key�?
	 * 
	 * @param key
	 * @return
	 */
	public Long delKeyFromJedis(String key) {
		boolean isBroken = false;
		Jedis jedis = null;
		long result = 0;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			return jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 根据dbIndex flushDB每个shard
	 * 
	 * @param dbIndex
	 */
	public void flushDBFromJedis(int dbIndex) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(dbIndex);
			jedis.flushDB();
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 验证key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean existKey(String key) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return false;
	}

	/**
	 * 查看哈希�?key 中，指定的字段是否存�?
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public boolean existHashKeyField(String key, String field) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(getDBIndex());
			return jedis.hexists(key, field);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return false;
	}

	/**
	 * 释放连接�?
	 * 
	 * @param jedis
	 * @param isBroken
	 */
	public void release(Jedis jedis, boolean isBroken) {
		if (jedis != null) {
			if (isBroken) {
				jedisPool.returnBrokenResource(jedis);
			} else {
				jedisPool.returnResource(jedis);
			}
		}
	}
}
