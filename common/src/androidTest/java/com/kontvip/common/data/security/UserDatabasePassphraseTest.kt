package com.kontvip.common.data.security

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.junit.Test
import java.io.File

class UserDatabasePassphraseDefaultTest {

    private lateinit var context: Context
    private lateinit var passphraseGenerator: PassphraseGenerator

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        passphraseGenerator = object : PassphraseGenerator {
            override fun generate(): ByteArray {
                return "mock_passphrase".toByteArray()
            }
        }
    }

    @Test
    fun getPassphrase_whenFileExists() {
        deletePassphraseFileIfExists()
        val mockFile = File.createTempFile("user_passphrase.bin", null, context.filesDir)
        mockFile.delete()
        mockFile.writeBytes("mock_passphrase".toByteArray())

        val userDatabasePassphrase = UserDatabasePassphrase.Default(context, passphraseGenerator)

        val result = userDatabasePassphrase.getPassphrase()

        assertArrayEquals("mock_passphrase".toByteArray(), result)

        mockFile.delete()
    }

    @Test
    fun getPassphrase_whenFileDoesNotExist() {
        deletePassphraseFileIfExists()
        val userDatabasePassphrase = UserDatabasePassphrase.Default(context, passphraseGenerator)

        val result = userDatabasePassphrase.getPassphrase()

        assertArrayEquals("mock_passphrase".toByteArray(), result)
    }

    private fun deletePassphraseFileIfExists() {
        val file = File(context.filesDir, "user_passphrase.bin")
        if (file.exists()) {
            file.delete()
        }
    }
}