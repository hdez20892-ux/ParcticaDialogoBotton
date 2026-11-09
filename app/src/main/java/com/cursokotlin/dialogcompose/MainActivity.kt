package com.cursokotlin.dialogcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cursokotlin.dialogcompose.ui.theme.DialogComposeTheme
import android.util.Log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DialogComposeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    var show by rememberSaveable { mutableStateOf(false) }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(onClick = { show = true }) {
                            Text(text = "Dialogo")
                        }

                        MyDialog(
                            show = show,
                            onDismiss = { show = false },
                            onConfirm = { Log.i("MyDialog", "Confirmado") }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MyDialog(
    show: Boolean = true,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Cancelar")
                }
            },
            title = { Text(text = "Hola") },
            text = { Text(text = "Esto es el body del dialog") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DialogComposeTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = {}) {
                Text(text = "Dialogo")
            }
        }
    }
}