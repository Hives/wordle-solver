package worldle_solver

import worldle_solver.Validation.ABSENT
import worldle_solver.Validation.CORRECT
import worldle_solver.Validation.PRESENT
import worldle_solver.LetterKnowledge.Absence
import worldle_solver.LetterKnowledge.Locations
import worldle_solver.LetterKnowledge.Presence

fun Knowledge.addNewInfo(guess: List<Char>, validations: List<Validation>): Knowledge {
    val mutableKnowledge = this.toMutableMap()

    guess.zip(validations).forEachIndexed { index, (char, assessment) ->
        val previousKnowledge = mutableKnowledge[char]

        mutableKnowledge[char] = when (assessment) {
            CORRECT -> {
                if (previousKnowledge is Locations) {
                    Locations((previousKnowledge.values + index).sorted())
                } else Locations(listOf(index))
            }
            PRESENT -> {
                if (previousKnowledge is Presence) {
                    Presence(locationIsNot = (previousKnowledge.locationIsNot + index).sorted())
                } else Presence(locationIsNot = listOf(index))
            }
            ABSENT -> Absence
        }
    }

    return mutableKnowledge.toMap()
}
