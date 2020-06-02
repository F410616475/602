package com.example.a0602

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Color.rgb
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Handler
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * TODO: document your custom view class.
 */
class MyView : View {



    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {

        init(attrs, defStyle)
    }

    var spx =5
    var spy =5

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        r=Runnable {
            for( i in (0..58)){
                posx[i]=posx[i+1]
                posy[i]=posy[i+1]
            }
            posx[59]=sx
            posy[59]=sy
            sx = sx+spx
            sy = sy+spy
            if (sx>1080||sx<0){
                spx =-spx
            }
            if (sy>1550||sy<0)
                spy = -spy

            invalidate()
            h.postDelayed(r,10)


        }
        h.post(r)
    }
    var h = Handler()
    var sx =0f
    var sy =0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action){
            MotionEvent.ACTION_DOWN->{
                for (i in (0..59)){
                    posx[i]=event?.x
                    posy[i]=event?.y

                }

            }
            MotionEvent.ACTION_MOVE->{

                for (i in (0..58)){
                    posx[i]=posx[i+1]
                    posy[i]=posy[i+1]

                }
                posx[59]=event?.x
                posy[59]=event?.y
                invalidate()
            }
        }
        return true
    }

    var posx = FloatArray( 60)
    var posy = FloatArray(60)

    var paint =Paint()
    var r : Runnable? = null

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (i in (0..59)) {
            var  c = 255-255/60*i
            paint.setColor(Color.rgb(c,c,c))
            canvas.drawCircle(posx[i], posy[i], i*1f, Paint())

        }

    }
}
