package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
public class MovieController {

    @Autowired
    MovieService movieService;



    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody() Movie movie) {
        movieService.addMovie(movie);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody() Director director) {
        movieService.addDirector(director);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name) {
        return new ResponseEntity(movieService.getMovieByName(name),HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name) {
        return new ResponseEntity(movieService.getDirectorByName(name),HttpStatus.ACCEPTED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movie, @RequestParam("director") String director) {
        movieService.addMovieDirectorPair(movie,director);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String director) {
        return new ResponseEntity(movieService.getMoviesByDirectorName(director),HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        return new ResponseEntity(movieService.findAllMovies(),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String name) {
        movieService.deleteDirectorByName(name);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

//    @DeleteMapping("/movies/delete-all-directors")
//    public ResponseEntity deleteAllDirectors() {
//        movieService.deleteAllDirectors();
//        return new ResponseEntity(HttpStatus.ACCEPTED);
//    }
}
