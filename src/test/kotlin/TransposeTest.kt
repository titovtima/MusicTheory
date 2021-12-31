import Note.Companion.sharp
import org.junit.Test
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
}
