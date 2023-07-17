package com.example.tutorial5.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tutorial5.Database.daos.todoDao
import com.example.tutorial5.Database.entities.Todo
import java.security.AccessControlContext


@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase:RoomDatabase() {

       abstract  fun  getTododao(): todoDao

       companion object {
           @Volatile
           private var INSTANCE: TodoDatabase? = null
           fun getInstannce(context: Context): TodoDatabase {

               synchronized(this) {
                   return INSTANCE ?: Room.databaseBuilder(
                       context.applicationContext,
                       TodoDatabase::class.java,
                       "todo_db"

                   ).build().also {

                       INSTANCE = it


                   }

               }


           }


       }

}