package com.example.clickgame2.adapter.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clickgame2.R
import com.example.clickgame2.entity.Weapon
import com.example.clickgame2.service.setWeaponImage

class ShopAdapter(var weapons: List<Weapon>, val click: OnItemClickWeapon) : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {


    class ShopViewHolder(itemView: View,val onItemClickWeapon: OnItemClickWeapon) : RecyclerView.ViewHolder(itemView) {
        val btnBuy: Button = itemView.findViewById(R.id.btnBuy)
        val attackTxt: TextView = itemView.findViewById(R.id.attackTxt)
        val priceTxt: TextView = itemView.findViewById(R.id.priceTxt)
        val nameWeapone: TextView = itemView.findViewById(R.id.name)
        var image: ImageView = itemView.findViewById(R.id.image)


        fun bind(weapon: Weapon) {
            if (weapon.isPay){
                btnBuy.isClickable = false
                btnBuy.text = "Купленно"
            }
            attackTxt.text = weapon.power.toString()
            priceTxt.text = weapon.price.toString()
            nameWeapone.text = weapon.name.toString()
            setWeaponImage(weapon,image)
            //на полученных данных собрать объект weapone

            //получить баланс юзера если его хватает то срабатывает метод покупки
            btnBuy.setOnClickListener(){
                onItemClickWeapon.click(weapon.id)

            }
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weapone, parent, false)
        val shopViewHolder = ShopViewHolder(view,click)
        return shopViewHolder
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        var weapon: Weapon = weapons.get(position)
        holder.bind(weapon)
    }

    override fun getItemCount(): Int {
        return weapons.size
    }
}