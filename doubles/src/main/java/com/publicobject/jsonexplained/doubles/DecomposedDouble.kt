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

import java.math.BigDecimal
import java.math.RoundingMode

data class DecomposedDouble(
  val numerator: BigDecimal,
  val denominator: BigDecimal
) {
  constructor(numerator: Long, denominator: Long) :
      this(BigDecimal.valueOf(numerator), BigDecimal.valueOf(denominator))

  fun toBigDecimal(
    scale: Int = 3,
    roundingMode: RoundingMode = RoundingMode.HALF_UP
  ): BigDecimal {
    return numerator.divide(denominator, scale, roundingMode)
  }

  companion object {
    private val TWO = BigDecimal.valueOf(2)

    fun create(d: Double): DecomposedDouble {
      val longBits = java.lang.Double.doubleToLongBits(d)
      val exponent = ((longBits and 0x7ff0000000000000L shr 52) - 1023).toInt()
      var base = 0x10000000000000L
      var numerator = longBits and 0xfffffffffffffL or base

      // Simplify by shifting the numerator and denominator.
      val numberTrailingZeros = java.lang.Long.numberOfTrailingZeros(numerator)
      base = base shr numberTrailingZeros
      numerator = numerator shr numberTrailingZeros

      val denominator = when {
        exponent < 0 -> BigDecimal.valueOf(base).multiply(TWO.pow(-exponent))
        else -> BigDecimal.valueOf(base).divide(TWO.pow(exponent))
      }

      return DecomposedDouble(BigDecimal.valueOf(numerator), denominator)
    }
  }
}