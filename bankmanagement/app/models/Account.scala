package models

trait Account {
  def accountId: String
  def balance: Double
  def withdraw(amount: Double): Account
  def deposit(amount: Double): Account
}

