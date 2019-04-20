package com.lastsys.aoc.aoc2018

import com.lastsys.aoc.AocTask
import fastparse.Parsed.Success
import fastparse.parse

import scala.io.Source
import scala.util.{Try, Using}

package object Day04 extends AocTask {
  override def run(): Unit = {
    val data = readData("2018/day04/input.txt")
    val events = data.map(parse(_, Parser.events(_)))
    events.foreach {
      case Success(e, _) =>
        println(s"Day04 / Part 1 = ${part1(e)}")
    }
  }

  /** Generate list of days from events.
    *
    * @param events sequence of events
    * @return list of days
    */
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

    days.map { day =>
      // Calculate cumulative sum of 0, +1 and -1 such that we end up with a vector of zeroes and ones
      // where 0 is awake and 1 is asleep.
      val awake = day.awake.foldLeft((Vector.empty[Int], 0)) { case ((vec, x), v) =>
        val y = x + v
        (vec :+ y, y)
      }._1
      day.copy(awake = awake)
    }
  }

  /** Search for the minute where a given guard is most asleep.
    *
    * Search through all of the days to find which minute between 00:00 and 00:59 the guard is most asleep.
    *
    * @param guardId guard id
    * @return count and minute between 00:00 and 00:59
    */
  def findMinuteMostAsleep(guardId: Int, days: Seq[Day]): Count = {
    val count = days.filter(_.guardId == guardId)
      .foldLeft(Vector.fill(60)(0)) { (c, v) =>
        c.zip(v.awake).map { case (v1, v2) => v1 + v2 }
      }
    Count.tupled.apply(count.zipWithIndex.maxBy(_._1))
  }

  def part1(events: Seq[Event]): Int = {
    case class MaxValues(guardId: Int, mostAsleep: Count)

    val sortedEvents = events.sortBy(_.time)
    val days = generateDays(sortedEvents)
    val guards = days.foldLeft(Set.empty[Int]) { (s, d) => s + d.guardId }
    val maxValues = guards.map { guardId => MaxValues(guardId, findMinuteMostAsleep(guardId, days)) }
    val max = maxValues.maxBy(_.mostAsleep.count)
    max.guardId * max.mostAsleep.minute
  }

  def readData(filename: String): Try[String] =
    Using(Source.fromResource(filename))(_.mkString)
}
