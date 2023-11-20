package ru.titovtima.musicTheory

class PlainTextAPI {
    companion object {
        fun musicTextFromPlainText(text: String) =
            sharpFromGridSymbol(flatFromSmallB(text))

        fun cyrillicLettersToLatin(text: String) = text.map { char ->
            when(char) {
                'А' -> 'A'
                'В' -> 'B'
                'С' -> 'C'
                'Е' -> 'E'
                'Н' -> 'H'
                else -> char
            }
        }.joinToString("")

        fun sharpFromGridSymbol(text: String) = text.map { char ->
            when(char) {
                '#' -> Note.sharp
                else -> char
            }
        }.joinToString("")

        fun flatFromSmallB(text: String) = text.map { char ->
            when(char) {
                'b' -> Note.flat
                else -> char
            }
        }.joinToString("")
    }
}