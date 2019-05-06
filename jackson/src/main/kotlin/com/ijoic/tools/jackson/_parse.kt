package com.ijoic.tools.jackson

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import java.lang.Exception

/**
 * Parse current text to json node or null
 */
fun String.toJsonNodeOrNull(): JsonNode? {
  val mapper = ObjectMapper()

  return try {
    mapper.readTree(this)
  } catch (e: Exception) {
    e.printStackTrace()
    null
  }
}

/* -- current node extensions :begin -- */

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

/* -- current node extensions :end -- */

/* -- field node extensions :begin -- */

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

/* -- field node extensions :end -- */