package com.jicay.bookmanagement.infrastructure.driven.adapter

import com.jicay.bookmanagement.domain.model.Book
import com.jicay.bookmanagement.domain.port.BookPort
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service

@Service
class BookDAO(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate): BookPort {
    override fun getAllBooks(): List<Book> {
        return namedParameterJdbcTemplate
            .query("SELECT * FROM BOOK", MapSqlParameterSource()) { rs, _ ->
                Book(
                    name = rs.getString("title"),
                    author = rs.getString("author"),
                    isreserved = rs.getBoolean("isreserved")
                )
            }
    }

    override fun createBook(book: Book) {
        namedParameterJdbcTemplate
            .update("INSERT INTO BOOK (title, author,isreserved) values (:title, :author, :isreserved)", mapOf(
                "title" to book.name,
                "author" to book.author,
                "isreserved" to book.isreserved
            ))
    }
    override fun getBookByTitle(title: String): Book? {
        val paramMap = mapOf("title" to title)


            return namedParameterJdbcTemplate.queryForObject(
                    "SELECT * FROM BOOK WHERE title = :title",
                    paramMap
            ) { rs, _ ->
                Book(
                        name = rs.getString("title"),
                        author = rs.getString("author"),
                        isreserved = rs.getBoolean("isreserved")
                )
            }

    }

    override fun reserveABook(title: String) {
        namedParameterJdbcTemplate
                .update("UPDATE BOOK SET isreserved = true WHERE title=:title", mapOf(
                        "title" to title
                ))
    }
}