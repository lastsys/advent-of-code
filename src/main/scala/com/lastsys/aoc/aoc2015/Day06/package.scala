package com.lastsys.aoc.aoc2015

import scala.collection.mutable
import scala.io.Source
import scala.util.{Try, Using}

package object Day06 {
  object Parser {
    import fastparse._
    import SingleLineWhitespace._

    def instruction[_: P]: P[Instruction] = P(turnOn | turnOff | toggle)
    def turnOn[_: P]: P[Instruction] = P("turn on" ~ rect).map(TurnOn)
    def turnOff[_: P]: P[Instruction] = P("turn off" ~ rect).map(TurnOff)
    def toggle[_: P]: P[Instruction] = P("toggle" ~ rect).map(Toggle)

    def rect[_: P]: P[Rect] = P(number ~ "," ~ number ~ "through" ~ number ~ "," ~ number)
      .map(Rect.tupled)
    def number[_: P]: P[Int] = P(CharIn("0-9").rep).!.map(_.toInt)
  }

  def run(): Unit = {
    val data = readData("2015/day06/input.txt")
    data.foreach { d =>
      println(s"Day06 / Part 1 = ${part1(d)}")
      println(s"Day06 / Part 2 = ${part2(d)}")
    }
  }

  def runProgram1(instructions: Seq[Instruction]): Vector[Boolean] = {
    val state = mutable.ArrayBuffer.fill[Boolean](1000*1000)(false)
    instructions.foreach {
      case TurnOn(r) => for (x <- r.x1 to r.x2; y <- r.y1 to r.y2) yield state(x + y * 1000) = true
      case TurnOff(r) => for (x <- r.x1 to r.x2;  y <- r.y1 to r.y2) yield state(x + y * 1000) = false
      case Toggle(r) => for (x <- r.x1 to r.x2;  y <- r.y1 to r.y2) yield state(x + y * 1000) = !state(x + y * 1000)
    }
    state.toVector
  }

  def part1(instructions: Seq[Instruction]): Int = runProgram1(instructions).count(identity)

  def runProgram2(instructions: Seq[Instruction]): Vector[Long] = {
    val state = mutable.ArrayBuffer.fill[Long](1000*1000)(0L)
    instructions.foreach {
      case TurnOn(r) => for (x <- r.x1 to r.x2; y <- r.y1 to r.y2) yield state(x + y * 1000) += 1
      case TurnOff(r) => for (x <- r.x1 to r.x2;  y <- r.y1 to r.y2) yield {
        state(x + y * 1000) -= 1
        if (state(x + y * 1000) < 0) state(x + y * 1000) = 0
      }
      case Toggle(r) => for (x <- r.x1 to r.x2;  y <- r.y1 to r.y2) yield state(x + y * 1000) += 2
    }
    state.toVector
  }

  def part2(instructions: Seq[Instruction]): Long = runProgram2(instructions).sum

  def readData(filename: String): Try[Seq[Instruction]] =
    Using(Source.fromResource(filename))(_.getLines.toVector)
    .map { lines =>
      lines.map { line =>
        fastparse.parse(line, Parser.instruction(_)) match {
          case fastparse.Parsed.Success(v, _) => v
          case fastparse.Parsed.Failure(label, index, _) =>
            throw new RuntimeException(s"Failed to parse $label : $index")
        }
      }
    }
}
