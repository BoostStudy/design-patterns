package design.patterns.study.ezhoon

import design.patterns.study.ezhoon.chapter06.DoubleCheckSingletonSample
import design.patterns.study.ezhoon.chapter06.LazyLoadingSingletonSampleSample
import design.patterns.study.ezhoon.chapter06.ObjectSample
import design.patterns.study.ezhoon.chapter06.SingletonSample
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


class SingletonSampleTest {
    @ParameterizedTest(name = "{1}")
    @MethodSource("provideSingletonFactories")
    fun `싱글톤 객체 테스트`(singleTonSamples: `싱글톤 페어`, displayName: String) {
        val firstSingleton = singleTonSamples.first
        val secondSingleton = singleTonSamples.second

        `singleton이므로 둘의 hashCode는 같아야 한다`(firstSingleton, secondSingleton)
        `firstSingleton에서 testInt 변경된 값이 secondSingleton에서 호출해도 반영돼 있어야 한다`(firstSingleton, secondSingleton)
    }

    private fun `singleton이므로 둘의 hashCode는 같아야 한다`(firstSingleton: SingletonSample, secondSingleton: SingletonSample) {
        assertEquals(firstSingleton.hashCode(), secondSingleton.hashCode())
    }

    private fun `firstSingleton에서 testInt 변경된 값이 secondSingleton에서 호출해도 반영돼 있어야 한다`(firstSingleton: SingletonSample, secondSingleton: SingletonSample) {
        firstSingleton.count += 1
        secondSingleton.count += 1
        assertEquals(firstSingleton.count, secondSingleton.count)
    }

    companion object {
        @JvmStatic
        fun provideSingletonFactories(): Stream<Arguments> = Stream.of(
            Arguments.of(Pair(ObjectSample, ObjectSample), "Object 클래스를 사용한 싱글턴 테스트"),
            Arguments.of(Pair(LazyLoadingSingletonSampleSample.getInstance(), LazyLoadingSingletonSampleSample.getInstance()), "by lazy를 활용한 싱글턴 테스트"),
            Arguments.of(Pair(DoubleCheckSingletonSample.getInstance(), DoubleCheckSingletonSample.getInstance()), "double check를 활용한 싱글턴 테스트"),
        )
    }
}

typealias `싱글톤 페어` = Pair<SingletonSample, SingletonSample>
