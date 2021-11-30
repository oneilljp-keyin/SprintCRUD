package com.keyin.SprintCRUD.HttpClient;

import com.keyin.SprintCRUD.Utilities.Colours;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

import static com.keyin.SprintCRUD.HttpClient.MainMenu.JSONErrorConverter;

public class TournamentMenu {
  // header when list of members is selected
  final static String tournamentHeader = " ".repeat(22) + "  #  | Location:" + " ".repeat(18) + "|   Start:   " +
      "|    End:    " + "| Entry Fee: |  Prize Total: |\n" + " ".repeat(22) + "-".repeat(90);
  private static JSONObject response;

  private static final Scanner scan = new Scanner(System.in);

  // output when a single tournament details is requested
  public static void singleTournamentDisplay() throws JSONException {
    try {
      System.out.print(" ".repeat(22) + Colours.YEL_BR + "Location: " + Colours.RESET + response.getString("location"));
      System.out.print("    " + Colours.YEL_BR + "Start Date: " + Colours.RESET + response.getString("start_date"));
      System.out.println("     " + Colours.YEL_BR + "End Date: " + Colours.RESET + response.getString("end_date"));
      System.out.print(" ".repeat(22) + Colours.YEL_BR + "Entry Fee: $" + Colours.RESET + response.getString("entry_fee"));
      System.out.println(" ".repeat(22) + Colours.YEL_BR + "Prize Total: $" + Colours.RESET + response.getString("total_cash_prize"));
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
  }

  // output to confirm deletion of tournament
  public static void deleteTournament() {
    try {
      System.out.print("Are you sure you want to delete tournament " + response.getString("name") +
          " [" + response.getString("id") + "]? (Type \"Yes\" to Confirm): ");
      String deleteTournament = scan.nextLine();
      if (deleteTournament.equalsIgnoreCase("yes")) {
        String deleteResponse = HTTPClient.deleteById("tournament", response.getString("id"));
        System.out.println(deleteResponse);
      } else {
        System.out.println("Tournament NOT deleted");
      }
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
    System.out.println("-".repeat(90));
  }

  // output to change/update tournament information
  public static void editTournament() throws JSONException {
    System.out.println("Press enter to keep the field the same");

    System.out.print("Location [" + response.getString("location") + "]: ");
    String updatedLocation = scan.nextLine();
    if (updatedLocation.equals("")) updatedLocation = response.getString("location");

    System.out.print("Start Date [" + response.getString("start_date") + "]: ");
    String updatedStartDate = scan.nextLine();
    if (updatedStartDate.equals("")) updatedStartDate = response.getString("start_date");

    System.out.print("End Date [" + response.getString("end_date") + "]: ");
    String updatedEndDate = scan.nextLine();
    if (updatedEndDate.equals("")) updatedEndDate = response.getString("end_date");

    System.out.print("Entry Fee [" + response.getString("entry_fee") + "]: ");
    String updatedEntryFee = scan.nextLine();
    if (updatedEntryFee.equals("")) updatedEntryFee = response.getString("entry_fee");

    System.out.print("Prize Total [" + response.getString("total_cash_prize") + "]: ");
    String updatedTotalCashPrize = scan.nextLine();
    if (updatedTotalCashPrize.equals("")) updatedTotalCashPrize = response.getString("total_cash_prize");

    String updateResponse = HTTPClient.updateTournamentById(response.getInt("id"),updatedLocation, updatedStartDate,
        updatedEndDate, updatedEntryFee, updatedTotalCashPrize);
    System.out.println(updateResponse);
  }

  // output to add new tournament information
  public static void newTournament() {
    System.out.println("Press complete all fields");

    System.out.print("Location: ");
    String newLocation = scan.nextLine();

    System.out.print("Start Date: ");
    String newStartDate = scan.nextLine();

    System.out.print("End Date: ");
    String newEndDate = scan.nextLine();

    System.out.print("Entry Fee: ");
    String newEntryFee = scan.nextLine();

    System.out.print("Prize Total: ");
    String newTotalCashPrize = scan.nextLine();

    String insertResponse = HTTPClient.insertNewTournament(newLocation, newStartDate, newEntryFee, newEndDate, newTotalCashPrize);
    System.out.println(insertResponse);

  }

  // output of menu to select options to edit or delete tournament
  public static void menuSelection() throws JSONException {
    while (true) {
      System.out.println(" ".repeat(22) + "-".repeat(90));
      System.out.print(" ".repeat(22) + "Press " + Colours.RED_BR + "\"E\"" + Colours.RESET + " to edit, "
          + Colours.RED_BR + "\"D\"" + Colours.RESET + " to delete, or " + Colours.RED_BR + "\"R\""
          + Colours.RESET + " to return to previous menu ");
      String editCheck = scan.nextLine();
      System.out.println(" ".repeat(22) + "-".repeat(90));
      if (editCheck.equalsIgnoreCase("e")) {
        editTournament();
      } else if (editCheck.equalsIgnoreCase("d")) {
        deleteTournament();
        break;
      } else if (editCheck.equalsIgnoreCase("r")) {
        break;
      } else {
        System.out.println(" ".repeat(22) + "Invalid Selection");
      }
    }
  }

  // retrieve tournament info if ID# is inputted
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
      response = new JSONObject(HTTPClient.searchById("tournament", tournamentNumber));
      int n = response.length();
      if (n > 0) {
        singleTournamentDisplay();
        menuSelection();
      } else {
        System.out.println("No Tournament with that number found");
      }
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
  }

  public static void getAllTournaments(JSONArray responseData) throws JSONException {
    int n = responseData.length();
    if (n < 1) {
      System.out.println("No Tournament(s) Found");
    } else {
      // output of tournaments list
    System.out.println(tournamentHeader);
    for (int i = 0; i < n; i++) {
      final JSONObject tournament = responseData.getJSONObject(i);
      if (tournament.getInt("id") < 10) {
        System.out.print(" ");
      }
      System.out.print(" ".repeat(22) + " " + Colours.BLU_BR + tournament.getInt("id") + Colours.RESET + " ");
      System.out.print(" | " + tournament.getString("location") + " ".repeat(26 - tournament.getString("location").length()));
      System.out.print(" | " + tournament.getString("start_date"));
      System.out.print(" | " + tournament.getString("end_date") + " | ");
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
      System.out.print(" ".repeat(20) + "To view or edit member info, " + Colours.RED_BOL + "enter # beside name" + Colours.RESET +
          " and press enter, or " + Colours.RED_BOL + "\"R\"" + Colours.RESET + " and enter to return: ");
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
