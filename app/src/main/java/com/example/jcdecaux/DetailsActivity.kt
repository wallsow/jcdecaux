package com.example.jcdecaux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    lateinit var markerTitle : TextView
    lateinit var statusTextView: TextView
    lateinit var addressTextView: TextView
    lateinit var bikesTextView: TextView
    lateinit var standsTextView: TextView
    lateinit var mechanicalBikesTextView: TextView
    lateinit var electricalBikesTextView: TextView
    lateinit var electricalInternalBatteryBikesTextView: TextView
    lateinit var electricalRemovableBatteryBikesTextView: TextView
    lateinit var capacityTextView: TextView
    lateinit var lastUpdateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val title = intent.getStringExtra("title")
        markerTitle = findViewById(R.id.markerTitle)
        markerTitle.text = title

        val status = intent.getStringExtra("status")
        statusTextView = findViewById(R.id.status)
        statusTextView.text = "status: $status"

        val address = intent.getStringExtra("address")
        addressTextView = findViewById(R.id.address)
        addressTextView.text = "address: $address"

        val bikes = intent.getStringExtra("bikes")
        bikesTextView = findViewById(R.id.bikes)
        bikesTextView.text = "bikes: $bikes"

        val stands = intent.getStringExtra("stands")
        standsTextView = findViewById(R.id.stands)
        standsTextView.text = "stands: $stands"

        val mechanicalBikes = intent.getStringExtra("mechanicalBikes")
        mechanicalBikesTextView = findViewById(R.id.mechanicalBikes)
        mechanicalBikesTextView.text = "mechanicalBikes: $mechanicalBikes"

        val electricalBikes = intent.getStringExtra("electricalBikes")
        electricalBikesTextView = findViewById(R.id.electricalBikes)
        electricalBikesTextView.text = "electricalBikes: $electricalBikes"

        val electricalInternalBatteryBikes = intent.getStringExtra("electricalInternalBatteryBikes")
        electricalInternalBatteryBikesTextView = findViewById(R.id.electricalInternalBatteryBikes)
        electricalInternalBatteryBikesTextView.text = "electricalInternalBatteryBikes: $electricalInternalBatteryBikes"

        val electricalRemovableBatteryBikes = intent.getStringExtra("electricalRemovableBatteryBikes")
        electricalRemovableBatteryBikesTextView = findViewById(R.id.electricalRemovableBatteryBikes)
        electricalRemovableBatteryBikesTextView.text = "electricalRemovableBatteryBikes: $electricalRemovableBatteryBikes"

        val capacity = intent.getStringExtra("capacity")
        capacityTextView = findViewById(R.id.capacity)
        capacityTextView.text = "capacity: $capacity"

        val lastUpdate = intent.getStringExtra("lastUpdate")
        lastUpdateTextView = findViewById(R.id.lastUpdate)
        lastUpdateTextView.text = "lastUpdate: $lastUpdate"

    }
}

