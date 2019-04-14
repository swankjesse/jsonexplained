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
package com.publicobject.jsonexplained.kotlinx

import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okio.buffer
import okio.source
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.fail
import org.junit.Test
import java.util.concurrent.CompletableFuture

@ImplicitReflectionSerializer
@UseExperimental(UnstableDefault::class)
class KotlinxTest {
  private val json = Json(JsonConfiguration(strictMode = false))

  @Test fun decodeJson() {
    val searchResponseBody = json.parse(
        SearchResponseBody.serializer(), readResource("/songs.json"))

    val summaries = searchResponseBody.response.hits.map {
      "${it.result.primary_artist.name}: ${it.result.full_title}"
    }

    assertThat(summaries).containsExactly(
        "Dreezy: Cash App by Dreezy (Ft. Offset)",
        "Rick Stevenson: Cash App by Rick Stevenson",
        "Future: Appeal by Future (Ft. Young Scooter)",
        "Future: Appetite by Future",
        "Marty Mula: Cash App by Marty Mula",
        "A1: Cash App by A1 (Ft. YBN Almighty Jay)",
        "Dewey Da Don: Cash App by Dewey Da Don (Ft. Novacain)",
        "OG 3Three: 10 Cash App by OG 3Three",
        "June Carter Cash: Appalachian Pride by June Carter Cash",
        "Iggy Azalea: Kream by Iggy Azalea (Ft. Tyga)"
    )
  }

  @Test fun unexpectedType() {
    try {
      json.toJson(CompletableFuture<String>())
      fail()
    } catch (_: SerializationException) {
    }
  }

  private fun readResource(path: String): String {
    return javaClass.getResourceAsStream(path).source().buffer().use { it.readUtf8() }
  }
}