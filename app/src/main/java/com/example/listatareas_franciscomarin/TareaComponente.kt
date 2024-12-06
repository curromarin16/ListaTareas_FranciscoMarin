package com.example.listatareas_franciscomarin

import android.text.Layout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.example.listatareas_franciscomarin.ui.theme.Tarea
import com.example.listatareas_franciscomarin.ui.theme.prioridad
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.text.Layout.Alignment.ALIGN_CENTER as ALIGN_CENTER1


@Composable
fun ListadeTareas(
    tareas: List<Tarea>,
    onMarkCompleted: (Tarea) -> Unit,
    onChangePriority: (Tarea, prioridad) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        tareas.forEach { tarea ->
            Tareacomponente(
                tarea = tarea,
                onMarkCompleted = onMarkCompleted,
                onChangePriority = onChangePriority
            )
        }
    }
}

@Composable
fun Tareacomponente(
    tarea: Tarea,
    onMarkCompleted: (Tarea) -> Unit,
    onChangePriority: (Tarea, prioridad) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (tarea.estahecha) Color(0xFF63379F) else Color(0xFF6200EE)
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(
                        color = when (tarea.prioridad) {
                            prioridad.ALTA -> Color.Red
                            prioridad.MEDIA -> Color.Yellow
                            prioridad.BAJA -> Color.Green
                        },
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = tarea.descripcion,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium ,
                color = Color.White

            )

            DropdownMenuExample(
                tarea = tarea,
                onMarkCompleted = onMarkCompleted,
                onChangePriority = onChangePriority
            )
        }
    }
}

@Composable
fun DropdownMenuExample(
    tarea: Tarea,
    onMarkCompleted: (Tarea) -> Unit,
    onChangePriority: (Tarea, prioridad) -> Unit
) {
    var desplegado by remember { mutableStateOf(false) }

    Box {
        TextButton(onClick = { desplegado = true }) {
            Text("Opciones",color = Color.White)
        }

        DropdownMenu(
            expanded = desplegado,
            onDismissRequest = { desplegado = false }
        ) {
            DropdownMenuItem(
                text = { Text(if (tarea.estahecha) "Poner como pendiente" else "Poner como completada") },
                onClick = {
                    desplegado = false
                    onMarkCompleted(tarea)
                }
            )
            DropdownMenuItem(
                text = { Text("Cambiar a prioridad: ALTA") },
                onClick = {
                    desplegado = false
                    onChangePriority(tarea, prioridad.ALTA)
                }
            )
            DropdownMenuItem(
                text = { Text("Cambiar a prioridad: MEDIA") },
                onClick = {
                    desplegado = false
                    onChangePriority(tarea, prioridad.MEDIA)
                }
            )
            DropdownMenuItem(
                text = { Text("Cambiar a prioridad: BAJA") },
                onClick = {
                    desplegado = false
                    onChangePriority(tarea, prioridad.BAJA)
                }
            )
        }
    }
}


