package com.example.tutorial5.Database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tutorial5.Database.entities.Todo

@Dao
interface todoDao {

    @Insert
     suspend fun  insert(todo: Todo)

     @Delete
      suspend fun delete(todo: Todo)

    @Query("SELECT * From Todo")
    fun getAllTodos(): List<Todo>


}