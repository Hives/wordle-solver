package worldle_solver

import worldle_solver.LetterKnowledge.Absence
import worldle_solver.LetterKnowledge.Locations
import worldle_solver.LetterKnowledge.Presence

fun List<Char>.isCompatibleWith(knowledge: Knowledge): Boolean {

    knowledge.toList().forEach { (letter, letterKnowledge) ->
        when (letterKnowledge) {
            is Absence -> if (this.contains(letter)) return false
            is Locations -> letterKnowledge.values.forEach { index ->
                if (this[index] != letter) return false
            }
            is Presence -> {
                if (!this.contains(letter)) return false
                letterKnowledge.locationIsNot.forEach { index ->
                    if (this[index] == letter) return false
                }
            }
        }
    }

    return true
}
