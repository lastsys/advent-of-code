package com.lastsys.aoc2018

import fastparse.Parsed.Success

import scala.io.Source
import scala.util.{Try, Using}
import fastparse.parse

import scala.annotation.tailrec

package object Day04 extends AocTask {
  override def run(): Unit = {
    val data = readData("day04/input.txt")
    val events = data.map(parse(_, Parser.events(_)))
    val days = events.map {
      case Success(e, _) => generateDays(e)
    }
  }

  def generateDays(events: Seq[Event]): Seq[Day] = {
    val days = events.foldLeft(Vector.empty[Day]) { (days, event) =>
      event.action match {
        case BeginShift(id) => days :+ Day(id)
        case FallsAsleep =>
          days.updated(days.size - 1,
            days.last.copy(awake = days.last.awake.updated(event.time.getMinute, 1)))
        case WakesUp =>
          days.updated(days.size - 1,
            days.last.copy(awake = days.last.awake.updated(event.time.getMinute, -1)))
      }
    }

    val awake: Vector[Int] = (days.map { day =>
      day.awake.foldLeft((Vector.empty[Int], 0)) { case ((vec, x), v) =>
        val y = x + v
        (vec :+ y, y)
      }
    })
  }

  def readData(filename: String): Try[String] =
    Using(Source.fromResource(filename))(_.mkString)
}
