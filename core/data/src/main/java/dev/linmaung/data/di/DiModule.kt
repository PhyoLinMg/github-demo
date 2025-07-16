package dev.linmaung.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.linmaung.core.domain.repository.GithubRepoRepository
import dev.linmaung.data.repository.GithubRepoRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DiModule {
    @Binds
    internal abstract fun bindRepository(impl: GithubRepoRepositoryImpl): GithubRepoRepository

}