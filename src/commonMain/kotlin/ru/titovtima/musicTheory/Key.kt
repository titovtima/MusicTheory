package ru.titovtima.musicTheory

import kotlin.math.min

class Key (val tonic: Note, val mode: Mode) {
    fun name(notationSystem: NotationSystem = defaultNotation) = tonic.name(notationSystem) + mode.string

    constructor(key: Key) : this(key.tonic, key.mode)
    constructor(name: String, notationSystem: NotationSystem = defaultNotation) : this(keyFromName(name, notationSystem))
    constructor(tonic: Note, stringMode: String) : this(tonic.name() + stringMode)

    companion object {
        fun keyFromString(name: String, notationSystem: NotationSystem = defaultNotation): Pair<Key?, String> {
            val (note, lastName) = Note.noteFromString(name, notationSystem)
            if (note == null) return (null to name)
            for (i in min(Mode.maxStringLength, lastName.length) downTo 0) {
                val mode = Mode.getMode(lastName.dropLast(lastName.length - i))
                if (mode != null)
                    return (Key(note, mode) to lastName.drop(i))
            }
            return (null to name)
        }

        fun keyFromName(name: String, notationSystem: NotationSystem = defaultNotation): Key {
            val (key, rest) = keyFromString(name, notationSystem)
            if (key == null || rest != "") throw KeyException("titovtima.MusicTheory.Key name = $name, Strict cast failed")
            return key
        }

        val fifthsCircle = arrayOf(
            Key("C"), Key("Am"),
            Key("G"), Key("Em"),
            Key("D"), Key("Bm"),
            Key("A"), Key("F${Note.sharp}m"),
            Key("E"), Key("C${Note.sharp}m"),
            Key("B"), Key("G${Note.sharp}m"),
            Key("F${Note.sharp}"), Key("D${Note.sharp}m"),
            Key("D${Note.flat}"), Key("B${Note.flat}m"),
            Key("A${Note.flat}"), Key("Fm"),
            Key("E${Note.flat}"), Key("Cm"),
            Key("B${Note.flat}"), Key("Gm"),
            Key("F"), Key("Dm")
        )

        val chromaticCircle = arrayOf(
            Key("C"), Key("D${Note.flat}"),
            Key("D"), Key("E${Note.flat}"),
            Key("E"), Key("F"),
            Key("F${Note.sharp}"), Key("G"),
            Key("A${Note.flat}"), Key("A"),
            Key("B${Note.flat}"), Key("B"),
            Key("Am"), Key("B${Note.flat}m"),
            Key("Bm"), Key("Cm"),
            Key("C${Note.sharp}m"), Key("Dm"),
            Key("D${Note.sharp}m"), Key("Em"),
            Key("Fm"), Key("F${Note.sharp}m"),
            Key("Gm"), Key("G${Note.sharp}m"),
        )
    }

    fun getDegree(degree: Int): Note {
        val degree1 = degree % mode.degreesNumber
        return Note(tonic.noteId + mode.degrees[degree1], tonic.natural + degree1)
    }
}
