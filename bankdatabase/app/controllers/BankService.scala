package controllers

trait BankService {
  def addCustomer(name: String, id: String, email: String, balance: Double): Unit
  def deleteCustomer(id: String): Unit
  def showCustomers(): Seq[Customer]
  def transferMoney(senderId: String, receiverId: String, amount: Double): Unit
}
