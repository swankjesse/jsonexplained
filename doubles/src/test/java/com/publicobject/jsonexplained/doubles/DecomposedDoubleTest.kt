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
package com.publicobject.jsonexplained.doubles

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DecomposedDoubleTest {
  @Test
  fun test() {
    assertThat(DecomposedDouble.create(0.5))
        .isEqualTo(DecomposedDouble(1, 2))
    assertThat(DecomposedDouble.create(5.5))
        .isEqualTo(DecomposedDouble(11, 2))
    assertThat(DecomposedDouble.create(0.1))
        .isEqualTo(DecomposedDouble(3602879701896397, 36028797018963968))
    assertThat(DecomposedDouble(3, 8).toBigDecimal().toString()).isEqualTo("0.375")
  }
}
