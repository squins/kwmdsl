package com.squins.kwmdsl

// TODO("Document: assumption paths are not empty")
// TODO("Document: assumption paths are not the same")
internal fun relativizePath(basePath: List<String>, pathToRelativize: List<String>): String {
    val baseParentPathNumberOfElements = basePath.size - 1

    var firstPathElementNotInCommonIndex = 0
    while (basePath[firstPathElementNotInCommonIndex] == pathToRelativize[firstPathElementNotInCommonIndex] &&
        firstPathElementNotInCommonIndex < baseParentPathNumberOfElements &&
        firstPathElementNotInCommonIndex < pathToRelativize.size
    ) {
        firstPathElementNotInCommonIndex += 1
    }

    val numberOfParentOperators = baseParentPathNumberOfElements - firstPathElementNotInCommonIndex

    val parentOperators =
        (0 until numberOfParentOperators).fold(StringBuilder(numberOfParentOperators * 3)) { builder, _ ->
            builder.append("..:")
        }.toString()
    return parentOperators +
            pathToRelativize.subList(firstPathElementNotInCommonIndex, pathToRelativize.size).joinToString(":")
}
