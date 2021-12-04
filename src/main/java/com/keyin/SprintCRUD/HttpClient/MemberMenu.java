package com.keyin.SprintCRUD.HttpClient;

import com.keyin.SprintCRUD.Utilities.Colours;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

import static com.keyin.SprintCRUD.HttpClient.MainMenu.JSONErrorConverter;

public class MemberMenu {
  // header when list of members is selected
  final static String memberHeader = " ".repeat(100) + "|       Membership Details       |\n" +
      "  #  | Name:" + " ".repeat(19) + "| Address:" + " ".repeat(16) +  "| Phone:" + " ".repeat(9) +
      "| Email:" + " ".repeat(18) + "|  Type:" + "  |   Start:" + "   | Length: |" + " ".repeat(4) + "\n" +
      "-".repeat(134);
  private static JSONObject MemberResponse;

  private static final Scanner scan = new Scanner(System.in);

  //output when a single member details is requested
  public static void singleMemberDisplay() throws JSONException {
    try {
      System.out.print(  Colours.YEL_BR + " ".repeat(30) + "Name: " + Colours.RESET + MemberResponse.getString("name") + " ".repeat(21 - MemberResponse.getString("name").length()));
      System.out.println(Colours.YEL_BR + "Address: " + Colours.RESET + MemberResponse.getString("address"));
      System.out.print(  Colours.YEL_BR + " ".repeat(30) +  "Phone: " + Colours.RESET + "(" + MemberResponse.getString("phone").substring(0, 3) +
          ") " + MemberResponse.getString("phone").substring(3, 6) + "-" + MemberResponse.getString("phone").substring(6, 10));
      System.out.println(Colours.YEL_BR + "      E-mail: " + Colours.RESET + MemberResponse.getString("email"));
      System.out.print(  Colours.YEL_BR + " ".repeat(30) + "Membership Type: " + Colours.RESET + MemberResponse.getString("membershipType"));
      System.out.print(  Colours.YEL_BR + "    Start Date: " + Colours.RESET + MemberResponse.getString("membershipStartDate"));
      System.out.println(Colours.YEL_BR + "    Length: " + Colours.RESET + MemberResponse.getInt("membershipLength") + " mon.");
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
  }

  // retrieve tournament Results
  public static void ListOfResultsByMember() {
    try {
      JSONArray resultsResponse = new JSONArray(HTTPClient.getResultsByMemberId("results", MemberResponse.getInt("id")));
      int n = resultsResponse.length();
      if (n < 1) {
        System.out.println(" ".repeat(50) + "*** No Tournament Results Found ***");
      } else {
        System.out.println(" ".repeat(45) + "Tournament Results for " + MemberResponse.getString("name") + " [" + MemberResponse.getString("id") + "]");
        System.out.println(" ".repeat(39) + "|    Date:   |        Location:        |  Result: |");
        for (int i = 0; i < n; i++) {
          final JSONObject result = resultsResponse.getJSONObject(i);
          System.out.print(" ".repeat(39) + "| " + result.getJSONObject("tournament").getString("startDate"));
          System.out.print(" | " + result.getJSONObject("tournament").getString("location") +
              " ".repeat(24 - result.getJSONObject("tournament").getString("location").length()) + "| ");
          if (result.getInt("result") < 10) {System.out.print(" ");}
          System.out.print("  " + result.getInt("result"));
          if (result.getInt("result") % 10 == 1) {
            System.out.print("st");
          } else if (result.getInt("result") % 10 == 2) {
            System.out.print("nd");
          } else if (result.getInt("result") % 10 == 3) {
            System.out.print("rd");
          } else {
            System.out.print("th");
          }
          System.out.println("   |");
        }
      System.out.println("-".repeat(134));
      }
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
  }

  // output to confirm deletion of member
  public static void deleteMember() {
    try {
      System.out.print("Are you sure you want to delete member " + MemberResponse.getString("name") +
          " [" + MemberResponse.getString("id") + "]? (Type \"Yes\" to Confirm): ");
      String deleteMember = scan.nextLine();
      if (deleteMember.equalsIgnoreCase("yes")) {
        String deleteResponse = HTTPClient.deleteById("member", MemberResponse.getInt("id"));
        System.out.println(deleteResponse);
      } else {
        System.out.println("Member NOT deleted");
      }
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
    System.out.println("-".repeat(134));
  }

  // output to change/update member information
  public static void editMember() throws JSONException {
    System.out.println("Press enter to keep the field the same");

    System.out.print("Name [" + MemberResponse.getString("name") + "]: ");
    String updatedName = scan.nextLine();
    if (updatedName.equals("")) updatedName = MemberResponse.getString("name");

    System.out.print("Address [" + MemberResponse.getString("address") + "]: ");
    String updatedAddress = scan.nextLine();
    if (updatedAddress.equals("")) updatedAddress = MemberResponse.getString("address");

    System.out.print("Phone [" + MemberResponse.getString("phone") + "]: ");
    String updatedPhone = scan.nextLine();
    if (updatedPhone.equals("")) updatedPhone = MemberResponse.getString("phone");

    System.out.print("E-mail [" + MemberResponse.getString("email") + "]: ");
    String updatedEmail = scan.nextLine();
    if (updatedEmail.equals("")) updatedEmail = MemberResponse.getString("email");

    System.out.print("Membership Type [" + MemberResponse.getString("membershipType") + "]: ");
    String updatedType = scan.nextLine();
    if (updatedType.equals("")) updatedType = MemberResponse.getString("membershipType");

    System.out.print("Membership Start Date [" + MemberResponse.getString("membershipStartDate") + "]: ");
    String updatedStartDate = scan.nextLine();
    if (updatedStartDate.equals("")) updatedStartDate = MemberResponse.getString("membershipStartDate");

    System.out.print("Membership Length (in months) [" + MemberResponse.getString("membershipLength") + "]: ");
    int updatedLength = Integer.parseInt("0" +scan.nextLine());
    if (updatedLength == 0) updatedLength = MemberResponse.getInt("membershipLength");

    String updateResponse = HTTPClient.updateMemberById(MemberResponse.getInt("id"),updatedName, updatedAddress, updatedEmail, updatedPhone,
        updatedStartDate, updatedLength, updatedType, "member");
    System.out.println(updateResponse);
  }

  // output to add new member information
  public static void newMember() {
    System.out.println("Press complete all fields");

    System.out.print("Name: ");
    String newName = scan.nextLine();

    System.out.print("Address: ");
    String newAddress = scan.nextLine();

    System.out.print("Phone: ");
    String newPhone = scan.nextLine();

    System.out.print("E-mail: ");
    String newEmail = scan.nextLine();

    System.out.print("Membership Type: ");
    String newType = scan.nextLine();

    System.out.print("Membership Start Date: ");
    String newStartDate = scan.nextLine();

    System.out.print("Membership Length (in months): ");
    Integer newLength = Integer.parseInt("0" +scan.nextLine());

    String insertResponse = HTTPClient.insertNewMember(newName, newAddress, newEmail, newPhone,
        newStartDate, newLength, newType, "member");
    System.out.println(insertResponse);

  }

  // output of menu to select options to edit or delete member
  public static void menuSelection() throws JSONException {
    while (true) {
      System.out.println("-".repeat(134));
      System.out.print(" ".repeat(34) + "Press \"T\" to list last 15 Tournaments played by this member,\n");
      System.out.print(" ".repeat(30) + "Press \"E\" to edit, \"D\" to delete, or \"R\" to return to previous menu ");
      String editCheck = scan.nextLine();
      System.out.println("-".repeat(134));
      if (editCheck.equalsIgnoreCase("E")) {
        editMember();
      } else if (editCheck.equalsIgnoreCase("D")) {
        deleteMember();
        break;
      } else if (editCheck.equalsIgnoreCase("R")) {
        break;
      } else if (editCheck.equalsIgnoreCase("T")) {
        ListOfResultsByMember();
        break;
      } else {
        System.out.println("Invalid Selection");
      }
    }
  }

  // retrieve member info if ID# is inputted
  public static void getMemberInfoById(Integer memberId) {
    String memberNumber;
    if (memberId < 0) {
      System.out.print("Enter membership #: ");
      memberNumber = scan.nextLine();
      System.out.println("-".repeat(134));
    } else {
      memberNumber = memberId.toString();
    }
    try {
      MemberResponse = new JSONObject(HTTPClient.searchById("member", memberNumber));
      int n = MemberResponse.length();
      if (n > 0) {
        singleMemberDisplay();
        menuSelection();
      } else {
        System.out.println("No Member with that number found");
      }
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
  }

  public static void getAllMembers(JSONArray responseData) throws JSONException {
    if (responseData == null) {
      System.out.println("No Member(s) Found");
    } else {
      // output of members list
      int n = responseData.length();
      System.out.println(memberHeader);
      for (int i = 0; i < n; i++) {
        final JSONObject member = responseData.getJSONObject(i);
        if (member.getInt("id") < 9) {
          System.out.print(" ");
        }
        System.out.print(" " + Colours.BLU_BR + member.getInt("id") + Colours.RESET + " ");
        System.out.print(" | " + member.getString("name") + " ".repeat(23 - member.getString("name").length()));
        System.out.print(" | ");
        if (member.getString("address").length() < 20) {
          System.out.print(member.getString("address") + " ".repeat(23 - member.getString("address").length()));
        } else {
          System.out.print(member.getString("address").substring(0, 20) + "...");
        }
        System.out.print(" | (" + member.getString("phone").substring(0, 3) + ") " + member.getString("phone").substring(3, 6) + "-" + member.getString("phone").substring(6, 10));

        System.out.print(" | ");
        if (member.getString("email").length() < 20) {
          System.out.print(member.getString("email") + " ".repeat(23 - member.getString("email").length()));
        } else {
          System.out.print(member.getString("email").substring(0, 20) + "...");
        }

        System.out.print(" | " + member.getString("membershipType") + " ".repeat(7 - member.getString("membershipType").length()));
        System.out.print(" | " + member.getString("membershipStartDate") + " | ");
        if (member.getInt("membershipLength") < 10) {System.out.print(" ");}
        System.out.println(member.getInt("membershipLength") + " mon. |");
      }
      System.out.println("-".repeat(134));
      while (true) {
        System.out.print(" ".repeat(19) + "To view or edit member info, " + Colours.RED_BOL + "enter # beside name" + Colours.RESET +
            " and press enter, or " + Colours.RED_BOL + "\"R\"" + Colours.RESET + " and enter to return: ");
        String memberNumber = scan.nextLine();
        System.out.println("-".repeat(134));
        if (memberNumber.equalsIgnoreCase("r")) {
          break;
        } else {
          getMemberInfoById(Integer.parseInt(memberNumber));
        }
      }
    }

  }
}
