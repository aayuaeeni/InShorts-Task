package com.raju.inshorts.di
import com.raju.inshorts.domain.usecase.GetMovieCast
import com.raju.inshorts.domain.repository.MoviesRepository
import com.raju.inshorts.domain.usecase.GetFavoriteMovies
import com.raju.inshorts.domain.usecase.GetMoviesByCategory
import com.raju.inshorts.domain.usecase.GetTrendingMovies
import com.raju.inshorts.domain.usecase.MovieUseCase
import com.raju.inshorts.domain.usecase.SearchMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCase(
        moviesRepository: MoviesRepository,
    ): MovieUseCase {
        return MovieUseCase(
            getTrendingMovies = GetTrendingMovies(moviesRepository),
            getMoviesByCategory = GetMoviesByCategory(moviesRepository),
            getMovieCast = GetMovieCast(moviesRepository),
            searchMovies = SearchMovies(moviesRepository),
            getFavoriteMovies = GetFavoriteMovies(moviesRepository),
        )
    }

}