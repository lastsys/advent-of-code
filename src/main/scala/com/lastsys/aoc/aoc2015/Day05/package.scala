package com.lastsys.aoc.aoc2015

import com.lastsys.aoc.AocTask

import scala.io.Source
import scala.util.{Try, Using}

package object Day05 extends AocTask {
  val vowels: Set[Char] = Set('a', 'e', 'i', 'o', 'u')
  val forbidden: Set[String] = Set("ab", "cd", "pq", "xy")

  def run(): Unit = {
    val data = readData("2015/day05/input.txt")
    data.foreach { d =>
      println(s"Day05 / Part 1 = ${part1(d)}")
      println(s"Day05 / Part 2 = ${part2(d)}")
    }
  }

  def part1(data: Seq[String]): Int = data.map(isNice).count(identity)

  def part2(data: Seq[String]): Int = data.map(isNice2).count(identity)

  def containsChars(s: String, chars: Set[Char], count: Int): Boolean = {
    s.foldLeft(0) { (found, char) =>
      if (chars.contains(char)) found + 1 else found
    } >= count
  }

  def containsRepeatedChar(s: String, repeat: Int): Boolean = {
    val (success, _, _) = s.chars.toArray.foldLeft((false, 1, ' ')) { case ((state, count, prevChar), char) =>
      val newCount = if (prevChar == char.toChar) count + 1 else 1
      if (newCount == repeat) (true, newCount, char.toChar) else (state, newCount, char.toChar)
    }
    success
  }

  def doesNotContainStrings(s: String, forbidden: Set[String]): Boolean = {
    !forbidden.foldLeft(false) { (state, f) => state || s.contains(f) }
  }

  def isNice(s: String): Boolean =
    containsChars(s, vowels, 3) && containsRepeatedChar(s, 2) && doesNotContainStrings(s, forbidden)

  def containsRepeatingPairs(s: String): Boolean = {
    for (i <- 0 until (s.length - 3);
         j <- (i + 2) until s.length - 1) yield {
      val s1 = s.substring(i, i + 2)
      val s2 = s.substring(j, j + 2)
      if (s1 == s2) return true
    }
    false
  }

  def containsRepeatingLetterWithOneInBetween(s: String): Boolean = {
    for (i <- 0 until (s.length - 2)) yield {
      val c1 = s.charAt(i)
      val c2 = s.charAt(i + 2)
      if (c1 == c2) return true
    }
    false
  }

  def isNice2(s: String): Boolean =
    containsRepeatingPairs(s) && containsRepeatingLetterWithOneInBetween(s)

  def readData(filename: String): Try[Seq[String]] =
    Using(Source.fromResource(filename))(_.getLines().toVector)
}
