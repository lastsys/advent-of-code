package com.lastsys.aoc2018

import scala.annotation.tailrec
import scala.io.Source
import scala.util.{Try, Using}

package object Day01 extends AocTask {
  def run(): Unit = {
    val data = readData("day01-input.txt")
    println(s"Day01 / Part 1 = ${data.map(part1)}")
    println(s"Day02 / Part 2 = ${data.map(part2)}")
  }

  def name: String = "Task 1"

  def part1(data: Seq[Long]): Long = data.sum

  def part2(data: Seq[Long]): Long = {
    @tailrec
    def next(stream: Stream[Long], sum: Long = 0, state: Set[Long] = Set.empty): Long = {
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
