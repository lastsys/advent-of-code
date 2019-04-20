package com.lastsys.aoc.aoc2015

import com.lastsys.aoc.Util.header

object Main {
  def main(args: Array[String]): Unit = {
    println("Advent of Code 2015")

    header("Day01") { Day01.run() }
    header("Day02") { Day02.run() }
    header("Day03") { Day03.run() }
    header("Day04") { Day04.run() }
  }
}
