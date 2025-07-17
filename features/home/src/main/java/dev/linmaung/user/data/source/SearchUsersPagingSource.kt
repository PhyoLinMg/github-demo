package dev.linmaung.user.data.source


import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import dev.linmaung.core.util.Result
import dev.linmaung.core.domain.model.User
import dev.linmaung.user.domain.repository.UserRepository


class SearchUsersPagingSource(
    private val userRepository: UserRepository,
    private val query: String
): PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val closestPage = state.closestPageToPosition(anchorPosition)
        return closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val currentPage= params.key ?: 1
        val result= userRepository.searchUser(query, page = currentPage)
        return when(result){
            is Result.Success -> {
                Page(
                    data = result.data,
                    prevKey = if(currentPage == 1) null else currentPage-1,
                    nextKey = if(result.data.isEmpty()) null else currentPage+1
                )
            }
            is Result.Error-> {
                Error(result.exception)
            }
        }
    }

}