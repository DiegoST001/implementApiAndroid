package com.sanchez.diego.ejercicio01

import TeacherAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanchez.diego.ejercicio01.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val teacherApi: TeacherApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TeacherApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewTeachers.layoutManager = LinearLayoutManager(this)
        getTeachers()
    }

    private fun getTeachers() {
        lifecycleScope.launch {
            try {
                val response = teacherApi.getTeachers()

                if (response.isSuccessful) {
                    val teachers = response.body()?.teachers ?: emptyList()
                    val adapter = TeacherAdapter(teachers)
                    binding.recyclerViewTeachers.adapter = adapter
                } else {
                    Toast.makeText(this@MainActivity, "Error al cargar los profesores", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error en la solicitud: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
