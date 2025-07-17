package dev.linmaung.repo.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.linmaung.repo.data.repository.UserProfileRepositoryImpl
import dev.linmaung.repo.domain.repository.UserProfileRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DiModule {
    @Binds
    internal abstract fun bindRepository(impl: UserProfileRepositoryImpl): UserProfileRepository
}