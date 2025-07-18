package com.eygraber.json.sugar

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromStream
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream

@OptIn(ExperimentalSerializationApi::class)
public fun Json.parseToJsonElement(bytes: ByteArray): JsonElement =
  decodeFromStream<JsonElement>(ByteArrayInputStream(bytes))

public fun Json.parseToJsonObject(bytes: ByteArray): JsonObject =
  parseToJsonElement(bytes).asJsonObject

public fun Json.parseToJsonObjectOrNull(bytes: ByteArray): JsonObject? =
  parseToJsonElement(bytes).asJsonObjectOrNull

public fun Json.parseToJsonArray(bytes: ByteArray): JsonArray =
  parseToJsonElement(bytes).asJsonArray

public fun Json.parseToJsonArrayOrNull(bytes: ByteArray): JsonArray? =
  parseToJsonElement(bytes).asJsonArrayOrNull

@OptIn(ExperimentalSerializationApi::class)
public fun Json.parseToJsonElement(stream: InputStream): JsonElement =
  decodeFromStream<JsonElement>(stream)

public fun Json.parseToJsonObject(stream: InputStream): JsonObject =
  parseToJsonElement(stream).asJsonObject

public fun Json.parseToJsonObjectOrNull(stream: InputStream): JsonObject? =
  parseToJsonElement(stream).asJsonObjectOrNull

public fun Json.parseToJsonArray(stream: InputStream): JsonArray =
  parseToJsonElement(stream).asJsonArray

public fun Json.parseToJsonArrayOrNull(stream: InputStream): JsonArray? =
  parseToJsonElement(stream).asJsonArrayOrNull

@OptIn(ExperimentalSerializationApi::class)
public fun Json.parseToJsonElement(file: File): JsonElement =
  decodeFromStream<JsonElement>(file.inputStream())

public fun Json.parseToJsonObject(file: File): JsonObject =
  parseToJsonElement(file).asJsonObject

public fun Json.parseToJsonObjectOrNull(file: File): JsonObject? =
  parseToJsonElement(file).asJsonObjectOrNull

public fun Json.parseToJsonArray(file: File): JsonArray =
  parseToJsonElement(file).asJsonArray

public fun Json.parseToJsonArrayOrNull(file: File): JsonArray? =
  parseToJsonElement(file).asJsonArrayOrNull
