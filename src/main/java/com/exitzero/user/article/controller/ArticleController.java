package com.exitzero.user.article.controller;

import com.exitzero.com.thymeleaf.pagination.PaginationInfo;
import com.exitzero.com.thymeleaf.pagination.PaginationUtils;
import com.exitzero.user.article.ArticleService;
import com.exitzero.user.article.domain.ArticleCommentVO;
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
    public String articlesByCategory(@PathVariable("category") String category, @ModelAttribute("searchVO") ArticleSearchVO searchVO, Model model) {
        searchVO.setCategory(category);

        int articlesCnt = articleService.articleListByCategory(category);

        PaginationInfo paginationInfo = PaginationUtils.buildPagination(searchVO, articlesCnt);

        List<ArticleVO> articles = articleService.articlesByCategory(searchVO);

        model.addAttribute("articles", articles);
        model.addAttribute("paginationInfo", paginationInfo);
        return "article/articles";
    }

    @GetMapping("/line/{category}/{articleId}")
    public String article(
            @PathVariable("category") String category,
            @PathVariable("articleId") Long articleId,
            @ModelAttribute("searchVO") ArticleSearchVO searchVO, Model model) {
        searchVO.setCategory(category);
        searchVO.setArticleId(articleId);

        ArticleVO articleVO = articleService.articleById(searchVO);
        List<ArticleCommentVO> commentVO = articleService.commentByArticleId(searchVO);

        model.addAttribute("articleVO", articleVO);
        model.addAttribute("commentVO", commentVO);

        return "article/article";

    }
}