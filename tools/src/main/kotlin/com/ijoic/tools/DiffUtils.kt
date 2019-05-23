package com.ijoic.tools

/**
 * Differ utils
 *
 * @author verstsiu created at 2019-05-23 09:30
 */
object DiffUtils {
  /**
   * Diff list changes with item id
   */
  fun <T, R> diffWithItemId(
    original: List<T>?,
    revised: List<T>?,
    getItemId: (T) -> R,
    isItemEquals: (T, T) -> Boolean = { o1, o2 -> o1 == o2 },
    onInsert: ((T) -> Unit)? = null,
    onUpdate: ((T, T) -> Unit)? = null,
    onRemove: ((T) -> Unit)? = null) {

    // original empty
    if (original.isNullOrEmpty()) {
      if (!revised.isNullOrEmpty() && onInsert != null) {
        revised.forEach(onInsert)
      }
      return
    }

    // revised empty
    if (revised.isNullOrEmpty()) {
      if (onRemove != null) {
        original.forEach(onRemove)
      }
      return
    }

    // diff
    val originalMap = original.associateBy(getItemId).toMutableMap()
    val revisedMap = revised.associateBy(getItemId)

    for ((id, revisedItem) in revisedMap) {
      val originalItem = originalMap[id]

      if (originalItem == null) {
        onInsert?.invoke(revisedItem)
      } else {
        originalMap.remove(id)

        if (!isItemEquals(originalItem, revisedItem)) {
          onUpdate?.invoke(originalItem, revisedItem)
        }
      }
    }

    if (onRemove != null) {
      for ((_, originalItem) in originalMap) {
        onRemove.invoke(originalItem)
      }
    }
  }
}