package com.keyin.SprintCRUD.HttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

public class MainMenu {

  private static JSONArray responseData;

  public static String JSONErrorConverter(JSONException e) {
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    return sw.toString();
  }

  public static void main(String[] args) throws JSONException, IOException, InterruptedException {
    Scanner scan = new Scanner(System.in);

    // Main Menu
    while (true) {
      System.out.println("#".repeat(76));
      System.out.println("#                  Welcome to to The Golf Club - Main Menu                 #");
      System.out.println("#                  Press \"enter/return\" after every entry                  #");
      System.out.println("#".repeat(76));
      System.out.println("|             Press \"M\" for Members, Press \"T\" for Tournaments             |");
      System.out.println("|                            Press \"Q\" to Quit                             |");
      System.out.println("-".repeat(76));
      String categorySelection = scan.nextLine();

      // Members Menu
      if (categorySelection.equalsIgnoreCase("m")) {
        while (true) {
          System.out.println("#".repeat(76));
          System.out.println("#                     The Golf Club - Members Section                      #");
          System.out.println("#                  Press \"enter/return\" after every entry                  #");
          System.out.println("#".repeat(76));
          System.out.println("|                     Press \"N\" to enter a new member                      |");
          System.out.println("|             Press \"F\" to search by first letter of the name              |");
          System.out.println("|                       Press \"S\" to search by name                        |");
          System.out.println("|                      Press \"A\" to list all members                       |");
//          System.out.println("|                     Press \"M\" to search by member #                      |");
          System.out.println("|                     Press \"R\" to return to main menu                     |");
          System.out.println("-".repeat(76));
          String nextSelection = scan.nextLine();
          // return to Main Menu
          if (nextSelection.equalsIgnoreCase("r")) {
            break;
          } else if (nextSelection.equalsIgnoreCase("n")) {
            MemberMenu.newMember();
          } else {
            JSONObject response;
            if (nextSelection.equalsIgnoreCase("s")) {
              System.out.print("Enter a name (first or last) to search by: ");
              String queryString = scan.nextLine();
              try {
                response = new JSONObject(HTTPClient.searchByNameContains("member", queryString));
                responseData = response.getJSONObject("_embedded").getJSONArray("member");
              } catch (JSONException e) {
                System.out.println(JSONErrorConverter(e));
              }
            } else if (nextSelection.equalsIgnoreCase("a")) {
              try {
                response = new JSONObject(HTTPClient.getAllMembers("member"));
                responseData = response.getJSONObject("_embedded").getJSONArray("member");
              } catch (JSONException e) {
                System.out.println(JSONErrorConverter(e));
              }
            } else if (nextSelection.equalsIgnoreCase("M")) {
              MemberMenu.getMemberInfoById(-1);
            } else {
              System.out.println("Invalid selection");
            }
            MemberMenu.getAllMembers(responseData);
          }
        }
      } else if (categorySelection.equalsIgnoreCase("t")) {
        while (true) {
          System.out.println("#".repeat(76));
          System.out.println("#                   The Golf Club - Tournaments Section                    #");
          System.out.println("#                  Press \"enter/return\" after every entry                  #");
          System.out.println("#".repeat(76));
          System.out.println("|                   Press \"N\" to enter a new tournament                    |");
          System.out.println("|                      Press \"L\" to search by location                     |");
          System.out.println("|                    Press \"A\" to list all tournaments                     |");
//          System.out.println("|                       Press \"D\" to search by date                        |");
          System.out.println("|                     Press \"R\" to return to main menu                     |");
          System.out.println("-".repeat(76));
          String nextSelection = scan.nextLine();
          if (nextSelection.equalsIgnoreCase("r")) {
            break;
          } else if (nextSelection.equalsIgnoreCase("n")) {
            TournamentMenu.newTournament();
          } else {
            JSONObject response;
            // Search by Location Name
            if (nextSelection.equalsIgnoreCase("L")) {
              System.out.print("Enter a location to search by: ");
              String queryString = scan.nextLine();
              try {
                response = new JSONObject(HTTPClient.searchByLocationContains("tournament", queryString));
                responseData = response.getJSONObject("_embedded").getJSONArray("tournament");
              } catch (JSONException e) {
                System.out.println(JSONErrorConverter(e));
              }
//            // Search by Date
//            } else if (nextSelection.equalsIgnoreCase("D")) {
//              System.out.print("Enter date of tournament: ");
//              String dateString = scan.nextLine();
//              try {
//                response = new JSONObject(HTTPClient.searchByDate("tournament", dateString));
//                responseData = response.getJSONObject("_embedded").getJSONArray("tournament");
//              } catch (JSONException e) {
//                System.out.println(JSONErrorConverter(e));
//              }
            // List all tournaments
            } else if (nextSelection.equalsIgnoreCase("A")) {
              try {
                response = new JSONObject(HTTPClient.getAllTournaments("tournament"));
                responseData = response.getJSONObject("_embedded").getJSONArray("tournament");
              } catch (JSONException e) {
                System.out.println(JSONErrorConverter(e));
              }
            } else if (nextSelection.equalsIgnoreCase("M")) {
              TournamentMenu.getTournamentInfoById(-1);
            } else {
              System.out.println("Invalid selection");
            }
            TournamentMenu.getAllTournaments(responseData);
          }
        }
      } else if (categorySelection.equalsIgnoreCase("q")){
        break;
      } else {
        System.out.println("Invalid Selection");
      }
    }
    scan.close();
  }
}
