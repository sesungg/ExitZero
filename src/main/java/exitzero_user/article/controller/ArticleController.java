package exitzero_user.article.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {

    @GetMapping("/line/{category}")
    public String categoryArticle(@PathVariable("category") int category, Model model) {
        model.addAttribute("category", category);
        return "article/articles";
    }
}
