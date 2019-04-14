/*
 * Copyright (C) 2019 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.publicobject.jsonexplained.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import okhttp3.HttpUrl

object HttpUrlSerializer : StdSerializer<HttpUrl>(HttpUrl::class.java) {
  override fun serialize(value: HttpUrl, generator: JsonGenerator, provider: SerializerProvider) {
    generator.writeString(value.toString())
  }
}

object HttpUrlDeserializer : StdDeserializer<HttpUrl>(HttpUrl::class.java) {
  override fun deserialize(parser: JsonParser, context: DeserializationContext?): HttpUrl {
    val text = parser.getValueAsString()
    return HttpUrl.get(text)
  }
}
