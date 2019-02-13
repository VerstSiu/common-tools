/*
 *
 *  Copyright(c) 2019 VerstSiu
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.ijoic.tools.jackson

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

private val mapper = ObjectMapper()

/**
 * Parse current string to expected [clazz] entity
 *
 * @author verstsiu created at 2019-02-13 10:19
 */
fun <T> String.toEntity(clazz: Class<T>, onError: ((Throwable) -> Unit)? = null): T? {
  return try {
    mapper.readValue(this, clazz)
  } catch (t: Throwable) {
    dispatchParseError(t, onError)
    null
  }
}

/**
 * Parse current string to entity items
 *
 * @author verstsiu created at 2019-02-13 10:19
 */
inline fun <reified T> String.toEntityItems(): List<T>? {
  return try {
    ObjectMapper().readValue(this, object: TypeReference<List<T>>() {})
  } catch (t: Throwable) {
    t.printStackTrace()
    null
  }
}

/**
 * Parse current string to entity items
 *
 * @author verstsiu created at 2019-02-13 10:37
 */
inline fun <reified T> String.toEntityItems(onError: (Throwable) -> Unit): List<T>? {
  return try {
    ObjectMapper().readValue(this, object: TypeReference<List<T>>() {})
  } catch (t: Throwable) {
    onError.invoke(t)
    null
  }
}

private fun dispatchParseError(t: Throwable, onError: ((Throwable) -> Unit)?) {
  if (onError == null) {
    t.printStackTrace()
  } else {
    onError.invoke(t)
  }
}