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