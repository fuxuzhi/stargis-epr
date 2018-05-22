package com.stargis.epr.controller;

import com.stargis.epr.entity.Picture;
import com.stargis.epr.service.PictureService;
import com.stargis.epr.utils.PageUtils;
import com.stargis.epr.utils.Query;
import com.stargis.epr.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 图片信息控制器
 */
@RestController
@RequestMapping("/pictures")
public class PictureController {
	@Autowired
	private PictureService pictureService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Picture> pictures = pictureService.queryList(query);
		int total = pictureService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(pictures, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		Picture picture = pictureService.queryObject(id);
		
		return R.ok().put("picture", picture);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody Picture picture){
		pictureService.save(picture);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody Picture picture){
		pictureService.update(picture);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		pictureService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
