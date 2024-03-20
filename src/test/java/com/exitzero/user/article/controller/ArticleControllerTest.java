package com.exitzero.user.article.controller;

import com.exitzero.user.article.ArticleService;
import com.exitzero.user.article.domain.ArticleVO;
import com.exitzero.user.article.search.ArticleSearchVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("View 컨트롤러 - 카테고리별 게시글")
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ArticleService articleService;

    @DisplayName("카테고리에 따른 게시물 조회")
    @Test
    public void categoryArticle() throws Exception {
        // Given
        String category = "1";

        List<ArticleVO> mockArticles = Arrays.asList(
                new ArticleVO("Title 1", "Content 1", "1"),
                new ArticleVO("Title 2", "Content 2", "1"),
                new ArticleVO("Title 3", "Content 3", "1")
        );

        ArticleSearchVO searchVO = new ArticleSearchVO();
        searchVO.setCategory("1");
        searchVO.setPage(1);
        when(articleService.articlesByCategory(searchVO)).thenReturn(mockArticles);

        // When
        List<ArticleVO> articles = articleService.articlesByCategory(searchVO);

        // Then
        assertThat(articles).isNotNull().isNotEmpty();
        articles.forEach(article -> assertThat(article.getCategory()).isEqualTo(category));
    }
}