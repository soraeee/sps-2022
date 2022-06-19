package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet for displaying comments on the portfolio.
@WebServlet("/comment-display")
public class CommentDisplayServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		Query<Entity> query = Query.newEntityQueryBuilder().setKind("Message").build();
		QueryResults<Entity> results = datastore.run(query);

		// Feed datastore query into some garbage json... idk what i'm doing LOL
		ArrayList<String> commentList = new ArrayList<>();
		while (results.hasNext()) {
			Entity entity = results.next();
			
			long id = entity.getKey().getId();
			String name = entity.getString("name");
			String comment = entity.getString("comment");
			System.out.println("################## " + comment);

			String jsonString = "{\"id\":" + id + ", \"name\":\"" + name + "\", \"comment\":\"" + comment + "\"}";
			commentList.add(jsonString);
		}

		// Convert to gson???
		Gson gson = new Gson();

		response.setContentType("application/json;");
		response.getWriter().println(gson.toJson(commentList));
	}
}
