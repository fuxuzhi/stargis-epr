package com.stargis.epr.service;

import com.stargis.epr.entity.Picture;

import java.util.List;
import java.util.Map;

/**
 * 接口定义
 */
public interface PictureService {
	
	Picture queryObject(Integer id);
	
	List<Picture> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(Picture picture);
	
	void update(Picture picture);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
