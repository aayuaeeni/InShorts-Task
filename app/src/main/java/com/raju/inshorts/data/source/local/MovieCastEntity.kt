package com.raju.inshorts.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raju.inshorts.utils.Constant.MOVIES_CAST_TABLE

@Entity(tableName = MOVIES_CAST_TABLE)
data class MovieCastEntity(
    @PrimaryKey
    val id: Int,
    val movieId: Int,

    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String,
)