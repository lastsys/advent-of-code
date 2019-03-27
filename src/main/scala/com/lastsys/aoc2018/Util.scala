package com.lastsys.aoc2018

object Util {
  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block
    val t1 = System.nanoTime()
    println("Elapsed time: " + ((t1 - t0)  / 1e6 ) + "ms")
    result
  }
}
