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

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import okhttp3.HttpUrl

object SongAdapters {
  @ToJson fun httpUrlToJson(httpUrl: HttpUrl) = httpUrl.toString()
  @FromJson fun httpUrlFromJson(string: String) = HttpUrl.get(string)
}
