package ru.titovtima.musicTheory

class Interval(val lowNote: NoteWithOctave, val highNote: NoteWithOctave) {
    val name_ru: String
    init {
        if (lowNote.natural > highNote.natural || lowNote.noteId > highNote.noteId)
            throw IntervalException(lowNote, highNote)
        if (highNote.natural - lowNote.natural > 7 ||  highNote.noteId - lowNote.noteId > 12)
            throw IntervalException(lowNote, highNote, "Interval is too wide")
        val pair = (highNote.natural - lowNote.natural) to (highNote.noteId - lowNote.noteId)
        name_ru = possibleIntervals[pair] ?: throw IntervalException(lowNote, highNote, "No matching interval")
    }

    companion object {
        // first number is naturals' difference, second number is noteIds' difference
        val possibleIntervals = BiMap(mapOf(
            (0 to 0) to "прима",
            (1 to 1) to "малая секунда",
            (1 to 2) to "большая секунда",
            (1 to 3) to "увеличенная секунда",
            (2 to 3) to "малая терция",
            (2 to 4) to "большая терция",
            (3 to 4) to "уменьшенная кварта",
            (3 to 5) to "чистая кварта",
            (3 to 6) to "увеличенная кварта",
            (4 to 6) to "уменьшенная квинта",
            (4 to 7) to "чистая квинта",
            (4 to 8) to "увеличенная квинта",
            (5 to 8) to "малая секста",
            (5 to 9) to "большая секста",
            (6 to 9) to "уменьшенная септима",
            (6 to 10) to "малая септима",
            (6 to 11) to "большая септима",
            (7 to 12) to "октава",
        ))

        fun distanceToIntervalName(distance: Int) =
            when (distance) {
                0 -> "прима"
                1 -> "малая секунда"
                2 -> "большая секунда"
                3 -> "малая терция"
                4 -> "большая терция"
                5 -> "чистая кварта"
                6 -> "тритон"
                7 -> "чистая квинта"
                8 -> "малая секста"
                9 -> "большая секста"
                10 -> "малая септима"
                11 -> "большая септима"
                12 -> "октава"
                else -> throw IntervalException(message = "Interval is too wide, distance = $distance")
            }

        fun makeIntervalWithSwapNotesIfNeeded(firstNote: NoteWithOctave, secondNote: NoteWithOctave) =
            if (firstNote.natural > secondNote.natural) Interval(secondNote, firstNote)
            else Interval(firstNote, secondNote)
    }

    constructor(lowNote: NoteWithOctave, naturalsDiff: Int, noteIdsDiff: Int) :
            this(lowNote, NoteWithOctave(lowNote.noteId + noteIdsDiff, lowNote.natural + naturalsDiff))
}
