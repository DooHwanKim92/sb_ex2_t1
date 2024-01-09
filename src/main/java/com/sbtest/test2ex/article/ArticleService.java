package com.sbtest.test2ex.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getList() {
        return this.articleRepository.findAll();
    }

    public void createPost(String subject, String content) {
        Article a = new Article();
        a.setSubject(subject);
        a.setContent(content);
        a.setCreateDate(LocalDateTime.now());
        this.articleRepository.save(a);
    }

    public Article findById(Integer id) {
        Optional<Article> a = this.articleRepository.findById(id);

        if(a.isEmpty()) {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }

        return a.get();
    }

    public void modifyArticle(Integer id, String subject, String content) {
        Optional<Article> a = this.articleRepository.findById(id);

        if(a.isEmpty()) {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }

        Article article = a.get();
        article.setSubject(subject);
        article.setContent(content);
        this.articleRepository.save(article);
    }

    public void removeArticle(Article article) {
        this.articleRepository.delete(article);
    }
}