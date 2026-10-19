package com.example.proyecto2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TocaElCirculoGame()
        }
    }
}

@Composable
fun TocaElCirculoGame() {
    var posicion by remember { mutableStateOf(Offset(400f, 400f)) }
    val diametro = 100f

    fun moverCirculo(ancho: Float, alto: Float) {
        val nuevaX = Random.nextFloat() * (ancho - diametro)
        val nuevaY = Random.nextFloat() * (alto - diametro)
        posicion = Offset(nuevaX, nuevaY)
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .clickable { }
    ) {
        if (posicion.x == 400f && posicion.y == 400f) {
            moverCirculo(size.width, size.height)
        }

        drawCircle(
            color = Color(0xFFFF5722),
            radius = diametro / 2,
            center = posicion
        )
    }

    // Detectar toque en el círculo
    androidx.compose.foundation.Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { toque ->
                    val distancia = Math.hypot(
                        (toque.x - posicion.x).toDouble(),
                        (toque.y - posicion.y).toDouble()
                    )
                    if (distancia <= diametro / 2) {
                        moverCirculo(size.width, size.height)
                    }
                }
            }
    )
}