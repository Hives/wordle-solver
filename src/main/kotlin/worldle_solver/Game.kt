package worldle_solver

import java.io.File

fun main() {
    val words = GetResource.getIt("words.txt").map(String::toList)

    File("wordle-scores.txt").printWriter().use { out ->
        words.forEachIndexed { index, answer ->
            if (index % 20 == 0) {
                println("$index/${words.size}")
                out.flush()
            }
            val (score, guesses) = Game(answer, words).play()
            out.println("${answer.joinToString("")}, $score, ${guesses.joinToString { it.joinToString("") }}")
        }
    }

    println("done")
}

class Game(private val answer: List<Char>, words: List<List<Char>>) {
    private var knowledge: Knowledge = emptyMap()
    private var possibleWords = words
    private var turn = 1;
    private val guesses = mutableListOf<List<Char>>()
    private var nextGuess = "raise".toList()

    tailrec fun play(): Pair<Int, List<List<Char>>> {
//        println("guess number $turn: $nextGuess")
        guesses += nextGuess

        if (nextGuess == answer) {
            return Pair(turn, guesses)
        }

        val validations = validateGuess(nextGuess, answer)
        knowledge = knowledge.addNewInfo(nextGuess, validations)
        possibleWords = possibleWords.filter { it.isCompatibleWith(knowledge) } - guesses

        nextGuess =
            possibleWords.minByOrNull { word -> assessGuess(word, knowledge, possibleWords) }!!
        turn += 1

        return play()
    }
}
