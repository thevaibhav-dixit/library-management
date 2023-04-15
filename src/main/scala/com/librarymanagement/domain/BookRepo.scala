package com.librarymanagement.domain

import scala.collection.mutable.ListBuffer

class BookRepo {
  private val books: ListBuffer[Book] = ListBuffer.empty

  def add(book: Book): Unit = books += book

  def remove(book: Book): Unit = books -= book

  def findByTitle(title: String): Option[Book] =
    books.find(_.title == title)

  def findByAuthor(author: String): List[Book] =
    books.filter(_.author == author).toList

  def findByISBN(isbn :  String): Option[Book] =
    books.find(_.isbn == isbn)

  def findAll(): List[Book] = books.toList

  private var _isAvailable: Boolean = true

  def isAvailable: Boolean = _isAvailable

  def checkout(title : String): Unit = {
    if (!_isAvailable) {
      throw new RuntimeException(s"Book '$title' is not available for checkout.")
    }
    _isAvailable = false
  }

  def checkin(title : String): Unit = {
    if (_isAvailable) {
      throw new RuntimeException(s"Book '$title' is already available.")
    }
    _isAvailable = true
  }

}