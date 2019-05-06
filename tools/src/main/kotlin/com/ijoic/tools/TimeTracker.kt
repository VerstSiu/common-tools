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
 * Time tracker
 *
 * @author verstsiu created at 2019-05-06 11:40
 */
class TimeTracker(private val getCurrTime: () -> Long = { System.currentTimeMillis() }) {

  private var currTag: String? = null
  private val tagItems = mutableSetOf<String>()
  private val startMsMap = mutableMapOf<String, Long>()
  private val endMsMap = mutableMapOf<String, Long>()

  /**
   * Start a new time trace with [tag]
   */
  fun start(tag: String): TimeTracker {
    val currTime = getCurrTime()
    startMsMap[tag] = currTime
    tagItems.add(tag)
    currTag = tag
    return this
  }

  /**
   * End time time trace with [tag]
   */
  fun end(tag: String): TimeTracker {
    val currTime = getCurrTime()
    endMsMap[tag] = currTime
    return this
  }

  /**
   * End last time trace
   */
  fun end(): TimeTracker {
    val lastTag = currTag
    currTag = null

    if (lastTag != null) {
      end(lastTag)
    }
    return this
  }

  /**
   * End last time trace and start a new time trace with [tag]
   */
  fun next(tag: String): TimeTracker {
    end()
    start(tag)
    return this
  }

  /**
   * Returns elapsed ms with [tag]
   */
  fun getElapsedMs(tag: String): Long? {
    val startMs = startMsMap[tag]
    val endMs = endMsMap[tag]

    return if (startMs == null || endMs == null) {
      null
    } else {
      endMs - startMs
    }
  }

  /**
   * Returns elapsed ms with [tags]
   */
  fun getElapsedMsInfo(vararg tags: String): List<ElapsedInfo> {
    return tags.mapNotNull { tag ->
      getElapsedMs(tag)?.let { ElapsedInfo(tag, it) }
    }
  }

  /**
   * Returns elapsed ms all
   */
  fun getElapsedMsAll(): Long? {
    val tags = tagItems.toTypedArray()
    val items = getElapsedMsInfo(*tags)

    if (items.isEmpty()) {
      return null
    }
    return items.map { it.elapsedMs }.sum()
  }

  /**
   * Print elapsed info with [message] and [tags]
   */
  fun printElapsed(message: String, vararg tags: String) {
    val elapsedMsItems = getElapsedMsInfo(*tags)
      .map { it.elapsedMs }

    if (elapsedMsItems.isEmpty()) {
      return
    }
    val totalMs = elapsedMsItems.sum()

    if (elapsedMsItems.size == 1) {
      println("$message elapsed ms: $totalMs")
    } else {
      println("$message elapsed ms: $totalMs (${elapsedMsItems.joinToString("/")})")
    }
  }

  /**
   * Print elapsed info all with [message]
   */
  fun printElapsedAll(message: String) {
    val tags = tagItems.toTypedArray()
    printElapsed(message, *tags)
  }

  /**
   * Clear saved trace info
   */
  fun clear() {
    currTag = null
    tagItems.clear()
    startMsMap.clear()
    endMsMap.clear()
  }

  /**
   * Elapsed info
   */
  data class ElapsedInfo(val tag: String, val elapsedMs: Long)
}