package com.librarymanagement.domain

import scala.collection.mutable.ListBuffer

class AccountRepo {
  private val accounts: ListBuffer[Account] = ListBuffer.empty

  def add(account: Account): Unit = accounts += account

  def remove(account: Account): Unit = accounts -= account
  def findByContact(contact: String): Option[Account] =
    accounts.find(_.contact == contact)
  def findAll(): List[Account] = accounts.toList

  def checkout(account: Account, book: Book): Unit = {
    if (!book.isAvailable) {
      throw new RuntimeException(s"Book '${book.title}' is not available for checkout.")
    }
    account.checkoutBook(book)
    book.checkout()
  }

  def checkin(account: Account, book: Book): Unit = {
    account.checkinBook(book)
    book.checkin()
  }
}