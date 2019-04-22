package com.lastsys.aoc.aoc2015.Day06

sealed trait Instruction
case class TurnOn(r: Rect) extends Instruction
case class TurnOff(r: Rect) extends Instruction
case class Toggle(r: Rect) extends Instruction
