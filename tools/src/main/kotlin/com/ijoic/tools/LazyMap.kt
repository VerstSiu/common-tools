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
package com.ijoic.tools

/**
 * Lazy map
 *
 * Support operation of getOrCreateOnce
 *
 * @author verstsiu created at 2019-05-11 17:21
 */
class LazyMap<K, V>(private val source: MutableMap<K, V> = mutableMapOf()): MutableMap<K, V> by source {

  private val createdMap by lazy { mutableMapOf<K, Boolean>() }

  /**
   * Returns cached or first created field value(even if first create failed)
   */
  fun getOrCreateOnce(key: K, onCreate: () -> V?): V? {
    if (createdMap[key] != true) {
      createdMap[key] = true

      val value = onCreate.invoke()

      if (value != null) {
        source[key] = value
        return value
      }
    }
    return source[key]
  }

  override fun remove(key: K): V? {
    createdMap.remove(key)
    return source.remove(key)
  }

  override fun remove(key: K, value: V): Boolean {
    createdMap.remove(key)
    return source.remove(key, value)
  }

  override fun clear() {
    createdMap.clear()
    source.clear()
  }
}