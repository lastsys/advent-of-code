package com.lastsys.aoc.aoc2018.Day04

case class Day(guardId: Int, awake: Vector[Int] = Vector.fill(60)(0))

object Day {
  def apply(guardId: Int, awake: String): Day = {
    Day(guardId, awake.chars().toArray.map(v => if (v == '#') 1 else 0).toVector)
  }
}
