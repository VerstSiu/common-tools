/*
 *
 *  Copyright(c) 2019 VerstSiu
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.ijoic.tools

import org.junit.Test

/**
 * Json text builder test
 *
 * @author verstsiu created at 2018-01-11 21:32
 */
class JsonTextBuilderTest {

  /* -- pure ** :start */

  @Test
  fun testPureJson() {
    val result = JsonTextBuilder.json {  }
    assert(result == "{}")
  }

  @Test
  fun testPureJsonArray() {
    val result = JsonTextBuilder.jsonArray(listOf<String>()) {  }
    assert(result == "[]")
  }

  @Test
  fun testPureJsonArrayCustom() {
    val result = JsonTextBuilder.jsonArray {  }
    assert(result == "[]")
  }

  @Test
  fun testPureText() {
    val result = JsonTextBuilder.jsonArray {
      text("Tony")
    }
    assert(result == "[\"Tony\"]")
  }

  @Test
  fun testPureTextEmpty() {
    val result = JsonTextBuilder.jsonArray {
      text(null)
    }
    assert(result == "[null]")
  }

  @Test
  fun testPureNumber() {
    val result = JsonTextBuilder.jsonArray {
      number(1)
    }
    assert(result == "[1]")
  }

  @Test
  fun testPureNumberText() {
    val result = JsonTextBuilder.jsonArray {
      numberText("0.00000000001")
    }
    assert(result == "[0.00000000001]")
  }

  @Test
  fun testPureBoolean() {
    val result = JsonTextBuilder.jsonArray {
      boolean(true)
    }
    assert(result == "[true]")
  }

  @Test
  fun testPureChildJson() {
    val result = JsonTextBuilder.json {
      json {
        text("name", "Tony")
      }
    }
    assert(result == "{{\"name\":\"Tony\"}}")
  }

  @Test
  fun testPureChildJsonArray() {
    val result = JsonTextBuilder.jsonArray {
      jsonArray(listOf(1, 2, 3)) {
        number(it)
      }
    }
    assert(result == "[[1,2,3]]")
  }

  @Test
  fun testPureChildJsonArrayCustom() {
    val result = JsonTextBuilder.jsonArray {
      jsonArray {
        number(1)
        nextText("Tony")
        nextText(null)
      }
    }
    assert(result == "[[1,\"Tony\",null]]")
  }

  /* -- pure ** :end */

  /* -- pure next ** :start */

  @Test
  fun testPureNextText() {
    val result = JsonTextBuilder.jsonArray {
      number(1)
      nextText("Tony")
    }
    assert(result == "[1,\"Tony\"]")
  }

  @Test
  fun testPureNextTextEmpty() {
    val result = JsonTextBuilder.jsonArray {
      number(1)
      nextText(null)
    }
    assert(result == "[1,null]")
  }

  @Test
  fun testPureNextNumber() {
    val result = JsonTextBuilder.jsonArray {
      number(1)
      nextNumber(2)
    }
    assert(result == "[1,2]")
  }

  @Test
  fun testPureNextNumberText() {
    val result = JsonTextBuilder.jsonArray {
      number(1)
      nextNumberText("0.00000000001")
    }
    assert(result == "[1,0.00000000001]")
  }

  @Test
  fun testPureNextBoolean() {
    val result = JsonTextBuilder.jsonArray {
      number(1)
      nextBoolean(true)
    }
    assert(result == "[1,true]")
  }

  @Test
  fun testPureNextChildJson() {
    val result = JsonTextBuilder.jsonArray {
      number(1)
      nextJson {
        text("name", "Tony")
      }
    }
    assert(result == "[1,{\"name\":\"Tony\"}]")
  }

  @Test
  fun testPureNextChildJsonArray() {
    val result = JsonTextBuilder.jsonArray {
      number(1)
      nextJsonArray(listOf(1, 2, 3)) { number(it) }
    }
    assert(result == "[1,[1,2,3]]")
  }

  @Test
  fun testPureNextChildJsonArrayCustom() {
    val result = JsonTextBuilder.jsonArray {
      number(1)
      nextJsonArray {
        number(1)
        nextText("Tony")
        nextText(null)
      }
    }
    assert(result == "[1,[1,\"Tony\",null]]")
  }

  /* -- pure next ** :end */

  /* -- key-value pair ** :begin */

  @Test
  fun testPairText() {
    val result = JsonTextBuilder.json {
      text("name", "Tony")
    }
    assert(result == "{\"name\":\"Tony\"}")
  }

  @Test
  fun testPairTextEmpty() {
    val result = JsonTextBuilder.json {
      text("name", null)
    }
    assert(result == "{\"name\":null}")
  }

  @Test
  fun testPairNumber() {
    val result = JsonTextBuilder.json {
      number("age", 11)
    }
    assert(result == "{\"age\":11}")
  }

  @Test
  fun testPairNumberText() {
    val result = JsonTextBuilder.json {
      numberText("age", "0.000000000001")
    }
    assert(result == "{\"age\":0.000000000001}")
  }

  @Test
  fun testPairBoolean() {
    val result = JsonTextBuilder.json {
      boolean("active", true)
    }
    assert(result == "{\"active\":true}")
  }

  @Test
  fun testPairChildJson() {
    val result = JsonTextBuilder.json {
      json("info") {
        text("name", "Tony")
      }
    }
    assert(result == "{\"info\":{\"name\":\"Tony\"}}")
  }

  @Test
  fun testPairChildJsonArray() {
    val result = JsonTextBuilder.json {
      jsonArray("info", listOf(1, 2, 3)) { number(it) }
    }
    assert(result == "{\"info\":[1,2,3]}")
  }

  @Test
  fun testPairChildJsonArrayCustom() {
    val result = JsonTextBuilder.json {
      jsonArray("info") {
        number(1)
        nextText("Tony")
        nextText(null)
      }
    }
    assert(result == "{\"info\":[1,\"Tony\",null]}")
  }

  /* -- key-value pair ** :end */

  /* -- key-value pair next ** :begin */

  @Test
  fun testPairNextText() {
    val result = JsonTextBuilder.json {
      number("age", 1)
      nextText("name", "Tony")
    }
    assert(result == "{\"age\":1,\"name\":\"Tony\"}")
  }

  @Test
  fun testPairNextTextEmpty() {
    val result = JsonTextBuilder.json {
      number("age", 1)
      nextText("name", null)
    }
    assert(result == "{\"age\":1,\"name\":null}")
  }

  @Test
  fun testPairNextNumber() {
    val result = JsonTextBuilder.json {
      number("age", 1)
      nextNumber("age", 11)
    }
    assert(result == "{\"age\":1,\"age\":11}")
  }

  @Test
  fun testPairNextNumberText() {
    val result = JsonTextBuilder.json {
      number("age", 1)
      nextNumberText("age", "0.000000000001")
    }
    assert(result == "{\"age\":1,\"age\":0.000000000001}")
  }

  @Test
  fun testPairNextBoolean() {
    val result = JsonTextBuilder.json {
      number("age", 1)
      nextBoolean("active", true)
    }
    assert(result == "{\"age\":1,\"active\":true}")
  }

  @Test
  fun testPairNextChildJson() {
    val result = JsonTextBuilder.json {
      number("age", 1)
      nextJson("info") {
        text("name", "Tony")
      }
    }
    assert(result == "{\"age\":1,\"info\":{\"name\":\"Tony\"}}")
  }

  @Test
  fun testPairNextChildJsonArray() {
    val result = JsonTextBuilder.json {
      number("age", 1)
      nextJsonArray("info", listOf(1, 2, 3)) { number(it) }
    }
    assert(result == "{\"age\":1,\"info\":[1,2,3]}")
  }

  @Test
  fun testPairNextChildJsonArrayCustom() {
    val result = JsonTextBuilder.json {
      number("age", 1)
      nextJsonArray("info") {
        number(1)
        nextText("Tony")
        nextText(null)
      }
    }
    assert(result == "{\"age\":1,\"info\":[1,\"Tony\",null]}")
  }

  /* -- key-value pair next ** :end */
}