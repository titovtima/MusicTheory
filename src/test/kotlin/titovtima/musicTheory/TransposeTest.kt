package titovtima.musicTheory

import titovtima.musicTheory.Note.Companion.sharp
import org.junit.Test
import titovtima.musicTheory.Note.Companion.flat
import kotlin.test.assertEquals

class TransposeTest {
    @Test
    fun testTranspose_CsharpmFromAToB() {
        val chord = Chord("C${sharp}m")
        val transposed = chord.transpose(Key("A"), Key("B"))
        assertEquals("D${sharp}m", transposed.name)
    }

    @Test
    fun testTranspose_BFromEmToCm() {
        val chord = Chord("B")
        val transposed = chord.transpose(Key("Em"), Key("Cm"))
        assertEquals("G", transposed.name)
    }

    @Test
    fun testTransposeText_1() {
        val text = ChordsText("G | D | Em | C |")
        val result = text.transpose(Key("G"), Key("D"))
        assertEquals("D | A | Bm | G |", result.toString())
    }

    @Test
    fun testTransposeText_2() {
        val text = ChordsText("C A Dm7 G")
        val result = text.transpose(Key("C"), Key("A${flat}"))
        assertEquals("A${flat} F B${flat}m7 E${flat}", result.toString())
    }
}
