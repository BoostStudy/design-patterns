package design.patterns.study.ezhoon.chapter06

class LazyLoadingSingletonSampleSample private constructor() : SingletonSample {

    override var count = 0

    companion object {

        private val lazyLoadingSingletonSample by lazy { LazyLoadingSingletonSampleSample() }

        const val testInt = 0

        fun getInstance(): LazyLoadingSingletonSampleSample {
            return lazyLoadingSingletonSample
        }
    }
}