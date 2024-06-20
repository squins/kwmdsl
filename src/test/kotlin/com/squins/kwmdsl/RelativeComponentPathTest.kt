package com.squins.kwmdsl

import kotlin.test.Test
import kotlin.test.assertEquals

class RelativeComponentPathTest {
    @Test
    fun `returns sibling name if both at root`() {
        val basePath = listOf("")
        val pathToRelativize = listOf("bar")
        val relativePath = relativizePath(basePath, pathToRelativize)

        assertEquals("bar", relativePath)
    }

    @Test
    fun `returns sibling name if both at 1 level deep`() {
        val basePath = listOf("l1", "")
        val pathToRelativize = listOf("l1", "bar")
        val relativePath = relativizePath(basePath, pathToRelativize)

        assertEquals("bar", relativePath)
    }

    @Test
    fun `returns sibling name if both at 2 levels deep`() {
        val basePath = listOf("l1", "l2", "")
        val pathToRelativize = listOf("l1", "l2", "bar")
        val relativePath = relativizePath(basePath, pathToRelativize)

        assertEquals("bar", relativePath)
    }

    @Test
    fun `returns self and child if child`() {
        val basePath = listOf("")
        val pathToRelativize = listOf("foo", "bar")
        val relativePath = relativizePath(basePath, pathToRelativize)

        assertEquals("foo:bar", relativePath)
    }

    @Test
    fun `returns self, child and grandchild if grandchild`() {
        val basePath = listOf("")
        val pathToRelativize = listOf("foo", "bar", "baz")
        val relativePath = relativizePath(basePath, pathToRelativize)

        assertEquals("foo:bar:baz", relativePath)
    }

    @Test
    fun `returns correct path to sibling of parent`() {
        val basePath = listOf("l1", "")
        val pathToRelativize = listOf("bar")
        val relativePath = relativizePath(basePath, pathToRelativize)

        assertEquals("..:bar", relativePath)
    }

    @Test
    fun `returns correct path to sibling of grandparent`() {
        val basePath = listOf("l1", "l2", "")
        val pathToRelativize = listOf("bar")
        val relativePath = relativizePath(basePath, pathToRelativize)

        assertEquals("..:..:bar", relativePath)
    }

    @Test
    fun `returns correct path to child of sibling`() {
        val basePath = listOf("")
        val pathToRelativize = listOf("bar", "baz")
        val relativePath = relativizePath(basePath, pathToRelativize)

        assertEquals("bar:baz", relativePath)
    }

    @Test
    fun `returns correct path to grandchild of sibling`() {
        val basePath = listOf("")
        val pathToRelativize = listOf("bar", "baz", "qux")
        val relativePath = relativizePath(basePath, pathToRelativize)

        assertEquals("bar:baz:qux", relativePath)
    }

    @Test
    fun `returns correct path if first level is not the same`() {
        val basePath = listOf("l1", "")
        val pathToRelativize = listOf("m1", "bar")
        val relativePath = relativizePath(basePath, pathToRelativize)

        assertEquals("..:m1:bar", relativePath)
    }

    @Test
    fun `returns correct path if first 2 levels are not the same`() {
        val basePath = listOf("l1", "l2", "")
        val pathToRelativize = listOf("m1", "m2", "bar")
        val relativePath = relativizePath(basePath, pathToRelativize)

        assertEquals("..:..:m1:m2:bar", relativePath)
    }
}
