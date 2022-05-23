package com.WebApp.MovieNights.repositories;

import com.WebApp.MovieNights.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseUserRepository extends JpaRepository<User, String> {
}
