package com.example.meditationui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import java.lang.Math.abs

// This is just a helper function so that we don't have to repeat all the code for drawing the smooth lines inside the lazy grid cell.
fun Path.standardQuadFromTo(from: Offset, to: Offset){
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + to.x)/2f,
        abs(from.y + to.y)/2f,

        )
}