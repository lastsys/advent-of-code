package com.lastsys.aoc2018

import scala.annotation.tailrec
import scala.io.Source
import scala.util.{Try, Using}

package object Day01 extends AocTask {
  def run(): Unit = {
    val data = readData("day01/input.txt")
    data.foreach { d =>
      println(s"Day01 / Part 1 = ${part1(d)}")
      println(s"Day02 / Part 2 = ${part2(d)}")
    }
  }

  def part1(data: Seq[Long]): Long = data.sum

  def part2(data: Seq[Long]): Long = {
    @tailrec
    def next(stream: Stream[Long], sum: Long = 0, state: Set[Long] = Set(0)): Long = {
      val v = sum + stream.head
      if (state.contains(v)) v else next(stream.tail, sum + stream.head, state + v)
    }
    next(Stream.continually(data).flatten)
  }

  def readData(filename: String): Try[Seq[Long]] = {
    Using(Source.fromResource(filename)) { src =>
      src.getLines().map(_.toLong).toVector
    }
  }
}
