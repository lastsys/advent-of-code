package com.lastsys.aoc2018.Day04

import java.time.{OffsetDateTime, ZoneOffset}

import fastparse.Parsed.Success
import org.scalatest.{FlatSpec, Matchers}
import fastparse.parse

class Day04Spec extends FlatSpec with Matchers {
  def date(y: Int, mo: Int, d: Int, h: Int, mi: Int): OffsetDateTime =
    OffsetDateTime.of(y, mo, d, h, mi, 0, 0, ZoneOffset.UTC)

  "The event parser" should "read input files" in {
    val data = readData("day04/test-input-p1-01.txt")
    data.map(parse(_, Parser.events(_)) shouldBe Seq(
      Event(date(1518, 11, 1, 0, 0), BeginShift(10)),
      Event(date(1518, 11, 1, 0, 5), FallsAsleep),
      Event(date(1518, 11, 1, 0, 25), WakesUp),
      Event(date(1518, 11, 1, 0, 30), FallsAsleep),
      Event(date(1518, 11, 1, 0, 55), WakesUp),
      Event(date(1518, 11, 1, 23, 58), BeginShift(99)),
      Event(date(1518, 11, 2, 0, 40), FallsAsleep),
      Event(date(1518, 11, 2, 0, 50), WakesUp),
      Event(date(1518, 11, 3, 0, 5), BeginShift(10)),
      Event(date(1518, 11, 3, 0, 24), FallsAsleep),
      Event(date(1518, 11, 3, 0, 29), WakesUp),
      Event(date(1518, 11, 4, 0, 2), BeginShift(99)),
      Event(date(1518, 11, 4, 0, 36), FallsAsleep),
      Event(date(1518, 11, 4, 0, 46), WakesUp),
      Event(date(1518, 11, 5, 0, 3), BeginShift(99)),
      Event(date(1518, 11, 5, 0, 45), FallsAsleep),
      Event(date(1518, 11, 5, 0, 55), WakesUp)
    ))
  }

  "Generating data for days" should "result in correct sleeping patterns" in {
    val data = readData("day04/test-input-p1-01.txt")
    data.map(parse(_, Parser.events(_))).map {
      case Success(events, _) => generateDays(events) shouldBe Seq(
        Day(10, ".....####################.....#########################....."),
        Day(99, "........................................##########.........."),
        Day(10, "........................#####..............................."),
        Day(99, "....................................##########.............."),
        Day(99, ".............................................##########.....")
      )
    }
  }
}
