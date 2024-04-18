package com.example.labo_thermo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

class Thermo : View {

    private var tempEnDegC:Float = 0f
    private var unit:String = ""

    constructor(ctx : Context):super(ctx){
        invalidate()
    }
    constructor(ctx:Context, tempEnDegC:Float):super(ctx){
        this.tempEnDegC = tempEnDegC * 10
    }
    constructor(ctx: Context, tempEnDegC: Float, unit:String):super(ctx){
        this.tempEnDegC = tempEnDegC //230
        this.unit= unit
    }
    fun setTemp(temp:Float){
        this.tempEnDegC = temp
        invalidate()
    }
    fun setUnit(unit:String){
        this.unit = unit
    }
    fun CelsiusToFahrenheit():Float{
        this.tempEnDegC = ((this.tempEnDegC * 9/5) + 32) * 10
        return tempEnDegC;
    }
    fun CelsiusToKelvin():Float{
        this.tempEnDegC = (this.tempEnDegC + 273.15f) * 10
        return tempEnDegC
    }
    fun DrawEchelleCelsius(c: Canvas){
        //Background
        var p4 = Paint();
        p4.color = Color.LTGRAY
        c.drawRect(
            Rect(width/2 -350, 100, width/2 + 350, height-100),
            p4)

        //Title
        val p5 = Paint();
        p5.color = Color.BLACK
        p5.textSize = 70f
        c.drawText("Celsius",width/2f-110,250f,p5);

        //Mercury
        var p2 = Paint()
        var radius = 150f
        p2 = getColorFromTemp(tempEnDegC)
        p2.strokeWidth  = 25f
        var lineStartY = height-160f-radius*2
        if(tempEnDegC >= 0){
            c.drawLine(width/2f,lineStartY,width/2f,lineStartY-tempEnDegC * 15,p2)
        }

        //Circle
        var p =  Paint()
        p.color = Color.RED
        p.strokeWidth = 25f;
        val circleDiameter = radius * 2
        c.drawCircle(width/2f,height-160f - radius,radius, p)

        //Lines
        var p3 = Paint();
        p3.color= Color.BLACK;
        p3.textSize =50f;


        //Draw all lines
        for (i in 1..100){
            if(i%10 == 0){
                p3.strokeWidth = 4.5f
                c.drawText(i.toString(),width/2 + 200f,height - 155f - circleDiameter - i.toFloat()*15,p3)
                c.drawLine(width/2f + 12.5f, height - 160f - circleDiameter - i.toFloat()*15f,width/2f+100f,height - 160f - circleDiameter - i.toFloat()*15f,p3)
            }

            p3.strokeWidth = 3.5f
            c.drawLine(width/2f + 12.5f,height - 160f - circleDiameter - i.toFloat()*15, width/2f+50f, height - 160f - circleDiameter - i.toFloat()*15,p3)
        }
    }
    fun DrawEchelleFahrenheit(c:Canvas){
        //Background
        var p4 = Paint();
        p4.color = Color.LTGRAY
        c.drawRect(
            Rect(width/2 -350, 100, width/2 + 350, height-100),
            p4)

        //Title
        val p5 = Paint();
        p5.color = Color.BLACK
        p5.textSize = 70f
        c.drawText("Fahrenheit",width/2f-160,175f,p5);

        //Circle
        var p =  Paint()
        p.color = Color.RED
        p.strokeWidth = 25f;
        var radius = 150f
        val circleDiameter = radius * 2
        c.drawCircle(width/2f,height-160f - radius,radius, p)

        //Mercury
        var p2 = Paint();
        var tempEnFahr = CelsiusToFahrenheit()
        var lineHeight = tempEnFahr

        var lineStartY = height-160f-radius*2
        p2 = getColorFromTemp(tempEnFahr)
        p2.strokeWidth  = 25f;

        if(tempEnFahr >= 0){
            c.drawLine(width/2f,lineStartY,width/2f,lineStartY-lineHeight,p2)
        }


        //Lines
        var p3 = Paint();
        p3.color= Color.BLACK;
        p3.strokeWidth =1f;
        p3.textSize =50f;


        //Draw all lines
        for (i in 1..212){
            if(i%10 == 0){
                p3.strokeWidth = 2.9f
                c.drawText(i.toString(),width/2 + 200f,height - 155f - circleDiameter - i.toFloat()*10,p3)
                c.drawLine(width/2f + 12.5f, height - 160f - circleDiameter - i.toFloat()*10,width/2f+150f,height - 160f - circleDiameter - i.toFloat()*10,p3)
            }

            p3.strokeWidth = 1.8f
            c.drawLine(width/2f + 12.5f,height - 160f - circleDiameter - i.toFloat()*10, width/2f+65f, height - 160f - circleDiameter - i.toFloat()*10,p3)
        }
    }
    fun DrawEchelleKelvin(c:Canvas){
        //Background
        var p4 = Paint();
        p4.color = Color.LTGRAY
        c.drawRect(
            Rect(width/2 -350, 100, width/2 + 350, height-100),
            p4)

        //Title
        val p5 = Paint();
        p5.color = Color.BLACK
        p5.textSize = 70f
        c.drawText("Kelvin",width/2f-100,250f,p5);

        //Circle
        var p =  Paint()
        p.color = Color.RED
        p.strokeWidth = 25f;
        var radius = 150f
        val circleDiameter = radius * 2
        c.drawCircle(width/2f,height-160f - radius,radius, p)

        //Mercury
        var p2 = Paint();
        var tempEnKelv = CelsiusToKelvin()
        p2 = getColorFromTemp(tempEnKelv)

        p2.strokeWidth  = 40f;
        var lineStartY = height-160f-(radius*2)

        var lineHeight = (tempEnKelv / 5f) * 2

        if(tempEnKelv/10  >= 0){
            c.drawLine(width/2f,lineStartY,width/2f,lineStartY-lineHeight,p2)
        }

        //Lines
        var p3 = Paint();
        p3.color= Color.BLACK;
        p3.textSize =50f;

        //Draw all lines
        for (i in 1..100){
            if(i%10 == 0){
                p3.strokeWidth = 6.5f
                c.drawText((i+273).toString(),width/2 + 200f,height - 155f - circleDiameter - i.toFloat()*15,p3)
                c.drawLine(width/2f + 22.5f, height - 160f - circleDiameter - i.toFloat()*15,width/2f+150f,height - 160f - circleDiameter - i.toFloat()*15,p3)
            }

            p3.strokeWidth = 4.8f
            c.drawLine(width/2f + 22.5f,height - 160f - circleDiameter - i.toFloat()*15, width/2f+65f, height - 160f - circleDiameter - i.toFloat()*15,p3)
        }
    }
    override fun onDraw(c: Canvas) {
        super.onDraw(c)

        if(this.unit === "celsius"){
            DrawEchelleCelsius(c);
        }
        if(this.unit === "fahrenheit"){
            DrawEchelleFahrenheit(c)
        }
        if(this.unit === "kelvin"){
            DrawEchelleKelvin(c)
        }
    }
    fun getColorFromTemp(temp: Float): Paint {
        var r: Int = 0
        var b: Int = 255

        var p = Paint()

        if(this.unit === "celsius"){
            r += (temp*2.5f).toInt()
            b -= (temp*2.5f).toInt()
        }
        if(this.unit === "fahrenheit"){
            r += (temp.toInt()/10)
            b -= (temp.toInt()/10)
        }
        if(this.unit === "kelvin"){
            r += (temp.toInt()/15)
            b -= (temp.toInt()/15)
            println(r)
            println(b)
        }



        p.color = Color.rgb(r, 0, b)
        return p
    }
}