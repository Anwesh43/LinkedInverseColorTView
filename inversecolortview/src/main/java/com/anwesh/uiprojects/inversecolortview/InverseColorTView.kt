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

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawInverseColorT(scale : Float, w : Float, h : Float, paint : Paint) {
    val size : Float = w / sizeFactor
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, 2)
    val sf2 : Float = sf.divideScale(1, 2)
    drawRect(RectF(-size, 0f, size, h * sf1), paint)
    for (j in 0..1) {
        save()
        translate(0f, h)
        scale(1f - 2 * j, 1f)
        save()
        translate(size, 0f)
        drawRect(RectF(0f, -size, (w / 2 - size) * sf2, 0f), paint)
        restore()
        restore()
    }
}

fun Canvas.drawICTNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = Color.parseColor(colors[i])
    save()
    translate(w / 2, 0f)
    drawInverseColorT(scale, w, h, paint)
    restore()
}

class InverseColorTView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += scGap * dir
            if (Math.abs(scale - prevScale ) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }
}