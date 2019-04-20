package com.lastsys.aoc.aoc2015

import java.security.MessageDigest

import scala.annotation.tailrec

package object Day04 {
  def run(): Unit = {
    val data = "bgvyzdsv"
    println(s"Day04 / Part 1 = ${part1(data)}")
    println(s"Day04 / Part 2 = ${part2(data)}")
 }

  def findHash(key: String, prefix: String): Long = {
    val digest = MessageDigest.getInstance("MD5")

    @tailrec
    def next(i: Long): Long = {
      val bytes = digest.digest(s"$key$i".getBytes)
      val hex = toHex(bytes)
      if (hex.startsWith(prefix)) i else next(i + 1)
    }

    next(0)
  }

  def toHex(bytes: Array[Byte]): String = bytes.map("%02x" format _).mkString

  def part1(key: String): Long = findHash(key, "00000")
  def part2(key: String): Long = findHash(key, "000000")
}
