package com.ijoic.tools

import java.lang.Exception

/**
 * Catch error with [onCatch] and [ignoreError]
 */
inline fun catchError(ignoreError: Boolean = false, onCatch: () -> Unit) {
  try {
    onCatch.invoke()
  } catch (e: Exception) {
    if (!ignoreError) {
      e.printStackTrace()
    }
  }
}

/**
 * Catch error with [onCatch], [onFinal] and [ignoreError]
 */
inline fun catchError(ignoreError: Boolean = false, onCatch: () -> Unit, onFinal: () -> Unit) {
  try {
    onCatch.invoke()
  } catch (e: Exception) {
    if (!ignoreError) {
      e.printStackTrace()
    }
  } finally {
    onFinal.invoke()
  }
}