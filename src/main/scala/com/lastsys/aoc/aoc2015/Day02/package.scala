package com.lastsys.aoc.aoc2015

import scala.io.Source
import scala.util.{Try, Using}

package object Day02 {
  def run(): Unit = {
    val data = readData("2015/day02/input.txt")
    data.foreach { d =>
      println(s"Day02 / Part 1 = ${part1(d)}")
      println(s"Day02 / Part 2 = ${part2(d)}")
    }
  }

  def part1(data: Seq[Box]): Int =
    data.map(_.paperArea).sum

  def part2(data: Seq[Box]): Int =
    data.map(_.ribbonLength).sum

  def readData(filename: String): Try[Seq[Box]] =
    Using(Source.fromResource(filename)) { f => f.getLines
        .map(_.split("x").map(_.toInt))
        .map(v => Box(v(0), v(1), v(2)))
        .toVector
    }
}
