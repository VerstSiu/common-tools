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
import com.ijoic.tools.catchError
import com.ijoic.tools.mapFileInput
import com.ijoic.tools.mapResInput

internal val mapper = ObjectMapper()

/**
 * Parse current path to entity
 *
 * @author verstsiu created at 2019-02-13 10:19
 */
fun <T> String.toEntity(clazz: Class<T>, ignoreError: Boolean = false): T? {
  catchError(ignoreError) {
    return mapper.readValue(this, clazz)
  }
  return null
}

/**
 * Parse current path to entity items
 *
 * @author verstsiu created at 2019-02-13 10:19
 */
inline fun <reified T> String.toEntityItems(ignoreError: Boolean = false): List<T>? {
  catchError(ignoreError) {
    return ObjectMapper().readValue(this, object: TypeReference<List<T>>() {})
  }
  return null
}

/**
 * Parse current path to file entity
 *
 * @author verstsiu created at 2019-07-10 22:57
 */
fun <T> String.toFileEntity(clazz: Class<T>, ignoreError: Boolean = false): T? {
  return mapFileInput(ignoreError) {
    mapper.readValue(it, clazz)
  }
}

/**
 * Parse current path to file entity items
 *
 * @author verstsiu created at 2019-07-10 22:57
 */
inline fun <reified T> String.toFileEntityItems(ignoreError: Boolean = false): List<T>? {
  return mapFileInput(ignoreError) {
    ObjectMapper().readValue(it, object: TypeReference<List<T>>() {})
  }
}

/**
 * Parse current path to res entity
 *
 * @author verstsiu created at 2019-07-10 22:57
 */
fun <T> String.toResEntity(clazz: Class<T>, ignoreError: Boolean = false): T? {
  return mapResInput(ignoreError) {
    mapper.readValue(it, clazz)
  }
}

/**
 * Parse current path to res entity items
 *
 * @author verstsiu created at 2019-07-10 22:57
 */
inline fun <reified T> String.toResEntityItems(ignoreError: Boolean = false): List<T>? {
  return mapResInput(ignoreError) {
    ObjectMapper().readValue(it, object: TypeReference<List<T>>() {})
  }
}

/**
 * Parse current entity to json text
 *
 * @author verstsiu created at 2019-11-21 14:59
 */
fun <T: Any> T.toJsonText(ignoreError: Boolean = false): String? {
  catchError(ignoreError) {
    return mapper.writeValueAsString(this)
  }
  return null
}