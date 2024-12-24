package com.eygraber.json.sugar

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

public inline fun <T> JsonArray.toListOf(mapper: (JsonElement) -> T): List<T> = map(mapper)

public fun JsonArray.toListOfString(): List<String> = map { it.asString }

public fun JsonArray.toListOfInt(): List<Int> = map { it.asInt }

public fun JsonArray.toListOfLong(): List<Long> = map { it.asLong }

public fun JsonArray.toSetOfString(): Set<String> = mapTo(LinkedHashSet(size)) { it.asString }

public fun JsonArray.toListOfObject(): List<JsonObject> = map { it.jsonObject }

public fun JsonArray?.toListOfObjectOrEmpty(): List<JsonObject> = this?.map { it.jsonObject }.orEmpty()

public fun JsonArray.asSequenceOfObject(): Sequence<JsonObject> = asSequence().map { it.jsonObject }

public fun JsonArray.toListOfPrimitive(): List<JsonPrimitive> = map { it.jsonPrimitive }

public fun List<String>.toJsonArray(): JsonArray = JsonArray(map(::JsonPrimitive))

public fun Set<String>.toJsonArray(): JsonArray = JsonArray(map(::JsonPrimitive))

public fun Collection<String>.toJsonArray(): JsonArray = JsonArray(map(::JsonPrimitive))

public fun emptyJsonArray(): JsonArray = JsonArray(emptyList())
