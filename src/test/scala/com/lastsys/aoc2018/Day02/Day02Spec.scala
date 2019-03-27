package com.lastsys.aoc2018.Day02

import org.scalatest.{FlatSpec, Matchers}

import scala.util.Success

class Day02Spec extends FlatSpec with Matchers {
  "Part 1" should "pass test case" in {
    val data = readData("day02/test-input-p1-01.txt")
    data.map(part1) shouldBe Success(12)
  }

  "Part 2" should "pass test case" in {
    val data = readData("day02/test-input-p2-01.txt")
    data.map(part2) shouldBe Success("fgij")
  }
}
