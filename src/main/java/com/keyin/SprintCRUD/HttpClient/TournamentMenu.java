package com.keyin.SprintCRUD.HttpClient;

import com.keyin.SprintCRUD.Utilities.Colours;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

import static com.keyin.SprintCRUD.HttpClient.MainMenu.JSONErrorConverter;
import static com.keyin.SprintCRUD.HttpClient.MainMenu.RankSuffix;

public class TournamentMenu {
  // header when list of members is selected
  final static String tournamentHeader = " ".repeat(22) + "  #  | Location:" + " ".repeat(18) + "|   Start:   " +
      "|    End:    " + "| Entry Fee: |  Prize Total: |\n" + " ".repeat(22) + "-".repeat(90);
  private static JSONObject response;

  private static final Scanner scan = new Scanner(System.in);

  // ************************************************************************************************************
  // output when a single tournament details is requested
  // ************************************************************************************************************
  public static void singleTournamentDisplay(JSONObject res) throws JSONException {
    try {
      System.out.print(" ".repeat(22) + Colours.YEL_BR + "Location: " + Colours.RESET + res.getString("location"));
      System.out.print(" ".repeat(33 - res.getString("location").length()));
      System.out.print(Colours.YEL_BR + "Start Date: " + Colours.RESET + res.getString("startDate"));
      System.out.println("     " + Colours.YEL_BR + "End Date: " + Colours.RESET + res.getString("endDate"));
      System.out.print(" ".repeat(22) + Colours.YEL_BR + "Entry Fee: $" + Colours.RESET + res.getString("entry_fee"));
      System.out.println(" ".repeat(31 - res.getString("entry_fee").length()) + Colours.YEL_BR
          + "Prize Total: $" + Colours.RESET + res.getString("total_cash_prize"));
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
  }

  // ************************************************************************************************************
  // Retrieve ranking results
  // ************************************************************************************************************
  public static void ListOfResultsByTournament(JSONObject res) {
    try {
      JSONArray resultsResponseByTournament = new JSONArray(HTTPClient.getResultsByTournamentId("results", res.getInt("id")));
      int n = resultsResponseByTournament.length();
      if (n < 1) {
        System.out.println(" ".repeat(50) + "*** No Ranking Results Found ***");
      } else {
        System.out.print(" ".repeat(30) + "Ranking Results for Tournament at \"" + res.getString("location")
            + "\" [" + res.getString("startDate"));
        if (!res.getString("startDate").equals(res.getString("endDate"))) {
          System.out.print("-" + res.getString("endDate"));
        }
        System.out.println("]");
        System.out.println(" ".repeat(37) + "   #  | Member:                        | Result: |  Prize:  |");
        System.out.println(" ".repeat(38) + "-".repeat(60));
        for (int i = 0; i < n; i++) {
          final JSONObject results = resultsResponseByTournament.getJSONObject(i);
          System.out.print(" ".repeat(39));
          if (results.getInt("id") < 100) {
            System.out.print(" ");
          } else if (results.getInt("id") < 10) {
            System.out.print("  ");
          }
          System.out.print(results.getJSONObject("member").getString("id") + " | "
              + results.getJSONObject("member").getString("name")
              + " ".repeat(30 - results.getJSONObject("member").getString("name").length()) + " | "
          );
          if (results.getInt("result") < 100) {
            System.out.print(" ");
          } else if (results.getInt("result") < 10) {
            System.out.print("  ");
          }
          System.out.print(" " + results.getInt("result") + RankSuffix(results.getInt("result")) + "   |  ");
          if (results.getInt("result") < 1000) {
            System.out.print(" ");
          } else if (results.getInt("result") < 100) {
            System.out.print("  ");
          }
          System.out.println("$" + results.getInt("cashPrize") + "   |");
        }
        System.out.println(" ".repeat(22) + "-".repeat(90));
      }
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
  }

  // ************************************************************************************************************
  // output to confirm deletion of tournament
  // ************************************************************************************************************
  public static void deleteTournament() {
    try {
      System.out.print("Are you sure you want to delete tournament " + response.getString("name") +
          " [" + response.getString("id") + "]? (Type \"Yes\" to Confirm): ");
      String deleteTournament = scan.nextLine();
      if (deleteTournament.equalsIgnoreCase("yes")) {
        String deleteResponse = HTTPClient.deleteById("tournament", response.getInt("id"));
        System.out.println(deleteResponse);
      } else {
        System.out.println("Tournament NOT deleted");
      }
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
    System.out.println(" ".repeat(22) + "-".repeat(90));
  }

  // ************************************************************************************************************
  // output to change/update tournament information
  // ************************************************************************************************************
  public static void editTournament() throws JSONException {
    System.out.println(" ".repeat(48) + "Leave field blank and press enter to keep unchanged");

    System.out.print(" ".repeat(48) + "Location [" + response.getString("location") + "]: ");
    String updatedLocation = scan.nextLine();
    if (updatedLocation.equals("")) updatedLocation = response.getString("location");

    System.out.print(" ".repeat(48) + "Start Date [" + response.getString("startDate") + "]: ");
    String updatedStartDate = scan.nextLine();
    if (updatedStartDate.equals("")) updatedStartDate = response.getString("startDate");

    System.out.print(" ".repeat(48) + "End Date [" + response.getString("endDate") + "]: ");
    String updatedEndDate = scan.nextLine();
    if (updatedEndDate.equals("")) updatedEndDate = response.getString("endDate");

    System.out.print(" ".repeat(48) + "Entry Fee [" + response.getString("entry_fee") + "]: ");
    String updatedEntryFee = scan.nextLine();
    if (updatedEntryFee.equals("")) updatedEntryFee = response.getString("entry_fee");

    System.out.print(" ".repeat(48) + "Prize Total [" + response.getString("total_cash_prize") + "]: ");
    String updatedTotalCashPrize = scan.nextLine();
    if (updatedTotalCashPrize.equals("")) updatedTotalCashPrize = response.getString("total_cash_prize");

    String updateResponse = HTTPClient.updateTournamentById(response.getInt("id"),updatedLocation, updatedStartDate,
        updatedEndDate, updatedEntryFee, updatedTotalCashPrize);
    System.out.println(updateResponse);
  }

  // ************************************************************************************************************
  // output to add new tournament information
  // ************************************************************************************************************
  public static void newTournament() {
    System.out.println(" ".repeat(54) + "Please complete all fields");

    System.out.print(" ".repeat(50) + "Location: ");
    String newLocation = scan.nextLine();

    System.out.print(" ".repeat(50) + "Start Date [YYYY-MM-DD]: ");
    String newStartDate = scan.nextLine();

    System.out.print(" ".repeat(50) + "End Date [YYYY-MM-DD]: ");
    String newEndDate = scan.nextLine();

    System.out.print(" ".repeat(50) + "Entry Fee: $");
    String newEntryFee = scan.nextLine();

    System.out.print(" ".repeat(50) + "Prize Total: $");
    String newTotalCashPrize = scan.nextLine();

    String insertResponse = HTTPClient.insertNewTournament(newLocation, newStartDate, newEntryFee, newEndDate, newTotalCashPrize);
    System.out.println(insertResponse);

  }

  // ************************************************************************************************************
  // output of menu to select options to edit or delete tournament
  // ************************************************************************************************************
  public static void menuSelection(JSONObject res) throws JSONException {
    while (true) {
      System.out.println(" ".repeat(22) + "-".repeat(90));
      System.out.print(" ".repeat(43) + "Press " + Colours.RED_BR + "\"T\"" + Colours.RESET + " to list the members in this tournament\n");
      System.out.print(" ".repeat(34) + "Press " + Colours.RED_BR + "\"E\"" + Colours.RESET + " to edit, "
          + Colours.RED_BR + "\"D\"" + Colours.RESET + " to delete, or " + Colours.RED_BR + "\"R\""
          + Colours.RESET + " to return to previous menu ");
      String editCheck = scan.nextLine();
      System.out.println(" ".repeat(22) + "-".repeat(90));
      if (editCheck.equalsIgnoreCase("E")) {
        editTournament();
      } else if (editCheck.equalsIgnoreCase("D")) {
        deleteTournament();
        break;
      } else if (editCheck.equalsIgnoreCase("R")) {
        break;
      } else if (editCheck.equalsIgnoreCase("T")) {
        ListOfResultsByTournament(res);
        break;
      } else {
        System.out.println(" ".repeat(22) + "Invalid Selection");
      }
    }
  }

  // ************************************************************************************************************
  // retrieve tournament info if ID# is inputted
  // ************************************************************************************************************
  public static void getTournamentInfoById(Integer memberId) {
    String tournamentNumber;
    if (memberId < 0) {
      System.out.print("Enter tournament #: ");
      tournamentNumber = scan.nextLine();
      System.out.println(" ".repeat(22) + "-".repeat(90));
    } else {
      tournamentNumber = memberId.toString();
    }
    try {
      JSONObject tournamentRes = new JSONObject(HTTPClient.searchById("tournament", tournamentNumber));
      int n = tournamentRes.length();
      if (n > 0) {
        singleTournamentDisplay(tournamentRes);
        menuSelection(tournamentRes);
      } else {
        System.out.println("No Tournament with that number found");
      }
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
  }

  // ************************************************************************************************************
  // output of tournaments list
  // ************************************************************************************************************
  public static void getAllTournaments(JSONArray responseData) throws JSONException {
    int n = responseData.length();
    if (n < 1) {
      System.out.println("No Tournament(s) Found");
    } else {
    System.out.println(tournamentHeader);
    for (int i = 0; i < n; i++) {
      final JSONObject tournament = responseData.getJSONObject(i);
      if (tournament.getInt("id") < 10) {
        System.out.print(" ");
      }
      System.out.print(" ".repeat(22) + " " + Colours.BLU_BR + tournament.getInt("id") + Colours.RESET + " ");
      System.out.print(" | " + tournament.getString("location") + " ".repeat(26 - tournament.getString("location").length()));
      System.out.print(" | " + tournament.getString("startDate"));
      System.out.print(" | " + tournament.getString("endDate") + " | ");
      if (tournament.getInt("entry_fee") < 10) {
        System.out.print("  ");
      } else if (tournament.getInt("entry_fee") < 100) {
        System.out.print(" ");
      }
      System.out.print("   $" + tournament.getString("entry_fee") + "    | ");
      if (tournament.getInt("total_cash_prize") < 10) {
        System.out.print("   ");
      } else if (tournament.getInt("total_cash_prize") < 100) {
        System.out.print("  ");
      } else if (tournament.getInt("total_cash_prize") < 1000) {
        System.out.print(" ");
      }
      System.out.println("    $" + tournament.getString("total_cash_prize") + "     |");
    }
    System.out.println(" ".repeat(22) + "-".repeat(90));
    while (true) {
      System.out.print(" ".repeat(20) + "To view or edit tournament info, " + Colours.RED_BOL
          + "enter # beside location" + Colours.RESET + " and press enter, or " + Colours.RED_BOL
          + "\"R\"" + Colours.RESET + " and enter to return: ");
      String tournamentNumber = scan.nextLine();
      System.out.println(" ".repeat(22) + "-".repeat(90));
      if (tournamentNumber.equalsIgnoreCase("r")) {
        break;
      } else {
        getTournamentInfoById(Integer.parseInt(tournamentNumber));
      }
    }
    }

  }
}
