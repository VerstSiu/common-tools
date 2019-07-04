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
package com.ijoic.tools.reflect

import java.lang.Exception

/**
 * Returns child instance of current class
 *
 * Throws exceptions when creation failed
 */
@Throws(
  ClassNotFoundException::class,
  InstantiationException::class,
  IllegalAccessException::class,
  IllegalArgumentException::class
)
inline fun <reified R> Class<R>.createChildInstance(className: String): R {
  return Class.forName(className).newInstance() as? R
    ?: throw IllegalArgumentException("couldn't create instance for $className as $this")
}

/**
 * Returns child instance of current class or null
 */
inline fun <reified R> Class<R>.createChildInstanceOrNull(className: String, ignoreError: Boolean = false): R? {
  return try {
    createChildInstance(className)
  } catch (e: Exception) {
    if (!ignoreError) {
      e.printStackTrace()
    }
    null
  }
}