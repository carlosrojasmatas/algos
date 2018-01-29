package ds

import org.scalatest.{MustMatchers, WordSpec}

class QueueSpec extends WordSpec with MustMatchers{


  "Circular queue" must {
    "Add and remove elements properly " in{

      val q = new SCircularQueue[Int](5)

      val finalq = q
        .enqueue(1)
        .enqueue(2)
        .enqueue(3)
        .enqueue(4)
        .enqueue(5)

      finalq.size must be (5)

      val a = finalq.pop
      val b = finalq.pop
      val c = finalq.pop

      a must equal (b)
      b must equal (c)
    }
  }

}
