package ru.titovtima.musicTheory

import ru.titovtima.musicTheory.Note.Companion.doubleFlat
import ru.titovtima.musicTheory.Note.Companion.flat
import ru.titovtima.musicTheory.Note.Companion.sharp
import kotlin.test.Test
import kotlin.test.assertEquals

class ChordTest {
    @Test
    fun testCreateChordByNote_G() {
        val chord = Chord(Note("G"), "")
        assertEquals("G", chord.name())
    }

    @Test
    fun testCreateChordByNote_Cflatm() {
        val chord = Chord(Note(1, 0), "m")
        assertEquals("C${sharp}m", chord.name())
    }

    @Test
    fun testCreateChordByNote_Bdim7() {
        val chord = Chord(Note("B"), "dim7")
        assertEquals("Bdim7", chord.name())
    }

    @Test
    fun testCreateChordByName_Fflatmmaj7() {
        val chord = Chord("F${flat}mmaj7")
        assertEquals("F${flat}mmaj7", chord.name())
        assertEquals(3, chord.note.natural)
        assertEquals(4, chord.note.noteId)
        assertEquals("mmaj7", chord.type.name)
    }

    @Test
    fun testCreateChordByName_A() {
        val chord = Chord("A")
        assertEquals("A", chord.name())
        assertEquals(5, chord.note.natural)
        assertEquals(9, chord.note.noteId)
        assertEquals("", chord.type.name)
    }

    @Test
    fun testCreateChordByName_GdoubleFlatSus4() {
        val chord = Chord("G${doubleFlat}sus4")
        assertEquals("G${doubleFlat}sus4", chord.name())
        assertEquals(4, chord.note.natural)
        assertEquals(5, chord.note.noteId)
        assertEquals("sus4", chord.type.name)
    }

    @Test
    fun testChordNotes_C() {
        val chord = Chord("C")
        val notes = chord.notes()
        assertEquals(3, notes.size)
        assertEquals(0, notes[0].noteId)
        assertEquals(4, notes[1].noteId)
        assertEquals(7, notes[2].noteId)
    }

    @Test
    fun testChordNotes_Gm() {
        val chord = Chord("Gm")
        val notes = chord.notes()
        assertEquals(3, notes.size)
        assertEquals(7, notes[0].noteId)
        assertEquals(10, notes[1].noteId)
        assertEquals(14, notes[2].noteId)
    }

    @Test
    fun testChordNotes_Fmaj7() {
        val chord = Chord("Fmaj7")
        val notes = chord.notes()
        assertEquals(4, notes.size)
        assertEquals(5, notes[0].noteId)
        assertEquals(9, notes[1].noteId)
        assertEquals(12, notes[2].noteId)
        assertEquals(16, notes[3].noteId)
    }

    @Test
    fun testChordNotes_Bflatsus4() {
        val chord = Chord("B${flat}sus4")
        val notes = chord.notes()
        assertEquals(3, notes.size)
        assertEquals(10, notes[0].noteId)
        assertEquals(15, notes[1].noteId)
        assertEquals(17, notes[2].noteId)
    }
}