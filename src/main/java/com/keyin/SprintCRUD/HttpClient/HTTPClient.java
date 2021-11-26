package com.keyin.SprintCRUD.HttpClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPClient {
  private static HttpClient client = HttpClient.newHttpClient();

  public static String getAll(String category) {

    HttpRequest getAllRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/" + category)).build();
    try {
      HttpResponse<String> response = client.send(getAllRequest, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        return response.body();
      }

    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      return null;
    }
    return null;
  }

  public static void getById(String category, Integer id) {

    HttpRequest request2 = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/" + category + "/" + id)).build();
    try {
      HttpResponse<String> response = client.send(request2, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode()==200) {
        System.out.println(response.body());
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static String getByFirstLetter(String category, String firstLetter) {

    HttpRequest findByFirstLetterResponse = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/" + category + "/search/findByNameStartsWith?size=5&firstLetter=" + firstLetter)).build();
    try {
      HttpResponse<String> FirstLetterResponse = client.send(findByFirstLetterResponse, HttpResponse.BodyHandlers.ofString());
      if (FirstLetterResponse.statusCode()==200) {
        return FirstLetterResponse.body();
      }
    } catch (IOException | InterruptedException e) {
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      String exceptionAsString = sw.toString();

      return exceptionAsString;
    }
    return null;
  }

  public static String getByNameContains(String category, String searchQuery) {

    HttpRequest findByNameContainingResponse = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/" + category + "/search/findByNameContaining?size=5&searchQuery=" + searchQuery)).build();
    try {
      HttpResponse<String> NameContainingResponse = client.send(findByNameContainingResponse, HttpResponse.BodyHandlers.ofString());
      if (NameContainingResponse.statusCode()==200) {
        return NameContainingResponse.body();
      }
    } catch (IOException | InterruptedException e) {
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      String exceptionAsString = sw.toString();

      return exceptionAsString;
    }
    return null;
  }
}
