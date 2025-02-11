package ru.titovtima.musicTheory

import kotlin.test.Test
import kotlin.test.assertEquals

class KeyTest {
    @Test
    fun testKeyName1() {
        val key = Key("C")
        assertEquals("C", key.name())
    }

    @Test
    fun testKeyName2() {
        val key = Key("F${Note.sharp}m")
        assertEquals("F${Note.sharp}m", key.name())
    }
}