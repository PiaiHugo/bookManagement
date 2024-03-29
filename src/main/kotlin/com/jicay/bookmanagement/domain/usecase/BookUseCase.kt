package com.jicay.bookmanagement.domain.usecase

import com.jicay.bookmanagement.domain.model.Book
import com.jicay.bookmanagement.domain.port.BookPort

class BookUseCase(
    private val bookPort: BookPort
) {
    fun getAllBooks(): List<Book> {
        return bookPort.getAllBooks().sortedBy {
            it.name.lowercase()
        }
    }

    fun addBook(book: Book) {
        bookPort.createBook(book)
    }

    fun reserveABook(title: String) {
        val book = bookPort.getBookByTitle(title)

        if (book != null) {
            if (!book.isreserved) {
                bookPort.reserveABook(title)
            } else{
                throw Exception("Le livre est déjà reservé")
            }
        }
    }
}