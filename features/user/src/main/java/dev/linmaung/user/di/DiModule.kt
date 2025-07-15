package dev.linmaung.user.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.linmaung.user.data.repository.UserRepositoryImpl
import dev.linmaung.user.domain.repository.UserRepository


@Module
@InstallIn(SingletonComponent::class)
abstract class DiModule {
    @Binds
    internal abstract fun bindRepository(impl: UserRepositoryImpl): UserRepository

}