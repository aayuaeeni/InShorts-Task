package com.raju.inshorts.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.withTransaction
import com.raju.inshorts.data.mapper.toFavoriteMovieEntity
import com.raju.inshorts.data.mapper.toMovie
import com.raju.inshorts.data.mapper.toMovieCastEntity
import com.raju.inshorts.data.mapper.toMovieEntity
import com.raju.inshorts.data.paging.DiscoverMoviesRemoteMediator
import com.raju.inshorts.data.paging.MovieSearchPagingSource
import com.raju.inshorts.data.paging.NowPlayingMoviesRemoteMediator
import com.raju.inshorts.data.paging.PopularMoviesRemoteMediator
import com.raju.inshorts.data.paging.TopRatedMoviesRemoteMediator
import com.raju.inshorts.data.paging.UpcomingMoviesRemoteMediator
import com.raju.inshorts.data.source.local.FavoriteMovieEntity
import com.raju.inshorts.data.source.local.MovieCastEntity
import com.raju.inshorts.data.source.local.MovieDatabase
import com.raju.inshorts.data.source.local.MovieEntity
import com.raju.inshorts.data.source.remote.MoviesApi
import com.raju.inshorts.data.source.remote.dto.movie.MovieDto
import com.raju.inshorts.domain.model.Movie
import com.raju.inshorts.domain.repository.MoviesRepository
import com.raju.inshorts.utils.Constant.DISCOVER
import com.raju.inshorts.utils.Constant.NOW_PLAYING
import com.raju.inshorts.utils.Constant.POPULAR
import com.raju.inshorts.utils.Constant.TOP_RATED
import com.raju.inshorts.utils.Constant.TRENDING
import com.raju.inshorts.utils.Constant.UPCOMING

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow

@ExperimentalPagingApi
class MoviesRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val movieDatabase: MovieDatabase,
) : MoviesRepository {

    override fun getTrendingMovies(): Flow<List<MovieEntity>> = channelFlow {
        val trendingMovies = moviesApi.getTrendingMovies(1).results

        movieDatabase.withTransaction {

            val listMovieEntity = trendingMovies.map { movieDto ->
                movieDto.toMovieEntity(TRENDING)
            }

            // insert trending movies to database
            movieDatabase.movieDao.upsertMoviesList(movies = listMovieEntity)

            val listTrendingMovies = movieDatabase.movieDao.getTrendingMovies()

            send(listTrendingMovies)
        }
    }


    override fun getMoviesListByCategory(
        category: String,
    ): Flow<PagingData<MovieEntity>> {
        val pagingSourceFactory = { movieDatabase.movieDao.getMoviesListByCategory(category) }

        val mediator = when (category) {
            POPULAR -> PopularMoviesRemoteMediator(moviesApi, movieDatabase)
            TOP_RATED -> TopRatedMoviesRemoteMediator(moviesApi, movieDatabase)
            UPCOMING -> UpcomingMoviesRemoteMediator(moviesApi, movieDatabase)
            DISCOVER -> DiscoverMoviesRemoteMediator(moviesApi, movieDatabase)
            NOW_PLAYING -> NowPlayingMoviesRemoteMediator(moviesApi, movieDatabase)
            else -> throw IllegalArgumentException("Unknown category: $category")
        }

        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = mediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }


    override fun getMovieCast(movieId: Int): Flow<List<MovieCastEntity>> = channelFlow {
        val movieCast = moviesApi.getMovieCast(movieId).movieCastDto

        movieDatabase.withTransaction {

            val listMovieCastEntity = movieCast.map { movieCastDto ->
                movieCastDto.toMovieCastEntity(movieId)
            }

            // insert movies cast to database
            movieDatabase.movieDao.upsertMovieCastList(movieCast = listMovieCastEntity)

            val listMovieCast = movieDatabase.movieDao.getMovieCastById(movieId)

            send(listMovieCast)
        }
    }


    override fun searchMovies(query: String): Flow<PagingData<MovieDto>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MovieSearchPagingSource(moviesApi, query)
            }
        ).flow
    }

    override suspend fun addFavoriteMovie(movie: Movie) {
        movieDatabase.movieDao.upsertFavoriteMovie(movie.toFavoriteMovieEntity())
    }

    override suspend fun getFavoriteMovieById(movieId: Int): Movie? {
        return movieDatabase.movieDao.getFavoriteMovieById(movieId)?.toMovie()
    }

    override suspend fun deleteFavoriteMovie(movieId: Int) {
        movieDatabase.movieDao.deleteFavoriteMovie(movieId)
    }

    override fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return flow {
            emit(movieDatabase.movieDao.getAllFavoriteMovies())
        }
    }

}
