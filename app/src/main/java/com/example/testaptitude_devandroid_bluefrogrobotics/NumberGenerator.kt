package com.example.testaptitude_devandroid_bluefrogrobotics

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random

// Classe pour générer un nombre aléatoire après un compte à rebours
class NumberGenerator {
    companion object {

        // Declaration de la variable MutableStateFlow venant de la librarie Kotlin Coroutines
        var countdown = MutableStateFlow(0)


        suspend fun generateNumber(): Int {
            //Géneration du compte a rebours aleatoire et on l'affecte au flow comme valeur
            countdown.value = Random.nextInt(5, 11)
            while (countdown.value > 0) {
                delay(1000)// on attend 1 seconde
                countdown.value -- //on decremente de 1
            }
            return Random.nextInt(1, 4) //on renvoi le nombre aleatoire entre 1 et 3
        }
    }
}