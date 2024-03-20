package com.exitzero.user.article.mapper;

import com.exitzero.user.article.domain.ArticleVO;
import com.exitzero.user.article.search.ArticleSearchVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int articleListByCategory(String category);

    List<ArticleVO> articlesByCategory(ArticleSearchVO searchVO);
}
