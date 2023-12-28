package models

trait TransactionLog {
  def logTransaction(transaction: Transaction): Unit

  def getTransactions(accountId: String): List[Transaction]

}
