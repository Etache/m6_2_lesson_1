package com.example.m6_2_lesson_1.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m6_2_lesson_1.TaskAdapter
import com.example.m6_2_lesson_1.TaskModel
import com.example.m6_2_lesson_1.TaskViewModel
import com.example.m6_2_lesson_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    companion object {
        private const val REQUEST_CODE_ADD_TASK = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        taskAdapter = TaskAdapter(emptyList(), this::onLongClik) { position, isChecked ->
            viewModel.markTaskAsDone(position)
        }

        binding.recycleView.apply { 
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }

        viewModel.taskList.observe(this) { taskList ->
            if (taskList != null) {
                taskAdapter.taskList = taskList
            }
            binding.recycleView.post {
                taskAdapter.notifyDataSetChanged()
            }
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_TASK)
        }
    }

    private fun onLongClik(task: TaskModel){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Вы точно хотите удалить ?")
        alertDialog.setNegativeButton("Нет", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.cancel()
            }
        })

        alertDialog.setPositiveButton("Да", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, with: Int) {
                viewModel.deleteTask(0)
            }
        })
        alertDialog.create().show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_TASK && resultCode == Activity.RESULT_OK) {
            val taskTitle = data?.getStringExtra("title")
            if (!taskTitle.isNullOrEmpty()) {
                viewModel.addTask(taskTitle)
            }
        }
    }
}