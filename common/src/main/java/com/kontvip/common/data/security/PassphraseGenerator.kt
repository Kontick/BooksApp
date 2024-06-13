package com.kontvip.common.data.security

import android.os.Build
import java.security.SecureRandom

interface PassphraseGenerator {

    fun generate(): ByteArray

    class Default : PassphraseGenerator {
        override fun generate(): ByteArray {
            val random = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                SecureRandom.getInstanceStrong()
            } else {
                SecureRandom()
            }
            val result = ByteArray(32)

            random.nextBytes(result)
            while (result.contains(0)) {
                random.nextBytes(result)
            }

            return result
        }
    }

}