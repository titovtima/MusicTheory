package ru.titovtima.musicTheory

class NoteException(val noteId: Int? = null, val natural: Int? = null, val noteName: String? = null, message: String? = null)
    : Exception("Exception: note id $noteId, natural $natural, note name $noteName, message: $message")

class ChordException(val note: Note? = null, val type: String? = null, val chordName: String? = null, message: String? = null)
    : Exception("Exception: note ${note?.name()}, type $type, chord name $chordName, message: $message")

class KeyException(override val message: String?) : Exception(message)

class IntervalException(val lowNote: NoteWithOctave? = null, val highNote: NoteWithOctave? = null, message: String? = null) :
        Exception("Exception: lowNote (noteId: ${lowNote?.noteId}, natural: ${lowNote?.natural}), " +
                "highNote (noteId: ${highNote?.noteId}, natural: ${highNote?.natural}), message: $message");