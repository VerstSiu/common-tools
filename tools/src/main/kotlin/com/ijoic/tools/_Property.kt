package com.ijoic.tools

import kotlin.reflect.KMutableProperty0

/**
 * Get or create(and cached) property value
 */
fun <T> KMutableProperty0<T?>.getOrCreate(onCreate: () -> T): T {
  return get() ?: onCreate().also { set(it) }
}

/**
 * Get or fill(and cached) property value
 */
fun <T> KMutableProperty0<T?>.getOrFill(onCreate: () -> T?): T? {
  return get() ?: onCreate()?.also { set(it) }
}