package com.example.mapas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

//ARRANJO QUE CONTEM OS DADOS DE ENDEREÇOS
private val places = arrayListOf(
    Place("FIAP Campus Vila Olimpia", LatLng(-23.5955843, -46.6851937),
        "Rua Olimpíadas, 186 - São Paulo - SP", 4.8f),
    Place("FIAP Campus Paulista", LatLng(-23.5643721, -46.652857),
        "Av. Paulista, 1106 - São Paulo - SP", 5.0f),
    Place("FIAP Campus Vila Mariana", LatLng(-23.5746685, -46.6232043),
        "Av. Lins de Vasconcelos, 1264 - São Paulo - SP", 4.8f)
)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync{
            //INCLUINDO OS MARCADORES
                googleMap -> addMarkers(googleMap)
            googleMap.setOnMapLoadedCallback {
                //FAZENDO LIMITE
                val bounds = LatLngBounds.builder()
                places.forEach {
                    bounds.include(it.latLng)
                }
                //DELIMITANDO A CAMERA PARA FICA DENTRO DO LIMITE
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
            }
        }
    }
    //COLOCANDO MARCADOR NO ARRANJO
    private fun addMarkers(googleMap: GoogleMap){
        places.forEach { place ->
            val market = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .snippet(place.address)
                    .position(place.latLng)
                    .icon(BitmapHelper.vectorToBitmap(
                        this, R.drawable.ic_baseline_school_24,
                        ContextCompat.getColor(this, R.color.purple_700)))
            )

        }
    }
}
// CLASSE QUE COMTÉM AS VÁRIÁVEIS DOS ENDEREÇOS
data class Place(
    val name: String,
    val latLng: LatLng,
    val address: String,
    val rating: Float
)