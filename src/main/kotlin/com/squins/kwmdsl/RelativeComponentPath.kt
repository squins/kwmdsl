package com.squins.kwmdsl

/**
 * Determine the relative path from one component to another. This only works correctly if the paths are not the same.
 *
 * @param basePath the base path (i.e. the path of the referencing component). This must contain an element. The last
 * path part does not actually have to contain the ID of the referencing component, as it is not used. An empty string
 * is fine.
 * @param pathToRelativize the path for which to get the relative path to, starting from [basePath]. This must contain
 * an element.
 * @return the relative path from `basePath` to [pathToRelativize].
 */
internal fun relativizePath(basePath: List<String>, pathToRelativize: List<String>, separator: Char = ':'): String {
    val baseParentPathNumberOfElements = basePath.size - 1

    var firstPathElementNotInCommonIndex = 0
    while (basePath[firstPathElementNotInCommonIndex] == pathToRelativize[firstPathElementNotInCommonIndex] &&
        firstPathElementNotInCommonIndex < baseParentPathNumberOfElements &&
        firstPathElementNotInCommonIndex < pathToRelativize.size -1
    ) {
        firstPathElementNotInCommonIndex += 1
    }

    val numberOfParentOperators = baseParentPathNumberOfElements - firstPathElementNotInCommonIndex

    val parentOperators =
        (0 until numberOfParentOperators).fold(StringBuilder(numberOfParentOperators * 3)) { builder, _ ->
            builder.append("..").append(separator)
        }.toString()
    return parentOperators +
            pathToRelativize.subList(firstPathElementNotInCommonIndex, pathToRelativize.size).joinToString("$separator")
}
