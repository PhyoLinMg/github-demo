import dev.linmaung.data.dto.UserDto
import dev.linmaung.user.data.dto.SearchUserDto
import dev.linmaung.user.data.repository.UserRepositoryImpl
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import dev.linmaung.core.util.Result
import dev.linmaung.user.data.source.UserRemoteDataSource
import dev.linmaung.user.domain.repository.UserRepository


class UserRepositoryImplTest {

    private lateinit var remoteDataSource: UserRemoteDataSource
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        remoteDataSource = mockk()
        userRepository = UserRepositoryImpl(remoteDataSource)
    }



    @Test
    fun getUsersReturnsSuccessWhenHttpCallSucceeds() = runTest {
        // Given
        val since = 10
        val dtoList = listOf(UserDto(id = 1))
        coEvery { remoteDataSource.getUsers(since) } returns dtoList

        val result = userRepository.getUsers(since)

        assertTrue(result is Result.Success)
    }

    @Test
    fun getUsersReturnsErrorWhenHttpCallThrowsException() = runTest {
        // Given
        val since = 10
        val exception = RuntimeException("Network error")

        coEvery { remoteDataSource.getUsers(since) } throws exception

        // When
        val result = userRepository.getUsers(since)

        // Then
        assertTrue(result is Result.Error)
        assertEquals(exception, result.exception)
    }

    @Test
    fun searchUserReturnsSuccessWhenHttpCallSucceeds() = runTest {
        // Given
        val query = "john"
        val perPage = 15
        val page = 2
        val dtoList = listOf(UserDto(id = 1), UserDto(id = 2), UserDto(id = 3))

        val searchDto = SearchUserDto(items = dtoList)

        coEvery { remoteDataSource.searchUsers(query, perPage, page) } returns searchDto


        // When
        val result = userRepository.searchUser(query, perPage, page)

        // Then
        assertTrue(result is Result.Success)
        assertEquals(3, result.data.size)

    }


    @Test
    fun searchUserReturnsErrorWhenHttpCallThrowsException() = runTest {
        // Given
        val query = "test"
        val perPage = 10
        val page = 1
        val exception = RuntimeException("Search failed")

        coEvery { remoteDataSource.searchUsers(query, perPage, page) } throws exception

        // When
        val result = userRepository.searchUser(query, perPage, page)

        // Then
        assertTrue(result is Result.Error)
        assertEquals(exception, result.exception)

    }

    @Test
    fun getProfileReturnsSuccessWhenHttpCallSucceeds() = runTest {
        // Given

        val userDto = UserDto(id = 1)
        coEvery { remoteDataSource.getProfile() } returns userDto

        // When
        val result = userRepository.getProfile()

        // Then
        assertTrue(result is Result.Success)

        assertEquals(1, result.data.id)
    }

    @Test
    fun getProfileReturnsErrorWhenHttpCallThrowsException() = runTest {
        // Given
        val exception = RuntimeException("Profile fetch failed")

        coEvery { remoteDataSource.getProfile() } throws exception

        // When
        val result = userRepository.getProfile()

        // Then
        assertTrue(result is Result.Error)
        assertEquals(exception, result.exception)
    }

}