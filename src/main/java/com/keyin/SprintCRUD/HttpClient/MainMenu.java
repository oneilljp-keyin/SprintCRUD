package com.keyin.SprintCRUD.HttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class MainMenu {
  public static void main(String[] args) throws JSONException {
    Scanner scan = new Scanner(System.in);

    // Main Menu
    while (true) {
      System.out.println("#".repeat(76));
      System.out.println("#                  Welcome to to The Golf Club - Main Menu                 #");
      System.out.println("#                  Press \"enter/return\" after every entry                  #");
      System.out.println("#".repeat(76));
      System.out.println("|             Press \"M\" for Members, Press \"T\" for Tournaments             |");
      System.out.println("|                            Press \"Q\" for Quit                            |");
      System.out.println("-".repeat(76));
      String categorySelection = scan.nextLine();

      // Members Menu
      if (categorySelection.equalsIgnoreCase("m")) {
        while (true) {
          System.out.println("#".repeat(76));
          System.out.println("#                     The Golf Club - Members Section                      #");
          System.out.println("#                  Press \"enter/return\" after every entry                  #");
          System.out.println("#".repeat(76));
          System.out.println("|             Press \"F\" to search by first letter of the name              |");
          System.out.println("|                       Press \"N\" to search by name                        |");
          System.out.println("|                      Press \"A\" to list all members                       |");
          System.out.println("|                     Press \"#\" to search by member #                      |");
          System.out.println("|                  Press \"R\" to return to previous menu                    |");
          System.out.println("-".repeat(76));
          String nextSelection = scan.nextLine();
          // return to Main Menu
          if (nextSelection.equalsIgnoreCase("r")) {
            break;
          } else if (nextSelection.equalsIgnoreCase("F")) {
            // Search By First Name
            System.out.print("Enter Letter to Search By: ");
            String firstLetter = scan.nextLine();
            final JSONObject response = new JSONObject(HTTPClient.getByFirstLetter("member", firstLetter));
            final JSONArray responseData = response.getJSONObject("_embedded").getJSONArray("member");
            final int n = responseData.length();
            if (n < 1) {System.out.println("No Members Found"); continue;}
            System.out.println(" ".repeat(100) + "|       Membership Details       |");
            System.out.print("  #  | Name:" + " ".repeat(19) + "| Address:" + " ".repeat(16));
            System.out.print("| Phone:" + " ".repeat(9) + "| Email:" + " ".repeat(18));
            System.out.println("|  Type:" + "  |   Start:" + "   | Length: |" + " ".repeat(4));
            System.out.println("-".repeat(127));
            for (int i = 0; i < n; i++) {
              final JSONObject member = responseData.getJSONObject(i);
              System.out.print(" " + i + " ");
              System.out.print(" | " + member.getString("name") + " ".repeat(23 - member.getString("name").length()));
              System.out.print(" | " + member.getString("address").substring(0,20) + "...");
              System.out.print(" | (" + member.getString("phone").substring(0,3) + ") " + member.getString("phone").substring(3,6) + "-" + member.getString("phone").substring(6,10));
              System.out.print(" | " + member.getString("email") + " ".repeat(23 - member.getString("email").length()));
              System.out.print(" | " + member.getString("membershipType") + " ".repeat(7 - member.getString("membershipType").length()));
              System.out.print(" | " + member.getString("membershipStartDate"));
              System.out.println(" |   " + member.getInt("membershipLength") + "    |");
            }
            System.out.print("Press Enter To Continue");
            String EnterContinue = scan.nextLine();
            continue;
          } else {
            System.out.println("Invalid selection");
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
        System.out.println("Invalid Selection, Try Again");
      }
    }
    scan.close();
  }
}
