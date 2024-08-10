package kwmdsl.examples

fun classSourceFilename(companion: Any): String {
    val classSimpleName = checkNotNull(
        checkNotNull(companion::class)
            .qualifiedName
    )
        .removeSuffix(".Companion")
        .substringAfterLast('.')
    return "$classSimpleName.kt"
}
