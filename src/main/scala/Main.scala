package com.librarymanagementsystem

import com.librarymanagement.domain.{Account, Book , BookRepo , AccountRepo}
import com.librarymanagement.service.LibraryService

object Main {
  def main(args: Array[String]): Unit = {
    val bookRepository = new BookRepo()
    val accountRepository = new AccountRepo()
    val libraryService = new LibraryService(bookRepository, accountRepository)

    // Adding a new book
    val book1 = Book("The Great Gatsby", "F. Scott Fitzgerald" , "1234567890")
    libraryService.addBook(book1)

    // Adding a new account
    val account1 = Account("John Doe", "9098765432", 100.0)
    libraryService.addAccount(account1)

    // Checking out a book
    libraryService.checkoutBook(book1.isbn , account1.contact)
    // Checking in a book
    libraryService.checkinBook(book1.isbn , account1.contact)
    println(s"Book ${book1.title} checked in by ${account1.name}. Is available: ${book1.isAvailable}")

    // Adding funds to an account
    libraryService.depositMoney(account1.contact, 50.0)
    println(s"${account1.name}'s balance: ${account1.balance}")

    // Removing an account
    libraryService.removeAccount(account1)
    println(s"Account for ${account1.name} removed.")
  }
}
