package com.lastsys.aoc.aoc2018

import com.lastsys.aoc.{AocTask, Util}

import scala.io.Source
import scala.util.{Try, Using}

package object Day02 extends AocTask {
  def run(): Unit = {
    val data = readData("2018/day02/input.txt")
    data.foreach { d =>
      println(s"Day02 / Part1 = ${part1(d)}")
      println(s"Day02 / Part2 = ${part2(d)}")
    }
  }

  def countLetters(s: String): Map[Char, Int] =
    s.foldLeft(Map.empty[Char, Int])(Util.incrementMap)

  def haveCount(count: Map[Char, Int], n: Int): Boolean =
    count.values.foldLeft(false) { (s, m) =>
      s || (m == n)
    }

  def part1(data: Seq[String]): Int = {
    val counted = data.map(countLetters)
    val count2 = counted.count(haveCount(_, 2))
    val count3 = counted.count(haveCount(_, 3))
    count2 * count3
  }

  def countDifferingChars(s1: String, s2: String): Int =
    s1.zip(s2).count { case (c1, c2) => c1 != c2 }

  def commonLetters(s1: String, s2: String): String =
    s1.zip(s2).filter { case (c1, c2) => c1 == c2 }.map(_._1).mkString("")

  def part2(data: Seq[String]): String = {
    val serials = for {
      i <- 0 until (data.length - 1)
      j <- (i + 1) until data.length
      if countDifferingChars(data(i), data(j)) == 1
    } yield commonLetters(data(i), data(j))
    serials.head
  }

  def readData(filename: String): Try[Seq[String]] = {
    Using(Source.fromResource(filename)) { src =>
      src.getLines().toVector
    }
  }
}
