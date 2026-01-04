package com.raju.inshorts.di

import android.app.Application
import androidx.room.Room
import com.raju.inshorts.data.source.local.MovieDao
import com.raju.inshorts.data.source.local.MovieDatabase
import com.raju.inshorts.utils.Constant.MOVIES_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(
        application: Application,
    ): MovieDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = MovieDatabase::class.java,
            name = MOVIES_DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideMovieDao(
        movieDatabase: MovieDatabase,
    ): MovieDao = movieDatabase.movieDao

}