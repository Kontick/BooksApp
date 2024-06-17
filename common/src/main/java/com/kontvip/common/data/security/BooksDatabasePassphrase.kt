package com.kontvip.common.data.security

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import java.io.File

interface BooksDatabasePassphrase {

    fun getPassphrase(): ByteArray

    class Default(
        private val context: Context,
        private val passphraseGenerator: PassphraseGenerator
    ) : BooksDatabasePassphrase {

        companion object {
            private const val PASSPHRASE_FILE_NAME = "user_passphrase.bin"
        }

        override fun getPassphrase(): ByteArray {
            val file = File(context.filesDir, PASSPHRASE_FILE_NAME)
            val encryptedFile = EncryptedFile.Builder(
                file,
                context,
                MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()

            return if (file.exists()) {
                encryptedFile.openFileInput().use { it.readBytes() }
            } else {
                passphraseGenerator.generate().also { passPhrase ->
                    encryptedFile.openFileOutput().use { it.write(passPhrase) }
                }
            }
        }

    }

}