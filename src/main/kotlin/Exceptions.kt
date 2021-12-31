class NoteException(val noteId: Int? = null, val natural: Int? = null, val noteName: String? = null, message: String? = null)
    : Exception("Exception: note id $noteId, natural $natural, note name $noteName, message: $message")

class ChordException(val note: Note? = null, val type: String? = null, val chordName: String? = null, message: String? = null)
    : Exception("Exception: note ${note?.name}, type $type, chord name $chordName, message: $message")

class KeyException(override val message: String?) : Exception(message)