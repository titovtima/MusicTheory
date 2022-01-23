package titovtima.musicTheory

import org.junit.Ignore
import org.junit.Test
import java.io.File

class CopyFiles {
    @Test
    @Ignore
    fun copyToJS() {
        val root = File("./src/main/kotlin/titovtima/musicTheory/")
        val newRoot = File("./../MusicTheoryJS/src/titovtima.musicTheory/")
        root.copyRecursively(newRoot, true)
    }
}