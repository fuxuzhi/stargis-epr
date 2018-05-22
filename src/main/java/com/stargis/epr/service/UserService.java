package com.stargis.epr.service;

import com.stargis.epr.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 接口定义
 */
public interface UserService {

	User login(User user);

	List<User> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(User user);
	
	void update(User user);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	User queryObject(Integer id);
}
