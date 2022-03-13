package titovtima.musicTheory

import titovtima.musicTheory.Note.Companion.flat
import titovtima.musicTheory.Note.Companion.sharp

class ChordsText (val list: List<Either<Chord, String>>) {
    companion object {
        fun fromPlainText(text: String) = ChordsText(PlainTextAPI.musicTextFromPlainText(text))
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
                resultList =
                    if (resultList.isEmpty())
                        resultList.plus(restText[0].toString().eitherRight())
                    else
                        when(val last = resultList.last()) {
                            is Either.Left -> resultList.plus(restText[0].toString().eitherRight())
                            is Either.Right -> resultList.dropLast(1).plus((last.value + restText[0]).eitherRight())
                        }
                restText = restText.substring(1)
            }
        }
        if (resultList.isEmpty())
            resultList = resultList.plus("".eitherRight())
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