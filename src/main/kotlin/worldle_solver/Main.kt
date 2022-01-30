package worldle_solver

fun main() {
    val words = GetResource.getIt("words.txt").map(String::toList)

    val mutableColl = mutableListOf<Pair<String, Double>>()

    words.forEachIndexed { index, word ->
        val assessment = assessGuess(word, emptyMap(), words)
        println("(${index}/${words.size}) ${word.joinToString("")}: $assessment")
        mutableColl.add(Pair(word.joinToString(""), assessment))
    }

    println("-----------------------")

    val coll = mutableColl.toList()

    coll.sortedBy { it.second }.take(10).forEach { (word, assessment) ->
        println("$word: $assessment")
    }
}

