package titovtima.musicTheory

interface BiMap<K, V> : Map<K, V> {
    override val values: Set<V>
    val inverse: BiMap<V, K>
}

class BiHashMap<K, V> (private val direct: Map<K, V>): HashMap<K, V>(direct), BiMap<K, V> {
    override val values: MutableSet<V>
        get() = direct.values.toMutableSet()


    private val reverse: Map<V, K> = run {
        val res = emptyMap<V, K>().toMutableMap()
        for (pair in direct) {
            res[pair.value] = pair.key
        }
        res
    }

    override val inverse: BiMap<V, K>
        get() = BiHashMap(reverse)

}