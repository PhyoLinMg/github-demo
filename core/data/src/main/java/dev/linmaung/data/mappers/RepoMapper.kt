package dev.linmaung.data.mappers

import dev.linmaung.core.domain.model.GithubRepo
import dev.linmaung.data.dto.RepoItemDto

fun RepoItemDto.toGithubRepo() : GithubRepo{
    return GithubRepo(
        name= this.name?:"",
        language= this.language?:"None",
        starCount= this.stargazersCount?:0,
        description= this.description ?:"",
        repoUrl = this.htmlUrl
    )
}