package co.gc.Lab26.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import co.gc.Lab26.controller.entity.Movie;
import co.gc.Lab26.repo.MovieRepo;

@RestController
public class HomeController {
	
	@Autowired
	MovieRepo repo;

	//get a list of movies
	@GetMapping("/")
	public List<Movie> getListMovie(){
		List<Movie> list = repo.findAll();
		
		return list;
	}
	
	//get a list of movies in a specific cat
	@GetMapping("movie-cat/{category}")
	public List<Movie> getMovieCat(@PathVariable("category") String category){
		List<Movie> list = repo.findByCategory(category);
		return list;
		
	}
	
	//get a random movie pick
	@GetMapping("random")
	public Movie getRandomMovie() {
		List<Movie> list = repo.findAll();
		Random rand = new Random();
		int num = rand.nextInt(list.size()) + 1;
		
		return repo.findById(num);
	}
	
	//get a random movie from a specific category
	@GetMapping("cat-random/{category}")
	public Movie getRandomMovieCat(@PathVariable("category") String category) {
		List<Movie> list = repo.findByCategory(category);
		Random rand = new Random();
		int num = rand.nextInt(list.size()) + 1;
		
		return repo.findById(num);
	}
	
	//get a list of random movie picks
	@GetMapping("random-list/{id}")
	public List<Movie> getRandomList(@PathVariable("id") int limit){
		Random rand = new Random();
		List<Movie> list1 = repo.findAll();
		List<Movie> list = new ArrayList<Movie>();
		for (int i = 0; i <= limit; i++) {
			int num1 = rand.nextInt(list1.size()) + 1;
			list.add(repo.findById(num1));
			
		}
		return list;
	}
	
	//get a list of distinct categories
		@GetMapping("category")
		public List<String> getCategory(){
			List<String> list = repo.findByCat();
			return list;
			
			
		}
		
		//get info from user input of movie title
		@GetMapping("movie-info/{title}")
		public String getInfo(@PathVariable("title") String title) {
			return repo.findByTitle(title);
		}
		
		@GetMapping("search-title/{title}")
		public List<Movie> getTitleKey(@PathVariable("title") String title){
			return repo.findByTitleContaining(title);
		}
		
		
	
	
	
	
	
}
