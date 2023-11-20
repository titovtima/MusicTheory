package ru.titovtima.musicTheory

class NoteWithOctave(override val noteId: Int, override val natural: Int) : Note(noteId, natural) {
    val octave: Int
    init {
        if (noteId < 0 || natural < 0)
            throw NoteException(noteId, natural, message = "Try to create note in octave")
        val octaveByNatural = natural % 7
        val octaveByNoteId = noteId % 12
        octave = if (octaveByNatural == octaveByNoteId)
            octaveByNatural
        else
            4
    }

    constructor(note: Note, octave: Int = 4) :
            this(note.noteId + octave * 12, note.natural + octave * 7)

    fun noteWithoutOctave() = Note(this)
}