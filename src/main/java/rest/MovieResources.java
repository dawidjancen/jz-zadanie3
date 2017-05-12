package rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Comment;
import domain.Movie;
import domain.services.CommentService;
import domain.services.MovieService;

@Path("/movie")
public class MovieResources {
	private MovieService db = new MovieService();
	private CommentService commentDb = new CommentService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Movie> getAll() {
		return db.getAll();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Movie movie) {
		db.add(movie);

		return Response.ok(movie.getId()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		Movie result = db.get(id);
		
		if (result == null) {
			return Response.status(404).build();
		}
		
		return Response.ok(result).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Movie movie) {
		Movie result = db.get(id);
		
		if (result == null) {
			return Response.status(404).build();
		}

		movie.setId(id);
		db.update(movie);

		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		Movie result = db.get(id);
		
		if (result == null) {
			return Response.status(404).build();
		}
		
		db.delete(result);
		
		return Response.ok().build();
	}

	@GET
	@Path("/{movieId}/comment")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getCommentList(@PathParam("movieId") int movieId) {
		Movie result = db.get(movieId);
		
		if (result == null) {
			return null;
		}
		
		if (result.getCommentList() == null) {
			result.setCommentList(new ArrayList<Comment>());
		}
		
		return result.getCommentList();
	}

	@POST
	@Path("/{movieId}/comment")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("movieId") int movieId, Comment comment) {
		Movie result = db.get(movieId);
		
		if (result == null) {
			return Response.status(404).build();
		}
		
		if (result.getCommentList() == null) {
			result.setCommentList(new ArrayList<Comment>());
		}
		
		commentDb.add(comment);

		comment.setId(commentDb.getCurrentId());
		result.getCommentList().add(comment);
		
		return Response.ok().build();
	}

	@DELETE
	@Path("/{movieId}/comment/{commentId}")
	public Response deleteComment(@PathParam("movieId") int movieId, @PathParam("commentId") int commentId) {
		Movie m = db.get(movieId);
		
		if (m == null) {
			return Response.status(404).build();
		}
		
		ArrayList<Comment> comments = m.getCommentList();
		
		if (comments == null) {
			return Response.status(404).build();
		}
		
		Comment found = new Comment();
		
		for (Comment c : comments) {
			if (c.getId() == commentId) {
				found = c;
			}
		}
		
		comments.remove(found);
		
		Comment comment = commentDb.get(commentId);
		commentDb.delete(comment);
		
		return Response.ok().build();
	}

	@GET
	@Path("/{movieId}/score")
	@Produces(MediaType.APPLICATION_JSON)
	public String getScoreList(@PathParam("movieId") int movieId) {
		Movie result = db.get(movieId);

		if (result == null) {
			return null;
		}

		if (result.getScoreList() == null) {
			result.setScoreList(new ArrayList<Integer>());
		}
		
		ArrayList<Integer> scoreList = result.getScoreList();
		
		int[] array = new int[scoreList.size()];

	    Iterator<Integer> iterator = scoreList.iterator();
	    for (int i = 0; i < array.length; i++)
	    {
	        array[i] = iterator.next().intValue();
	    }
	    return Arrays.toString(array);
	}

	@GET
	@Path("/{movieId}/averagescore")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAverageScore(@PathParam("movieId") int movieId) {
		Movie result = db.get(movieId);
		
		if (result == null) {
			return null;
		}
		
		if (result.getScoreList() == null) {
			result.setScoreList(new ArrayList<Integer>());
		}
		
		return String.valueOf(result.getAverageScore());
	}

	@POST
	@Path("/{movieId}/score")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addScore(@PathParam("movieId") int movieId, int score) {
		Movie result = db.get(movieId);
		
		if (result == null) {
			return Response.status(404).build();
		}
		
		if (result.getScoreList() == null) {
			result.setScoreList(new ArrayList<Integer>());
		}
		
		result.getScoreList().add(score);
		
		return Response.ok().build();
	}
}
