package dev.linmaung.user.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.linmaung.user.data.source.AllUsersPagingSource
import dev.linmaung.user.data.source.SearchUsersPagingSource
import dev.linmaung.user.domain.model.User
import dev.linmaung.user.domain.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest


@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
): ViewModel() {

    private val _searchQuery= MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery


    fun setSearchQuery(query: String){
        _searchQuery.value = query
    }


    val allUsersPagingData: Flow<PagingData<User>> = Pager(
        config= PagingConfig(
            pageSize = 20,
            enablePlaceholders = true
        ),
        pagingSourceFactory = { AllUsersPagingSource(userRepository)}
    ).flow.cachedIn(viewModelScope)

    @OptIn(FlowPreview::class)
    val searchUsersPagingData: Flow<PagingData<User>> =_searchQuery
        .debounce(300)
        .filter { it.isNotBlank() }
        .flatMapLatest { query ->
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = true
                ),
                pagingSourceFactory = { SearchUsersPagingSource(userRepository, query) }
            ).flow
        }
        .cachedIn(viewModelScope)
}