package com.example.tutorial5.Database.repositories

import com.example.tutorial5.Database.TodoDatabase
import com.example.tutorial5.Database.entities.Todo

class TodoRepository(private val db:TodoDatabase){

    //these are return statement

    suspend fun insert(todo: Todo) =db.getTododao().insert(todo)
    suspend fun delete(todo: Todo) = db.getTododao().delete(todo)
    fun getAllTodos() = db.getTododao().getAllTodos()


}