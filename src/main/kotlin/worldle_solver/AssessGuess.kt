package worldle_solver

fun assessGuess(guess: List<Char>, knowledge: Knowledge, possibleWords: List<List<Char>>): Double =
    possibleWords
        .filter { it.isCompatibleWith(knowledge) }
        .map { possibleAnswer ->
            val validation = validateGuess(guess, possibleAnswer)
            val newKnowledge = knowledge.addNewInfo(guess, validation)
            val remainingPossibilities = possibleWords.filter { it.isCompatibleWith(newKnowledge) }
            remainingPossibilities.size
        }
        .filter { it > 0 }
        .average()
