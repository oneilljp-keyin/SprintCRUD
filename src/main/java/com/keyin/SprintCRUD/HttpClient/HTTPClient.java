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
  private static String sendRequest(HttpRequest request) {
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

  // *****************************************************************
  //                     HTTP Client Requests
  // *****************************************************************

  private static String httpGetRequest(String httpURL) {
    HttpRequest InfoRequest = HttpRequest.newBuilder().uri(URI.create(httpURL)).build();
    return sendRequest(InfoRequest);
  }

  private static String httpPostRequest(String httpURL, HttpRequest.BodyPublisher jsonPayload) {
    HttpRequest insertRequest = HttpRequest.newBuilder().uri(URI.create(httpURL)).method("POST", jsonPayload)
        .header("Content-Type", "application/json").build();
    return sendRequest(insertRequest);
  }

  private static String httpDeleteRequest(String httpURL) {
    HttpRequest memberDeleteRequest = HttpRequest.newBuilder().DELETE().uri(URI.create(httpURL)).build();
    return sendRequest(memberDeleteRequest);
  }

  private static String httpPutRequest(String httpURL, HttpRequest.BodyPublisher jsonPayload) {
    HttpRequest memberDeleteRequest = HttpRequest.newBuilder().method("PUT", jsonPayload)
        .header("Content-Type", "application/json").uri(URI.create(httpURL)).build();
    return sendRequest(memberDeleteRequest);
  }

  // *****************************************************************
  //                      Shared URI Constructors
  // *****************************************************************

  public static String searchById(String category, String id) {
    return httpGetRequest("http://localhost:8080/" + category + "/" + id);
  }

  public static String deleteById(String category, String id) {
    return httpDeleteRequest("http://localhost:8080/" + category + "/delete?id=" + id);
  }

  // *****************************************************************
  //                      Member URI Constructors
  // *****************************************************************

  public static String getAllMembers(String category) {
    return httpGetRequest("http://localhost:8080/" + category + "?sort=name");
  }

  public static String searchByFirstLetter(String category, String firstLetter) {
    return httpGetRequest("http://localhost:8080/" + category + "/search/findByNameStartsWith?firstLetter=" + firstLetter);
  }

  public static String searchByNameContains(String category, String searchQuery) {
    return httpGetRequest("http://localhost:8080/" + category + "/search/findByNameContaining?searchQuery=" + searchQuery);
  }

  public static String insertNewMember(String name, String address, String email,
                                      String phone, String startDate, Integer lengthInMonths,
                                      String membershipType, String category) {

    String jsonPayloadString = "{\"name\": \"" + name + "\",\"address\": \"" + address +
        "\",\"email\": \"" + email + "\",\"phone\": \"" + phone + "\",\"membershipStartDate\": \"" + startDate +
        "\",\"membershipLength\": \"" + lengthInMonths + "\",\"membershipType\": \"" + membershipType + "\"}";
    HttpRequest.BodyPublisher jsonPayload = HttpRequest.BodyPublishers.ofString(jsonPayloadString);

      return httpPostRequest("http://localhost:8080/" + category + "/add", jsonPayload);
  }

  public static String updateMemberById(Integer id, String name, String address, String email,
                                        String phone, String startDate, Integer lengthInMonths,
                                        String membershipType, String category) {
    String jsonPayloadString = "{\"name\": \"" + name + "\",\"address\": \"" + address +
        "\",\"email\": \"" + email + "\",\"phone\": \"" + phone + "\",\"membershipStartDate\": \"" + startDate +
        "\",\"membershipLength\": \"" + lengthInMonths + "\",\"membershipType\": \"" + membershipType + "\"}";
    HttpRequest.BodyPublisher jsonPayload = HttpRequest.BodyPublishers.ofString(jsonPayloadString);

    return httpPutRequest("http://localhost:8080/" + category +"/update/" + id, jsonPayload);
  }

  // *****************************************************************
  //                    Tournament URI Constructors
  // *****************************************************************

  public static String getAllTournaments(String category) {
    return httpGetRequest("http://localhost:8080/" + category + "?sort=startDate");
  }

  public static String searchByLocationContains(String category, String searchQuery) {
    return httpGetRequest("http://localhost:8080/" + category + "/search/findByLocationContaining?searchQuery=" + searchQuery);
  }

  public static String searchByDate(String category, String searchDate) {
    return httpGetRequest("http://localhost:8080/" + category + "/search/findByStart_dateEquals?searchDate=" + searchDate);
  }

  public static String insertNewTournament(String location, String start_date, String end_date,
                                           String entry_fee, String total_cash_prize) {

    String jsonPayloadString = "{\"location\": \"" + location + "\",\"start_date\": \"" + start_date +
        "\",\"end_date\": \"" + end_date + "\",\"entry_fee\": \"" + entry_fee +
        "\",\"total_cash_prize\": \"" + total_cash_prize + "\"}";
    HttpRequest.BodyPublisher jsonPayload = HttpRequest.BodyPublishers.ofString(jsonPayloadString);

    return httpPostRequest("http://localhost:8080/tournament/add", jsonPayload);
  }

  public static String updateTournamentById(Integer id, String location, String start_date, String end_date,
                                            String entry_fee, String total_cash_prize) {
    String jsonPayloadString = "{\"location\": \"" + location + "\",\"start_date\": \"" + start_date +
        "\",\"end_date\": \"" + end_date + "\",\"entry_fee\": \"" + entry_fee +
        "\",\"total_cash_prize\": \"" + total_cash_prize + "\"}";
    HttpRequest.BodyPublisher jsonPayload = HttpRequest.BodyPublishers.ofString(jsonPayloadString);

    return httpPutRequest("http://localhost:8080/tournament/update/" + id, jsonPayload);
  }

  // *****************************************************************
  //                      Result URI Constructors
  // *****************************************************************

  public static String getResultsByMemberId(int memberId) {
    return null;
  }

  public static String getResultsByTournamentId(int tournamentId) {
    return null;
  }

  public static String insertResult(int tournamentId, int member_id, int result) {
    return null;
  }

}

