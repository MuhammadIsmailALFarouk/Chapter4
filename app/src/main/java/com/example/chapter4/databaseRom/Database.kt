package com.example.chapter4.databaseRom

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chapter4.cart.Cart


@androidx.room.Database(entities = [Cart :: class], version = 2)
abstract class Database : RoomDatabase(){

    abstract val modalDataDao: ModalDataDao
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