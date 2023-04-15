package com.librarymanagement.domain

import scala.collection.mutable
case class Account (name : String, contact : String, balance: Double) {
  private val checkedOutBooks: mutable.Set[Book] = mutable.Set()

  def checkoutBook(book: Book): Unit = {
    checkedOutBooks += book
  }

  def checkinBook(book: Book): Unit = {
    checkedOutBooks -= book
  }

  def hasCheckedOut(book: Book): Boolean = {
    checkedOutBooks.contains(book)
  }
}
