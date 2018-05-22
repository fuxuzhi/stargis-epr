package com.stargis.epr.service;

import com.stargis.epr.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * 接口定义
 */
public interface ArticleService {
	
	Article queryObject(Integer id);
	
	List<Article> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(Article article);
	
	void update(Article article);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
