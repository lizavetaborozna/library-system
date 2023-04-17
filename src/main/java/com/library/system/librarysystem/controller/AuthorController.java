package com.library.system.librarysystem.controller;


import com.library.system.librarysystem.model.Author;
import com.library.system.librarysystem.repository.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("")
    public String authorHome(Model model) {
        model.addAttribute("listOfAuthors", authorRepository.findAll());
        return "author/authorHome";
    }

    @GetMapping("/add")
    public String createAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "author/authorForm";
    }

    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @GetMapping("delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }

    @GetMapping("update/{id}")
    public String updateAuthor(@PathVariable Long id, Model model) {
        if (authorRepository.findById(id).isPresent()) {
            model.addAttribute("author", authorRepository.findById(id).get());
        }
        return "author/updateAuthorForm";
    }
}
