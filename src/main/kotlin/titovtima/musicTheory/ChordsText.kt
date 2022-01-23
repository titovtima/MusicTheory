package titovtima.musicTheory

class ChordsText (val text: String) {
    fun transpose(origin: Key, target: Key): ChordsText {
        var result = ""
        var restText = text
        while (restText.isNotEmpty()) {
            val (chord, newRestText) = Chord.chordFromString(restText)
            if (chord != null) {
                result += chord.transpose(origin, target).name
                restText = newRestText
            } else {
                result += restText[0]
                restText = restText.substring(1)
            }
        }
        return ChordsText(result)
    }
}