package com.lastsys.aoc.aoc2015

import com.lastsys.aoc.util.Position
import com.lastsys.aoc.{AocTask, Util}

import scala.io.Source
import scala.util.chaining._
import scala.util.{Try, Using}

package object Day03 extends AocTask {
  def run(): Unit = {
    val data = readData("2015/day03/input.txt")
    data.foreach { d =>
      println(s"Day03 / Part 1 = ${part1(d)}")
      println(s"Dat03 / Part 2 = ${part2(d)}")
    }
  }

  def runProgram(directions: String, startState: Map[Position, Int] = Map(Position(0, 0) ->1)): Map[Position, Int] = {
    directions.chars.toArray.foldLeft((Position(0, 0), startState)) {
      case ((p: Position, m: Map[Position, Int]), direction: Int) =>
        direction match {
          case '>' =>
            val np = p.move(1, 0)
            (np, Util.incrementMap(m, np))
          case '<' =>
            val np = p.move(-1, 0)
            (np, Util.incrementMap(m, np))
          case '^' =>
            val np = p.move(0, -1)
            (np, Util.incrementMap(m, np))
          case 'v' =>
            val np = p.move(0, 1)
            (np, Util.incrementMap(m, np))
        }
    }._2
  }

  def part1(directions: String): Int = runProgram(directions).size

  def part2(directions: String): Int = {
    val d1 = (for (i <- 0 until(directions.length, 2)) yield directions.charAt(i)).mkString
    val d2 = (for (i <- 1 until(directions.length, 2)) yield directions.charAt(i)).mkString
    runProgram(d1).pipe(runProgram(d2, _)).size
  }

  def readData(filename: String): Try[String] =
    Using(Source.fromResource(filename))(_.mkString)
}
