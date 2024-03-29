package com.exitzero.user.article;

import com.exitzero.user.article.domain.ArticleCommentVO;
import com.exitzero.user.article.domain.ArticleVO;
import com.exitzero.user.article.search.ArticleSearchVO;

import java.util.List;

public interface ArticleService {
    int articleListByCategory(String category);

    List<ArticleVO> articlesByCategory(ArticleSearchVO searchVO);

    ArticleVO articleById(ArticleSearchVO searchVO);

    List<ArticleCommentVO> commentByArticleId(ArticleSearchVO searchVO);
}
