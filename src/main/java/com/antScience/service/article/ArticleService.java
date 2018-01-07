package com.antScience.service.article;

import com.antScience.entity.article.Article;
import com.antScience.repository.article.ArticleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by lirui on 2017/12/30.
 */
@Service
public class ArticleService {

    @Resource
    private ArticleRepository articleRepository;

    public Article create(Article article, String userName) {
        String id = UUID.randomUUID().toString();
        article.setId(id);
        article.setUserName(userName);
        articleRepository.create(article);
        return articleRepository.selectById(id);
    }

    public List<Article> selectByUserName(String userName) {
        return articleRepository.selectByUserName(userName);
    }

    public void deleteById(String id) {
        articleRepository.deleteById(id);
    }

    public Article update(Article article, String id) {
        article.setId(id);
        articleRepository.update(article);
        return articleRepository.selectById(id);
    }

    public List<Article> loadArticles(String label, String userName) {
        return articleRepository.loadArticles(label, userName);
    }
}
