package com.lastsys.aoc.aoc2015.Day02

case class Box(l: Int, w: Int, h: Int) {
  def areas: (Int, Int, Int) = (l*w, w*h, h*l)
  def smallestArea: Int = {
    val a = areas
    Array(a._1, a._2, a._3).min
  }
  def area: Int = {
    val a = areas
    2*a._1 + 2*a._2 + 2*a._3
  }
  def paperArea: Int = area + smallestArea
  def smallestPerimeter: Int = {
    val s = Array(l, w, h).sorted
    2*s(0) + 2*s(1)
  }
  def bowLength: Int = l * w * h
  def ribbonLength: Int = smallestPerimeter + bowLength
}
