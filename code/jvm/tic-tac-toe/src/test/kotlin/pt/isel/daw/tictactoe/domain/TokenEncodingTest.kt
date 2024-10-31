package pt.isel.daw.tictactoe.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.time.Duration


class TokenEncodingTest {
    private val token = Random.nextBytes(ByteArray(100)).decodeToString()
    private val sha256 = Sha256TokenEncoder()

    @Test
    fun `encoded token is different from original token`() {
        assertNotEquals(sha256.createValidationInformation(token).validationInfo,
                        token)
    }

    @Test
    fun `token encoder is deterministic`() {
        val encoded1 = sha256.createValidationInformation(token)
        val encoded2 = sha256.createValidationInformation(token)

        assertEquals(encoded1, encoded2)
    }
}