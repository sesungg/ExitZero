package exitzero_user.article.controller;

import config.WebSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 카테고리별 게시글")
@WebMvcTest(ArticleController.class)
@Import(WebSecurityConfig.class)
class ArticleControllerTest {

    @Autowired
    private MockMvc mvc;

    @DisplayName("카테고리 선택")
    @Test
    public void categoryArticle() throws Exception {
        // Given
        int category = 1;

        // When & Then
        mvc.perform(get("/line/{category}", category))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("article/articles"))
                .andExpect(model().attribute("category", category));
    }
}