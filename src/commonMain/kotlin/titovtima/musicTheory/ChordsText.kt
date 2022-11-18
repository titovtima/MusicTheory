package titovtima.musicTheory

import kotlin.math.max
import kotlin.math.min

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

    fun transposeReducingSpaces(origin: Key, target: Key): ChordsText {
        var needSpaces = 0
        return ChordsText(this.list.map { elem ->
            when (elem) {
                is Either.Left -> {
                    val newChord = elem.value.transpose(origin, target)
                    needSpaces += elem.value.name.length - newChord.name.length
                    newChord.eitherLeft()
                }
                is Either.Right -> {
                    if (elem.value.contains('\n')) {
                        needSpaces = 0
                        return@map elem
                    }
                    when {
                        needSpaces == 0 -> elem
                        needSpaces > 0 ->
                            if (elem.value[0] != ' ') elem
                            else {
                                val neededSpaces = needSpaces
                                needSpaces = 0
                                (" ".repeat(neededSpaces) + elem.value).eitherRight()
                            }
                        else -> {
                            var haveSpaces = elem.value.indexOfFirst { it != ' ' }
                            if (haveSpaces == -1) haveSpaces = elem.value.length
                            haveSpaces--
                            haveSpaces = max(0, min(haveSpaces, -needSpaces))
                            needSpaces += haveSpaces
                            elem.value.drop(haveSpaces).eitherRight()
                        }
                    }
                }
            }
        })
    }
}