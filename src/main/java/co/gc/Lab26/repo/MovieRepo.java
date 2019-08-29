package co.gc.Lab26.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.gc.Lab26.controller.entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
	
//	@Query("SELECT * FROM movies where category='action'")
//	List<Movie> findByCategory(String category);

	@Query(value="SELECT * FROM movies where category=?", nativeQuery = true)
	List<Movie> findByCategory(String category);
	
	@Query(value="SELECT * FROM movies where id=?", nativeQuery = true)
	Movie findById(int id);
	
	@Query(value="SELECT * FROM movies where category=? and id=?", nativeQuery = true)
	Movie findByIdCat(String category, int id);
	
	@Query(value="SELECT distinct category FROM movies", nativeQuery = true)
	List<String> findByCat();
	
	@Query(value="SELECT category FROM movies where title=?", nativeQuery = true)
	String findByTitle(String title);
	
	List<Movie> findByTitleContaining(String title);
}

