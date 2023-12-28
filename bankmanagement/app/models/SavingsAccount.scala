package models


case class SavingsAccount(accountId: String, balance: Double) extends Account {
  def withdraw(amount: Double): Account = copy(balance = balance - amount)
  def deposit(amount: Double): Account = copy(balance = balance + amount)
}