package com.librarymanagement.service

import com.librarymanagement.domain.{Account, Book , AccountRepo , BookRepo}
import scala.collection.mutable.ListBuffer

class LibraryService(bookRepository: BookRepo, accountRepository: AccountRepo) {

  def getAccountDetails(contact: String): Option[Account] = {
    accountRepository.findByContact(contact)
  }

  def addAccount(account: Account): Unit = {
    accountRepository.add(account)
  }

  def removeAccount(account: Account): Unit = {
    accountRepository.remove(account)
  }

  def depositMoney(contact : String, amount: Double): Option[Account] = {
    val accountOption = accountRepository.findByContact(contact)
    accountOption match {
      case Some(account) =>
        val updatedAccount = account.copy(balance = account.balance + amount)

        accountRepository.add(updatedAccount)
        accountRepository.remove(account)
        Some(updatedAccount)
      case None => None
    }
  }

  def addBook(book: Book): Unit = {
    bookRepository.add(book)
  }

  def checkoutBook(bookId: String, contact: String): Option[Account] = {
    val bookOption = bookRepository.findByISBN(bookId)
    val accountOption = accountRepository.findByContact(contact)

    (bookOption, accountOption) match {
      case (Some(book), Some(account)) if book.isAvailable =>
        book.checkout()
        account.checkoutBook(book)
        Some(account)
      case _ => None
    }
  }

  def checkinBook(bookId: String, contact: String): Option[Account] = {
    val bookOption = bookRepository.findByISBN(bookId)
    val accountOption = accountRepository.findByContact(contact)

    (bookOption, accountOption) match {
      case (Some(book), Some(account)) =>
        val updated_book = book.checkin()
        account.checkinBook(book)
        Some(account)
      case _ => None
    }
  }
}
