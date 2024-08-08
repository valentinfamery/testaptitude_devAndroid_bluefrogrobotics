package com.example.testaptitude_devandroid_bluefrogrobotics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random

// Classe pour générer un nombre aléatoire après un compte à rebours
class NumberGenerator {
    companion object {

        var countdown = MutableStateFlow(0)

        suspend fun generateNumber(): Int {
            countdown.value = Random.nextInt(5, 11)
            while (countdown.value > 0) {
                delay(1000)
                countdown.value --
            }
            return Random.nextInt(1, 4)
        }
    }
}