package com.example.todotry.Activity

import android.app.Application
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.todotry.OBJ.DataBaseOBJ
import com.example.todotry.OBJ.DateMonth
import com.example.todotry.OBJ.Time
import com.example.todotry.OBJ.TitleDescription
import com.example.todotry.R
import com.example.todotry.Room.TODOTaskTable
import com.example.todotry.Room.TODOdatabase
import com.example.todotry.databinding.ActivityAddTaskinDbBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTaskinDb : AppCompatActivity() {
    lateinit var binding: ActivityAddTaskinDbBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskinDbBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mainactivity = Intent(this, MainActivity::class.java)
        val selece = binding.textView
       // DataBaseOBJ.initialize(applicationContext)

        registerForContextMenu(selece)
        binding.datetimecontainerlinearlayout.setOnClickListener {
            binding.datepickercad.visibility = View.VISIBLE
        }
        binding.setdatebutn.setOnClickListener {
            binding.datepickercad.visibility = View.GONE
        }
        binding.datepicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            DateMonth.date = dayOfMonth
            DateMonth.month = monthOfYear
            DateMonth.year = year

        }
        binding.settime.setOnTimeChangedListener { view, hourOfDay, minute ->
            Time.min = minute
            Time.houre = hourOfDay
        }

        binding.taskaddindb.setOnClickListener {
            startActivity(mainactivity)
        }
        binding.taskaddindb.setOnClickListener {
            val description = binding.description.text.toString()
            val time = "${Time.min}:${Time.houre}"
            val date = "${DateMonth.date}/${DateMonth.month}/${DateMonth.year}"


            Toast.makeText(this, "$description $time $date", Toast.LENGTH_LONG).show()
            try {
                GlobalScope.launch {
                    DataBaseOBJ.database.gettoDAO().insertiask(TODOTaskTable( 0,TitleDescription.Title, description, TitleDescription.image, time, date))
                }
            } catch (E: Exception) {
                Toast.makeText(this, "$E", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.select, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        binding.textView.text = item.title
        TitleDescription.Title = item.title.toString()
        return when(item.itemId)
        {
            R.id.Groceryshopping ->{
                TitleDescription.image=R.drawable.p3
                true
            }
            R.id.Laundry ->{
                TitleDescription.image=R.drawable.p2
                true
            }
            R.id.Exercise ->{
                TitleDescription.image=R.drawable.p5
                true
            }
            R.id.Reading ->{
                TitleDescription.image=R.drawable.p11
                true
            }
            R.id.Projectdeadlines ->{
                TitleDescription.image=R.drawable.p9
                true
            }
            R.id.Meetings ->{
                TitleDescription.image=R.drawable.p13
                true
            }
            R.id.Homeworkassignments ->{
                TitleDescription.image=R.drawable.p11
                true
            } R.id.Exampreparation ->{
                TitleDescription.image=R.drawable.p9
                true
            }
            R.id.Doctorappointments ->{
                TitleDescription.image=R.drawable.p9
                true
            }
            R.id.Billpayments ->{
                TitleDescription.image=R.drawable.p4
                true
            }
            R.id.Budgeting ->{
                TitleDescription.image=R.drawable.p4
                true
            }
            R.id.Familyevents->{
                TitleDescription.image=R.drawable.p14
                true
            }
            R.id.Anniversaries ->{
                TitleDescription.image=R.drawable.p14
                true
            }
            R.id.Repairs ->{
                TitleDescription.image=R.drawable.p6
                true
            }
            R.id.Eventplanning->{
                TitleDescription.image=R.drawable.p4
                true
            }
            else ->super.onContextItemSelected(item)
        }


    }
}