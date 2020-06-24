package com.anwesh.uiprojects.inversecolortview

/**
 * Created by anweshmishra on 24/06/20.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Color
import android.content.Context
import android.app.Activity

val colors : Array<String> = arrayOf("#3F51B5", "#F44336", "#4CAF50", "#03A9F4", "#009688")
val scGap : Float = 0.02f
val sizeFactor : Float = 5.6f
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20
