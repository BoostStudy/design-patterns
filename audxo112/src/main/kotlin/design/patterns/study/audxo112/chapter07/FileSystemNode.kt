package design.patterns.study.audxo112.chapter07

abstract class FileSystemNode(
    val path: String,
) {
    protected var parent: Directory? = null
    fun setParent(dir: Directory?) {
        parent = dir
    }

    abstract fun countNumOfFiles(): Int
    abstract fun countSizeOfFiles(): Long
}