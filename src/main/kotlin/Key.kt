class Key (val tonic: Note, val mode: String) {
    val name = tonic.name + mode

    constructor(key: Key) : this(key.tonic, key.mode)
    constructor(name: String) : this(keyFromName(name))

    companion object {
        val modes = listOf("", "m")

        fun keyFromString(name: String): Pair<Key?, String> {
            val (note, last_name) = Note.noteFromString(name)
            if (note == null) return (null to name)
            modes.sortedBy { it.length }.reversed().forEach { mode ->
                if (last_name.length >= mode.length && last_name.substring(0, mode.length) == mode)
                    return (Key(note, mode) to last_name.substring(mode.length))
            }
            return (null to name)
        }

        fun keyFromName(name: String): Key {
            val (key, rest) = keyFromString(name)
            if (key == null || rest != "") throw KeyException("Key name = $name, Strict cast failed")
            return key
        }
    }
}
