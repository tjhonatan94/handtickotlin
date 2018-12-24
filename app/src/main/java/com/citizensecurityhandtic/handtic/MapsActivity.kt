package com.citizensecurityhandtic.handtic

import android.content.ContentProviderClient
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.citizensecurityhandtic.handtic.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnMarkerClickListener {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var lastLocation : Location
    companion object {

        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onMarkerClick(p0: Marker?) = false



    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btn1.setOnClickListener {

            mMap.mapType= GoogleMap.MAP_TYPE_TERRAIN
        }

        btn2.setOnClickListener {

            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }
        btn3.setOnClickListener {

            mMap.mapType= GoogleMap.MAP_TYPE_HYBRID
        }

        btn4.setOnClickListener {

            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
        btn_alerta.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
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

        // Add a marker in Sydney and move the camera
        //val popayan = LatLng( 2.457029, -76.645324)
        //mMap.addMarker(MarkerOptions()
        // .position(popayan)
        // .title("Marker in Popayán"))
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(popayan))
        mMap.setOnMarkerClickListener (this)
        mMap.uiSettings.isZoomControlsEnabled = true

        setUpMap()
        // val Indianapoles = LatLng( 2.433, -76.617)
        //  mMap.addMarker(MarkerOptions().position(Indianapoles).title("Marker in Barrio Indianapoles"))
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(Indianapoles))



        // val Unicauca = LatLng(2.446439, -76.599422)
        //mMap.addMarker(MarkerOptions().position(Unicauca).title("Marker in Unicauca"))
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(Unicauca))
    }
    private fun placeMarker(location: LatLng){
        val markerOption = MarkerOptions().position(location)
        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        mMap.addMarker(markerOption)
    }
    private fun setUpMap (){
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        fusedLocationClient.lastLocation.addOnSuccessListener (this){ location ->

            if (location != null){

                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarker(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16f))
            }

        }
    }

}