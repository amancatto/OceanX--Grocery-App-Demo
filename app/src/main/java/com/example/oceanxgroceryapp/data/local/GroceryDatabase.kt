package com.example.oceanxgroceryapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.oceanxgroceryapp.data.model.CartItemEntity

/**
 * Singleton Room database for the app.
 *
 * Increment [version] and provide a [Migration] if you change the schema
 * in a future prompt/step to avoid data loss on upgrade.
 */
@Database(
    entities = [CartItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GroceryDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao

    companion object {
        private const val DB_NAME = "grocery_database"

        // Volatile ensures writes are immediately visible to other threads.
        @Volatile
        private var INSTANCE: GroceryDatabase? = null

        fun getInstance(context: Context): GroceryDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    GroceryDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration() // safe during development; add migrations before release
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
