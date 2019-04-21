package com.lastsys.aoc.aoc2015.Day05

import com.lastsys.aoc.aoc2015.Day05
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class Day05Spec extends FlatSpec with Matchers with TableDrivenPropertyChecks {
  "containsChars" should "work for example inputs" in {
    val vowels = Set('a', 'e', 'i', 'o', 'u')
    Day05.containsChars("ugknbfddgicrmopn", vowels, 3) shouldBe true
    Day05.containsChars("dvszwmarrgswjxmb", vowels, 3) shouldBe false
  }

  "containsRepeatedChar" should "work for example inputs" in {
    Day05.containsRepeatedChar("ugknbfddgicrmopn", 2) shouldBe true
    Day05.containsRepeatedChar("jchzalrnumimnmhp", 2) shouldBe false
  }

  "doesNotContainStrings" should "work for example inputs" in {
    val forbidden = Set("ab", "cd", "pq", "xy")
    Day05.doesNotContainStrings("ugknbfddgicrmopn", forbidden) shouldBe true
    Day05.doesNotContainStrings("haegwjzuvuyypxyu", forbidden) shouldBe false
  }

  "isNice" should "work for example inputs" in {
    val examples = Table(
      ("text", "nice"),
      ("ugknbfddgicrmopn", true),
      ("aaa", true),
      ("jchzalrnumimnmhp", false),
      ("haegwjzuvuyypxyu", false),
      ("dvszwmarrgswjxmb", false)
    )

    forAll (examples) { (text, nice) =>
      Day05.isNice(text) shouldBe nice
    }
  }

  "containsRepeatingPairs" should "work for all examples" in {
    val examples = Table(
      ("text", "result"),
      ("xyxy", true),
      ("aabcdefgaa", true),
      ("aaa", false),
      ("qjhvhtzxzqqjkmpb", true),
      ("xxyxx", true),
      ("andujbliujop", true)
    )

    forAll (examples) { (text, result) =>
      Day05.containsRepeatingPairs(text) shouldBe result
    }
  }

  "containsRepeatingLetterWithOneInBetween" should "work for all examples" in {
    val examples = Table(
      ("text", "result"),
      ("xyx", true),
      ("abcdefeghi", true),
      ("qjhvhtzxzqqjkmpb", true),
      ("xxyxx", true),
      ("uurcxstgmygtbstg", false),
      ("ieodomkazucvgmuy", true)
    )

    forAll (examples) { (text, result) =>
      Day05.containsRepeatingLetterWithOneInBetween(text) shouldBe result
    }
  }

  "isNice2" should "work for all examples" in {
    val examples = Table(
      ("text", "result"),
      ("qjhvhtzxzqqjkmpb", true),
      ("xxyxx", true),
      ("uurcxstgmygtbstg", false),
      ("ieodomkazucvgmuy", false)
    )

    forAll (examples) { (text, result) =>
      Day05.isNice2(text) shouldBe result
    }
  }
}
