package titovtima.musicTheory

import titovtima.musicTheory.Note.Companion.flat
import titovtima.musicTheory.Note.Companion.sharp

class ChordsText (val list: List<Either<Chord, String>>) {
    companion object {
        fun fromPlainText(text: String) = ChordsText(text.map { char ->
                when(char) {
                    '#' -> sharp
                    'b' -> flat
                    // Russian letters to English
//                    'А' -> 'A'
//                    'В' -> 'B'
//                    'С' -> 'C'
//                    'Е' -> 'E'
//                    'Н' -> 'H'
                    else -> char
                }
            }.toString())
    }

    constructor(text: String) : this(ChordsText.run {
        var restText = text
        var resultList = listOf<Either<Chord, String>>()
        while (restText.isNotEmpty()) {
            val (chord, newRestText) = Chord.chordFromString(restText)
            if (chord != null) {
                resultList = resultList.plus(chord.eitherLeft())
                restText = newRestText
            } else {
                resultList = when(val last = resultList.last()) {
                    is Either.Left -> resultList.plus(restText[0].toString().eitherRight())
                    is Either.Right -> resultList.dropLast(1).plus((last.value + restText[0]).eitherRight())
                }
                restText = restText.substring(1)
            }
        }
        resultList
    })

    override fun toString() = list.map {
        when(it) {
            is Either.Left -> it.value.name
            is Either.Right -> it.value
        }
    }.reduce { res, s -> res + s }

    fun transpose(origin: Key, target: Key): ChordsText = ChordsText(this.list.map {
        when(it) {
            is Either.Left -> it.value.transpose(origin, target).eitherLeft()
            is Either.Right -> it
        }
    })
}