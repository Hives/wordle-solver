package worldle_solver

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test
import worldle_solver.LetterKnowledge.Absence
import worldle_solver.LetterKnowledge.Locations
import worldle_solver.LetterKnowledge.Presence

internal class WordIsCompatibleWithKnowledgeKtTest {
    @Test
    fun `word is not compatible with knowledge if it contains a letter that should be absent`() {
        val word = "xaxxx".toList()
        val knowledge = mapOf('a' to Absence)
        assertThat(word.isCompatibleWith(knowledge)).isFalse()
    }

    @Test
    fun `word is compatible with knowledge if it contains a letter in the same place as a known location`() {
        val word = "axxxx".toList()
        val knowledge = mapOf('a' to Locations(listOf(0)))
        assertThat(word.isCompatibleWith(knowledge)).isTrue()
    }

    @Test
    fun `word is compatible with knowledge if it contains a letter in a different place to the known locations`() {
        val word = "axxxa".toList()
        val knowledge = mapOf('a' to Locations(listOf(0)))
        assertThat(word.isCompatibleWith(knowledge)).isTrue()
    }

    @Test
    fun `word is not compatible with knowledge if it contains a letter in a place it is known not to be`() {
        val word = "axxxx".toList()
        val knowledge = mapOf('a' to Presence(locationIsNot = listOf(0)))
        assertThat(word.isCompatibleWith(knowledge)).isFalse()
    }

    @Test
    fun `word is compatible with knowledge if it contains a letter in a place other than one where it is known not to be`() {
        val word = "xaxxx".toList()
        val knowledge = mapOf('a' to Presence(locationIsNot = listOf(0)))
        assertThat(word.isCompatibleWith(knowledge)).isTrue()
    }

    @Test
    fun `word is not compatible with knowledge if it does not contains a letter which is known to be present`() {
        val word = "xxxxx".toList()
        val knowledge = mapOf('a' to Presence(locationIsNot = listOf(0)))
        assertThat(word.isCompatibleWith(knowledge)).isFalse()
    }
}