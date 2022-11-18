package titovtima.musicTheory

import titovtima.musicTheory.Note.Companion.sharp
import titovtima.musicTheory.Note.Companion.flat
import kotlin.test.Test
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

    @Test
    fun testTransposeText_1() {
        val text = ChordsText("G | D | Em | C |")
        val result = text.transpose(Key("G"), Key("D"))
        assertEquals("D | A | Bm | G |", result.toString())
    }

    @Test
    fun testTransposeText_2() {
        val text = ChordsText("C A Dm7 G")
        val result = text.transpose(Key("C"), Key("A${flat}"))
        assertEquals("A${flat} F B${flat}m7 E${flat}", result.toString())
    }

    @Test
    fun testTransposeText_3() {
        val text = ChordsText("""
            Куплет
            F | F | B${flat} | B${flat} |

            Предприпев (Какая радость)
            C | Dm | B${flat} | F |
            C | Dm | B${flat} | F |
            C | C |

            Припев
            B${flat} | B${flat} | F | F | B${flat} | B${flat} | C | C |

            О-о-о
            B${flat} | B${flat} | F | F |

            Мост (В доме Отца)
            B${flat} | F | Dm | C |
        """.trimIndent())
        val result = text.transpose(Key("F"), Key("C")).toString()
        assertEquals("""
            Куплет
            C | C | F | F |

            Предприпев (Какая радость)
            G | Am | F | C |
            G | Am | F | C |
            G | G |

            Припев
            F | F | C | C | F | F | G | G |

            О-о-о
            F | F | C | C |

            Мост (В доме Отца)
            F | C | Am | G |
        """.trimIndent(), result)
    }

    @Test
    fun testTransposeText_4() {
        val text = ChordsText.fromPlainText("""
            Куплет
            F | F | Bb | Bb |

            Предприпев (Какая радость)
            C | Dm | Bb | F |
            C | Dm | Bb | F |
            C | C |

            Припев
            Bb | Bb | F | F | Bb | Bb | C | C |

            О-о-о
            Bb | Bb | F | F |

            Мост (В доме Отца)
            Bb | F | Dm | C |
        """.trimIndent())
        val result = text.transpose(Key("F"), Key("C")).toString()
        assertEquals("""
            Куплет
            C | C | F | F |

            Предприпев (Какая радость)
            G | Am | F | C |
            G | Am | F | C |
            G | G |

            Припев
            F | F | C | C | F | F | G | G |

            О-о-о
            F | F | C | C |

            Мост (В доме Отца)
            F | C | Am | G |
        """.trimIndent(), result)
    }

    @Test
    fun testTransposeReducingSpaces_1() {
        val text = ChordsText.fromPlainText("""
Fm             A♭
Ты есть огонь, а мы-Твой храм
      E♭           Cm
Ты - голос, а мы - песня Твоя
Fm                 A♭
Ты - наш Господь, мы - Твои дети
       E♭             Cm
Ты - свет, мы Тебе вверяем себя
        """.trimIndent())
        val result = text.transposeReducingSpaces(Key("Fm"), Key("C${sharp}m")).toString()
        assertEquals("""
C♯m            E
Ты есть огонь, а мы-Твой храм
      B            G♯m
Ты - голос, а мы - песня Твоя
C♯m                E
Ты - наш Господь, мы - Твои дети
       B              G♯m
Ты - свет, мы Тебе вверяем себя
        """.trimIndent(), result)
    }

    @Test
    fun testTransposeReducingSpaces_2() {
        val text = ChordsText.fromPlainText("""
            G 
Люблю Твой голос
          C                G 
Что проводил меня сквозь пламя
        D/F♯  Em          C            D 
Средь тьмы ночной Ты со мной, Бог, Ты рядом
              Em 
Я знаю, Ты - Отец мой
C                   G  D/F♯
 Я знаю, Ты - мой друг
Em    C          D         G 
 Окружён Твоей благостью, Бог
        """.trimIndent())
        val result = text.transposeReducingSpaces(Key("G"), Key("F♯")).toString()
        assertEquals("""
            F♯ 
Люблю Твой голос
          B                F♯ 
Что проводил меня сквозь пламя
        C♯/E♯ D♯m         B            C♯ 
Средь тьмы ночной Ты со мной, Бог, Ты рядом
              D♯m 
Я знаю, Ты - Отец мой
B                   F♯ C♯/E♯
 Я знаю, Ты - мой друг
D♯m   B          C♯        F♯ 
 Окружён Твоей благостью, Бог
        """.trimIndent(), result)
    }
}
