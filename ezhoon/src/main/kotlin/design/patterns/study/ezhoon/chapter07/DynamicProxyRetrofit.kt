package design.patterns.study.ezhoon.chapter07

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

data class Result(val code: Int, val data: Any?)

@kotlin.annotation.Target(AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class GET

@kotlin.annotation.Target(AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class POST


interface PostInterface {

    @POST
    fun postMethod(data: String): Result
}

interface GetInterface {

    @GET
    fun getMethod(): Result
}

interface ServiceInterface : PostInterface, GetInterface

class ClassCreator {

    private val invocationHandler = InvocationHandler { proxy, method, arrayOfAnys ->
        method.annotations.getOrNull(0)?.let { annotation ->
            return@InvocationHandler when (annotation) {
                is GET -> {
                    Result(200, "SUCCESS GET")
                }

                is POST -> {
                    Result(200, arrayOfAnys[0])
                }

                else -> this
            }
        }
    }

    fun <T> create(clazz: Class<T>): ServiceInterface {
        return Proxy.newProxyInstance(
            /* loader = */ clazz.classLoader,
            /* interfaces = */ arrayOf(ServiceInterface::class.java),
            /* h = */ invocationHandler
        ) as ServiceInterface
    }

    fun <T> createOnlyGet(clazz: Class<T>): GetInterface {
        return Proxy.newProxyInstance(
            /* loader = */ clazz.classLoader,
            /* interfaces = */ arrayOf(GetInterface::class.java),
            /* h = */ invocationHandler
        ) as GetInterface
    }

    fun <T> createGetAndPost(clazz: Class<T>): Any {
        return Proxy.newProxyInstance(
            /* loader = */ clazz.classLoader,
            /* interfaces = */ arrayOf(GetInterface::class.java, PostInterface::class.java),
            /* h = */ invocationHandler
        )
    }
}

fun main() {
    val myService: ServiceInterface = ClassCreator().create(ServiceInterface::class.java)

    println(myService.getMethod())
    println(myService.postMethod("Dataaaaaaaaaaaaa"))

    val myGetService: GetInterface = ClassCreator().createOnlyGet(ServiceInterface::class.java)

    println(myGetService.getMethod())

    val myGetAndPostService = ClassCreator().createGetAndPost(ServiceInterface::class.java)

    println((myGetAndPostService as GetInterface).getMethod())
    println((myGetAndPostService as PostInterface).postMethod("Dataaaaaaaaaaaaa"))
}