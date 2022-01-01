package titovtima.musicTheory

import titovtima.musicTheory.Note.Companion.doubleFlat
import titovtima.musicTheory.Note.Companion.doubleSharp
import titovtima.musicTheory.Note.Companion.flat
import titovtima.musicTheory.Note.Companion.sharp
import org.junit.Test
import kotlin.test.assertEquals

class NoteTest {
    @Test
    fun testCreateNoteById_C() {
        val note = Note(0, 0)
        assertEquals("C", note.name)
    }

    @Test
    fun testCreateNoteById_Fsharp() {
        val note = Note(6, 3)
        assertEquals("F$sharp", note.name)
    }

    @Test
    fun testCreateNoteById_Bflat() {
        val note = Note(10, 6)
        assertEquals("B$flat", note.name)
    }

    @Test
    fun testCreateNoteById_Asharp() {
        val note = Note(10, 5)
        assertEquals("A$sharp", note.name)
    }

    @Test
    fun testCreateNoteById_G() {
        val note = Note(7, 4)
        assertEquals("G", note.name)
    }

    @Test
    fun testCreateNoteById_FdoubleSharp() {
        val note = Note(7, 3)
        assertEquals("F$doubleSharp", note.name)
    }

    @Test
    fun testCreateNoteById_CdoubleFlat() {
        val note = Note(10, 0)
        assertEquals("C$doubleFlat", note.name)
    }

    @Test
    fun testCreateNoteByName_D() {
        val note = Note("D")
        assertEquals("D", note.name)
        assertEquals(2, note.noteId)
        assertEquals(1, note.natural)
    }

    @Test
    fun testCreateNoteByName_Esharp() {
        val note = Note("E$sharp")
        assertEquals("E$sharp", note.name)
        assertEquals(5, note.noteId)
        assertEquals(2, note.natural)
    }

    @Test
    fun testCreateNoteByName_Aflat() {
        val note = Note("A$flat")
        assertEquals("A$flat", note.name)
        assertEquals(8, note.noteId)
        assertEquals(5, note.natural)
    }

    @Test
    fun testCreateNoteByName_C() {
        val note = Note("C")
        assertEquals("C", note.name)
        assertEquals(0, note.noteId)
        assertEquals(0, note.natural)
    }

    @Test
    fun testCreateNoteByName_Bsharp() {
        val note = Note("B${sharp}")
        assertEquals("B$sharp", note.name)
        assertEquals(0, note.noteId)
        assertEquals(6, note.natural)
    }

    @Test
    fun testCreateNoteByName_FdoubleFlat() {
        val note = Note("F$doubleFlat")
        assertEquals("F$doubleFlat", note.name)
        assertEquals(3, note.noteId)
        assertEquals(3, note.natural)
    }

    @Test
    fun testCreateNoteByName_GdoubleSharp() {
        val note = Note("G$doubleSharp")
        assertEquals("G$doubleSharp", note.name)
        assertEquals(9, note.noteId)
        assertEquals(4, note.natural)
    }

    @Test
    fun testCreateNoteByString_D() {
        val note = Note.noteFromString("Ddim").first
        assertEquals("D", note?.name)
        assertEquals(2, note?.noteId)
        assertEquals(1, note?.natural)
    }

    @Test
    fun testCreateNoteByString_CdoubleFlat() {
        val note = Note.noteFromString("C$doubleFlat").first
        assertEquals("C$doubleFlat", note?.name)
        assertEquals(10, note?.noteId)
        assertEquals(0, note?.natural)
    }
}
