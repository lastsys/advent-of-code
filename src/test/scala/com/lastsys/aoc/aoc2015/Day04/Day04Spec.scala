package com.lastsys.aoc.aoc2015.Day04

import com.lastsys.aoc.aoc2015.Day04
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class Day04Spec extends FlatSpec with Matchers with TableDrivenPropertyChecks {
  "toHex" should "convert examples correctly" in {
    val a = Array[Byte](0x00.toByte, 0x00.toByte, 0x02.toByte, 0x1f.toByte, 0xf0.toByte, 0x43.toByte)
    toHex(a) shouldBe "0000021ff043"
  }

  "findHash" should "return results according to examples" in {
    val examples = Table(
      ("key", "number"),
      ("abcdef", 609043L),
      ("pqrstuv", 1048970L)
    )

    forAll (examples) { (key, number) =>
      Day04.findHash(key, "00000") shouldBe number
    }
  }
}
