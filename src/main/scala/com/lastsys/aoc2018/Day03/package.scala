package com.lastsys.aoc2018

import fastparse.Parsed.Success

import scala.io.Source
import scala.util.{Try, Using}
import fastparse.parse

package object Day03 extends AocTask {
  override def run(): Unit = {
    val data = readData("day03/input.txt")
    val cuts = data.map(parse(_, Parser.cuts(_)))
    cuts.map {
      case Success(value, _) =>
        println(s"Day03 / Part 1 = ${part1(value)}")
        println(s"Day03 / Part 2 = ${part2(value)}")
    }
  }

  def part1(data: Seq[Cut]): Int = {
    data.foldLeft(Map.empty[(Int, Int), Int]) { (count, cut) =>
      val coords = for {
        x <- cut.x until (cut.x + cut.w)
        y <- cut.y until (cut.y + cut.h)
      } yield (x, y)
      coords.foldLeft(count) { (s, v) => s + (v -> s.get(v).fold(1) { _ + 1}) }
    }.values.count(_ >= 2)
  }

  def part2(data: Seq[Cut]): Int = {
    val allCuts = (for (c <- data) yield c.id).toSet

    val pairs = for {
      i <- 0 until data.length - 1
      j <- i + 1 until data.length
    } yield (data(i), data(j))

    val notOverlapping = pairs.foldLeft(allCuts) { case (state, (cut1, cut2)) =>
      if (cut1.overlap(cut2)) state -- Set(cut1.id, cut2.id) else state }

    notOverlapping.head
  }

  def readData(filename: String): Try[String] =
    Using(Source.fromResource(filename))(_.mkString)
}
