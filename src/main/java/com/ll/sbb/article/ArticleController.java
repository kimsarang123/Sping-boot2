package com.ll.sbb.article;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/article/list")
    public String list(Model model){
        List<Article> articleList =this.articleService.getList();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }

    @GetMapping("/article/create")
    public String create(){
        return "article_form";
    }
    @PostMapping("/article/create")
    public String create2(@RequestParam String title, @RequestParam String content){
        this.articleService.getCreate(title, content);
        return "redirect:/article/list";
    }

    @GetMapping("/article/detail/{id}")
    public String detail(Model model,@PathVariable("id") Integer id){
        Article article = this.articleService.getDetail(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

}
