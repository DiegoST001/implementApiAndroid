package com.sanchez.diego.ejercicio01

data class TeacherListResponse(
    val count: Int,
    val next: String?,
    val teachers: List<TeacherResponse>
)
