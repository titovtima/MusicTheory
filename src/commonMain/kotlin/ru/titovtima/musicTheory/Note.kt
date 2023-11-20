package ru.titovtima.musicTheory

open class Note (noteId: Int, natural: Int) {
    open val noteId = (noteId + 1200) % 12
    open val natural = (natural + 700) % 7
    fun name(notationSystem: NotationSystem = defaultNotation): String =
        nameFromId(this.noteId, this.natural, notationSystem)
            ?: throw NoteException(noteId = this.noteId, natural = this.natural)

    constructor(note: Note) : this(note.noteId, note.natural)
    constructor(name: String, notationSystem: NotationSystem = defaultNotation) :
            this(noteFromName(name, notationSystem))

    companion object {
        val sharp = '\u266F'
        val flat = '\u266D'
        val doubleSharp = "\uD834\uDD2A"
        val doubleFlat = "\uD834\uDD2B"
//        val doubleSharp = "\uE002"
//        val doubleFlat = "\uE001"

        val naturalToId = BiMap(mapOf(
            0 to 0,
            1 to 2,
            2 to 4,
            3 to 5,
            4 to 7,
            5 to 9,
            6 to 11
        ))

        fun naturalToName(notationSystem: NotationSystem = defaultNotation): BiMap<Int, Char> =
            when (notationSystem) {
                NotationSystem.English -> BiMap(mapOf(
                    0 to 'C',
                    1 to 'D',
                    2 to 'E',
                    3 to 'F',
                    4 to 'G',
                    5 to 'A',
                    6 to 'B'
                ))
                NotationSystem.German -> BiMap(mapOf(
                    0 to 'C',
                    1 to 'D',
                    2 to 'E',
                    3 to 'F',
                    4 to 'G',
                    5 to 'A',
                    6 to 'H'
                ))
            }




        fun nameFromId (noteId: Int, natural: Int, notationSystem: NotationSystem = defaultNotation): String? {
            if (notationSystem == NotationSystem.German && noteId == 10 && natural == 6) return "B"
            val naturalName = naturalToName(notationSystem)[natural] ?: return null
            val naturalId = naturalToId[natural] ?: return null
            return naturalName.plus(
                when ((noteId - naturalId + 12) % 12) {
                    10 -> doubleFlat
                    11 -> flat.toString()
                    0 -> ""
                    1 -> sharp.toString()
                    2 -> doubleSharp
                    else -> return null
                }
            )
        }

        fun noteFromString (name: String, notationSystem: NotationSystem = defaultNotation): Pair<Note?, String> {
            if (name.isEmpty()) return (null to name)
            val naturalChar = name[0]
            if (notationSystem == NotationSystem.German && naturalChar == 'B')
                return (Note(10, 6) to name.substring(1))
            val natural = naturalToName(notationSystem).reverse[naturalChar] ?: return (null to name)
            val noteId = naturalToId[natural] ?: return (null to name)
            return when {
                name.length >= 2 && name[1] == sharp ->
                    (Note(noteId + 1, natural) to name.substring(2))
                name.length >= 2 && name[1] == flat ->
                    (Note(noteId - 1, natural) to name.substring(2))
                name.length >= 3 && name.substring(1,3) == doubleSharp ->
                    (Note(noteId + 2, natural) to name.substring(3))
                name.length >= 3 && name.substring(1,3) == doubleFlat ->
                    (Note(noteId - 2, natural) to name.substring(3))
                else ->
                    (Note(noteId, natural) to name.substring(1))
            }
        }

        fun noteFromName (name: String, notationSystem: NotationSystem = defaultNotation): Note {
            val (note, rest) = noteFromString(name, notationSystem)
            if (note == null || rest != "") throw NoteException(noteName = name, message = "Strict cast failed")
            return note
        }
    }

    fun transpose(origin: Key, target: Key) =
        if (origin.mode != target.mode) throw KeyException("Try to transpose from ${origin.name()} to ${target.name()}")
        else Note(this.noteId + (target.tonic.noteId - origin.tonic.noteId),
            this.natural + (target.tonic.natural - origin.tonic.natural))

}
