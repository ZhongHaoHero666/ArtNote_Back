package rml.dao;

import org.springframework.stereotype.Service;
import rml.model.ArticleModel;
import rml.model.MapperModel.SearchPageArticlebyUserIdModel;
import rml.request.ArticleRequest;

import java.util.List;

/**
 * Created by BBLink on 2017/5/9.
 */
@Service("ArticleMapper")
public interface ArticleMapper {


    int upArticle(ArticleRequest request);


    int searchArticleCountByUserId(String userId);

    List<ArticleModel> searchPageArticlebyUserId(SearchPageArticlebyUserIdModel searchPageArticlebyUserIdModel);
}
