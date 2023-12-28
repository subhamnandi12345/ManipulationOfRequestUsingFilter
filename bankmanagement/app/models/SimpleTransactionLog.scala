package models

class SimpleTransactionLog extends TransactionLog {
  private var transactions: List[Transaction] = List()

  def logTransaction(transaction: Transaction): Unit = {
    transactions = transaction :: transactions
  }

  def getTransactions(accountId: String): List[Transaction] = {
    transactions.filter(_.accountId == accountId)
  }
}
