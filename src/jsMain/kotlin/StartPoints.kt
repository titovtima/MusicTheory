import titovtima.musicTheory.*

@JsName("chordFromName")
fun chordFromName_JS(name: String, notationSystem: String) =
    Chord.chordFromName(name, notationFromStringOrDefault(notationSystem))

@JsName("keyFromName")
fun keyFromName_JS(name: String, notationSystem: String) =
    Key.keyFromName(name, notationFromStringOrDefault(notationSystem))

@JsName("chordFromString")
fun chordFromString_JS(name: String, notationSystem: String) =
    Chord.chordFromString(name, notationFromStringOrDefault(notationSystem))

@JsName("keyFromString")
fun keyFromString_JS(name: String, notationSystem: String) =
    Key.keyFromString(name, notationFromStringOrDefault(notationSystem))

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
fun chordsTextFromPlainText_JS(text: String, notationSystem: String) =
    ChordsText.fromPlainText(text, notationFromStringOrDefault(notationSystem))

@JsName("changeChordsTextNotation")
fun changeChordsTextNotation_JS(chordsText: ChordsText, newNotation: String, reduceSpaces: Boolean = false) =
    chordsText.changeNotation(notationFromStringOrDefault(newNotation), reduceSpaces)

@JsName("noteName")
fun noteName_JS(note: Note, notationSystem: String = "English") = note.name(notationFromStringOrDefault(notationSystem))

@JsName("chordName")
fun chordName_JS(chord: Chord, notationSystem: String = "English") = chord.name(notationFromStringOrDefault(notationSystem))

@JsName("keyName")
fun keyName_JS(key: Key, notationSystem: String = "English") = key.name(notationFromStringOrDefault(notationSystem))

@JsName("chordsTextToString")
fun chordsTextToString_JS(chordsText: ChordsText) = chordsText.toString()

@JsName("getIntervalNameByNotesIdsDifference")
fun getIntervalNameByNotesIdsDifference_JS(noteIdsDiff: Int) =
    Interval.distanceToIntervalName(noteIdsDiff)

@JsName("getDifferenceNumbersByIntervalName")
fun getDifferenceNumbersByIntervalName_JS(intervalName: String): Array<Int?> {
    val pair = Interval.possibleIntervals.reverse[intervalName]
    return arrayOf(pair?.first, pair?.second)
}

@JsName("createNoteWithOctave")
fun createNoteWithOctave_JS(noteId: Int, natural: Int) = NoteWithOctave(noteId, natural)

@JsName("createIntervalByNoteAndDiffs")
fun createIntervalByNoteAndDiffs_JS(startNote: NoteWithOctave, naturalsDiff: Int, noteIdsDiff: Int) =
    Interval(startNote, naturalsDiff, noteIdsDiff)
