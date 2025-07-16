package dev.linmaung.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.linmaung.core.domain.model.GithubRepo
import dev.linmaung.core.domain.repository.GithubRepoRepository
import dev.linmaung.core.util.Result

class GithubRepoDataSource(
    private val githubRepoRepository: GithubRepoRepository,
    private val userName: String,
): PagingSource<Int, GithubRepo>() {
    override fun getRefreshKey(state: PagingState<Int, GithubRepo>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val closestPage = state.closestPageToPosition(anchorPosition)
        return closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepo> {
       val currentPage= params.key ?: 1
        val result= githubRepoRepository.getRepos(userName, page= currentPage)
        return when(result){
            is Result.Error -> LoadResult.Error(result.exception)
            is Result.Success -> LoadResult.Page(
                data= result.data,
                prevKey = if(currentPage == 1) null else currentPage-1,
                nextKey = if(result.data.isEmpty()) null else currentPage+1
            )
        }
    }
}