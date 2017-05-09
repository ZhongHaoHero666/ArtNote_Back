package rml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rml.model.HttpResult;
import rml.model.PageResult;
import rml.request.ArticleRequest;
import rml.request.SearchRequest;
import rml.service.ArticleService;

/**
 * Created by BBLink on 2017/5/9.
 */
@Controller(value = "ArticleController")
@RequestMapping(value = "Article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    //
    @RequestMapping(value = "/upArticle")
    @ResponseBody
    public HttpResult upArticle(ArticleRequest articleRequest) {

        HttpResult httpResult = new HttpResult();
        if (null == articleRequest.getTitle()) {
            httpResult.setState("文章的标题不能为空");
            httpResult.setCode(0);
            return httpResult;

        }
        boolean result = articleService.upArticle(articleRequest);
        if (result) {
            httpResult.setCode(0);
            httpResult.setState("上传文章成功");
        } else {
            httpResult.setCode(-1);
            httpResult.setState("上传文章失败");

        }

        return httpResult;
    }


    @RequestMapping(value = "getMyArticleList")
    @ResponseBody
    public PageResult getMyArticleList(SearchRequest searchRequest) {

        return articleService.getMyArticleList(searchRequest);
    }
}
