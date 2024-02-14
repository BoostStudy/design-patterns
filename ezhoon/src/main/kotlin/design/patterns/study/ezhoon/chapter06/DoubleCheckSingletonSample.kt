package design.patterns.study.ezhoon.chapter06

class DoubleCheckSingletonSample private constructor() : SingletonSample {

    override var count: Int = 0

    companion object {

        @Volatile
        private var instance: DoubleCheckSingletonSample? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: DoubleCheckSingletonSample().also { instance = it }
        }
    }
}