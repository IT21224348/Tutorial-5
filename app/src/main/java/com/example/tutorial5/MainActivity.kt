package com.example.tutorial5

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorial5.Adapters.TodoAdapter
import com.example.tutorial5.Database.TodoDatabase
import com.example.tutorial5.Database.entities.Todo
import com.example.tutorial5.Database.repositories.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val repository = TodoRepository(TodoDatabase.getInstannce(this))
        val recyclerview:RecyclerView=findViewById(R.id.rvTodoList)
        val ui = this
        val adapter = TodoAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(ui)

        val btnAddTodo = findViewById<Button>(R.id.btnAddTodo)
        btnAddTodo.setOnClickListener(){

            DisplayDialog(repository,adapter)

        }


        CoroutineScope(Dispatchers.IO).launch{

             val data = repository.getAllTodos()
             adapter.setData(data,ui)

             }
        }

    fun DisplayDialog(repository: TodoRepository,adapter: TodoAdapter) {

        //create a object AlertDialog.Builder

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Enter new Todo Item")
        builder.setMessage("Enter Todo Items below")

        //create Edittext input Field

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)


        builder.setPositiveButton("Ok") { Dialog, which ->

            val item = input.text.toString()
            CoroutineScope(Dispatchers.IO).launch{

                repository.insert(Todo(item) )
                val data= repository.getAllTodos()
                runOnUiThread{

                    adapter.setData(data,this@MainActivity)

                }

            }



        }


        builder.setNegativeButton("Cancle"){Dialog,which->

            Dialog.cancel()
        }

        // Create and show the alert dialog
        val alertDialog = builder.create()
        alertDialog.show()



    }




}