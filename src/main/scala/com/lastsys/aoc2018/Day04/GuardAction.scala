package com.lastsys.aoc2018.Day04

sealed trait GuardAction
case object WakesUp extends GuardAction
case object FallsAsleep extends GuardAction
case class BeginShift(guardId: Int) extends GuardAction
