package com.example.jcdecaux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jcdecaux.controllers.api.StationsService
import com.example.jcdecaux.models.Station
import com.example.jcdecaux.models.getJsonDataFromAsset

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    val url = "https://api.jcdecaux.com/vls/v1/"
    var listCergyStations: List<Station> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

//        val service = retrofit.create(StationsService::class.java)
//
//        val stationsRequest = service.getCergyStations("Velo2","7f81362be6ebc1ff7d7d7b438f87fc8da5b88c04")
//
//        stationsRequest.enqueue(object : Callback<List<Station>> {
//            override fun onResponse(call: Call<List<Station>>, response: Response<List<Station>>) {
//                if (response.isSuccessful) {
//                    listCergyStations = response.body()!!
//                }
//            }
//
//            override fun onFailure(call: Call<List<Station>>, t: Throwable) {
//                error("error from api")
//            }
//        })

//        val callResponse = stationsRequest.execute()

//        if (callResponse.isSuccessful) {
//            listCergyStations = callResponse.body()!!
//        }

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

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Add a marker in Sydney and move the camera
        val cergy = LatLng(49.0333, 2.0667)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cergy))


        listCergyStations = getStationsList()

        //add Stations markers
        for (station in listCergyStations) {
            mMap.addMarker(MarkerOptions().position(LatLng(station.latitude, station.longitude)).title("${station.name} - ${station.address} "))

        }

//        mMap.setOnMarkerClickListener { GoogleMap.OnMarkerClickListener() }

    }

    fun getStationsList(): List<Station> {
        val jsonString = getJsonDataFromAsset(applicationContext, "cergy-pontoise.json")
        Log.i("data= ", jsonString)

        val listStationsType = object : TypeToken<List<Station>>() {}.type

        val gson = Gson()
        val listStations = gson.fromJson<List<Station>>(jsonString, listStationsType)

        Log.i("stations", listStations.toString())

        return listStations
    }
}