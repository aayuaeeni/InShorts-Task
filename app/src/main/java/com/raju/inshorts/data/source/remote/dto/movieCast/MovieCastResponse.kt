package com.raju.inshorts.data.source.remote.dto.movieCast

import com.google.gson.annotations.SerializedName

data class MovieCastResponse(
    @SerializedName("cast")
    val movieCastDto: List<MovieCastDto>,
    @SerializedName("crew")
    val movieCrewDto: List<MovieCrewDto>,
    @SerializedName("id")
    val movieId: Int,
)