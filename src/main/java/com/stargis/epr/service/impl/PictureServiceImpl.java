package com.stargis.epr.service.impl;

import com.stargis.epr.dao.PictureDao;
import com.stargis.epr.entity.Picture;
import com.stargis.epr.service.PictureService;
import com.stargis.epr.utils.AntiXssUtil;
import com.stargis.epr.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("pictureService")
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureDao pictureDao;

    @Override
    public Picture queryObject(Integer id) {
        return pictureDao.findPictureById(id);
    }

    @Override
    public List<Picture> queryList(Map<String, Object> map) {
        return pictureDao.findPictures(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return pictureDao.getTotalPictures(map);
    }

    @Override
    public void save(Picture picture) {
        try {
            picture.setTime(DateUtil.getCurrentDateStr());
            picture.setUrl(AntiXssUtil.replaceHtmlCode(picture.getUrl()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        pictureDao.insertPicture(picture);
    }

    @Override
    public void update(Picture picture) {
        picture.setUrl(AntiXssUtil.replaceHtmlCode(picture.getUrl()));
        pictureDao.updPicture(picture);
    }

    @Override
    public void delete(Integer id) {
        pictureDao.delPicture(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        pictureDao.deleteBatch(ids);
    }

}
