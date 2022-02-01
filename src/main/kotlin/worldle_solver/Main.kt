package worldle_solver

fun main() {
    val words = GetResource.getIt("words.txt").map(String::toList)

    var knowledge: Knowledge = emptyMap()
    var guess = "raise".toList()
    var count = 1
    val guesses = mutableListOf<List<Char>>()

    while (true) {
        println()
        println("Guess $count: ${guess.joinToString("")}")
        println()

        var input: String?
        var inputIsOk: Boolean
        do {
            println("Enter validations. [c]orrect, [p]resent, [a]bsent, e.g. \"aapac\"")
            input = readLine()
            inputIsOk = (input != null && input.length == 5 && (input.toSet() - 'a' - 'p' - 'c').isEmpty())
        } while (!inputIsOk)

        val validations = input!!.toList().map {
            when (it) {
                'a' -> Validation.ABSENT
                'p' -> Validation.PRESENT
                'c' -> Validation.CORRECT
                else -> throw RuntimeException("wtf")
            }
        }

        guesses.add(guess)
        count += 1
        knowledge = knowledge.addNewInfo(guess, validations)

        val potential = words.filter { it.isCompatibleWith(knowledge) } - guesses.toSet()

        val assessedGuesses =
            potential.map { word -> Pair(word, assessGuess(word, knowledge, potential)) }.sortedBy { it.second }

        println()
        println("Top choices:")
        assessedGuesses.take(10).forEach { (word, score) ->
            println("${word.joinToString("")} - ${score}")
        }

        guess = assessedGuesses.first().first
    }
}