package com.sbtest.test2ex;

import com.sbtest.test2ex.article.Article;
import com.sbtest.test2ex.article.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class Test2exApplicationTests {

	@Autowired
	ArticleRepository articleRepository;
	@Test
	void contextLoads() {
		Article a = new Article();
		a.setSubject("654654");
		a.setContent("1058704");
		a.setCreateDate(LocalDateTime.now());
		this.articleRepository.save(a);

	}

}
