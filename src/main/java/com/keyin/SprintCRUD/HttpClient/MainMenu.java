package com.keyin.SprintCRUD.HttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

public class MainMenu {

  final static String memberHeader = " ".repeat(100) + "|       Membership Details       |\n" +
      "  #  | Name:" + " ".repeat(19) + "| Address:" + " ".repeat(16) +  "| Phone:" + " ".repeat(9) +
      "| Email:" + " ".repeat(18) + "|  Type:" + "  |   Start:" + "   | Length: |" + " ".repeat(4) + "\n" +
      "-".repeat(134);
  private static JSONArray responseData;
  private static int n;

  public static String JSONErrorConverter(JSONException e) {
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    return sw.toString();
  }

  public static void main(String[] args) throws JSONException {
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
          System.out.println("|                     Press \"#\" to search by member #                      |");
          System.out.println("|                     Press \"R\" to return to main menu                     |");
          System.out.println("-".repeat(76));
          String nextSelection = scan.nextLine();
          // return to Main Menu
          if (nextSelection.equalsIgnoreCase("r")) {
            break;
          } else {
            JSONObject response;
            if (nextSelection.equalsIgnoreCase("f")) {
              // Search By First Name
              System.out.print("Enter Letter to Search By: ");
              String firstLetter = scan.nextLine();
              try {
                response = new JSONObject(HTTPClient.searchByFirstLetter("member", firstLetter));
                responseData = response.getJSONObject("_embedded").getJSONArray("member");
                n = responseData.length();
              } catch (JSONException e) {
                System.out.println(JSONErrorConverter(e));
              }
            } else if (nextSelection.equalsIgnoreCase("s")) {
              System.out.print("Enter a name (first or last) to search by: ");
              String queryString = scan.nextLine();
              try {
                response = new JSONObject(HTTPClient.searchByNameContains("member", queryString));
                responseData = response.getJSONObject("_embedded").getJSONArray("member");
                n = responseData.length();
              } catch (JSONException e) {
                System.out.println(JSONErrorConverter(e));
              }
            } else if (nextSelection.equalsIgnoreCase("a")) {
              try {
                response = new JSONObject(HTTPClient.getAll("member"));
                responseData = response.getJSONObject("_embedded").getJSONArray("member");
                n = responseData.length();
              } catch (JSONException e) {
                System.out.println(JSONErrorConverter(e));
              }
            } else if (nextSelection.equalsIgnoreCase("#")) {
              MemberMenu.getMemberInfoById();
            } else {
              System.out.println("Invalid selection");
            }
            if (n < 1) {
              System.out.println("No Member(s) Found");
            } else {
              System.out.println(memberHeader);
              for (int i = 0; i < n; i++) {
                final JSONObject member = responseData.getJSONObject(i);
                if (member.getInt("id") < 9) {
                  System.out.print(" ");
                }
                System.out.print(" " + member.getInt("id") + " ");
                System.out.print(" | " + member.getString("name") + " ".repeat(23 - member.getString("name").length()));
                System.out.print(" | " + member.getString("address").substring(0, 20) + "...");
                System.out.print(" | (" + member.getString("phone").substring(0, 3) + ") " + member.getString("phone").substring(3, 6) + "-" + member.getString("phone").substring(6, 10));
                System.out.print(" | " + member.getString("email") + " ".repeat(23 - member.getString("email").length()));
                System.out.print(" | " + member.getString("membershipType") + " ".repeat(7 - member.getString("membershipType").length()));
                System.out.print(" | " + member.getString("membershipStartDate") + " | ");
                if (member.getInt("membershipLength") < 10) {System.out.print(" ");}
                System.out.println(member.getInt("membershipLength") + " mon. |");
              }
              System.out.println("-".repeat(134));
            }
            while (true) {
              int numberCheck;
              System.out.print("To view or edit member info, enter # beside name and press enter, or press enter to return: ");
              String memberNumber = scan.nextLine();
              System.out.println("-".repeat(134));
              try {
                numberCheck = Integer.parseInt(memberNumber);
              } catch (NumberFormatException nfe) {
                break;
              }
              MemberMenu.getMemberInfoByURI(responseData.getJSONObject(Integer.parseInt(memberNumber)-1).getJSONObject("_links").getJSONObject("self").getString("href"));
            }
          }
        }
      } else if (categorySelection.equalsIgnoreCase("t")) {
        while (true) {
          System.out.println("Tournaments Section");
          System.out.println("Press \"R\" to return to previous menu");
          String nextSelection = scan.nextLine();
          if (nextSelection.equalsIgnoreCase("r")) {
            break;
          } else {
            System.out.println("Invalid selection");
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
