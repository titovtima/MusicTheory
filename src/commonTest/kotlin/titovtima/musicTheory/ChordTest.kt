package titovtima.musicTheory

import titovtima.musicTheory.Note.Companion.doubleFlat
import titovtima.musicTheory.Note.Companion.flat
import titovtima.musicTheory.Note.Companion.sharp
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
        assertEquals("mmaj7", chord.type)
    }

    @Test
    fun testCreateChordByName_A() {
        val chord = Chord("A")
        assertEquals("A", chord.name())
        assertEquals(5, chord.note.natural)
        assertEquals(9, chord.note.noteId)
        assertEquals("", chord.type)
    }

    @Test
    fun testCreateChordByName_GdoubleFlatSus4() {
        val chord = Chord("G${doubleFlat}sus4")
        assertEquals("G${doubleFlat}sus4", chord.name())
        assertEquals(4, chord.note.natural)
        assertEquals(5, chord.note.noteId)
        assertEquals("sus4", chord.type)
    }
}