package rml.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rml.dao.ArticleMapper;
import rml.model.ArticleModel;
import rml.model.MapperModel.SearchPageArticlebyUserIdModel;
import rml.model.PageResult;
import rml.request.ArticleRequest;
import rml.request.SearchRequest;
import rml.service.ArticleService;

import java.util.List;

/**
 * Created by BBLink on 2017/5/9.
 */

@Service("articleService")
public class ArticleServiceImp implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public boolean upArticle(ArticleRequest request) {
        if (-1 != articleMapper.upArticle(request)) return true;
        return false;
    }

    @Override
    public PageResult getMyArticleList(SearchRequest searchRequest) {
        PageResult pageResult = new PageResult();
        pageResult.setTotal(articleMapper.searchArticleCountByUserId(searchRequest.getUserId()));
        pageResult.setPageNum(searchRequest.getPageNum());
        pageResult.setPageSize(searchRequest.getPageSize());

        int startOffert = (searchRequest.getPageNum() - 1) * searchRequest.getPageSize();
        SearchPageArticlebyUserIdModel searchPageArticlebyUserIdModel  =
                new SearchPageArticlebyUserIdModel(searchRequest.getUserId(),searchRequest.getPageSize(),startOffert);
        pageResult.setData( articleMapper.searchPageArticlebyUserId(searchPageArticlebyUserIdModel));
        return pageResult;
    }
}
