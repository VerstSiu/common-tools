package com.ijoic.tools

import java.io.FileInputStream
import java.io.InputStream

/**
 * Map file input with [map] and [ignoreError]
 */
fun<T> String.mapFileInput(ignoreError: Boolean = false, map: (InputStream) -> T?): T? {
  var input: InputStream? = null
  var result: T? = null

  catchError(
    ignoreError,
    onCatch = {
      input = FileInputStream(this)
      result = input?.let(map)
    },
    onFinal = {
      if (input != null) {
        catchError(ignoreError) { input?.close() }
      }
    }
  )
  return result
}

/**
 * Map res input with [map] and [ignoreError]
 */
fun<T> String.mapResInput(ignoreError: Boolean = false, map: (InputStream) -> T?): T? {
  var input: InputStream? = null
  var result: T? = null

  catchError(
    ignoreError,
    onCatch = {
      input = Thread.currentThread().contextClassLoader.getResourceAsStream(this)
      result = input?.let(map)
    },
    onFinal = {
      if (input != null) {
        catchError(ignoreError) { input?.close() }
      }
    }
  )
  return result
}