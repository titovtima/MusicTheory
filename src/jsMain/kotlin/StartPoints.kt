import titovtima.musicTheory.*

@JsName("chordFromName")
fun chordFromName_JS(name: String) = Chord.chordFromName(name)

@JsName("keyFromName")
fun keyFromName_JS(name: String) = Key.keyFromName(name)

@JsName("chordFromString")
fun chordFromString_JS(name: String) = Chord.chordFromString(name)

@JsName("keyFromString")
fun keyFromString_JS(name: String) = Key.keyFromString(name)

@JsName("transposeChord")
fun transposeChord_JS(chord: Chord, originKey: Key, targetKey: Key) =
    chord.transpose(originKey, targetKey)

@JsName("transposeChordsText")
fun transposeChordsText_JS(chordsText: ChordsText, originKey: Key, targetKey: Key, reduceSpaces: Boolean = false) =
    if (reduceSpaces) chordsText.transposeReducingSpaces(originKey, targetKey)
    else chordsText.transpose(originKey, targetKey)

@JsName("musicTextFromPlainText")
fun musicTextFromPlainText_JS(text: String) = PlainTextAPI.musicTextFromPlainText(text)

@JsName("chordsTextFromPlainText")
fun chordsTextFromPlainText_JS(text: String) = ChordsText.fromPlainText(text)

@JsName("changeChordsTextNotation")
fun changeChordsTextNotation_JS(chordsText: ChordsText, newNotation: NotationSystem, reduceSpaces: Boolean = false) =
    chordsText.changeNotation(newNotation, reduceSpaces)
