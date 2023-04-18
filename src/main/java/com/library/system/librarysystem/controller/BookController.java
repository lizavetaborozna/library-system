package com.library.system.librarysystem.controller;

import com.library.system.librarysystem.model.Book;
import com.library.system.librarysystem.model.Genre;
import com.library.system.librarysystem.repository.AuthorRepository;
import com.library.system.librarysystem.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("")
    public String homePage(Model model) {
        model.addAttribute("listOfBooks", bookRepository.findAll());
        return "home";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        setBookModelAttributes(new Book(), model);
        return "book/bookForm";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, Model model) {
        if (bookRepository.findById(id).isPresent()) {
            setBookModelAttributes(bookRepository.findById(id).get(), model);
        }
        return "book/updateBookForm";
    }

    @GetMapping("/{id}")
    public String getBookInfo(@PathVariable Long id, Model model) {
        if (bookRepository.findById(id).isPresent()) {
            model.addAttribute("book", bookRepository.findById(id).get());
        }
        return "book/bookInfo";
    }

    private void setBookModelAttributes(Book book, Model model) {
        model.addAttribute("book", book);
        model.addAttribute("bookGenre", Genre.values());
        model.addAttribute("bookAuthor", authorRepository.findAll());
    }
}
