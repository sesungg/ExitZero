package com.exitzero.user.article.impl;

import com.exitzero.user.article.domain.ArticleCommentVO;
import com.exitzero.user.article.mapper.ArticleMapper;
import com.exitzero.user.article.ArticleService;
import com.exitzero.user.article.domain.ArticleVO;
import com.exitzero.user.article.search.ArticleSearchVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper mapper;

    @Override
    public int articleListByCategory(String category) {
        return mapper.articleListByCategory(category);
    }

    @Override
    public List<ArticleVO> articlesByCategory(ArticleSearchVO searchVO) {
        return mapper.articlesByCategory(searchVO);
    }

    @Override
    public ArticleVO articleById(ArticleSearchVO searchVO) {
        return mapper.articleById(searchVO);
    }

    @Override
    public List<ArticleCommentVO> commentByArticleId(ArticleSearchVO searchVO) {
        return mapper.commentByArticleId(searchVO);
    }
}
