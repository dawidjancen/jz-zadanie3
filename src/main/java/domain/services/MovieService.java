package domain.services;

import java.util.ArrayList;

import domain.Movie;

public class MovieService {
	private static ArrayList<Movie> db = new ArrayList<Movie>();
	private static int currentId = 0;
	
	public ArrayList<Movie> getAll() {
		return db;
	}
	
	public Movie get(int id) {
		for (Movie m : db) {
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}
	
	public void add(Movie movie) {
		movie.setId(++currentId);
		db.add(movie);
	}
	
	public void update(Movie movie) {
		for (Movie m : db) {
			if (m.getId() == movie.getId()) {
				m.setName(movie.getName());
				m.setDescription(movie.getDescription());
			}
		}
	}
	
	public void delete(Movie movie) {
		db.remove(movie);
	}
}
