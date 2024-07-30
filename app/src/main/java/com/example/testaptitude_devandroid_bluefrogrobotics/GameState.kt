package com.example.testaptitude_devandroid_bluefrogrobotics

// États possibles du jeu
enum class GameState {
    INACTIVE, // Jeu inactif
    STARTED, // Séquence 1-2-3 en cours
    GENERATING, // Génération du nombre aléatoire
    WAITING_FOR_FINAL, // Attente du clic final
}