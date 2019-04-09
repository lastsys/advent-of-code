package com.lastsys.aoc2018

import com.lastsys.aoc2018.Util.time

object Main {
  def main(args: Array[String]): Unit = {
    println("Advent of Code 2018")

    header("Day01") { Day01.run() }
    header("Day02") { Day02.run() }
    header("Day03") { Day03.run() }
    header("Day04") { Day04.run() }
  }

  def header(label: String)(block: => Unit): Unit = {
    println(s"-- $label -----------------------------")
    time { block }
  }
}
