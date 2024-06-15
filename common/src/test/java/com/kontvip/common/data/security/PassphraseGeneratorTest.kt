package com.kontvip.common.data.security

import org.junit.Assert.*
import org.junit.Test

class PassphraseGeneratorTest {

    @Test
    fun `generate method should return non-zero length ByteArray`() {
        val passphraseGenerator = PassphraseGenerator.Default()
        val result = passphraseGenerator.generate()

        assertEquals(32, result.size)
        assertEquals(false, result.contains(0))
    }
}