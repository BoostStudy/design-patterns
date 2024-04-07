package design.patterns.study.audxo112.chapter06

class DIFactory {
    private val objects = mutableMapOf<String, Any>()
    private val definitions = mutableMapOf<String, Definition>()

    fun addDefinitions(list: List<Definition>) {
        list.forEach {
            definitions[it.className] = it
        }

        list.forEach {
            if (it.isSingleton) {
                create(it)
            }
        }
    }

    fun get(className: String): Any {
        val definition = definitions[className] ?: throw NullPointerException("Null")
        return create(definition)
    }

    fun create(definition: Definition): Any {
        if (definition.isSingleton && objects.contains(definition.className)) {
            return objects[definition.className] ?: throw NullPointerException("알 수 없는 객체 입니다")
        }

        val clazz = Class.forName(definition.className)
        val args = definition.args
        val obj = if (args.isEmpty()) {
            clazz.getDeclaredConstructor().newInstance()
        } else {
            val argClasses = arrayOfNulls<Class<*>>(args.size)
            val argObjects = arrayOfNulls<Any>(args.size)
            args.forEachIndexed { i, arg ->
                if (!arg.isRef) {
                    argClasses[i] = arg.type
                    argObjects[i] = arg.obj
                } else {
                    val refDefinition = definitions[arg.type.name] ?: throw NullPointerException("알 수 없는 Ref 객체 입니다")
                    argClasses[i] = arg.type
                    argObjects[i] = create(refDefinition)
                }
            }
            clazz.getConstructor(*argClasses).newInstance(*argObjects)
        } ?: throw NullPointerException("알 수 없는 객체 입니다")

        if (definition.isSingleton) {
            objects[definition.className] = obj
        }

        return obj
    }
}

data class C(val value: Int)

data class B(val c: C)

data class A(val b: B)

class Definition(
    val isSingleton: Boolean,
    val className: String,
    val args: List<DefinitionArg>,
)

class DefinitionArg(
    val isRef: Boolean,
    val type: Class<*>,
    val obj: Any? = null,
)

fun main() {
    val factory = DIFactory()
    val definitions = listOf(
        Definition(
            isSingleton = true,
            className = "design.patterns.study.audxo112.chapter06.A",
            args = listOf(
                DefinitionArg(
                    isRef = true,
                    type = B::class.java,
                )
            )
        ),
        Definition(
            isSingleton = true,
            className = "design.patterns.study.audxo112.chapter06.B",
            args = listOf(
                DefinitionArg(
                    isRef = true,
                    type = C::class.java,
                )
            )
        ),
        Definition(
            isSingleton = true,
            className = "design.patterns.study.audxo112.chapter06.C",
            args = listOf(
                DefinitionArg(
                    isRef = false,
                    type = Int::class.java,
                    obj = 10,
                )
            )
        ),
    )
    factory.addDefinitions(definitions)

    val a = factory.get("design.patterns.study.audxo112.chapter06.A") as A
    val b = factory.get("design.patterns.study.audxo112.chapter06.B") as B
    val c1 = factory.get("design.patterns.study.audxo112.chapter06.C") as C

    val c2 = factory.get("design.patterns.study.audxo112.chapter06.C") as C

    println("[${System.identityHashCode(a)}] $a")
    println("[${System.identityHashCode(b)}] $b")
    println("[${System.identityHashCode(c1)}] $c1")
    println("[${System.identityHashCode(c2)}] $c2")
}