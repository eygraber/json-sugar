@file:Suppress("MethodOverloading")

package com.eygraber.json.sugar

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlin.jvm.JvmName

public inline fun JsonArray.copyAndBuildJsonArray(builder: JsonSugarArrayBuilder.() -> Unit): JsonArray =
  buildJsonArray {
    forEach(::add)

    builder()
  }

public inline fun buildJsonArray(builderAction: JsonSugarArrayBuilder.() -> Unit): JsonArray {
  val builder = JsonSugarArrayBuilder()
  builder.builderAction()
  return builder.build()
}

@JsonBuilderDslMarker
public class JsonSugarArrayBuilder @PublishedApi internal constructor() {
  private val content: MutableList<JsonElement> = mutableListOf()

  public fun add(element: JsonElement): Boolean {
    content += element
    return true
  }

  public operator fun plusAssign(element: JsonElement) {
    content += element
  }

  public fun addAll(elements: Collection<JsonElement>): Boolean =
    content.addAll(elements)

  public operator fun plusAssign(elements: Collection<JsonElement>) {
    content += elements
  }

  @PublishedApi
  internal fun build(): JsonArray = JsonArray(content)
}

public fun JsonSugarArrayBuilder.add(value: Boolean?): Boolean = add(JsonPrimitive(value))
public operator fun JsonSugarArrayBuilder.plusAssign(value: Boolean?) {
  add(JsonPrimitive(value))
}

public fun JsonSugarArrayBuilder.add(value: Number?): Boolean = add(JsonPrimitive(value))
public operator fun JsonSugarArrayBuilder.plusAssign(value: Number?) {
  add(JsonPrimitive(value))
}

public fun JsonSugarArrayBuilder.add(value: String?): Boolean = add(JsonPrimitive(value))
public operator fun JsonSugarArrayBuilder.plusAssign(value: String?) {
  add(JsonPrimitive(value))
}

@Suppress("UNUSED_PARAMETER") // allows to call `add(null)`
public fun JsonSugarArrayBuilder.add(value: Nothing?): Boolean = add(JsonNull)

@Suppress("UNUSED_PARAMETER") // allows to call `this += (null)`
public operator fun JsonSugarArrayBuilder.plusAssign(value: Nothing?) {
  add(JsonNull)
}

public fun JsonSugarArrayBuilder.addJsonObject(builderAction: JsonSugarObjectBuilder.() -> Unit): Boolean =
  add(buildJsonObject(builderAction))

public fun JsonSugarArrayBuilder.addJsonArray(builderAction: JsonSugarArrayBuilder.() -> Unit): Boolean =
  add(buildJsonArray(builderAction))

@JvmName("addAllStrings")
public fun JsonSugarArrayBuilder.addAll(values: Collection<String?>): Boolean =
  addAll(values.map(::JsonPrimitive))

@JvmName("plusAssignStrings")
public operator fun JsonSugarArrayBuilder.plusAssign(values: Collection<String?>) {
  addAll(values.map(::JsonPrimitive))
}

@JvmName("addAllBooleans")
public fun JsonSugarArrayBuilder.addAll(values: Collection<Boolean?>): Boolean =
  addAll(values.map(::JsonPrimitive))

@JvmName("plusAssignBooleans")
public operator fun JsonSugarArrayBuilder.plusAssign(values: Collection<Boolean?>) {
  addAll(values.map(::JsonPrimitive))
}

@JvmName("addAllNumbers")
public fun JsonSugarArrayBuilder.addAll(values: Collection<Number?>): Boolean =
  addAll(values.map(::JsonPrimitive))

@JvmName("plusAssignNumbers")
public operator fun JsonSugarArrayBuilder.plusAssign(values: Collection<Number?>) {
  addAll(values.map(::JsonPrimitive))
}
