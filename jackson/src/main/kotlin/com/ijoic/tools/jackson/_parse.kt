package com.ijoic.tools.jackson

import com.fasterxml.jackson.databind.JsonNode
import kotlin.math.min

/**
 * Parse current text to json node or null
 */
fun String.toJsonNodeOrNull(ignoreError: Boolean = false): JsonNode? {
  return try {
    mapper.readTree(this)
  } catch (e: Exception) {
    if (!ignoreError) {
      e.printStackTrace()
    }
    null
  }
}

/* -- current node extensions :begin -- */

/**
 * Verify current node as boolean or null
 */
fun JsonNode.verifyAsBoolean(): Boolean? {
  if (this.isNull || !this.isBoolean) {
    return null
  }
  return this.asBoolean()
}

/**
 * Verify current node as string or null
 */
fun JsonNode.verifyAsString(): String? {
  if (this.isNull) {
    return null
  }
  return this.asText()
}

/**
 * Verify current node as string or null
 */
fun JsonNode.verifyAsInt(): Int? {
  if (this.isNull) {
    return null
  }
  if (!this.canConvertToInt()) {
    return null
  }
  return this.intValue()
}

/**
 * Verify current node as object
 */
fun JsonNode.verifyAsObject(): JsonNode? {
  if (!this.isObject) {
    return null
  }
  return this
}

/**
 * Verify current node as array
 */
fun JsonNode.verifyAsArray(): JsonNode? {
  if (!this.isArray) {
    return null
  }
  return this
}

/**
 * Verify current node as items list
 */
fun <T> JsonNode.verifyAsItemsList(limit: Int? = null, mapValue: (JsonNode) -> T?): List<T>? {
  if (!this.isArray) {
    return null
  }
  val itemSize = limit?.let { min(limit, this.size()) } ?: this.size()

  if (itemSize <= 0) {
    return null
  }
  val items = mutableListOf<T>()

  if (itemSize > 0) {
    for (i in 0 until itemSize) {
      val node = this.get(i)
      val value = node?.let(mapValue)

      if (value != null) {
        items.add(value)
      }
    }
  }
  return items
}

/* -- current node extensions :end -- */

/* -- field node extensions :begin -- */

/**
 * Verify [field] node as boolean or null
 */
fun JsonNode.verifyAsBoolean(field: String): Boolean? {
  return get(field)?.verifyAsBoolean()
}

/**
 * Verify [field] node as string or null
 */
fun JsonNode.verifyAsString(field: String): String? {
  return get(field)?.verifyAsString()
}

/**
 * Verify [field] node as string or null
 */
fun JsonNode.verifyAsInt(field: String): Int? {
  return get(field)?.verifyAsInt()
}

/**
 * Verify [field] node as object
 */
fun JsonNode.verifyAsObject(field: String): JsonNode? {
  return get(field)?.verifyAsObject()
}

/**
 * Verify [field] node as array
 */
fun JsonNode.verifyAsArray(field: String): JsonNode? {
  return get(field)?.verifyAsArray()
}

/**
 * Verify [field] node as items list
 */
fun <T> JsonNode.verifyAsItemsList(field: String, limit: Int? = null, mapValue: (JsonNode) -> T?): List<T>? {
  return get(field)?.verifyAsItemsList(limit, mapValue)
}

/* -- field node extensions :end -- */