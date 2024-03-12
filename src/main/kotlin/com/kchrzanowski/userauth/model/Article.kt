package com.kchrzanowski.userauth.model

import com.kchrzanowski.userauth.controller.article.ArticleResponse
import java.util.*

data class Article(
        val id: UUID,
        val title: String,
        val content: String,
) {

}
