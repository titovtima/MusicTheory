package ru.titovtima.musicTheory

class NoteWithOctave(override val noteId: Int, override val natural: Int) : Note(noteId, natural) {
    val octave: Int
    init {
        if (noteId < 0 || natural < 0)
            throw NoteException(noteId, natural, message = "Try to create note in octave")
        val octaveByNatural = natural / 7
        val octaveByNoteId = noteId / 12
        octave = if (octaveByNatural == octaveByNoteId)
            octaveByNatural
        else
            0
    }

    constructor(note: Note) : this(note.noteId, note.natural)
    constructor(note: Note, octave: Int) :
            this(note.noteId % 12 + octave * 12, note.natural % 7 + octave * 7)

    fun noteWithoutOctave() = Note(this)
}