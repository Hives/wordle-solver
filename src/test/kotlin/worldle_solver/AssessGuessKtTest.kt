package worldle_solver

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import worldle_solver.LetterKnowledge.Absence

internal class AssessGuessKtTest {
    @Test
    fun `if a guess must be right, it should return 1`() {
        val possibleWord = listOf("abcde", "fghij", "lmnop").map { it.toList() }
        val knowledge = mapOf(
            'a' to Absence,
            'f' to Absence
        )
        val guess = "lmnop".toList()

        assertThat(assessGuess(guess, knowledge, possibleWord)).isEqualTo(1.0)
    }

    @Test
    fun `given a guess, some knowledge, and the list of possible words, it returns the average number of remaining possibilities`() {
        val possibleWords = listOf("aider", "aisle", "alarm", "album", "alert").map { it.toList() }
        val knowledge = mapOf(
            'i' to Absence,
        )
        val guess = "mXXXX".toList()

        val averageRemainingPossibilities = assessGuess(guess, knowledge, possibleWords)

        assertThat(averageRemainingPossibilities).isEqualTo((2 + 2 + 1) / 3.0)
    }
}