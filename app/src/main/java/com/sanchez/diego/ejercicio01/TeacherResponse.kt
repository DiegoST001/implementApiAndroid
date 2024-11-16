package com.sanchez.diego.ejercicio01

data class TeacherResponse(
    val name: String,
    val last_name: String,
    val phone: String,
    val email: String,
    val imageUrl: String
) {

    fun getFullName(): String = "$name $last_name"

    fun getProfileImage(): String = imageUrl

    fun getPhoneNumber(): String = phone

}
