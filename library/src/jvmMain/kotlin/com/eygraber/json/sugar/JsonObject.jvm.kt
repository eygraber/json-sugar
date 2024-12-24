package com.eygraber.json.sugar

import kotlinx.serialization.json.JsonObject

public fun JsonObject.toUtf8Bytes(): ByteArray = toString().toByteArray(Charsets.UTF_8)
