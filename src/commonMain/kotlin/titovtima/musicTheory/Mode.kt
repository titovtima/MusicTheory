package titovtima.musicTheory

class Mode(val degrees: Array<Int>, val string: String) {
    val degreesNumber: Int
        get() = degrees.size

    companion object {
        fun getMode(string: String) =
            when(string) {
                "" -> MAJOR
                "m" -> MINOR
                else -> null
            }

        const val maxStringLength = 1
    }
}

val MAJOR = Mode(arrayOf(0, 2, 4, 5, 7, 9, 11), "")
val MINOR = Mode(arrayOf(0, 2, 3, 5, 7, 8, 10), "m")
