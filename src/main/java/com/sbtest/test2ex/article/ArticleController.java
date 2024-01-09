package com.sbtest.test2ex.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public String articleList(Model model) {
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList",articleList);
        return "article_list";
    }

    @GetMapping("/create")
    public String create() {
        return "article_form";
    }

    @PostMapping("/create")
    public String createPost(Model model,@RequestParam("subject") String subject, @RequestParam("content") String content) {
        this.articleService.createPost(subject,content);
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Article article = this.articleService.findById(id);
        model.addAttribute("article",article);
        return "article_detail";
    }

    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id) {
        Article article = this.articleService.findById(id);
        model.addAttribute("article",article);
        return "modify_form";
    }

    @PostMapping("/modify/{id}")
    public String modifyPost(Model model,@PathVariable("id") Integer id, @RequestParam("subject") String subject, @RequestParam("content") String content) {
        this.articleService.modifyArticle(id, subject, content);
        Article article = this.articleService.findById(id);
        model.addAttribute("article",article);
        return "article_detail";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id) {
        Article article = this.articleService.findById(id);
        this.articleService.removeArticle(article);
        return "redirect:/article/list";
    }
}