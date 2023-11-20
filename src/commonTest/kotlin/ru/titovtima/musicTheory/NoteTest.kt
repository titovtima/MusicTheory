package ru.titovtima.musicTheory

import ru.titovtima.musicTheory.Note.Companion.doubleFlat
import ru.titovtima.musicTheory.Note.Companion.doubleSharp
import ru.titovtima.musicTheory.Note.Companion.flat
import ru.titovtima.musicTheory.Note.Companion.sharp
import kotlin.test.Test
import kotlin.test.assertEquals

class NoteTest {
    @Test
    fun testCreateNoteById_C() {
        val note = Note(0, 0)
        assertEquals("C", note.name(NotationSystem.English))
        assertEquals("C", note.name(NotationSystem.German))
    }

    @Test
    fun testCreateNoteById_Fsharp() {
        val note = Note(6, 3)
        assertEquals("F$sharp", note.name(NotationSystem.English))
        assertEquals("F$sharp", note.name(NotationSystem.German))
    }

    @Test
    fun testCreateNoteById_Bflat() {
        val note = Note(10, 6)
        assertEquals("B$flat", note.name(NotationSystem.English))
        assertEquals("B", note.name(NotationSystem.German))
    }

    @Test
    fun testCreateNoteById_Asharp() {
        val note = Note(10, 5)
        assertEquals("A$sharp", note.name(NotationSystem.English))
        assertEquals("A$sharp", note.name(NotationSystem.German))
    }

    @Test
    fun testCreateNoteById_G() {
        val note = Note(7, 4)
        assertEquals("G", note.name(NotationSystem.English))
        assertEquals("G", note.name(NotationSystem.German))
    }

    @Test
    fun testCreateNoteById_FdoubleSharp() {
        val note = Note(7, 3)
        assertEquals("F$doubleSharp", note.name(NotationSystem.English))
        assertEquals("F$doubleSharp", note.name(NotationSystem.German))
    }

    @Test
    fun testCreateNoteById_CdoubleFlat() {
        val note = Note(10, 0)
        assertEquals("C$doubleFlat", note.name(NotationSystem.English))
        assertEquals("C$doubleFlat", note.name(NotationSystem.German))
    }

    @Test
    fun testCreateNoteByName_D() {
        val note = Note("D")
        assertEquals("D", note.name(NotationSystem.English))
        assertEquals("D", note.name(NotationSystem.German))
        assertEquals(2, note.noteId)
        assertEquals(1, note.natural)
    }

    @Test
    fun testCreateNoteByName_Esharp() {
        val note = Note("E$sharp")
        assertEquals("E$sharp", note.name(NotationSystem.English))
        assertEquals("E$sharp", note.name(NotationSystem.German))
        assertEquals(5, note.noteId)
        assertEquals(2, note.natural)
    }

    @Test
    fun testCreateNoteByName_Aflat() {
        val note = Note("A$flat")
        assertEquals("A$flat", note.name(NotationSystem.English))
        assertEquals("A$flat", note.name(NotationSystem.German))
        assertEquals(8, note.noteId)
        assertEquals(5, note.natural)
    }

    @Test
    fun testCreateNoteByName_C() {
        val note = Note("C")
        assertEquals("C", note.name(NotationSystem.English))
        assertEquals("C", note.name(NotationSystem.German))
        assertEquals(0, note.noteId)
        assertEquals(0, note.natural)
    }

    @Test
    fun testCreateNoteByName_Bsharp() {
        val note = Note("B${sharp}")
        assertEquals("B$sharp", note.name(NotationSystem.English))
        assertEquals("H$sharp", note.name(NotationSystem.German))
        assertEquals(0, note.noteId)
        assertEquals(6, note.natural)
    }

    @Test
    fun testCreateNoteByName_FdoubleFlat() {
        val note = Note("F$doubleFlat")
        assertEquals("F$doubleFlat", note.name(NotationSystem.English))
        assertEquals("F$doubleFlat", note.name(NotationSystem.German))
        assertEquals(3, note.noteId)
        assertEquals(3, note.natural)
    }

    @Test
    fun testCreateNoteByName_GdoubleSharp() {
        val note = Note("G$doubleSharp")
        assertEquals("G$doubleSharp", note.name(NotationSystem.English))
        assertEquals("G$doubleSharp", note.name(NotationSystem.German))
        assertEquals(9, note.noteId)
        assertEquals(4, note.natural)
    }

    @Test
    fun testCreateNoteByString_D() {
        val note = Note.noteFromString("Ddim").first
        assertEquals("D", note?.name(NotationSystem.English))
        assertEquals("D", note?.name(NotationSystem.German))
        assertEquals(2, note?.noteId)
        assertEquals(1, note?.natural)
    }

    @Test
    fun testCreateNoteByString_CdoubleFlat() {
        val note = Note.noteFromString("C$doubleFlat").first
        assertEquals("C$doubleFlat", note?.name(NotationSystem.English))
        assertEquals("C$doubleFlat", note?.name(NotationSystem.German))
        assertEquals(10, note?.noteId)
        assertEquals(0, note?.natural)
    }

    @Test
    fun testCreateNoteByNameGermanNotation_B() {
        val note = Note.noteFromString("B", NotationSystem.German).first
        assertEquals("B", note?.name(NotationSystem.German))
        assertEquals(10, note?.noteId)
        assertEquals(6, note?.natural)
    }

    @Test
    fun testCreateNoteByNameGermanNotation_GdoubleFlat() {
        val note = Note.noteFromString("G$doubleFlat", NotationSystem.German).first
        assertEquals("G$doubleFlat", note?.name(NotationSystem.German))
        assertEquals("G$doubleFlat", note?.name(NotationSystem.English))
        assertEquals(5, note?.noteId)
        assertEquals(4, note?.natural)
    }

    @Test
    fun testCreateNoteByNameGermanNotation_HdoubleFlat() {
        val note = Note.noteFromString("H$doubleFlat", NotationSystem.German).first
        assertEquals("H$doubleFlat", note?.name(NotationSystem.German))
        assertEquals("B$doubleFlat", note?.name(NotationSystem.English))
        assertEquals(9, note?.noteId)
        assertEquals(6, note?.natural)
    }

    @Test
    fun testCreateNoteByIdNotation_BdoubleFlat() {
        val note = Note(9, 6)
        assertEquals("H$doubleFlat", note.name(NotationSystem.German))
        assertEquals("B$doubleFlat", note.name(NotationSystem.English))
        assertEquals(9, note.noteId)
        assertEquals(6, note.natural)
    }
}
