package com.exitzero.user.article.controller;

import com.exitzero.com.thymeleaf.pagination.PaginationInfo;
import com.exitzero.com.thymeleaf.pagination.PaginationUtils;
import com.exitzero.user.article.ArticleService;
import com.exitzero.user.article.domain.ArticleVO;
import com.exitzero.user.article.search.ArticleSearchVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/line/{category}")
    public String categoryArticle(@PathVariable("category") String category, @ModelAttribute("searchVO") ArticleSearchVO searchVO, Model model) {
        searchVO.setCategory(category);

        int articlesCnt = articleService.articleListByCategory(category);

        PaginationInfo paginationInfo = PaginationUtils.buildPagination(searchVO, articlesCnt);

        List<ArticleVO> articles = articleService.articlesByCategory(searchVO);

        model.addAttribute("articles", articles);
        model.addAttribute("paginationInfo", paginationInfo);
        return "article/articles";
    }
}