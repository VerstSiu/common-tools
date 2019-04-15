package com.ijoic.tools

/**
 * Returns cached or new created value of [key]
 */
fun <KEY, VALUE> MutableMap<KEY, VALUE>.getOrCreate(key: KEY, createValue: () -> VALUE): VALUE {
  return this[key] ?: createValue().also {
    this[key] = it
  }
}