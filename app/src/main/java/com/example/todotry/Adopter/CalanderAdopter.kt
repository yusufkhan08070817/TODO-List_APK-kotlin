package com.example.todotry.Adopter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.todotry.Dataclass.DateAndDay
import com.example.todotry.R
import com.example.todotry.Room.TODOTaskTable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CalanderAdopter(val data:List<DateAndDay>, val toaccess:clenderclick):RecyclerView.Adapter<CalanderAdopter.viewHolde>() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolde {
      val views=LayoutInflater.from(parent.context).inflate(R.layout.clanderrec,parent,false)
        return viewHolde(views)
    }

    override fun getItemCount(): Int {
      return data.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: viewHolde, position: Int) {
      val current=data[position]
        holder.weekname.text=current.weekName.substring(0, 3)
        val date = LocalDate.parse(current.date.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        // Format the LocalDate to get the day part
        val dayPart = date.format(DateTimeFormatter.ofPattern("dd"))
        holder.clanderrecyclerdate.text=dayPart.toString()


    }
    @RequiresApi(Build.VERSION_CODES.O)
    inner class viewHolde(view:View):RecyclerView.ViewHolder(view){
      val clenderchunk:RelativeLayout=view.findViewById(R.id.clenderchunk)
        val weekname:TextView=view.findViewById(R.id.weekname)
        val clanderrecyclerdate:TextView=view.findViewById(R.id.clanderrecyclerdate)
        init {
            clanderrecyclerdate.setOnClickListener {
                var date=data[adapterPosition].date
                toaccess.onclienderclick(adapterPosition,convertDateFormat(date.toString()),data[adapterPosition].weekName,clanderrecyclerdate)
            }
        }
    }
    interface clenderclick{
        fun onclienderclick(position: Int,date:String,day:String,view:View)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDateFormat(inputDate: String): String {
        // Define input and output date formats using DateTimeFormatter
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy")

        try {
            // Parse the input date string using LocalDate
            val date = LocalDate.parse(inputDate, inputFormatter)

            // Format the parsed date to the desired output format
            return outputFormatter.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return "" // Return empty string if conversion fails
    }
}