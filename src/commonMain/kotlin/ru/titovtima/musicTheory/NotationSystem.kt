package ru.titovtima.musicTheory

enum class NotationSystem(val notation: String) { English("English"), German("German") }
val defaultNotation = NotationSystem.English

fun notationFromString(string: String): NotationSystem? = when(string) {
    "English" -> NotationSystem.English
    "German" -> NotationSystem.German
    else -> null
}

fun notationFromStringOrDefault(string: String): NotationSystem = notationFromString(string) ?: defaultNotation
