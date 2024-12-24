package com.eygraber.json.sugar

import kotlinx.serialization.json.JsonArrayBuilder

public inline fun JsonArrayBuilder.addJsonObject(builderAction: JsonSugarObjectBuilder.() -> Unit) {
  add(buildJsonObject(builderAction))
}
