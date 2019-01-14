
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

    Start text building with `JsonTextBuilder.json` or `JsonTextBuilder.jsonArray` and build `json/jsonArray` content with following apis.

    - Raw values:

        ```kotlin
        text("Tony") // \"Tony\"
        number(1) // 1
        numberText("0.00001") // 0.00001
        boolean(true) // true
        ```

    - Wrap with keys:

        ```kotlin
        text("name", "Tony") // \"name\":\"Tony\"
        ```

    - Wrap as next item:

        ```kotlin
        nextText("Tony") // ,\"Tony\"
        ```

    - Wrap with keys as next item:

        ```kotlin
        nextText("name", "Tony") // ,\"name\":\"Tony\"
        ```

    - Wrap as json object:

        ```kotlin
        json {
          text("name", "Tony")
          nextNumber("age", 11)
        }
        // {\"name\":\"Tony\",\"age\",11}
        ```

    - Wrap as json array:

        ```kotlin
        jsonArray {
          jsonArray("fruits", listOf("apple", "pear", "orange")) { text(it) }
        }
        // {\"fruits\":[\"apple\",\"pear\",\"orange\"]}
        ```

    - Wrap as custom json array:

        ```kotlin
        jsonArray {
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
