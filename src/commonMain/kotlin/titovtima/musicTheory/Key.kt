package titovtima.musicTheory

class Key (val tonic: Note, val mode: String) {
    fun name(notationSystem: NotationSystem = defaultNotation) = tonic.name(notationSystem) + mode

    constructor(key: Key) : this(key.tonic, key.mode)
    constructor(name: String, notationSystem: NotationSystem = defaultNotation) : this(keyFromName(name, notationSystem))

    companion object {
        val modes = listOf("", "m")

        fun keyFromString(name: String, notationSystem: NotationSystem = defaultNotation): Pair<Key?, String> {
            val (note, last_name) = Note.noteFromString(name, notationSystem)
            if (note == null) return (null to name)
            modes.sortedBy { it.length }.reversed().forEach { mode ->
                if (last_name.length >= mode.length && last_name.substring(0, mode.length) == mode)
                    return (Key(note, mode) to last_name.substring(mode.length))
            }
            return (null to name)
        }

        fun keyFromName(name: String, notationSystem: NotationSystem = defaultNotation): Key {
            val (key, rest) = keyFromString(name, notationSystem)
            if (key == null || rest != "") throw KeyException("titovtima.MusicTheory.Key name = $name, Strict cast failed")
            return key
        }
    }
}
