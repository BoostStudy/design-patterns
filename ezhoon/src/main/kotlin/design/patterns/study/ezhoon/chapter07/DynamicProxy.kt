package design.patterns.study.ezhoon.chapter07

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

interface Hello {
    fun sayHello()
}

class HelloWorld : Hello {
    override fun sayHello() {
        println("Hello, World!")
    }
}

class DynamicProxy(private val obj: Any) : InvocationHandler {
    override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any? {
        println("Before invoking method: ${method.name}")
        val result = method.invoke(obj, *(args ?: emptyArray()))
        println("After invoking method: ${method.name}")
        return result
    }
}

fun main() {
    val helloWorld = HelloWorld()
    val helloProxy = Proxy.newProxyInstance(
        Hello::class.java.classLoader,
        arrayOf(Hello::class.java),
        DynamicProxy(helloWorld)
    ) as Hello

    helloProxy.sayHello()
}