package rml.service;

import org.springframework.stereotype.Service;
import rml.model.PageResult;
import rml.request.ArticleRequest;
import rml.request.SearchRequest;

/**
 * Created by BBLink on 2017/5/9.
 */

public interface ArticleService {
    boolean upArticle(ArticleRequest request);


    PageResult getMyArticleList(SearchRequest searchRequest);
}
