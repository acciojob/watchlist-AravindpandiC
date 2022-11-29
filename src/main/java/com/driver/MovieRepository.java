package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Component
@Repository
public class MovieRepository {
    List<Movie> movies =new ArrayList<>();
    List<Director> directors = new ArrayList<>();
    Map<Director,List<Movie>> directorMovies = new HashMap<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addDirector(Director director) {
        directors.add(director);
    }

    public Movie getMovieByName(String name) {
        for(Movie movie:movies) {
            if(movie.getName().equals(name)) return movie;
        }
        return null;
    }

    public Director getDirectorByName(String name) {
        for(Director director:directors) {
            if(director.getName().equals(name)) return director;
        }
        return null;
    }

    public void addMovieDirectorPair(String movieName, String directorName){
        Director director = getDirectorByName(directorName);
        Movie movie = getMovieByName(movieName);
        if(!directorMovies.containsKey(director)) directorMovies.put(director,new ArrayList<>());
        directorMovies.get(director).add(movie);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        List<String> movieList = new ArrayList<>();
        Director director = getDirectorByName(directorName);
        if(director == null || !directorMovies.containsKey(director)) return movieList;
        for(Movie movie:directorMovies.get(director)) {
            movieList.add(movie.getName());
        }
        return movieList;
    }

    public List<String> findAllMovies() {
        List<String> movieList = new ArrayList<>();
        for(Movie movie:movies) movieList.add(movie.getName());
        return movieList;
    }

    public void deleteDirectorByName(String directorName) throws ConcurrentModificationException {

            Director director = getDirectorByName(directorName);
            if (directorMovies.containsKey(director) && director != null) {
                List<Movie> listmovie = directorMovies.get(director);
                for(Movie movie:listmovie) movies.remove(movie);
                directorMovies.remove(director);
            }
            directors.remove(director);
    }

    public void deleteAllDirectors() throws ConcurrentModificationException {
        for(Director director:directors) {
            deleteDirectorByName(director.getName());
        }
        directors.clear();
    }
}
