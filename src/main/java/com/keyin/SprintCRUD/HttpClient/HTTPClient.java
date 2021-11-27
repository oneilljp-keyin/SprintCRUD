package com.keyin.SprintCRUD.HttpClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPClient {
  final static HttpClient client = HttpClient.newHttpClient();

  private static String httpMemberRequest(String httpURL) {
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(httpURL)).build();
    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        return response.body();
      }
    } catch (IOException | InterruptedException e) {
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      return sw.toString();
    }
    return null;
  }

  public static String getAll(String category) {
    return httpMemberRequest("http://localhost:8080/" + category + "?sort=name");
  }

  public static String getByURI(String memberURI) {
    return httpMemberRequest(memberURI);
  }

  public static String searchById(String category, String memberId) {
    return httpMemberRequest("http://localhost:8080/" + category + "/" + memberId);
  }

  public static String searchByFirstLetter(String category, String firstLetter) {
    return httpMemberRequest("http://localhost:8080/" + category + "/search/findByNameStartsWith?firstLetter=" + firstLetter);
  }

  public static String searchByNameContains(String category, String searchQuery) {
    return httpMemberRequest("http://localhost:8080/" + category + "/search/findByNameContaining?searchQuery=" + searchQuery);
  }
}
