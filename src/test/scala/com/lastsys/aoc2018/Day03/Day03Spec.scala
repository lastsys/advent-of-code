package com.lastsys.aoc2018.Day03

import fastparse.Parsed
import org.scalatest.{FlatSpec, Matchers}
import fastparse.parse

import scala.util.Success

class Day03Spec extends FlatSpec with Matchers {
  "The cut parser" should "read input files" in {
    val data = readData("day03/test-input-p1-01.txt")
    data.map(parse(_, Parser.cuts(_)) shouldBe Seq(
      Cut(1, 1, 3, 4, 4),
      Cut(2, 3, 1, 4, 4),
      Cut(3, 5, 5, 2, 2)))
  }

  "Part 1" should "pass test case" in {
    val data = readData("day03/test-input-p1-01.txt")
    val cuts = data.map(parse(_, Parser.cuts(_)))
    cuts.map {
      case Parsed.Success(v, _) => part1(v)
    } shouldBe Success(4)
  }

  "Part 2" should "pass test case" in {
    val data = readData("day03/test-input-p1-01.txt")
    val cuts = data.map(parse(_, Parser.cuts(_)))
    cuts.map {
      case Parsed.Success(v, _) => part2(v)
    } shouldBe Success(3)
  }

  "Cut" should "give correct overlap result" in {
    val c1 = Cut(1, 1, 3, 4, 4)
    val c2 = Cut(2, 3, 1, 4, 4)
    val c3 = Cut(3, 5, 5, 2, 2)

    c1.overlap(c2) shouldBe true
    c2.overlap(c1) shouldBe true

    c1.overlap(c3) shouldBe false
    c3.overlap(c1) shouldBe false

    c2.overlap(c3) shouldBe false
    c3.overlap(c2) shouldBe false
  }
}
