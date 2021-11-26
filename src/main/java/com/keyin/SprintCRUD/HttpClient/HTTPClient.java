package com.keyin.SprintCRUD.HttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPClient {
  private static HttpClient client = HttpClient.newHttpClient();

  public static void getAll(String category){

    HttpRequest getAllRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/" + category)).build();
    try {
      HttpResponse<String> response = client.send(getAllRequest, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode()==200) {
        System.out.println(response.body());
      }

    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

    HttpRequest request2 = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/tournament")).build();
    try {
      HttpResponse<String> response = client.send(request2, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode()==200) {
        System.out.println(response.body());
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
