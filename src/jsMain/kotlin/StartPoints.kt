@file:OptIn(ExperimentalJsExport::class)

import ru.titovtima.musicTheory.*

@JsExport
@JsName("Note")
data class NoteJS(val noteId: Int, val natural: Int) {
    @JsName("fromKotlin")
    constructor(note: Note) : this(note.noteId, note.natural)

    fun toKotlin() = Note(noteId, natural)

    fun toNoteWithOctave() = NoteWithOctave(noteId, natural)
}

@JsExport
@JsName("Chord")
data class ChordJS(val note: NoteJS, val type: String) {
    @JsName("fromKotlin")
    constructor(chord: Chord) : this(NoteJS(chord.note), chord.type)

    fun toKotlin() = Chord(note.toKotlin(), type)
}

@JsExport
@JsName("ChordsText")
data class ChordsTextJS(val list: Array<Either<ChordJS, String>>, val notationSystem: String) {
    @JsName("fromKotlin")
    constructor(chordsText: ChordsText) : this(chordsText.list.map {
        when(it) {
            is Either.Left -> ChordJS(it.value).eitherLeft()
            is Either.Right -> it.value.eitherRight()
        }
    }.toTypedArray(), chordsText.notationSystem.notation)

    fun toKotlin() = ChordsText(list.map {
        when(it) {
            is Either.Left -> it.value.toKotlin().eitherLeft()
            is Either.Right -> it.value.eitherRight()
        }
    }.toList(), notationFromStringOrDefault(notationSystem))
}

@JsExport
@JsName("Key")
data class KeyJS(val tonic: NoteJS, val mode: String) {
    @JsName("fromKotlin")
    constructor(key: Key) : this(NoteJS(key.tonic), key.mode.string)

    fun toKotlin() = Mode.getMode(mode)?.let { Key(tonic.toKotlin(), it) } ?: throw KeyException("Invalid mode: \"$mode\"")
}

@JsExport
@JsName("Interval")
data class IntervalJS(val lowNote: NoteJS, val highNote: NoteJS, val name_ru: String) {
    @JsName("fromKotlin")
    constructor(interval: Interval) : this(NoteJS(interval.lowNote), NoteJS(interval.highNote), interval.name_ru)

    fun toKotlin() = Interval(lowNote.toNoteWithOctave(), highNote.toNoteWithOctave())
}

@JsExport
@JsName("chordFromName")
fun chordFromName_JS(name: String, notationSystem: String) =
    ChordJS(Chord.chordFromName(name, notationFromStringOrDefault(notationSystem)))

@JsExport
@JsName("keyFromName")
fun keyFromName_JS(name: String, notationSystem: String) =
    KeyJS(Key.keyFromName(name, notationFromStringOrDefault(notationSystem)))

@JsExport
@JsName("chordFromString")
fun chordFromString_JS(name: String, notationSystem: String) =
    Chord.chordFromString(name, notationFromStringOrDefault(notationSystem)).first?.let { ChordJS(it) }

@JsExport
@JsName("keyFromString")
fun keyFromString_JS(name: String, notationSystem: String) =
    Key.keyFromString(name, notationFromStringOrDefault(notationSystem)).first?.let { KeyJS(it) }

@JsExport
@JsName("transposeChord")
fun transposeChord_JS(chord: ChordJS, originKey: KeyJS, targetKey: KeyJS) =
    ChordJS(chord.toKotlin().transpose(originKey.toKotlin(), targetKey.toKotlin()))

@JsExport
@JsName("transposeChordsText")
fun transposeChordsText_JS(chordsText: ChordsTextJS, originKey: KeyJS, targetKey: KeyJS, reduceSpaces: Boolean = false) =
    if (reduceSpaces) ChordsTextJS(chordsText.toKotlin().transposeReducingSpaces(originKey.toKotlin(), targetKey.toKotlin()))
    else ChordsTextJS(chordsText.toKotlin().transpose(originKey.toKotlin(), targetKey.toKotlin()))

@JsExport
@JsName("musicTextFromPlainText")
fun musicTextFromPlainText_JS(text: String) = PlainTextAPI.musicTextFromPlainText(text)

@JsExport
@JsName("chordsTextFromPlainText")
fun chordsTextFromPlainText_JS(text: String, notationSystem: String) =
    ChordsTextJS(ChordsText.fromPlainText(text, notationFromStringOrDefault(notationSystem)))

@JsExport
@JsName("changeChordsTextNotation")
fun changeChordsTextNotation_JS(chordsText: ChordsTextJS, newNotation: String, reduceSpaces: Boolean = false): ChordsTextJS {
    val chordsTextKotlin = chordsText.toKotlin()
    chordsTextKotlin.changeNotation(notationFromStringOrDefault(newNotation), reduceSpaces)
    return ChordsTextJS(chordsTextKotlin)
}

@JsExport
@JsName("noteName")
fun noteName_JS(note: NoteJS, notationSystem: String = "English") = note.toKotlin().name(notationFromStringOrDefault(notationSystem))

@JsExport
@JsName("chordName")
fun chordName_JS(chord: ChordJS, notationSystem: String = "English") = chord.toKotlin().name(notationFromStringOrDefault(notationSystem))

@JsExport
@JsName("keyName")
fun keyName_JS(key: KeyJS, notationSystem: String = "English") = key.toKotlin().name(notationFromStringOrDefault(notationSystem))

@JsExport
@JsName("chordsTextToString")
fun chordsTextToString_JS(chordsText: ChordsTextJS) = chordsText.toKotlin().toString()

@JsExport
@JsName("getIntervalNameByNotesIdsDifference")
fun getIntervalNameByNotesIdsDifference_JS(noteIdsDiff: Int) =
    Interval.distanceToIntervalName(noteIdsDiff)

@JsExport
@JsName("getDifferenceNumbersByIntervalName")
fun getDifferenceNumbersByIntervalName_JS(intervalName: String): Array<Int?> {
    val pair = Interval.possibleIntervals.reverse[intervalName]
    return arrayOf(pair?.first, pair?.second)
}

@JsExport
@JsName("createNoteWithOctave")
fun createNoteWithOctave_JS(noteId: Int, natural: Int) = NoteJS(NoteWithOctave(noteId, natural))

@JsExport
@JsName("createIntervalByNoteAndDiffs")
fun createIntervalByNoteAndDiffs_JS(startNote: NoteJS, naturalsDiff: Int, noteIdsDiff: Int) =
    IntervalJS(Interval(startNote.toNoteWithOctave(), naturalsDiff, noteIdsDiff))

@JsExport
@JsName("getDegreesNumber")
fun getDegreesNumber_JS(key: KeyJS) = key.toKotlin().mode.degreesNumber

@JsExport
@JsName("getDegree")
fun getDegree_JS(key: KeyJS, degree: Int) = NoteJS(key.toKotlin().getDegree(degree))

@JsExport
@JsName("getCircleKeys")
fun getCircleKeys_JS() = Key.chromaticCircle.map { KeyJS(it) }.toTypedArray()
