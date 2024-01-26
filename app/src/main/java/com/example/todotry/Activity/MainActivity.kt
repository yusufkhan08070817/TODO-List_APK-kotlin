package com.example.todotry.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todotry.Adopter.CalanderAdopter
import com.example.todotry.Adopter.TaskAdopter
import com.example.todotry.Dataclass.DateAndDay
import com.example.todotry.OBJ.DataBaseOBJ
import com.example.todotry.Room.Second
import com.example.todotry.Room.TODOTaskTable
import com.example.todotry.Room.notcompletetable
import com.example.todotry.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainActivity : AppCompatActivity(), CalanderAdopter.clenderclick, TaskAdopter.taskbutton {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DataBaseOBJ.initialize(applicationContext)// initialization of db

        val addtask = Intent(this, AddTaskinDb::class.java)
        binding.addcard.setOnClickListener {
            startActivity(addtask)//start addtask activity
        }



        val recycler=binding.daysandweek//recycler view clander
        val dbshow=binding.dbshow//recycler view task

        recycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)// horizantal
        dbshow.layoutManager=LinearLayoutManager(this)//vetical

        DataBaseOBJ.database.gettoDAO().getdata().observe(this, Observer {
            dbshow.adapter=TaskAdopter(it,this)//update teask recycler
        })

// geting date and time
        val currentDate = LocalDate.now()


        // Extract day, month, and year components
        val day = currentDate.dayOfMonth
        val month = currentDate.monthValue.toString()
        val year = currentDate.year
        binding.numberdate.text=currentDate.dayOfMonth.toString()
        binding.weekdays.text=currentDate.dayOfWeek.toString()
        binding.monthyear.text="${month},$year"
        val firstDayOfMonth = currentDate.withDayOfMonth(1)

        val lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth())
        val dateListWithWeekNames = generateDateListWithWeekNames(firstDayOfMonth, lastDayOfMonth)
        recycler.adapter= CalanderAdopter(dateListWithWeekNames,this)//setting date and time in rv


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateDateListWithWeekNames(startDate: LocalDate, endDate: LocalDate): List<DateAndDay> {
        val dateList = mutableListOf<DateAndDay>()
        var currentDate = startDate

        while (!currentDate.isAfter(endDate)) {
            val weekName = currentDate.dayOfWeek.toString()
            dateList.add(DateAndDay(currentDate, weekName))
            currentDate = currentDate.plusDays(1)
        }


        return dateList
    }



    override fun taskcomplete(position: Int, data: TODOTaskTable) {
        GlobalScope.launch {
            try{
                DataBaseOBJ.database.fetseconddao().insert(Second(data.id,data.title,data.descripatation,data.icon,data.time,data.Date))
                Log.e("data","${DataBaseOBJ.database.fetseconddao().getdata()}")
               deletetask(data)
            }catch (e:Exception)
            {
               Log.e("dbeeror","$e")
            }

        }
t("good job")
    }

    override fun taskdelete(position: Int, data: TODOTaskTable) {
   deletetask(data)
        t("task deleted")
    }

    override fun tasknotcomplete(position: Int, data: TODOTaskTable) {
        GlobalScope.launch {
            try{
                DataBaseOBJ.database.notcompletedao().insertiask(notcompletetable(data.id,data.title,data.descripatation,data.icon,data.time,data.Date))
                Log.e("data not completed","${DataBaseOBJ.database.fetseconddao().getdata()}")
                deletetask(data)
            }catch (e:Exception)
            {
                Log.e("dbeeror","$e")
            }
        }
        t(":( ok try next time")

    }

    override fun onclienderclick(position: Int, date: String, day: String, view: View) {
        Toast.makeText(this, "$date ", Toast.LENGTH_SHORT).show()
DataBaseOBJ.database.gettoDAO().getRecordsByDate(date).observe(this, Observer {
    binding.dbshow.adapter=TaskAdopter(it,this)//update teask recycler
    t(it)
})

    }

fun t(e:Any)
{
    Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
}
    fun deletetask(data:TODOTaskTable){
        GlobalScope.launch {
            DataBaseOBJ.database.gettoDAO().deletetiask(data)
        }
    }


}