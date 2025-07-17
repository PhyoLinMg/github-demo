package dev.linmaung.core.domain.model

data class GithubRepo(
    val name: String,
    val language:String,
    val starCount: Int,
    val description: String?= "",
    val repoUrl: String="https://www.webtoon.xyz/"
)