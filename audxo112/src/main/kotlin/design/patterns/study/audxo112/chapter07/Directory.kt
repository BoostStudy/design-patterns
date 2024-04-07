package design.patterns.study.audxo112.chapter07

class Directory(path: String) : FileSystemNode(path) {
    private val subNodes = mutableListOf<FileSystemNode>()
    private val nodeInfo = mutableMapOf<String, NodeInfo>()

    override fun countNumOfFiles(): Int {
        return nodeInfo.values.sumOf { it.countNum }
    }

    override fun countSizeOfFiles(): Long {
        return nodeInfo.values.sumOf { it.size }
    }

    fun updateNodeInfo(dir: Directory?, file: FileSystemNode) {
        dir ?: return

        nodeInfo[file.path] = NodeInfo(file.countNumOfFiles(), file.countSizeOfFiles())
        updateNodeInfo(dir.parent, dir)
    }

    fun addSubNode(file: FileSystemNode) {
        if (subNodes.find { it.path.equals(file.path, true) } != null) {
            return
        }

        file.setParent(this)
        subNodes.add(file)
        nodeInfo[file.path] = NodeInfo(file.countNumOfFiles(), file.countSizeOfFiles())
        updateNodeInfo(parent, this)
    }

    fun removeSubNode(file: FileSystemNode) {
        subNodes.removeIf { it.path.equals(file.path, true) }
        nodeInfo.remove(file.path)
        updateNodeInfo(parent, this)
    }
}

class NodeInfo(
    val countNum: Int,
    val size: Long,
)
