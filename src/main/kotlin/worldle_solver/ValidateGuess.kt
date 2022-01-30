package worldle_solver

import worldle_solver.Validation.ABSENT
import worldle_solver.Validation.CORRECT
import worldle_solver.Validation.PRESENT

fun validateGuess(guess: List<Char>, answer: List<Char>): List<Validation> =
    guess.mapIndexed { index, c ->
        when {
            answer[index] == c -> CORRECT
            answer.contains(c) -> PRESENT
            else -> ABSENT
        }
    }
