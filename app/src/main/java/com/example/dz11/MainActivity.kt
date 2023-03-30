package com.example.dz11

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var textViewTemperature: TextView
    private lateinit var textViewCountry: TextView
    private lateinit var textViewWeather: TextView
    private lateinit var textViewMinTemp: TextView
    private lateinit var textViewMaxTemp: TextView
    private lateinit var textViewWindSpeed: TextView
    private lateinit var textViewPressure: TextView
    private lateinit var textViewHumidity: TextView
    private lateinit var textViewUpdate: TextView
    private lateinit var textViewSunSet: TextView
    private lateinit var textViewSunRise: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewTemperature = findViewById(R.id.temperature)
        textViewCountry = findViewById(R.id.country)
        textViewWeather = findViewById(R.id.weather)
        textViewMaxTemp = findViewById(R.id.max_temp)
        textViewMinTemp = findViewById(R.id.min_temp)
        textViewWindSpeed = findViewById(R.id.wind)
        textViewPressure = findViewById(R.id.pressure)
        textViewHumidity = findViewById(R.id.humidty)
        textViewUpdate = findViewById(R.id.update)
        textViewSunSet = findViewById(R.id.sunset)
        textViewSunRise = findViewById(R.id.sunrise)

        val t = Thread {
            val data = URL("https://api.openweathermap.org/data/2.5/weather?lat=47.06173939262416&lon=142.05415547881725&units=metric&appid=f832a301f43dd6fbae43b219317b5f4a").readText()
            val obj = Json{
                ignoreUnknownKeys = true

            }.decodeFromString<WeatherData>(data)
            runOnUiThread {
                textViewTemperature.text = "${Math.ceil(obj.main.temp).toInt()}°C"
                textViewCountry.text = obj.name
                textViewWeather.text = obj.weather[0].main
                textViewMaxTemp.text = "${Math.ceil(obj.main.temp_max).toInt()}°C"
                textViewMinTemp.text = "${Math.ceil(obj.main.temp_min).toInt()}°C"
                textViewWindSpeed.text = "${obj.wind.speed}"
                textViewPressure.text = "${obj.main.pressure}"
                textViewHumidity.text = "${obj.main.humidity}"
                textViewUpdate.text = "Updated at: " + SimpleDateFormat("dd/MM/yyyy HH:mm a").format(Date(obj.dt*1000))
                textViewSunRise.text = SimpleDateFormat("hh:mm a").format(Date(obj.sys.sunrise*1000 - 3600000))
                textViewSunSet.text = SimpleDateFormat("hh:mm a").format(Date(obj.sys.sunset*1000 - 3600000))
            }
        }
        t.start()
    }
}