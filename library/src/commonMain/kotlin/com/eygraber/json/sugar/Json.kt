@file:Suppress("NOTHING_TO_INLINE")

package com.eygraber.json.sugar

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

public inline fun Json.parseToJsonObject(json: String?): JsonObject =
  json?.let(::parseToJsonElement)?.asJsonObjectOrNull ?: emptyJsonObject()

public inline fun Json.parseToJsonObjectOrNull(json: String?): JsonObject? =
  json?.let(::parseToJsonElement)?.asJsonObjectOrNull

public inline fun Json.parseToJsonArray(json: String?): JsonArray =
  json?.let(::parseToJsonElement)?.asJsonArrayOrNull ?: emptyJsonArray()

public inline fun Json.parseToJsonArrayOrNull(json: String?): JsonArray? =
  json?.let(::parseToJsonElement)?.asJsonArrayOrNull

public inline fun <T> Json.parseToList(json: String?, mapper: (JsonElement) -> T): List<T> =
  when(json) {
    null -> emptyList()
    else ->
      parseToJsonArray(json)
        .filterNot {
          it is JsonNull
        }
        .map(mapper)
  }

public inline fun Json.parseToListOfString(json: String?): List<String> =
  parseToList(json) {
    it.asString
  }

public inline fun Json.parseToListOfJsonObject(json: String?): List<JsonObject> =
  parseToList(json) {
    it.jsonObject
  }
