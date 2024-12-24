package com.eygraber.json.sugar

import kotlinx.serialization.json.JsonArrayBuilder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray

@DslMarker
internal annotation class JsonBuilderDslMarker

public inline fun JsonObject.copyAndBuildJsonObject(builder: JsonSugarObjectBuilder.() -> Unit): JsonObject =
  buildJsonObject {
    entries.forEach { (k, v) ->
      k withValue v
    }

    builder()
  }

public inline fun buildJsonObject(builderAction: JsonSugarObjectBuilder.() -> Unit): JsonObject {
  val builder = JsonSugarObjectBuilder()
  builder.builderAction()
  return builder.build()
}

@JsonBuilderDslMarker
public class JsonSugarObjectBuilder @PublishedApi internal constructor() {
  private val content: MutableMap<String, JsonElement> = linkedMapOf()

  public infix fun String.withValue(value: JsonElement?) {
    content[this] = value ?: JsonNull
  }

  public infix fun String.withValue(value: Boolean?) {
    content[this] = JsonPrimitive(value)
  }

  public infix fun String.withValue(value: Number?) {
    content[this] = JsonPrimitive(value)
  }

  public infix fun String.withValue(value: String?) {
    content[this] = JsonPrimitive(value)
  }

  public infix fun String.withJsonObject(jsonObject: JsonObject) {
    content[this] = jsonObject
  }

  public infix fun String.withJsonObject(builderAction: JsonSugarObjectBuilder.() -> Unit) {
    content[this] = buildJsonObject(builderAction)
  }

  public infix fun String.withJsonArray(builderAction: JsonArrayBuilder.() -> Unit) {
    content[this] = buildJsonArray(builderAction)
  }

  @PublishedApi
  internal fun build(): JsonObject = JsonObject(content)
}
