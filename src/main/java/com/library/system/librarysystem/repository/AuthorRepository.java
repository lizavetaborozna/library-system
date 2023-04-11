package com.library.system.librarysystem.repository;

import com.library.system.librarysystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
