package com.WebApp.MovieNights.repositories;

import com.WebApp.MovieNights.models.Bookmarks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseBookmarksRepository extends JpaRepository<Bookmarks,String> {
}
