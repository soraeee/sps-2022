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
import java.util.Map;
import java.util.TreeMap;
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
		Query<Entity> query = Query.newEntityQueryBuilder().setKind("Message").setOrderBy(OrderBy.desc("name")).build();
		QueryResults<Entity> results = datastore.run(query);

		// Feed datastore query into some garbage json... idk what i'm doing LOL
		Map<String, String> commentList = new TreeMap<>();
		while (results.hasNext()) {
			Entity entity = results.next();
			
			//long id = entity.getKey().getId();
			String comment = entity.getString("comment");
			String name = entity.getString("name");
			commentList.put(name, comment);
		}

		// Convert to gson???
		Gson gson = new Gson();

		response.setContentType("application/json;");
		response.getWriter().println(gson.toJson(commentList));
	}
}
