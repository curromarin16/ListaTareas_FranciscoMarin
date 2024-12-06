package com.example.listatareas_franciscomarin.ui.theme

data class Tarea(
    val id: Int,
    val descripcion: String,
    var estahecha: Boolean,
    var prioridad: prioridad

)

enum class prioridad {
    ALTA, MEDIA, BAJA
}
