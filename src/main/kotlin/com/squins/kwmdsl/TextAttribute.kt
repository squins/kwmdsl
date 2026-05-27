package com.squins.kwmdsl

/**
 * Create an attribute containing text. This is useful for attributes for which a specific function is not available.
 *
 * @param name the attribute name.
 * @param value the attribute value. The value will be output as either `false` or `true`.
 */
fun attr(name: String, value: Boolean) = name to Text(value.toString())

/**
 * Create an attribute containing text. This is useful for attributes for which a specific function is not available.
 *
 * @param name the attribute name.
 * @param value the attribute value. The decimal representation will be output.
 */
fun attr(name: String, value: Byte) = name to Text(value.toString())

/**
 * Create an attribute containing text. This is useful for attributes for which a specific function is not available.
 *
 * @param name the attribute name.
 * @param value the attribute value.
 */
fun attr(name: String, value: Char) = name to Text(value.toString())

/**
 * Create an attribute containing text. This is useful for attributes for which a specific function is not available.
 *
 * @param name the attribute name.
 * @param value the attribute value. The decimal representation will be output.
 */
fun attr(name: String, value: Int) = name to Text(value.toString())

/**
 * Create an attribute containing text. This is useful for attributes for which a specific function is not available.
 *
 * @param name the attribute name.
 * @param value the attribute value. The decimal representation will be output.
 */
fun attr(name: String, value: Long) = name to Text(value.toString())

/**
 * Create an attribute containing text. This is useful for attributes for which a specific function is not available.
 *
 * @param name the attribute name.
 * @param value the attribute value. The decimal representation will be output.
 */
fun attr(name: String, value: Short) = name to Text(value.toString())

/**
 * Create an attribute containing text. This is useful for attributes for which a specific function is not available.
 *
 * @param name the attribute name.
 * @param value the attribute value.
 */
fun attr(name: String, value: String) = name to Text(value)
