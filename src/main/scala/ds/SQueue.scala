package ds

import scala.reflect._


trait SQueue[+T] {

  def enqueue[O >: T](obj: O)(implicit tag:ClassTag[O]): SQueue[O]

  def pop: T

  def remove: (T, SQueue[T])

  def size: Int

  def isEmpty: Boolean
}

object SCircularQueue {
  def apply[T](capacity: Int)(implicit tag:ClassTag[T]): SCircularQueue[T] = new SCircularQueue[T](capacity)
}

class SCircularQueue[+T](capacity: Int)(implicit ttag:ClassTag[T]) extends SQueue[T] {
  private[this] var holder = List.empty[T]

  private def this(holder: List[T], capacity: Int)(implicit m: ClassTag[T]) = {
    this(capacity)
    this.holder = holder
  }


   def enqueue[O >: T](obj: O)(implicit tag:ClassTag[O]): SQueue[O] = {
    if (isFull) throw new IllegalStateException("Queue is full")
    new SCircularQueue[O](holder ::: List(obj), capacity)
  }

  override def pop: T = holder.head

  override def remove: (T, SQueue[T]) = (holder.head, new SCircularQueue[T](holder.tail, capacity))

  override def size: Int = holder.length

  override def isEmpty: Boolean = holder.isEmpty

  private def isFull: Boolean = holder.lengthCompare(capacity) == 0


}