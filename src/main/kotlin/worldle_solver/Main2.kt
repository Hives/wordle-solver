package worldle_solver

import worldle_solver.LetterKnowledge.Absence

fun main() {
    val words = GetResource.getIt("words.txt").map(String::toList)

    val knowledge = mapOf(
        'r' to LetterKnowledge.Locations(listOf(1)),
        'u' to LetterKnowledge.Locations(listOf(2)),
        'a' to Absence,
        'i' to Absence,
        's' to Absence,
        'e' to Absence,
        't' to Absence,
        'l' to Absence,
        'y' to Absence,
    )

    val potential = words.filter { it.isCompatibleWith(knowledge) }

    val mutableColl = mutableListOf<Pair<String, Double>>()

    potential.forEachIndexed { index, word ->
        val assessment = assessGuess(word, emptyMap(), potential)
        println("(${index}/${potential.size}) ${word.joinToString("")}: $assessment")
        mutableColl.add(Pair(word.joinToString(""), assessment))
    }

    println("-----------------------")

    val coll = mutableColl.toList()

    coll.sortedBy { it.second }.take(10).forEach { (word, assessment) ->
        println("$word: $assessment")
    }
}