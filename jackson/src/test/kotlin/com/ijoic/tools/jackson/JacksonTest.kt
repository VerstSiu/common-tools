package com.ijoic.tools.jackson

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.junit.Test

/**
 * Jackson test
 */
class JacksonTest {
  @Test
  fun testToEntity() {
    val src = "{\"name\":\"Tony\",\"age\":11}"
    val result = src.toEntity(Person::class.java)!!

    assert(result.name == "Tony")
    assert(result.age == 11)
  }

  @Test
  fun testToEntityItems() {
    val src = "[{\"name\":\"Tony\",\"age\":11}]"
    val result = src.toEntityItems<Person>()!!

    assert(result.size == 1)
    assert(result[0].name == "Tony")
    assert(result[0].age == 11)
  }

  private data class Person @JsonCreator constructor(
    @JsonProperty("name") val name: String,
    @JsonProperty("age") val age: Int
  )
}