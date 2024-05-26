package com.example.clickgame2.adapter.level

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.clickgame2.R

class LevelAdapter(val levels :List<String>, val onItemClick: OnItemClick)
    : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        val levelViewHolder = LevelViewHolder(view,onItemClick)
        return levelViewHolder

    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        var level:String = levels.get(position)
        holder.bind(level)
    }

    override fun getItemCount(): Int {
        return levels.size
    }

    class LevelViewHolder(itemView: View , val onItemClick: OnItemClick)
        :RecyclerView.ViewHolder(itemView){

        val btn:Button = itemView.findViewById(R.id.levelButton)

        fun bind(level:String){
            btn.text = level

            btn.setOnClickListener(){
                onItemClick.click(level)
            }
        }
    }





}