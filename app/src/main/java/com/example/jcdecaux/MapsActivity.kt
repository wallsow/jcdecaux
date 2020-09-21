package com.example.jcdecaux

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jcdecaux.controllers.api.StationsService
import com.example.jcdecaux.models.Station
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    val url = "https://api.jcdecaux.com/vls/v3/"
    var listCergyStations: List<Station> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        val service = retrofit.create(StationsService::class.java)

        val stationsRequest =
            service.getCergyStations("cergy-pontoise", "7f81362be6ebc1ff7d7d7b438f87fc8da5b88c04")

        stationsRequest.enqueue(object : Callback<List<Station>> {
            override fun onResponse(call: Call<List<Station>>, response: Response<List<Station>>) {
                if (response.isSuccessful) {
                    listCergyStations = response.body()!!

                    //add Stations markers
                    for (station in listCergyStations) {

                        mMap.addMarker(
                            MarkerOptions().position(
                                LatLng(
                                    station.position.latitude,
                                    station.position.longitude
                                )
                            ).title(station.name)
                        )
                        mMap.moveCamera(
                            CameraUpdateFactory.newLatLng(
                                LatLng(
                                    station.position.latitude,
                                    station.position.longitude
                                )
                            )
                        )

                    }
                    mMap.setOnMarkerClickListener { onMarkerClick(it) }
                }
            }

            override fun onFailure(call: Call<List<Station>>, t: Throwable) {
                error("error from api")
            }
        })

        mMap.setMapType(MAP_TYPE_HYBRID);

    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        val currentStation = listCergyStations.find { it.name == marker!!.title }

        val infosIntent = Intent(this@MapsActivity, DetailsActivity::class.java)

        if (currentStation != null) {

            infosIntent.putExtra("title", currentStation!!.name)
            infosIntent.putExtra("status", currentStation.status)
            infosIntent.putExtra("address", currentStation.address)
            infosIntent.putExtra(
                "bikes",
                currentStation.totalStands.availabilities.bikes.toString()
            )
            infosIntent.putExtra(
                "stands",
                currentStation.totalStands.availabilities.stands.toString()
            )
            infosIntent.putExtra(
                "mechanicalBikes",
                currentStation.totalStands.availabilities.mechanicalBikes.toString()
            )
            infosIntent.putExtra(
                "electricalBikes",
                currentStation.totalStands.availabilities.electricalBikes.toString()
            )
            infosIntent.putExtra(
                "electricalInternalBatteryBikes",
                currentStation.totalStands.availabilities.electricalInternalBatteryBikes.toString()
            )
            infosIntent.putExtra(
                "electricalRemovableBatteryBikes",
                currentStation.totalStands.availabilities.electricalRemovableBatteryBikes.toString()
            )
            infosIntent.putExtra("capacity", currentStation.totalStands.capacity.toString())
            infosIntent.putExtra("lastUpdate", currentStation.lastUpdate)
            startActivity(infosIntent)
        }


        return false
    }

}