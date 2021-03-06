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
package com.publicobject.jsonexplained.moshi

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okio.Source
import okio.buffer
import okio.source
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.fail
import org.junit.Test
import java.util.concurrent.CompletableFuture

class MoshiTest {
  val moshi = Moshi.Builder()
      .add(SongAdapters)
      .build()
  val adapter = moshi.adapter(SearchResponseBody::class.java)

  @Test fun decodeJson() {
    val searchResponseBody = readResource("/songs.json").buffer().use { source ->
      adapter.fromJson(source)!!
    }

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
      moshi.adapter<CompletableFuture<String>>(
          Types.newParameterizedType(CompletableFuture::class.java, String::class.java))
      fail()
    } catch (_: IllegalArgumentException) {
    }
  }

  @Test fun ampersand() {
    // Moshi.
    val stringAdapter = moshi.adapter(String::class.java)
    println(stringAdapter.toJson("Barnes & Noble")) // "Barnes & Noble"
  }

  private fun readResource(path: String): Source = javaClass.getResourceAsStream(path).source()
}