package design.patterns.study.ezhoon.chapter06

class LazyLoadingSingletonSampleSample private constructor() : SingletonSample {

    override var count = 0

    companion object {

        private val lazyLoadingSingletonSample by lazy { LazyLoadingSingletonSampleSample() }

        fun getInstance(): LazyLoadingSingletonSampleSample {
            return lazyLoadingSingletonSample
        }
    }
}