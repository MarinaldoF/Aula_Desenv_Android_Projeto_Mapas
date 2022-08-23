package com.example.mapas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

object BitmapHelper {
    fun vectorToBitmap(
        context: Context,
        @DrawableRes id: Int,
        @ColorInt color: Int
    ) : BitmapDescriptor{
        val vectorDrawable = ResourcesCompat.getDrawable(context.resources, id, null)
        //CONDIÇÃO QUE PARA VERIFICAR SE VETOR É NULO
        if(vectorDrawable==null){
            return BitmapDescriptorFactory.defaultMarker()
        }
        //BIPMAP
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        //CANVAS COM BIPMAP
        val canvas = Canvas(bitmap)
        //COLOCANDO LIMITE
        vectorDrawable.setBounds(0,0,canvas.width,canvas.height)
        //COLOCA A COR
        DrawableCompat.setTint(vectorDrawable, color)
        //INCLUINDO O ICONE
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}