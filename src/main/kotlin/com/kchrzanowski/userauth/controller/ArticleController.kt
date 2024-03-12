package com.kchrzanowski.userauth.controller

import com.kchrzanowski.userauth.controller.article.ArticleResponse
import com.kchrzanowski.userauth.service.ArticleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/article")
class ArticleController(
        private val articleService: ArticleService
) {

        @GetMapping("/all")
        fun listAll(): List<ArticleResponse> {
            return articleService.findAll()
        }

}