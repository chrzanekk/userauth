package com.kchrzanowski.userauth.service.impl

import com.kchrzanowski.userauth.controller.article.ArticleResponse
import com.kchrzanowski.userauth.model.Article
import com.kchrzanowski.userauth.repository.ArticleRepository
import com.kchrzanowski.userauth.service.ArticleService
import org.springframework.stereotype.Service

@Service
class ArticleServiceImpl(
        private val articleRepository: ArticleRepository
) : ArticleService {
    override fun findAll(): List<ArticleResponse> {
        return articleRepository.findAll().map { it.toResponse() }
    }

    fun Article.toResponse(): ArticleResponse =
            ArticleResponse(
                    id = this.id,
                    title = this.title,
                    content = this.content
            )
}