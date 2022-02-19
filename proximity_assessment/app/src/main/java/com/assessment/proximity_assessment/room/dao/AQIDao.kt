package com.assessment.proximity_assessment.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assessment.proximity_assessment.room.entity.AQI

@Dao
interface AQIDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityAQI(aqi: AQI)

    @Query("SELECT * FROM aqi")
    suspend fun getAllCityList(): List<AQI>

    @Query("SELECT * FROM aqi WHERE city LIKE :city ORDER BY time DESC limit 9")
    suspend fun getCityAQIByCityName(city:String): List<AQI>

    @Query("SELECT EXISTS(SELECT * FROM aqi WHERE city LIKE :city)")
    fun hasCityExist(city: String): Boolean

    @Query("DELETE FROM aqi WHERE city LIKE :city")
    fun deleteCity(city: String)

    @Query("Select * from aqi WHERE city LIKE :city ORDER BY aqi DESC limit 1 ")
    fun getHighestValue(city: String): AQI
}