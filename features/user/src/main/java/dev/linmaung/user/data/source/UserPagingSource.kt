package dev.linmaung.user.data.source

import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.*
import androidx.paging.PagingState

import dev.linmaung.core.util.Result
import dev.linmaung.user.domain.repository.UserRepository
import dev.linmaung.user.domain.model.User
import jakarta.inject.Inject


class UserPagingSource @Inject constructor(
    private val userRepository: UserRepository
): PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val closestPage = state.closestPageToPosition(anchorPosition)
        return closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val key= params.key ?: 0
        val result= userRepository.getUsers(since= key)

        return when(result){
            is Result.Success -> {
                Page(
                    data = result.data,
                    prevKey = if(key==0) null else key-1,
                    nextKey = (result.data.lastOrNull()?.id ?:0) + 1
                )

            }
            is Result.Error-> {
                Error(result.exception)
            }
        }

    }
}