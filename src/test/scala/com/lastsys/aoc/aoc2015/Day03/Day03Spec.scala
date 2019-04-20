package com.lastsys.aoc.aoc2015.Day03

import com.lastsys.aoc.aoc2015.Day03
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class Day03Spec extends FlatSpec with Matchers with TableDrivenPropertyChecks {
  "Program" should "visit the correct number of houses" in {
    val examples = Table(
      ("program", "houses"),
      (">", 2),
      ("^>v<", 4),
      ("^v^v^v^v^v", 2)
    )

    forAll (examples) { (program, houses) =>
      Day03.runProgram(program).size shouldBe houses
    }
  }

  "RoboProgram" should "visit the correct number of houses" in {
    val examples = Table(
      ("program", "houses"),
      ("^v", 3),
      ("^>v<", 3),
      ("^v^v^v^v^v", 11)
    )

    forAll (examples) { (program, houses) =>
      Day03.part2(program) shouldBe houses
    }
  }
}
