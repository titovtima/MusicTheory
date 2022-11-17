package titovtima.musicTheory

class BiMap<K, V> (private val direct: Map<K, V>) : Map<K, V> {

    val reverse: Map<V, K> = run {
        val res = emptyMap<V, K>().toMutableMap()
        for (pair in this.direct) {
            res[pair.value] = pair.key
        }
        res.toMap()
    }

    override val entries: Set<Map.Entry<K, V>>
        get() = this.direct.entries
    override val keys: Set<K>
        get() = this.direct.keys
    override val size: Int
        get() = this.direct.size
    override val values: Collection<V>
        get() = direct.values

    override fun isEmpty(): Boolean = this.direct.isEmpty()

    override fun get(key: K): V? = this.direct[key]

    override fun containsValue(value: V): Boolean = this.direct.containsValue(value)

    override fun containsKey(key: K): Boolean = this.direct.containsKey(key)

}