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

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseBody(
  val response: SearchResponse
)

@Serializable
data class SearchResponse(
  val hits: List<Hit>
)

@Serializable
data class Hit(
  val index: String,
  val type: String,
  val result: SongResult
)

@Serializable
data class SongResult(
  val path: String,
  val full_title: String,
  val annotation_count: Int,
  val url: String,
  val header_image_url: String,
  val lyrics_state: String,
  val song_art_image_thumbnail_url: String,
  val id: Long,
  val title: String,
  val header_image_thumbnail_url: String,
  val lyrics_owner_id: Long,
  val primary_artist: Artist,
  val title_with_featured: String,
  val api_path: String,
  val pyongs_count: Int?
)

@Serializable
data class Artist(
  val id: Long,
  val api_path: String,
  val image_url: String,
  val is_meme_verified: Boolean,
  val is_verified: Boolean,
  val header_image_url: String,
  val url: String,
  val iq: Long? = null,
  val name: String
)