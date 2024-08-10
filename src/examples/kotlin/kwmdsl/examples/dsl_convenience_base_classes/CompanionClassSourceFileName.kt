package kwmdsl.examples.dsl_convenience_base_classes

fun classSourceFilename(companion: Any): String {
    val classSimpleName = checkNotNull(
        checkNotNull(companion::class)
            .qualifiedName
    )
        .removeSuffix(".Companion")
        .substringAfterLast('.')
    return "$classSimpleName.kt"
}
