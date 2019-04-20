package com.lastsys.aoc.aoc2015.Day01

import com.lastsys.aoc.aoc2015.Day01
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class Day01Spec extends FlatSpec with Matchers with TableDrivenPropertyChecks {
  "Conversion to delta floor" should "be either -1 or 1" in {
    instructionsToDeltaFloor("()") shouldBe Seq(1, -1)
  }

  "Part 1" should "pass test cases" in {
    val examples = Table(
      ("instructions", "floor"),
      ("(())", 0),
      ("()()", 0),
      ("(((", 3),
      ("(()(()(", 3),
      ("))(((((", 3),
      ("())", -1),
      ("))(", -1),
      (")))", -3),
      (")())())", -3)
    )

    forAll (examples) { (instructions: String, floor: Int) =>
      Day01.walkFloors(instructionsToDeltaFloor(instructions)) shouldBe floor
    }
  }

  "Part 2" should "pass test cases" in {
    val examples = Table(
      ("instructions", "character"),
      (")", 1),
      ("()())", 5)
    )

    forAll (examples) { (instructions, character) =>
      Day01.firstCharacterAtFloor(instructionsToDeltaFloor(instructions), -1) shouldBe character
    }
  }
}
