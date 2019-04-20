package com.lastsys.aoc.aoc2018.Day03

object Parser {
  import fastparse._
  import MultiLineWhitespace._

  def cuts[_: P]: P[Seq[Cut]] = P(cut.rep)

  private def cut[_: P]: P[Cut] = P("#" ~ number ~ "@" ~ number ~ "," ~ number ~ ":" ~ number ~ "x" ~ number)
    .map((Cut.apply _).tupled)

  private def number[_: P]: P[Int] = P(CharIn("0-9").rep(1)).!.map(_.toInt)
}
