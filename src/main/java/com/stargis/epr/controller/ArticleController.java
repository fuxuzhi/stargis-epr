package com.stargis.epr.controller;

import com.stargis.epr.entity.Article;
import com.stargis.epr.service.ArticleService;
import com.stargis.epr.utils.PageUtils;
import com.stargis.epr.utils.Query;
import com.stargis.epr.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章列表控制器
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Article> articles = articleService.queryList(query);
		int total = articleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(articles, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		Article article = articleService.queryObject(id);
		
		return R.ok().put("article", article);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody Article article){
		articleService.save(article);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody Article article){
		articleService.update(article);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		articleService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
