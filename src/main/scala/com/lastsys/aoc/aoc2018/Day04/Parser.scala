package com.lastsys.aoc.aoc2018.Day04

import java.time.{OffsetDateTime, ZoneOffset}

object Parser {
  import fastparse._
  import NoWhitespace._

  def events[_: P]: P[Seq[Event]] = P(event.rep(1, sep="\n"))
  private def event[_: P]: P[Event] = P("[" ~ date ~ "]" ~ " " ~ action).map((Event.apply _).tupled)

  private def action[_: P]: P[GuardAction] = P(wakesUp | fallsAsleep | beginShift)

  private def wakesUp[_: P]: P[GuardAction] = P("wakes up".!).map(_ => WakesUp)
  private def fallsAsleep[_: P]: P[GuardAction] = P("falls asleep".!).map(_ => FallsAsleep)
  private def beginShift[_: P]: P[GuardAction] = P("Guard #" ~ number ~ " begins shift").map(BeginShift)

  private def date[_: P]: P[OffsetDateTime] = P(number ~ "-" ~ number ~ "-" ~ number ~ " " ~ number ~ ":" ~ number)
    .map { case (year, month, day, hour, minute) =>
      OffsetDateTime.of(year, month, day, hour, minute, 0, 0, ZoneOffset.UTC)
    }

  private def number[_: P] : P[Int] = P(CharIn("0-9").rep(1)).!.map(_.toInt)
}
