package com.example.testaptitude_devandroid_bluefrogrobotics.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testaptitude_devandroid_bluefrogrobotics.GameState
import com.example.testaptitude_devandroid_bluefrogrobotics.NumberGenerator
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun MainScreen() {
    var gameState by remember { mutableStateOf(GameState.INACTIVE) }
    var currentStep by remember { mutableIntStateOf(1) }
    var errorCount by remember { mutableIntStateOf(0) }
    var countdownValue by remember { mutableIntStateOf(0) }
    var generatedNumber by remember { mutableIntStateOf(0) }
    var message by remember { mutableStateOf("") }

    fun handleSequenceStep(buttonPressed: Int) {
        if (buttonPressed == currentStep) {
            currentStep++
            if (currentStep > 3) {
                gameState = GameState.GENERATING
                message = "Génération du nombre..."
            } else {
                message = "Appuyez sur le bouton $currentStep"
            }
        } else {
            errorCount++
            if (errorCount >= 3) {
                gameState = GameState.INACTIVE
                message = "Trop d'erreurs. Appuyez sur Start pour recommencer."
            } else {
                message = "Erreur ! Appuyez sur le bouton $currentStep"
            }
        }
    }

    fun handleFinalStep(buttonPressed: Int) {
        if (buttonPressed == generatedNumber) {
            message = "Succès ! Appuyez sur Start pour recommencer."
            gameState = GameState.INACTIVE
        } else {
            errorCount++
            if (errorCount >= 3) {
                gameState = GameState.INACTIVE
                message = "Trop d'erreurs. Appuyez sur Start pour recommencer."
            } else {
                message = "Erreur ! Appuyez sur le bouton $generatedNumber"
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = message)

        if (countdownValue > 0) {
            Text(text = "Compte à rebours: $countdownValue")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                gameState = GameState.STARTED
                currentStep = 1
                errorCount = 0
                message = "Appuyez sur le bouton 1"
            }) {
                Text("Start")
            }
            Button(onClick = {
                gameState = GameState.INACTIVE
                message = "Appuyez sur Start pour commencer"
            }) {
                Text("Stop")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            for (i in 1..3) {
                Button(onClick = {
                    when (gameState) {
                        GameState.STARTED -> handleSequenceStep(i)
                        GameState.WAITING_FOR_FINAL -> handleFinalStep(i)
                        else -> message = "Appuyez sur Start pour commencer"
                    }
                }) {
                    Text(i.toString())
                }
            }
        }
    }

    LaunchedEffect(gameState) {
        if (gameState == GameState.GENERATING) {
            countdownValue = Random.nextInt(5, 11)
            while (countdownValue > 0) {
                delay(1000)
                countdownValue--
            }
            generatedNumber = NumberGenerator.generateNumber()
            message = "Appuyez sur le bouton $generatedNumber"
            gameState = GameState.WAITING_FOR_FINAL
        }
    }


}