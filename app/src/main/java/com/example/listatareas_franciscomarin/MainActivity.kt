package com.example.listatareas_franciscomarin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.listatareas_franciscomarin.ui.theme.ListaTareas_FranciscoMarinTheme
import com.example.listatareas_franciscomarin.ui.theme.Tarea
import com.example.listatareas_franciscomarin.ui.theme.prioridad

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaTareas_FranciscoMarinTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var tareas by remember {
        mutableStateOf(
            listOf(
                Tarea(1, "Terminar tarea acceso a datos", false, prioridad.ALTA),
                Tarea(2, "Ver a mi familia", true, prioridad.MEDIA),
                Tarea(3, "Dormir", false, prioridad.BAJA),
                Tarea(4, "Ver a mi pareja", true, prioridad.MEDIA),

            )
        )
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                tonalElevation = 2.dp,
                containerColor = Color.Black
            ) {
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(
                text = "Tareas Pendientes",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            ListadeTareas(
                tareas = tareas.filter { !it.estahecha },
                onMarkCompleted = { tarea ->
                    tareas = tareas.map {
                        if (it.id == tarea.id) it.copy(estahecha = true) else it
                    }
                },
                onChangePriority = { tarea, nuevaPrioridad ->
                    tareas = tareas.map {
                        if (it.id == tarea.id) it.copy(prioridad = nuevaPrioridad) else it
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))



            Text(
                text = "Tareas Completadas",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            ListadeTareas(
                tareas = tareas.filter { it.estahecha },
                onMarkCompleted = { tarea ->
                    tareas = tareas.map {
                        if (it.id == tarea.id) it.copy(estahecha = false) else it
                    }
                },
                onChangePriority = { tarea, nuevaPrioridad ->
                    tareas = tareas.map {
                        if (it.id == tarea.id) it.copy(prioridad = nuevaPrioridad) else it
                    }
                }
            )
        }
    }
}
