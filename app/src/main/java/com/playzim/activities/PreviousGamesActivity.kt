package com.playzim.activities

import com.playzim.adapters.CustomAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.playzim.activities.placar.R
import com.playzim.previousgames.PreviousGame


class PreviousGamesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_games)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.rcPreviousGames)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<PreviousGame>()

        // This loop will create 20 Views containing
        // the image with the count of view
        //for (i in 1..7) {
            data.add(PreviousGame("Semi-final: Trinta vs Windson ","2 x 0",true))
        data.add(PreviousGame("Final: Trinta vs Windson ","2 x 0",true))
        data.add(PreviousGame("Racha: Trinta vs Windson ","2 x 0",true))
        data.add(PreviousGame("Racha BT: Trinta vs Windson ","2 x 0",true))
        data.add(PreviousGame("Racha BT: Trinta vs Windson ","2 x 0",true))
        //}

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }
}
