package design.patterns.study.audxo112.chapter07

import java.io.File as JFile


class File(path: String) : FileSystemNode(path) {

    override fun countNumOfFiles(): Int {
        return 1
    }

    override fun countSizeOfFiles(): Long {
        val file = JFile(path)
        if (!file.exists()) {
            return 0
        }
        return file.length()
    }
}