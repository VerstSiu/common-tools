package com.ijoic.tools

import java.util.regex.Pattern

private const val MS_SECOND = 1000L
private const val MS_MINUTE = 60 * MS_SECOND
private const val MS_HOUR = 60 * MS_MINUTE
private const val MS_DAY = 24 * MS_HOUR

private val patternTimeMs = Pattern.compile("^([0-9]+)(d|h|m|s|ms)?$")
private val msMap = mapOf(
  "d" to MS_DAY,
  "h" to MS_HOUR,
  "m" to MS_MINUTE,
  "s" to MS_SECOND,
  "ms" to 1L,
  "" to 1L
)

/**
 * Parse current text to time ms
 */
fun String.toTimeMs(): Long? {
  val segments = this.split(" ")
  var sumMs = 0L
  var sumSize = 0

  segments.forEach {
    val matcher = patternTimeMs.matcher(it)

    if (matcher.find() && matcher.groupCount() >= 2) {
      val time = matcher.group(1).toLongOrNull()
      val unit = matcher.group(2)

      if (time != null && time >= 0) {
        val unitMs = msMap[unit.orEmpty()]

        if (unitMs != null) {
          sumMs += time * unitMs
          ++sumSize
        }
      }
    }
  }
  return sumMs.takeIf { sumSize > 0 }
}