package ru.titovtima.musicTheory

class Chord {
    val note: Note
    val type: ChordType
    fun name(notationSystem: NotationSystem = defaultNotation) = note.name(notationSystem) + type.name

    constructor(note: Note, type: ChordType) {
        this.note = note
        this.type = type
    }
    constructor(note: Note, type: String)  {
        this.note = note
        this.type = ChordType.types.find { it.name == type } ?: throw ChordException(note, type)
    }
    constructor(chord: Chord) : this(chord.note, chord.type.name)
    constructor(name: String, notationSystem: NotationSystem = defaultNotation) :
            this(chordFromName(name, notationSystem))

    companion object {
        fun chordFromString(name: String, notationSystem: NotationSystem = defaultNotation): Pair<Chord?, String> {
            val (note, last_name) = Note.noteFromString(name, notationSystem)
            if (note == null) return (null to name)
            ChordType.types.sortedBy { it.name }.reversed().forEach { chordType ->
                if (last_name.length >= chordType.name.length && last_name.substring(0, chordType.name.length) == chordType.name)
                    return (Chord(note, chordType.name) to last_name.substring(chordType.name.length))
            }
            return (null to name)
        }

        fun chordFromName(name: String, notationSystem: NotationSystem = defaultNotation): Chord {
            val (chord, rest) = chordFromString(name, notationSystem)
            if (chord == null || rest != "") throw ChordException(chordName = name, message = "Strict cast failed")
            return chord
        }
    }

    fun transpose(origin: Key, target: Key) = Chord(note.transpose(origin, target), type.name)

    fun notes(): Array<NoteWithOctave> {
        return arrayOf(NoteWithOctave(note, 0))
            .plus(type.notes.map { NoteWithOctave(note.noteId + it.second, note.natural + it.first) })
    }
}

class ChordType (val name: String, val notes: Array<Pair<Int,Int>>) {
    companion object {
        val types = arrayOf(
            ChordType("", arrayOf(Pair(2, 4), Pair(4, 7))),
            ChordType("m", arrayOf(Pair(2, 3), Pair(4, 7))),
            ChordType("7", arrayOf(Pair(2, 4), Pair(4, 7), Pair(6, 10))),
            ChordType("m7", arrayOf(Pair(2, 3), Pair(4, 7), Pair(6, 10))),
            ChordType("maj7", arrayOf(Pair(2, 4), Pair(4, 7), Pair(6, 11))),
            ChordType("mmaj7", arrayOf(Pair(2, 3), Pair(4, 7), Pair(6, 11))),
            ChordType("dim", arrayOf(Pair(2, 3), Pair(4, 6))),
            ChordType("sus4", arrayOf(Pair(3, 5), Pair(4, 7))),
            ChordType("sus2", arrayOf(Pair(1, 2), Pair(4, 7))),
            ChordType("dim7", arrayOf(Pair(2, 3), Pair(4, 6), Pair(6, 9))),
            ChordType("+5", arrayOf(Pair(2, 4), Pair(4, 8))),
            ChordType("ø", arrayOf(Pair(2, 3), Pair(4, 6), Pair(6, 10))),
            ChordType("6", arrayOf(Pair(2, 4), Pair(4, 7), Pair(5, 9))),
            ChordType("2", arrayOf(Pair(1, 2), Pair(2, 4), Pair(4, 7))),
            ChordType("m6", arrayOf(Pair(2, 3), Pair(4, 7), Pair(5, 8))),
            ChordType("m2", arrayOf(Pair(1, 2), Pair(2, 3), Pair(4, 7))),
            ChordType("9", arrayOf(Pair(2, 4), Pair(4, 7), Pair(6, 10), Pair(8, 14))),
            ChordType("+9", arrayOf(Pair(2, 4), Pair(4, 7), Pair(8, 14))),
        )
    }

    constructor(name: String) : this(name, types.find { it.name == name }?.notes ?: arrayOf())
}
