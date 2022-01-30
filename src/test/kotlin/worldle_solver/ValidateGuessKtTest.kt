package worldle_solver

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import worldle_solver.Validation.ABSENT
import worldle_solver.Validation.CORRECT
import worldle_solver.Validation.PRESENT

internal class ValidateGuessKtTest {
    @Test
    fun `can validate a guess`() {
        val validation = validateGuess(
            guess = "abcde".toList(),
            answer = "abdxx".toList()
        )
        assertThat(validation).isEqualTo(listOf(CORRECT, CORRECT, ABSENT, PRESENT, ABSENT))
    }
}