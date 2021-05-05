package com.example.fabrikal

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_gps.*


class GpsActivity : AppCompatActivity(), OnMapReadyCallback {
    companion object{
        const val REQUEST_CODE = 1004
        const val REQUEST_CODE_LOCATION = 0
    }
    var locationGranted = false
    var marker : MarkerOptions? = null
    private lateinit var map: GoogleMap

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        enableMyLocation()
        updateLocationUI()
        isPermissionsGranted()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gps)
        createMapFragment()
        buttonGPS.setOnClickListener {
            if(marker != null){
                intent.putExtra("LATITUD",marker!!.position.latitude)
                intent.putExtra("LONGITUD",marker!!.position.longitude)
                setResult(RESULT_OK, intent)
                finish()
            } else{
                Toast.makeText(this, "selecciona una ubicacion", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createMapFragment() {
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun isPermissionsGranted() = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_CODE_LOCATION)
        }
    }

    private fun enableMyLocation() {
        if (!::map.isInitialized) return
        if (isPermissionsGranted()) {
            map.isMyLocationEnabled = true
            locationGranted = true
        } else {
            requestLocationPermission()
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                map.isMyLocationEnabled = true
                locationGranted = true
            }else{
                Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
        if(!isPermissionsGranted()){
            map.isMyLocationEnabled = false
            Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        }
    }
    fun updateLocationUI() {
        map?.run {
            if (locationGranted) {
                this.isMyLocationEnabled = true
                this.uiSettings.isMyLocationButtonEnabled = true
            } else {
                this.isMyLocationEnabled = false
                this.uiSettings.isMyLocationButtonEnabled = false
                requestLocationPermission()
            }

            setOnMapClickListener {
                    clear()
                    marker = markerHome(it)
                addMarker(marker)
            }
        }
    }

    fun markerHome(location: Location) = markerHome(LatLng(location.latitude,location.longitude))

    fun markerHome(latLng: LatLng) = MarkerOptions()
        .position(latLng)
        .title("Punto de entrega")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
}