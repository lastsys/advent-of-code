package com.lastsys.aoc2018.Day01

import org.scalatest.{FlatSpec, Matchers}
import scala.util.Success

class Day01Spec extends FlatSpec with Matchers {
  "Part 1" should "pass test case 1" in {
    val data = readData("day01/test-input-p1-01.txt")
    data.map(part1) shouldBe Success(3)
  }

  it should "pass test case 2" in {
    val data = readData("day01/test-input-p1-02.txt")
    data.map(part1) shouldBe Success(3)
  }

  it should "pass test case 3" in {
    val data = readData("day01/test-input-p1-03.txt")
    data.map(part1) shouldBe Success(0)
  }

  it should "pass test case 4" in {
    val data = readData("day01/test-input-p1-04.txt")
    data.map(part1) shouldBe Success(-6)
  }

  "Part 2" should "pass test case 1" in {
    part2(Seq(1, -1)) shouldBe 0
  }

  it should "pass test case 2" in {
    part2(Seq(3, 3, 4, -2, -4)) shouldBe 10
  }

  it should "pass test case 3" in {
    part2(Seq(-6, 3, 8, 5, -6)) shouldBe 5
  }

  it should "pass test case 4" in {
    part2(Seq(7, 7, -2, -7, -4)) shouldBe 14
  }
}
