package com.stargis.epr.service.impl;

import com.stargis.epr.dao.ArticleDao;
import com.stargis.epr.entity.Article;
import com.stargis.epr.service.ArticleService;
import com.stargis.epr.utils.AntiXssUtil;
import com.stargis.epr.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article queryObject(Integer id) {
        return articleDao.getArticleById(id);
    }

    @Override
    public List<Article> queryList(Map<String, Object> map) {
        List<Article> articles = articleDao.findArticles(map);
        return articles;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return articleDao.getTotalArticles(map);
    }

    @Override
    public void save(Article article) {
        try {
            article.setArticleCreateDate(DateUtil.getCurrentDateStr());
            article.setArticleTitle(AntiXssUtil.replaceHtmlCode(article.getArticleTitle()));
            articleDao.insertArticle(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Article article) {
        article.setArticleTitle(AntiXssUtil.replaceHtmlCode(article.getArticleTitle()));
        articleDao.updArticle(article);
    }

    @Override
    public void delete(Integer id) {
        articleDao.delArticle(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        articleDao.deleteBatch(ids);
    }

}
