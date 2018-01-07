package com.antScience.repository.article;

import com.antScience.entity.article.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lirui on 2017/12/30.
 */
@Repository
public interface ArticleRepository {
    void create(Article article);

    Article selectById(@Param("id") String id);

    List<Article> selectByUserName(@Param("userName") String userName);

    void deleteById(@Param("id") String id);

    void update(@Param("article") Article article);

    List<Article> loadArticles(@Param("label") String label, @Param("userName") String userName);
}
