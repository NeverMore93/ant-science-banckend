package com.antScience.controller.article;

import com.antScience.entity.article.Article;
import com.antScience.foundation.common.Response;
import com.antScience.service.article.ArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lirui on 2017/12/30.
 */
@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @ApiOperation("创建文章")
    @RequestMapping(value = "/userName/{userName}/article", method = RequestMethod.POST)
    public HttpEntity create(@RequestBody Article article, @PathVariable("userName") String userName) {
        Article createArticle = articleService.create(article, userName);
        return Response.post(createArticle);
    }

    @ApiOperation("可能需要分页")
    @RequestMapping(value = "/userName/{userName}/articles", method = RequestMethod.GET)
    public HttpEntity selectByUserName(@PathVariable("userName") String userName) {
        List<Article> articleList = articleService.selectByUserName(userName);
        return Response.build(articleList);
    }

    @RequestMapping(value = "/article/id/{id}", method = RequestMethod.DELETE)
    public HttpEntity deleteById(@PathVariable("id") String id) {
        articleService.deleteById(id);
        return Response.build("删除成功");
    }

    @RequestMapping(value = "/article/id/{id}", method = RequestMethod.PUT)
    public HttpEntity update(@RequestBody Article article, @PathVariable("id") String id) {
        Article updatedArticle = articleService.update(article, id);
        return Response.build(updatedArticle);
    }

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public HttpEntity loadArticles(@RequestParam(required = false) String label, @RequestParam(required = false) String userName) {
        List<Article> articleList = articleService.loadArticles(label, userName);
        return Response.build(articleList);
    }

}
