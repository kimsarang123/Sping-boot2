package com.ll.sbb.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void Create(String title, String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        this.articleRepository.save(article);
    }

    public List<Article> List() {
        return this.articleRepository.findAll();
    }

    public Article Detail(Integer id) {
        Optional<Article> article = this.articleRepository.findById(id);
        if (article.isPresent()){
            return article.get();
        } else {
            return null;
        }
    }


    public void Modify(Article article,String title, String content) {
        article.setTitle(title);
        article.setContent(content);
        this.articleRepository.save(article);
    }

    public void Delete(Article article) {
        this.articleRepository.delete(article);
    }
}
