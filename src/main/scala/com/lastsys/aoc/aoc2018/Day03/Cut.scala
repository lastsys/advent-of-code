package com.lastsys.aoc.aoc2018.Day03

case class Cut(id: Int, x: Int, y: Int, w: Int, h: Int) {
  def overlap(that: Cut): Boolean = {
    if ((this.x + this.w - 1 < that.x) || (this.x > that.x + that.w - 1)) return false
    if ((this.y + this.h - 1 < that.y) || (this.y > that.y + that.h - 1)) return false
    true
  }
}
