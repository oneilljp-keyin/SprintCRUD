package com.keyin.SprintCRUD.HttpClient;

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
  private static JSONObject response;


  private static final Scanner scan = new Scanner(System.in);

  //output when a single member details is requested
  public static void singleMemberDisplay() throws JSONException {
    try {
      System.out.println("Name: " + response.getString("name"));
      System.out.println("Address: " + response.getString("address"));
      System.out.println("Phone: (" + response.getString("phone").substring(0, 3) + ") " + response.getString("phone").substring(3, 6) + "-" + response.getString("phone").substring(6, 10));
      System.out.println("E-mail: " + response.getString("email"));
      System.out.println("Membership Type: " + response.getString("membershipType"));
      System.out.println("Membership Start Date: " + response.getString("membershipStartDate"));
      System.out.println("Membership Length: " + response.getInt("membershipLength") + " mon.");
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
  }

  // output to confirm deletion of member
  public static void deleteMember() {
    try {
      System.out.print("Are you sure you want to delete member " + response.getString("name") +
          " [" + response.getString("id") + "]? (Type \"Yes\" to Confirm): ");
      String deleteMember = scan.nextLine();
      if (deleteMember.equalsIgnoreCase("yes")) {
        String deleteResponse = HTTPClient.deleteById("member", response.getString("id"));
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

    System.out.print("Name [" + response.getString("name") + "]: ");
    String updatedName = scan.nextLine();
    if (updatedName.equals("")) updatedName = response.getString("name");

    System.out.print("Address [" + response.getString("address") + "]: ");
    String updatedAddress = scan.nextLine();
    if (updatedAddress.equals("")) updatedAddress = response.getString("address");

    System.out.print("Phone [" + response.getString("phone") + "]: ");
    String updatedPhone = scan.nextLine();
    if (updatedPhone.equals("")) updatedPhone = response.getString("phone");

    System.out.print("E-mail [" + response.getString("email") + "]: ");
    String updatedEmail = scan.nextLine();
    if (updatedEmail.equals("")) updatedEmail = response.getString("email");

    System.out.print("Membership Type [" + response.getString("membershipType") + "]: ");
    String updatedType = scan.nextLine();
    if (updatedType.equals("")) updatedType = response.getString("membershipType");

    System.out.print("Membership Start Date [" + response.getString("membershipStartDate") + "]: ");
    String updatedStartDate = scan.nextLine();
    if (updatedStartDate.equals("")) updatedStartDate = response.getString("membershipStartDate");

    System.out.print("Membership Length (in months) [" + response.getString("membershipLength") + "]: ");
    int updatedLength = Integer.parseInt("0" +scan.nextLine());
    if (updatedLength == 0) updatedLength = response.getInt("membershipLength");

    String updateResponse = HTTPClient.updateMemberById(response.getInt("id"),updatedName, updatedAddress, updatedEmail, updatedPhone,
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
      System.out.print("Press \"E\" to edit, \"D\" to delete, or \"R\" to return to previous menu ");
      String editCheck = scan.nextLine();
      System.out.println("-".repeat(134));
      if (editCheck.equalsIgnoreCase("e")) {
        editMember();
      } else if (editCheck.equalsIgnoreCase("d")) {
        deleteMember();
        break;
      } else if (editCheck.equalsIgnoreCase("r")) {
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
      response = new JSONObject(HTTPClient.searchById("member", memberNumber));
      Integer n = response.length();
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
    int n = responseData.length();
    if (n < 1) {
      System.out.println("No Member(s) Found");
    } else {
      // output of members list
    System.out.println(memberHeader);
    for (int i = 0; i < n; i++) {
      final JSONObject member = responseData.getJSONObject(i);
      if (member.getInt("id") < 9) {
        System.out.print(" ");
      }
      System.out.print(" " + member.getInt("id") + " ");
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
      int numberCheck;
      System.out.print("To view or edit member info, enter # beside name and press enter, or press enter to return: ");
      String memberNumber = scan.nextLine();
      System.out.println("-".repeat(134));
      try {
        numberCheck = Integer.parseInt(memberNumber);
      } catch (NumberFormatException nfe) {
        break;
      }
      getMemberInfoById(numberCheck);
      break;
    }
    }

  }
}
