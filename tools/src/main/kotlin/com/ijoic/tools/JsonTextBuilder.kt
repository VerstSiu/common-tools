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

import java.lang.StringBuilder

/**
 * Json text builder
 *
 * @author verstsiu created at 2018-01-11 16:27
 */
class JsonTextBuilder private constructor() {

  private val sb = StringBuilder()

  /* -- json array root methods :begin -- */

  /**
   * Append text [value]
   */
  fun text(value: String?, skipNull: Boolean = false) {
    if (value == null) {
      if (!skipNull) {
        sb.append("null")
      }
    } else {
      sb.appendQuoteText(value)
    }
  }

  /**
   * Append number [value]
   */
  fun number(value: Number) {
    sb.append(value.toString())
  }

  /**
   * Append number text [value]
   */
  fun numberText(value: String) {
    sb.append(value)
  }

  /**
   * Append boolean [value]
   */
  fun boolean(value: Boolean) {
    sb.append(value.toString())
  }

  /**
   * Append json object with construct [func]
   */
  fun json(func: JsonTextBuilder.() -> Unit) {
    sb.append("{")
    func.invoke(this)
    sb.append("}")
  }

  /**
   * Append json array with [items] and construct [func]
   */
  fun <T> jsonArray(items: Collection<T>?, func: JsonTextBuilder.(T) -> Unit) {
    sb.append("[")

    var itemIndex = 0
    items?.forEach {
      if (itemIndex > 0) {
        sb.nextItem()
      }
      ++itemIndex
      func.invoke(this, it)
    }

    sb.append("]")
  }

  /**
   * Append json array with construct [func]
   */
  fun jsonArray(func: JsonTextBuilder.() -> Unit) {
    sb.append("[")
    func.invoke(this)
    sb.append("]")
  }

  /**
   * Append json array (checked) with [items] and construct [func]
   */
  fun <T> jsonArrayChecked(items: Collection<T>?, func: JsonTextBuilder.(T) -> Boolean) {
    sb.append("[")

    var itemIndex = 0

    items?.forEach {
      if (itemIndex > 0) {
        sb.nextItem()
      }

      when {
        func.invoke(this, it) -> ++itemIndex
        itemIndex > 0 -> sb.cancelNextItem()
      }
    }

    sb.append("]")
  }

  /**
   * Append next text [value]
   */
  fun nextText(value: String?, skipNull: Boolean = false) {
    if (skipNull && value == null) {
      return
    }
    sb.nextItem()
    text(value, skipNull = false)
  }

  /**
   * Append next number [value]
   */
  fun nextNumber(value: Number) {
    sb.nextItem()
    number(value)
  }

  /**
   * Append next number text [value]
   */
  fun nextNumberText(value: String) {
    sb.nextItem()
    numberText(value)
  }

  /**
   * Append next boolean [value]
   */
  fun nextBoolean(value: Boolean) {
    sb.nextItem()
    boolean(value)
  }

  /**
   * Append next json object with construct [func]
   */
  fun nextJson(func: JsonTextBuilder.() -> Unit) {
    sb.nextItem()
    json(func)
  }

  /**
   * Append next json array with [items] and construct [func]
   */
  fun <T> nextJsonArray(items: Collection<T>?, func: JsonTextBuilder.(T) -> Unit) {
    sb.nextItem()
    jsonArray(items, func)
  }

  /**
   * Append next json array with construct [func]
   */
  fun nextJsonArray(func: JsonTextBuilder.() -> Unit) {
    sb.nextItem()
    jsonArray(func)
  }

  /**
   * Append next json array (checked) with [items] and construct [func]
   */
  fun <T> nextJsonArrayChecked(items: Collection<T>?, func: JsonTextBuilder.(T) -> Boolean) {
    sb.nextItem()
    jsonArrayChecked(items, func)
  }

  /* -- json array root methods :end -- */

  /* -- json object root methods :begin -- */

  /**
   * Append text [value] with [key]
   */
  fun text(key: String, value: String?, skipNull: Boolean = false) {
    if (skipNull && value == null) {
      return
    }
    sb
      .appendQuoteText(key)
      .nextValue()

    text(value, skipNull = false)
  }

  /**
   * Append number [value] with [key]
   */
  fun number(key: String, value: Number) {
    sb
      .appendQuoteText(key)
      .nextValue()

    number(value)
  }

  /**
   * Append number text [value] with [key]
   */
  fun numberText(key: String, value: String) {
    sb
      .appendQuoteText(key)
      .nextValue()

    numberText(value)
  }

  /**
   * Append boolean [value] with [key]
   */
  fun boolean(key: String, value: Boolean) {
    sb
      .appendQuoteText(key)
      .nextValue()

    boolean(value)
  }

  /**
   * Append json object with [key] and construct [func]
   */
  fun json(key: String, func: JsonTextBuilder.() -> Unit) {
    sb
      .appendQuoteText(key)
      .nextValue()

    json(func)
  }

  /**
   * Append json array with [key], [items] and construct [func]
   */
  fun <T> jsonArray(key: String, items: Collection<T>?, func: JsonTextBuilder.(T) -> Unit) {
    sb
      .appendQuoteText(key)
      .nextValue()

    jsonArray(items, func)
  }

  /**
   * Append json array with [key] and construct [func]
   */
  fun jsonArray(key: String, func: JsonTextBuilder.() -> Unit) {
    sb
      .appendQuoteText(key)
      .nextValue()

    jsonArray(func)
  }

  /**
   * Append next text [value] with [key]
   */
  fun nextText(key: String, value: String?, skipNull: Boolean = false) {
    if (skipNull && value == null) {
      return
    }
    sb
      .nextItem()
      .appendQuoteText(key)
      .nextValue()

    text(value, skipNull = false)
  }

  /**
   * Append next number [value] with [key]
   */
  fun nextNumber(key: String, value: Number) {
    sb
      .nextItem()
      .appendQuoteText(key)
      .nextValue()

    number(value)
  }

  /**
   * Append next number text [value] with [key]
   */
  fun nextNumberText(key: String, value: String) {
    sb
      .nextItem()
      .appendQuoteText(key)
      .nextValue()

    numberText(value)
  }

  /**
   * Append next boolean [value] with [key]
   */
  fun nextBoolean(key: String, value: Boolean) {
    sb
      .nextItem()
      .appendQuoteText(key)
      .nextValue()

    boolean(value)
  }

  /**
   * Append next json object with [key] and construct [func]
   */
  fun nextJson(key: String, func: JsonTextBuilder.() -> Unit) {
    sb
      .nextItem()
      .appendQuoteText(key)
      .nextValue()

    json(func)
  }

  /**
   * Append next json array (checked) with [key], [items] and construct [func]
   */
  fun <T> nextJsonArrayChecked(key: String, items: Collection<T>?, func: JsonTextBuilder.(T) -> Boolean) {
    sb
      .nextItem()
      .appendQuoteText(key)
      .nextValue()

    jsonArrayChecked(items, func)
  }

  /**
   * Append next json array with [key] and construct [func]
   */
  fun nextJsonArray(key: String, func: JsonTextBuilder.() -> Unit) {
    sb
      .nextItem()
      .appendQuoteText(key)
      .nextValue()

    jsonArray(func)
  }

  /**
   * Append next json array with [key], [items] and construct [func]
   */
  fun <T> nextJsonArray(key: String, items: Collection<T>?, func: JsonTextBuilder.(T) -> Unit) {
    sb
      .nextItem()
      .appendQuoteText(key)
      .nextValue()

    jsonArray(items, func)
  }

  /* -- json object root methods :begin -- */

  override fun toString(): String {
    return sb.toString()
  }

  private fun StringBuilder.appendQuoteText(text: String): StringBuilder {
    return this
      .append('\"')
      .append(text)
      .append('\"')
  }

  private fun StringBuilder.nextItem(): StringBuilder {
    return this.append(",")
  }

  private fun StringBuilder.cancelNextItem(): StringBuilder {
    return this.deleteCharAt(this.length - 1)
  }

  private fun StringBuilder.nextValue(): StringBuilder {
    return this.append(":")
  }

  companion object {
    /**
     * Returns builder instance to create json object text
     */
    @JvmStatic
    fun json(func: JsonTextBuilder.() -> Unit): String {
      return JsonTextBuilder()
        .apply { json(func) }
        .toString()
    }

    /**
     * Returns builder instance to create json array text
     */
    @JvmStatic
    fun <T> jsonArray(items: Collection<T>?, func: JsonTextBuilder.(T) -> Unit): String {
      return JsonTextBuilder()
        .apply { jsonArray(items, func) }
        .toString()
    }

    /**
     * Returns builder instance to create json array text
     */
    @JvmStatic
    fun jsonArray(func: JsonTextBuilder.() -> Unit): String {
      return JsonTextBuilder()
        .apply { jsonArray(func) }
        .toString()
    }

    /**
     * Returns builder instance to create json array text (checked)
     */
    @JvmStatic
    fun <T> jsonArrayChecked(items: Collection<T>?, func: JsonTextBuilder.(T) -> Boolean): String {
      return JsonTextBuilder()
        .apply { jsonArrayChecked(items, func) }
        .toString()
    }
  }
}