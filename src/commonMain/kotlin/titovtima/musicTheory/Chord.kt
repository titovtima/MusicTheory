package titovtima.musicTheory

class Chord (val note: Note, val type: String) {
    init {
        if (!chordTypes.contains(type)) throw ChordException(note, type)
    }
    fun name(notationSystem: NotationSystem = defaultNotation) = note.name(notationSystem) + type

    constructor(chord: Chord) : this(chord.note, chord.type)
    constructor(name: String, notationSystem: NotationSystem = defaultNotation) :
            this(chordFromName(name, notationSystem))

    companion object {
        val chordTypes = listOf("", "m", "7", "m7", "maj7", "mmaj7", "dim", "sus4", "sus2", "dim7", "+5", "ø")

        fun chordFromString(name: String, notationSystem: NotationSystem = defaultNotation): Pair<Chord?, String> {
            val (note, last_name) = Note.noteFromString(name, notationSystem)
            if (note == null) return (null to name)
            chordTypes.sortedBy { it.length }.reversed().forEach { chordType ->
                if (last_name.length >= chordType.length && last_name.substring(0, chordType.length) == chordType)
                    return (Chord(note, chordType) to last_name.substring(chordType.length))
            }
            return (null to name)
        }

        fun chordFromName(name: String, notationSystem: NotationSystem = defaultNotation): Chord {
            val (chord, rest) = chordFromString(name, notationSystem)
            if (chord == null || rest != "") throw ChordException(chordName = name, message = "Strict cast failed")
            return chord
        }
    }

    fun transpose(origin: Key, target: Key) = Chord(note.transpose(origin, target), type)
}
