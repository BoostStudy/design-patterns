package design.patterns.study.ezhoon.chapter06

interface TEST {
    val name: String
}

enum class ENUM : TEST {
    A
}

fun main () {
    val test: TEST = ENUM.A
    println(test.name)
}