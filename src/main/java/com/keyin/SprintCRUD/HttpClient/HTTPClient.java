package com.keyin.SprintCRUD.HttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPClient {
  public static void main(String[]args){

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/bio/all/")).build();
    try {
      HttpResponse<String> response = client.send(request1, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode()==200) {
        System.out.println(response.body());
      }

    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

    HttpRequest request2 = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/bio/?wid=2")).build();
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
