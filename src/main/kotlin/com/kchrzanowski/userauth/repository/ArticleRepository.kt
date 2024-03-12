package com.kchrzanowski.userauth.repository

import com.kchrzanowski.userauth.model.Article
import org.springframework.stereotype.Repository
import java.util.*

//todo change/extend this to interface and implement connection to DB with SpringJPA
@Repository
class ArticleRepository {

    private val articles = listOf(
            Article(id = UUID.randomUUID(), "Article 1", "Content 1"),
            Article(id = UUID.randomUUID(), "Article 2", "Content 2"),
            Article(id = UUID.randomUUID(), "Article 3", "Content 3"),
            Article(id = UUID.randomUUID(), "Article 4", "Content 4"),
    )

    fun findAll(): List<Article> = articles;
}