package com.ll.sbb.article;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model){
        List<Article> articleList=this.articleService.List();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }

    @GetMapping("/create")
    public String Create(ArticleForm articleForm){
        return "article_form";
    }
    @PostMapping("/create")
    public String Create(@Valid ArticleForm articleForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "article_form";
        }
        this.articleService.Create(articleForm.getTitle(), articleForm.getContent());
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Article article = this.articleService.Detail(id);
        model.addAttribute("article", article);
        return "article_detail";
    }
    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id, ArticleForm articleForm) {
        Article article = this.articleService.Detail(id);
        model.addAttribute("article", article);

        article.setTitle(articleForm.getTitle());
        article.setContent(articleForm.getContent());
        return "article_modify";

    }
    @PostMapping("/modify/{id}")
    public String modify(@PathVariable("id") Integer id, @Valid ArticleForm articleForm, BindingResult bindingResult) {
        Article article = this.articleService.Detail(id);
        if (bindingResult.hasErrors()){
            return "article_modify";
        }
        this.articleService.Modify(article, articleForm.getTitle(),articleForm.getContent());
        return String.format("redirect:/article/detail/%s",article.getId());
    }

    @GetMapping("/delete/{id}")
    public String Delete1(Model model, @PathVariable("id") Integer id, ArticleForm articleForm) {
        Article article = this.articleService.Detail(id);
        this.articleService.Delete(article);
        article.setTitle(articleForm.getTitle());
        article.setContent(articleForm.getContent());
        return "redirect:/article/list";
    }
}
