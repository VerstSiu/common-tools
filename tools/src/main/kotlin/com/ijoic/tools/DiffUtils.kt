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

  /**
   * Apply update with item id
   */
  fun <T, R> applyUpdateWithItemId(
    original: List<T>?,
    patch: List<T>?,
    getItemId: (T) -> R,
    isItemEquals: (T, T) -> Boolean = { o1, o2 -> o1 == o2 },
    onInsert: ((T) -> Unit)? = null,
    onUpdate: ((T, T) -> Unit)? = null) {

    // patch empty
    if (patch.isNullOrEmpty()) {
      return
    }

    // original empty
    if (original.isNullOrEmpty()) {
      if (onInsert != null) {
        patch.forEach(onInsert)
      }
      return
    }

    // upgrade
    val originalMap = original.associateBy(getItemId).toMutableMap()
    val patchMap = patch.associateBy(getItemId)

    for ((id, patchItem) in patchMap) {
      val originalItem = originalMap[id]

      if (originalItem == null) {
        onInsert?.invoke(patchItem)
      } else if (!isItemEquals(originalItem, patchItem)) {
        onUpdate?.invoke(originalItem, patchItem)
      }
    }
  }

  /**
   * Apply remove with item id
   */
  fun <T, R> applyRemoveWithItemId(
    original: List<T>?,
    patch: List<T>?,
    getItemId: (T) -> R,
    onRemove: (T) -> Unit) {

    // patch or original empty
    if (patch.isNullOrEmpty() || original.isNullOrEmpty()) {
      return
    }

    // upgrade
    val originalMap = original.associateBy(getItemId).toMutableMap()
    val patchMap = patch.associateBy(getItemId)

    for ((id, _) in patchMap) {
      val originalItem = originalMap[id]

      if (originalItem != null) {
        onRemove.invoke(originalItem)
      }
    }
  }
}