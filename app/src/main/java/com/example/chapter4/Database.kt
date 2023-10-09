package com.example.chapter4

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


@androidx.room.Database(entities = [Cart :: class], version = 1)
abstract class Database : RoomDatabase(){

    abstract val modalDataDao:ModalDataDao
    companion object{
        @Volatile
        private var INSTANCE : Database?=null

        fun getInstance(context: Context): Database {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java,
                        "database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}