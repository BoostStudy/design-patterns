package design.patterns.study.audxo112.chapter06

import java.text.NumberFormat
import java.util.*

class Factory {

    fun example() {
        // Locale 에 따라 다른 출력을 줘야 하므로
        Calendar.getInstance()
        NumberFormat.getInstance()
    }

    class Editor private constructor(
        val type: String,
    ) {
        companion object {
            fun newPhotoEditor() = Editor("Photo")

            fun newVideoEditor() = Editor("Video")
        }
    }

    fun example2() {
        println("[함수 이름을 통해 목적을 나타낼 수 있다]")
        val photoEditor = Editor.newPhotoEditor()
        val videoEditor = Editor.newVideoEditor()

        println("[photoEditor] : ${photoEditor.type}")
        println("[videoEditor] : ${videoEditor.type}")
        println()
    }

    object Cache {
        private val dateMap = mutableMapOf<String, Time>()

        fun getCreateTime(name: String): Time {
            return dateMap.getOrPut(name) {
                Time(System.currentTimeMillis())
            }
        }
    }

    data class Time(val time: Long)

    fun example3() {
        println("[캐싱을 할 수 있다]")

        val nameList = listOf("apple", "orange", "apple")
        for (name in nameList) {
            val time = Cache.getCreateTime(name)
            println("[$name] (${System.identityHashCode(time)}) $time ")
        }
        println()
    }

    open class Grade {
        companion object {
            fun of(score: Int): Grade = when {
                score < 50 -> Normal
                score < 80 -> Advanced
                else -> God
            }
        }
    }

    data object Normal: Grade()

    data object Advanced: Grade()

    data object God: Grade()

    fun example4() {
        println("[하위 자료형을 반환할 수 있다]")

        val scoreList = listOf(40, 55, 65, 88)
        for (score in scoreList) {
            println("[$score] - ${Grade.of(score)}")
        }

        println()
    }

    data class Data(
        val id: Long,
        val name: String,
    ) {
        companion object {
            fun transfer(dto: DataDto): Data {
                return Data(dto.id, dto.name)
            }
        }
    }

    data class DataDto(
        val id: Long,
        val name: String,
        val version: Int,
        val updated: Long,
        val created: Long,
    )

    fun example5() {
        println("[mapper 도 정적 팩토리의 일종]")

        val dataDto = DataDto(1, "name", 1, System.currentTimeMillis(), System.currentTimeMillis())
        val data = Data.transfer(dataDto)
        println("dto : $dataDto")
        println("data : $data")

        println()
    }
}



fun main() {
    val factory = Factory()
    factory.example()
    factory.example2()
    factory.example3()
    factory.example4()
    factory.example5()
}