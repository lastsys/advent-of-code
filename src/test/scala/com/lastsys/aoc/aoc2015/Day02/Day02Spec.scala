package com.lastsys.aoc.aoc2015.Day02

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class Day02Spec extends FlatSpec with Matchers with TableDrivenPropertyChecks {
  "Box" should "calculate correct area" in {
    val examples = Table(
      ("box", "area", "paperArea"),
      (Box(2, 3, 4), 52, 58),
      (Box(1, 1, 10), 42, 43)
    )

    forAll (examples) { (box, area, paperArea) =>
      box.area shouldBe area
      box.paperArea shouldBe paperArea
    }
  }

  it should "calculate correct ribbon length" in {
    val examples = Table(
      ("box", "perimeter", "bow", "total"),
      (Box(2, 3, 4), 10, 24, 34),
      (Box(1, 1, 10), 4, 10, 14)
    )

    forAll (examples) { (box, perimeter, bow, total) =>
      box.smallestPerimeter shouldBe perimeter
      box.bowLength shouldBe bow
      box.ribbonLength shouldBe total
    }
  }
}
