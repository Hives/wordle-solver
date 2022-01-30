package worldle_solver

typealias Knowledge = Map<Char, LetterKnowledge>

sealed class LetterKnowledge {
    data class Locations(val values: List<Int>): LetterKnowledge()
    data class Presence(val locationIsNot: List<Int>): LetterKnowledge()
    object Absence: LetterKnowledge()
}
