package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get value from radio selection in form
    // False (mean) if nothing is selected
    String commentType = request.getParameter("commentType");
    boolean isNice = commentType.equals("nice");

    // Get the value entered in the form.
    String textValue = request.getParameter("text-input");

    // Print the value so you can see it in the server logs.
    System.out.println("comment: " + textValue + ", isNice: " + isNice);

    // Write the value to the response so the user can see it.
    response.getWriter().println("Thanks for submitting your 'feedback'! You submitted: " + textValue);
  }
}