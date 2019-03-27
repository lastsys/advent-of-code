package com.lastsys.aoc2018.Day01

import org.scalatest.{FlatSpec, Matchers}
import scala.util.Success

class Day01Spec extends FlatSpec with Matchers {
  "Part 1" should "pass test case 1" in {
    val data = readData("day01-test-input-01.txt")
    data.map(part1) shouldBe Success(3)
  }

  it should "pass test case 2" in {
    val data = readData("day01-test-input-02.txt")
    data.map(part1) shouldBe Success(3)
  }

  it should "pass test case 3" in {
    val data = readData("day01-test-input-03.txt")
    data.map(part1) shouldBe Success(0)
  }

  it should "pass test case 4" in {
    val data = readData("day01-test-input-04.txt")
    data.map(part1) shouldBe Success(-6)
  }
}
