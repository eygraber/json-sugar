package com.eygraber.json.sugar

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.double
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.float
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import kotlinx.serialization.json.longOrNull

public inline val JsonElement.asJsonObject: JsonObject get() = jsonObject

public inline val JsonElement?.asJsonObjectOrNull: JsonObject?
  get() = runCatching {
    this?.jsonObject
  }.getOrNull()

public inline val JsonElement.asJsonArray: JsonArray get() = jsonArray

public inline val JsonElement?.asJsonArrayOrNull: JsonArray?
  get() = runCatching {
    this?.jsonArray
  }.getOrNull()

public inline val JsonElement.asJsonPrimitive: JsonPrimitive get() = jsonPrimitive

public inline val JsonElement?.asJsonPrimitiveOrNull: JsonPrimitive?
  get() = runCatching {
    this?.jsonPrimitive
  }.getOrNull()

public inline val JsonElement.asString: String
  get() = jsonPrimitive.content

public inline val JsonElement?.asStringOrNull: String?
  get() = asJsonPrimitiveOrNull?.contentOrNull

public inline val JsonElement.asInt: Int
  get() = jsonPrimitive.int

public inline val JsonElement?.asIntOrNull: Int?
  get() = asJsonPrimitiveOrNull?.intOrNull

public inline val JsonElement.asLong: Long
  get() = jsonPrimitive.long

public inline val JsonElement?.asLongOrNull: Long?
  get() = asJsonPrimitiveOrNull?.longOrNull

public inline val JsonElement.asFloat: Float
  get() = jsonPrimitive.float

public inline val JsonElement?.asFloatOrNull: Float?
  get() = asJsonPrimitiveOrNull?.floatOrNull

public inline val JsonElement.asDouble: Double
  get() = jsonPrimitive.double

public inline val JsonElement?.asDoubleOrNull: Double?
  get() = asJsonPrimitiveOrNull?.doubleOrNull

public inline val JsonElement.asBoolean: Boolean
  get() = jsonPrimitive.boolean

public inline val JsonElement?.asBooleanOrNull: Boolean?
  get() = asJsonPrimitiveOrNull?.booleanOrNull

public inline val JsonElement?.isNull: Boolean
  get() = this == null || this == JsonNull

public inline val JsonElement?.isNotNull: Boolean
  get() = this != null && this != JsonNull

public inline fun JsonElement.primitiveOrParseObject(
  parseObject: JsonObject.() -> JsonElement?,
): JsonElement? = when(this) {
  is JsonObject -> parseObject()

  is JsonPrimitive -> this

  is JsonArray -> null
}
