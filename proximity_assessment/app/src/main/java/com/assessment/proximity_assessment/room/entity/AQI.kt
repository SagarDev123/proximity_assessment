package com.assessment.proximity_assessment.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aqi")
data class AQI(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "aqi") var aqi: Double,
    @ColumnInfo(name = "time") var time: Long,
    @ColumnInfo(name = "city") var city: String,
)