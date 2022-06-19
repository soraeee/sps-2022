package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Datastore init
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("Message");

        // Get value from radio selection in form
        // False (mean) if nothing is selected
        String commentType = request.getParameter("commentType");
        boolean isNice = commentType.equals("nice"); // thanks Diego

        // Get the values entered in the form.
        String textValue = Jsoup.clean(request.getParameter("text-input"), Safelist.none());
        String nameValue = Jsoup.clean(request.getParameter("name-input"), Safelist.none());

        // Print the values so you can see it in the server logs.
        System.out.println("name: " + nameValue + " comment: " + textValue + ", isNice: " + isNice);

        // woohoo more datastore!
        FullEntity messageEntity = Entity.newBuilder(keyFactory.newKey())
                .set("name", nameValue)
                .set("comment", textValue)
                .build();
        datastore.put(messageEntity);

        // Write the value to the response so the user can see it.
        response.getWriter().println("Thanks for submitting your 'feedback'! You submitted: " + textValue);
    }

    // this actually doesn't do anything significant because it just redirects you
    // after submitting. oops?
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;");
        response.getWriter().println("Thanks for submitting your 'feedback'!");
    }
}
