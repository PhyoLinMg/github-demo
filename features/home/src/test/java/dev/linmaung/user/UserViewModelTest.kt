package dev.linmaung.user

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.linmaung.core.domain.model.User
import dev.linmaung.user.data.source.SearchUsersPagingSource
import dev.linmaung.user.domain.repository.UserRepository
import dev.linmaung.user.presentation.UserViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkConstructor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class UserViewModelTest {

    private lateinit var userRepository: UserRepository
    private lateinit var viewModel: UserViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        userRepository = mockk()
        viewModel = UserViewModel(userRepository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `setSearchQuery updates searchQuery StateFlow`() = runTest {
        val query = "linmaung"
        viewModel.setSearchQuery(query)
        assertEquals(query, viewModel.searchQuery.first())
    }

    @Test
    fun `searchUsersPagingData emits paged data when searchQuery is set`() = runTest {
        // Create dummy user and paging source
        val dummyUser1 = User(id = 1,name="user 1", following = 0, followers = 0, avatarUrl = "", fullName = "")
        val dummyUser2= User(id = 2,name="user 2", following = 0, followers = 0, avatarUrl = "", fullName = "")

        val pagingSource = object : PagingSource<Int, User>() {
            override fun getRefreshKey(state: PagingState<Int, User>): Int? = null

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
                return LoadResult.Page(
                    data = listOf(dummyUser1,dummyUser2),
                    prevKey = null,
                    nextKey = null
                )
            }
        }

        mockkConstructor(SearchUsersPagingSource::class)
        coEvery { anyConstructed<SearchUsersPagingSource>().load(any()) } coAnswers {
            pagingSource.load(it.invocation.args[0] as PagingSource.LoadParams<Int>)
        }

        viewModel.setSearchQuery("user")
        val flow = viewModel.searchUsersPagingData

        val result = mutableListOf<PagingData<User>>()

        val job = launch {
            flow.collect {
                result.add(it)
            }
        }

        advanceUntilIdle()
        job.cancel()

        assertTrue(result.isNotEmpty())
    }
}
