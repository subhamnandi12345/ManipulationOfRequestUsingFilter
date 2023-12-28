package controllers
import javax.inject._
import play.api._
import play.api.mvc._

import scala.collection.mutable.ListBuffer

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
import javax.inject._
import play.api.mvc._
import models._

@Singleton
class BankController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def operateOnAccount(account: Account, amount: Double, operation: (Account, Double) => Account): Account = {
    operation(account, amount)
  }

  def makeTransaction(accountId: String, amount: Double, transactionType: String) = Action {
    val savingsAccount = SavingsAccount(accountId, 1000.0)
    val transactionLog = new SimpleTransactionLog

    val updatedAccount = operateOnAccount(savingsAccount, amount, (acc, amt) =>
      transactionType match {
        case "withdraw" => acc.withdraw(amt)
        case "deposit" => acc.deposit(amt)
        case _ => acc
      }
    )

    val transaction = Transaction(accountId, amount, transactionType)
    transactionLog.logTransaction(transaction)

    Ok(s"Transaction successful. New balance: ${updatedAccount.balance}")
  }
  def getTransactions(accountId: String) = Action {
    val transactionLog = new SimpleTransactionLog
    val transactions = transactionLog.getTransactions(accountId)
    Ok(s"Transactions for account $accountId: ${transactions.map(t => s"${t.transactionType} ${t.amount}").mkString(", ")}")
  }
}


