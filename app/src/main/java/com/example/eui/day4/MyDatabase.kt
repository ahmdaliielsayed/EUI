package com.example.eui.day4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class MyDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {

            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    "products_database"
                )
                    .build()
                    .also { instance = it }
            }
        }
    }
}