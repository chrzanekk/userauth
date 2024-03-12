package com.kchrzanowski.userauth.service

import com.kchrzanowski.userauth.controller.article.ArticleResponse
import com.kchrzanowski.userauth.model.Article

interface ArticleService {

    fun findAll(): List<ArticleResponse>
}