package com.example.testaptitude_devandroid_bluefrogrobotics

import kotlinx.coroutines.delay
import kotlin.random.Random

// Classe pour générer un nombre aléatoire après un compte à rebours
class NumberGenerator {
    companion object {
        suspend fun generateNumber(): Int {
            val countdown = Random.nextInt(5, 11)
            for (i in countdown downTo 1) {
                delay(1000)
            }
            return Random.nextInt(1, 4)
        }
    }
}