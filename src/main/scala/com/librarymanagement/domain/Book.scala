package com.librarymanagement.domain

case class Book(title: String, author: String, isbn: String){
  private var _isAvailable: Boolean = true

  def isAvailable: Boolean = _isAvailable

  def checkout(): Unit = {
    if (!_isAvailable) {
      throw new RuntimeException(s"Book '$title' is not available for checkout.")
    }
    _isAvailable = false
  }

  def checkin(): Unit = {
    if (_isAvailable) {
      throw new RuntimeException(s"Book '$title' is already available.")
    }
    _isAvailable = true
  }

}