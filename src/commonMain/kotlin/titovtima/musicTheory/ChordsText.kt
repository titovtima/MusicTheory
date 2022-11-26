package titovtima.musicTheory

import kotlin.math.max
import kotlin.math.min

class ChordsText (val list: List<Either<Chord, String>>, notationSystem: NotationSystem = defaultNotation) {
    var notationSystem: NotationSystem = notationSystem
    private set

    companion object {
        fun fromPlainText(text: String, notationSystem: NotationSystem = defaultNotation) =
            ChordsText(PlainTextAPI.musicTextFromPlainText(text), notationSystem)
    }

    constructor(text: String, notationSystem: NotationSystem = defaultNotation) : this(ChordsText.run {
        var restText = text
        var resultList = listOf<Either<Chord, String>>()
        while (restText.isNotEmpty()) {
            val (chord, newRestText) = Chord.chordFromString(restText, notationSystem)
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
    }, notationSystem)

    override fun toString() = list.map {
        when(it) {
            is Either.Left -> it.value.name(notationSystem)
            is Either.Right -> it.value
        }
    }.reduce { res, s -> res + s }

    fun transpose(origin: Key, target: Key): ChordsText = ChordsText(this.list.map {
        when(it) {
            is Either.Left -> it.value.transpose(origin, target).eitherLeft()
            is Either.Right -> it
        }
    }, notationSystem)

    private fun reduceSpaces(string: String, needSpaces: Int) : Pair<String, Int> {
        if (string.contains('\n')) {
            return string to 0
        }
        return when {
            needSpaces == 0 -> string to 0
            needSpaces > 0 ->
                if (string[0] != ' ') string to needSpaces
                else (" ".repeat(needSpaces) + string) to 0
            else -> {
                var haveSpaces = string.indexOfFirst { it != ' ' }
                if (haveSpaces == -1) haveSpaces = string.length
                haveSpaces--
                haveSpaces = max(0, min(haveSpaces, -needSpaces))
                string.drop(haveSpaces) to (needSpaces + haveSpaces)
            }
        }
    }

    fun transposeReducingSpaces(origin: Key, target: Key): ChordsText {
        var needSpaces = 0
        return ChordsText(this.list.map { elem ->
            when (elem) {
                is Either.Left -> {
                    val newChord = elem.value.transpose(origin, target)
                    needSpaces += elem.value.name(notationSystem).length - newChord.name(notationSystem).length
                    newChord.eitherLeft()
                }
                is Either.Right -> {
                    val reduced = reduceSpaces(elem.value, needSpaces)
                    needSpaces = reduced.second
                    reduced.first.eitherRight()
                }
            }
        }, notationSystem)
    }

    fun changeNotation(newNotation: NotationSystem, reduceSpaces: Boolean = false) {
        if (newNotation == this.notationSystem) return
        if (reduceSpaces) {
            var needSpaces = 0
            list.forEach { elem ->
                when (elem) {
                    is Either.Left ->
                        needSpaces += elem.value.name(notationSystem).length - elem.value.name(newNotation).length
                    is Either.Right -> {
                        val reduced = reduceSpaces(elem.value, needSpaces)
                        needSpaces = reduced.second
                        reduced.first.eitherRight()
                    }
                }
            }
        }
        this.notationSystem = newNotation
    }
}