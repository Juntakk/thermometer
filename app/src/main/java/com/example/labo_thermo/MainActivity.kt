package com.example.labo_thermo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var manager: SensorManager
    private lateinit var tempSensor: Sensor
    private lateinit var thermo : Thermo;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        thermo = Thermo(this,23f,"celsius")
        thermo = Thermo(this, 23f, "fahrenheit")
//        thermo = Thermo(this, 23f, "kelvin")

        setContentView(thermo)

        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        tempSensor = manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) as Sensor

        manager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        val temperatureCelsius = sensorEvent.values[0]
        thermo.setTemp(temperatureCelsius)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Implementation of onAccuracyChanged if needed
    }
}