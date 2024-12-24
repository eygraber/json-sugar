@file:Suppress("NOTHING_TO_INLINE")

package com.eygraber.json.sugar

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

public inline fun JsonObject.int(key: String): Int = requireNotNull(key).asInt

public inline fun JsonObject.intOr(key: String, default: Int): Int = intOrNull(key) ?: default

public inline fun JsonObject.intOr(key: String, default: () -> Int): Int = intOrNull(key) ?: default()

public inline fun JsonObject.intOrNull(key: String): Int? = get(key)?.asIntOrNull

public inline fun JsonObject.long(key: String): Long = requireNotNull(key).asLong

public inline fun JsonObject.longOr(key: String, default: Long): Long = longOrNull(key) ?: default

public inline fun JsonObject.longOr(key: String, default: () -> Long): Long = longOrNull(key) ?: default()

public inline fun JsonObject.longOrNull(key: String): Long? = get(key)?.asLongOrNull

public inline fun JsonObject.float(key: String): Float = requireNotNull(key).asFloat

public inline fun JsonObject.floatOr(key: String, default: Float): Float = floatOrNull(key) ?: default

public inline fun JsonObject.floatOr(key: String, default: () -> Float): Float = floatOrNull(key) ?: default()

public inline fun JsonObject.floatOrNull(key: String): Float? = get(key)?.asFloatOrNull

public inline fun JsonObject.double(key: String): Double = requireNotNull(key).asDouble

public inline fun JsonObject.doubleOr(key: String, default: Double): Double = doubleOrNull(key) ?: default

public inline fun JsonObject.doubleOr(key: String, default: () -> Double): Double = doubleOrNull(key) ?: default()

public inline fun JsonObject.doubleOrNull(key: String): Double? = get(key)?.asDoubleOrNull

public inline fun JsonObject.boolean(key: String): Boolean = requireNotNull(key).jsonPrimitive.let {
  it.booleanOrNull ?: (it.int != 0)
}

public inline fun JsonObject.booleanOr(key: String, default: Boolean): Boolean = booleanOrNull(key) ?: default

public inline fun JsonObject.booleanOr(key: String, default: () -> Boolean): Boolean = booleanOrNull(key) ?: default()

public inline fun JsonObject.booleanOrNull(key: String): Boolean? = get(key)?.asJsonPrimitiveOrNull?.let { primitive ->
  primitive.booleanOrNull ?: primitive.intOrNull?.let { it != 0 }
}

public inline fun JsonObject.string(key: String): String = requireNotNull(key).asString

public inline fun JsonObject.stringOr(key: String, default: String): String = stringOrNull(key) ?: default

public inline fun JsonObject.stringOr(key: String, default: () -> String): String = stringOrNull(key) ?: default()

public inline fun JsonObject.stringOrEmpty(key: String): String = stringOr(key, "")

public inline fun JsonObject.stringOrNull(key: String): String? = get(key)?.asStringOrNull

public inline fun <reified T : Enum<T>> JsonObject.enum(
  key: String,
  errorSuffix: String? = null,
  valueLookup: (String?) -> T?,
): T {
  val v = stringOrNull(key)
  return requireNotNull(valueLookup(v)) {
    val availableValues = enumValues<T>().joinToString()
    val suffix = errorSuffix?.let { " $it" }.orEmpty()

    "$key ($v) is required to be one of [$availableValues]$suffix"
  }
}

public inline fun <reified T : Enum<T>> JsonObject.enumOrNull(
  key: String,
  valueLookup: (String?) -> T?,
): T? {
  val v = stringOrNull(key)
  return valueLookup(v)
}

public inline fun <reified T : Enum<T>> JsonObject.enumOr(
  key: String,
  default: T,
  valueLookup: (String?) -> T?,
): T {
  val v = stringOrNull(key)
  return valueLookup(v) ?: default
}

public inline fun JsonObject.jsonElement(key: String): JsonElement = requireNotNull(key)

public inline fun JsonObject.jsonObject(key: String): JsonObject = requireNotNull(key).jsonObject

public inline fun JsonObject.jsonObjectOr(key: String, default: JsonObject): JsonObject =
  jsonObjectOrNull(key) ?: default

public inline fun JsonObject.jsonObjectOr(key: String, default: () -> JsonObject): JsonObject =
  jsonObjectOrNull(key) ?: default()

public inline fun JsonObject.jsonObjectOrEmpty(key: String): JsonObject = jsonObjectOr(key, emptyJsonObject())

public inline fun JsonObject.jsonObjectOrNull(key: String): JsonObject? = get(key)?.asJsonObjectOrNull

public inline fun JsonObject.jsonArray(key: String): JsonArray = requireNotNull(key).jsonArray

public inline fun JsonObject.jsonArrayOr(key: String, default: JsonArray): JsonArray = jsonArrayOrNull(key) ?: default

public inline fun JsonObject.jsonArrayOr(key: String, default: () -> JsonArray): JsonArray =
  jsonArrayOrNull(key) ?: default()

public inline fun JsonObject.jsonArrayOrEmpty(key: String): JsonArray = jsonArrayOr(key, emptyJsonArray())

public inline fun JsonObject.jsonArrayOrNull(key: String): JsonArray? = get(key)?.asJsonArrayOrNull

public inline fun JsonObject.jsonArrayOfString(key: String): List<String> =
  requireNotNull(key).jsonArray.toListOfString()

public inline fun JsonObject.jsonArrayOfStringOr(key: String, default: List<String>): List<String> =
  jsonArrayOfStringOrNull(key) ?: default

public inline fun JsonObject.jsonArrayOfStringOr(key: String, default: () -> List<String>): List<String> =
  jsonArrayOfStringOrNull(key) ?: default()

public inline fun JsonObject.jsonArrayOfStringOrEmpty(key: String): List<String> = jsonArrayOfStringOr(key, emptyList())

public inline fun JsonObject.jsonArrayOfStringOrNull(key: String): List<String>? =
  get(key)?.asJsonArrayOrNull?.toListOfString()

public inline fun JsonObject.jsonArrayOfObject(key: String): List<JsonObject> =
  requireNotNull(key).jsonArray.toListOfObject()

public inline fun JsonObject.jsonArrayOfObjectOr(key: String, default: List<JsonObject>): List<JsonObject> =
  jsonArrayOrNull(key)?.toListOfObject() ?: default

public inline fun JsonObject.jsonArrayOfObjectOr(key: String, default: () -> List<JsonObject>): List<JsonObject> =
  jsonArrayOrNull(key)?.toListOfObject() ?: default()

public inline fun JsonObject.jsonArrayOfObjectOrEmpty(key: String): List<JsonObject> =
  jsonArrayOr(key, emptyJsonArray()).toListOfObject()

public inline fun JsonObject.jsonArrayOfObjectOrNull(key: String): List<JsonObject>? =
  get(key)?.asJsonArrayOrNull?.toListOfObject()

public fun JsonObject?.emptyIfNull(): JsonObject = this ?: emptyJsonObject()

public fun emptyJsonObject(): JsonObject = JsonObject(emptyMap())

@PublishedApi internal inline fun JsonObject.requireNotNull(key: String): JsonElement =
  requireNotNull(get(key)) {
    """The key "$key" does not exist in $keys"""
  }.also { json ->
    require(json !is JsonNull) {
      """The key "$key" must not be null"""
    }
  }
