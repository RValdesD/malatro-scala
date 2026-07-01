package cl.uchile.dcc
package states

abstract class ARoundState(protected val controller: RoundController) extends TRoundState {

  def playCards(indices: List[Int]): Unit = {
    controller.setMessage("Action not allowed in the current state.")
  }

  def discardCards(indices: List[Int]): Unit = {
    controller.setMessage("Action not allowed in the current state.")
  }

  def onEnter(): Unit = {}

  override def equals(o: Any): Boolean =
    o.isInstanceOf[TRoundState] && o.asInstanceOf[TRoundState].getName.equals(this.getName)
}

