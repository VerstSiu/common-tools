
# Common Tools

Kotlin based common tools.

## Get Start

* Add library dependency:

    ```gradle
    dependencies {
        implementation ''
    }
    ```

## Usage

* Json text builder:

    - Api list:

        ```kotlin
        class JsonTextBuilder {
          fun text([key: String, ]value: String?)
          fun number([key: String, ]value: Number)
          fun numberText([key: String, ]value: String)
          fun json([key: String, ]func: JsonTextBuilder.() -> Unit)
          fun jsonArray([key: String, ]func: JsonTextBuilder.() -> Unit)
          fun <T> jsonArray([key: String, ]items: Collection<T>, func: JsonTextBuilder.(T) -> Unit)

          fun nextText([key: String, ]value: String?)
          fun nextNumber([key: String, ]value: Number)
          fun nextNumberText([key: String, ]value: String)
          fun nextJson([key: String, ]func: JsonTextBuilder.() -> Unit)
          fun nextJsonArray([key: String, ]func: JsonTextBuilder.() -> Unit)
          fun <T> nextJsonArray([key: String, ]items: Collection<T>, func: JsonTextBuilder.(T) -> Unit)

          companion object {
            fun json(func: JsonTextBuilder.() -> Unit): String
            fun jsonArray(func: JsonTextBuilder.() -> Unit): String
            fun <T> jsonArray(items: Collection<T>, func: JsonTextBuilder.(T) -> Unit): String
          }
        }
        ```

    - Simple json:

        ```kotlin
        JsonTextBuilder.json {
          text("name", "Tony")
          nextNumber("age", 11)
        }
        // {\"name\":\"Tony\",\"age\":11}
        ```

    - Simple json array:

        ```kotlin
        JsonTextBuilder.json {
          jsonArray("fruits", listOf("apple", "pear", "orange")) { text(it) }
        }
        // {\"fruits\":[\"apple\",\"pear\",\"orange\"]}
        ```

    - custom json array:

        ```kotlin
        JsonTextBuilder.json {
          jsonArray("info") {
            text("Tony")
            nextNumber(1)
          }
        }
        // {\"info\":[\"Tony\",1]}
        ```

## License

```

   Copyright(c) 2018 VerstSiu

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

```
