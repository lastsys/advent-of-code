package com.lastsys.aoc.util

case class Position(x: Int, y: Int) {
  def move(dx: Int, dy: Int): Position =
    Position(x + dx, y + dy)
}
