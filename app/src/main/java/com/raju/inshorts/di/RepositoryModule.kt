package com.raju.inshorts.di

import androidx.paging.ExperimentalPagingApi

import com.raju.inshorts.data.repository.MoviesRepositoryImpl
import com.raju.inshorts.data.source.local.MovieDatabase
import com.raju.inshorts.data.source.remote.MoviesApi
import com.raju.inshorts.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApi: MoviesApi,
        movieDatabase: MovieDatabase
    ): MoviesRepository {
        return MoviesRepositoryImpl(movieApi, movieDatabase)
    }

}