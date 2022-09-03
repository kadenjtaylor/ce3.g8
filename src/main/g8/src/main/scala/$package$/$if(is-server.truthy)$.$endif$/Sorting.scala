package dev.kaden

import cats.effect.IO

object Sorting {

  case class SortRequest(nums: Int*)

  case class SortResponse(nums: Int*)

  def sortingEndpoint(req: SortRequest): IO[SortResponse] = {
    IO.pure(SortResponse(req.nums.sorted: _*))
  }

}
