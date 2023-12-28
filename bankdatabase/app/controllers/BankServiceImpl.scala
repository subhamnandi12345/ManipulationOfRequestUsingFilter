package controllers

class BankServiceImpl extends BankService {
  var customers: Seq[Customer] = Seq.empty

  override def addCustomer(name: String, id: String, email: String, balance: Double): Unit = {
    customers = customers :+ Customer(name, id, email, balance)
  }

  override def deleteCustomer(id: String): Unit = {
    customers = customers.filterNot(_.id == id)
  }

  override def showCustomers(): Seq[Customer] = customers

  override def transferMoney(senderId: String, receiverId: String, amount: Double): Unit = {
    val sender = customers.find(_.id == senderId)
    val receiver = customers.find(_.id == receiverId)

    (sender, receiver) match {
      case (Some(sender), Some(receiver)) if sender.balance >= amount =>
        val updatedSender = sender.copy(balance = sender.balance - amount)
        val updatedReceiver = receiver.copy(balance = receiver.balance + amount)

        customers = customers.map(c => if (c.id == senderId) updatedSender else if (c.id == receiverId) updatedReceiver else c)

      case _ => // Handle insufficient funds or invalid customer IDs
    }
  }
}
