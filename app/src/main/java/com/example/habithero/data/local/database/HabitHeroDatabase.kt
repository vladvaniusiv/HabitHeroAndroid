package com.example.habithero.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.habithero.data.local.dao.HabitDao
import com.example.habithero.data.local.dao.HabitProgressDao
import com.example.habithero.data.local.dao.UserDao
import com.example.habithero.data.local.entity.HabitEntity
import com.example.habithero.data.local.entity.HabitProgressEntity
import com.example.habithero.data.local.entity.UserEntity

@Database(
    entities = [
        HabitEntity::class,
        HabitProgressEntity::class,
        UserEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class HabitHeroDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao
    abstract fun habitProgressDao(): HabitProgressDao
    abstract fun userDao(): UserDao
}