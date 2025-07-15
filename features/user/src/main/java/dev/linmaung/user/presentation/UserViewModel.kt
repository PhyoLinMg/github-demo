package dev.linmaung.user.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.linmaung.user.data.source.UserPagingSource
import dev.linmaung.user.domain.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userPagingSource: UserPagingSource
): ViewModel() {

    init {
        init()
    }

    fun init(){
        viewModelScope.launch {
            userRepository.getUsers(1)
        }
    }
}