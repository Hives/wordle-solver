package worldle_solver

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import worldle_solver.Validation.ABSENT
import worldle_solver.Validation.CORRECT
import worldle_solver.Validation.PRESENT
import worldle_solver.LetterKnowledge.Absence
import worldle_solver.LetterKnowledge.Locations
import worldle_solver.LetterKnowledge.Presence

internal class AddToKnowledgeKtTest {
    private val emptyKnowledge = emptyMap<Char, LetterKnowledge>()

    @Test
    fun `can add a validated guess to an empty knowledge`() {
        val guess = "abccd".toList()
        val validation = listOf(CORRECT, ABSENT, PRESENT, PRESENT, ABSENT)
        val knowledge = emptyKnowledge.addNewInfo(guess, validation)

        assertThat(knowledge).isEqualTo(mapOf(
            'a' to Locations(listOf(0)),
            'b' to Absence,
            'c' to Presence(listOf(2, 3)),
            'd' to Absence
        ))
    }

    @Test
    fun `can add a validated guess to existing knowledge`() {
        val existingKnowledge = mapOf(
            'a' to Locations(listOf(5)),
            'b' to Presence(locationIsNot = listOf(1, 3)),
            'c' to Presence(locationIsNot = listOf(1))
        )

        val guess = "acbXX".toList()
        val validation = listOf(CORRECT, CORRECT, PRESENT, ABSENT, ABSENT)

        val knowledge = existingKnowledge.addNewInfo(guess, validation)

        assertThat(knowledge).isEqualTo(mapOf(
            'a' to Locations(listOf(0, 5)),
            'b' to Presence(locationIsNot = listOf(1, 2, 3)),
            'c' to Locations(listOf(1)),
            'X' to Absence
        ))
    }
}