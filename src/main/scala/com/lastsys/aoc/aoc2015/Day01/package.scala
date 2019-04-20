package com.lastsys.aoc.aoc2015

import com.lastsys.aoc.AocTask

import scala.annotation.tailrec
import scala.io.Source
import scala.util.{Try, Using}

package object Day01 extends AocTask {
  def run(): Unit = {
    val data = readData("2015/day01/input.txt")
    data.foreach { d =>
      println(s"Day01 / Part 1 = ${part1(d)}")
      println(s"Day01 / Part 2 = ${part2(d)}")
    }
  }

  def walkFloors(instructions: Seq[Int]): Int = instructions.sum

  def firstCharacterAtFloor(instructions: Seq[Int], floor: Int): Int = {
    @tailrec
    def next(instructions: Seq[Int], pos: Int = 0, currentFloor: Int = 0): Int = {
      if (currentFloor == floor) {
        pos
      } else {
        next(instructions.tail, pos + 1, currentFloor + instructions.head)
      }
    }

    next(instructions)
  }

  def part1(data: Seq[Int]): Int = walkFloors(data)

  def part2(data: Seq[Int]): Int = firstCharacterAtFloor(data, -1)

  def readData(filename: String): Try[Seq[Int]] = Using(Source.fromResource(filename)) { instructions =>
    instructionsToDeltaFloor(instructions.mkString)
  }

  def instructionsToDeltaFloor(instructions: String): Seq[Int] =
    instructions.chars.toArray.map(i => -((i - '(') * 2 - 1))
}
