package com.keyin.SprintCRUD.HttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

import static com.keyin.SprintCRUD.HttpClient.MainMenu.JSONErrorConverter;

public class MemberMenu {
  private static JSONObject response;
  private static JSONArray responseData;
  private static int n;

  private static Scanner scan = new Scanner(System.in);

  public static String singleMemberDisplay(JSONObject response) throws JSONException {
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
    return null;
  }

  public static String getMemberInfoById() {
    System.out.print("Enter membership #: ");
    String memberNumber = scan.nextLine();
    System.out.println("-".repeat(134));
    try {
      response = new JSONObject(HTTPClient.searchById("member", memberNumber));
      n = response.length();
      if (n > 0) {
        singleMemberDisplay(response);
      } else {
        System.out.println("No Member with that number found");
      }
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
    return null;
  }

  public static String getMemberInfoByURI(String memberURI) {
    try {
      response = new JSONObject(HTTPClient.getByURI(memberURI));
      singleMemberDisplay(response);
      while (true) {
        System.out.print("Press \"E\" to edit, press \"R\" to return to previous menu ");
        String editCheck = scan.nextLine();
        System.out.println("-".repeat(134));
        if (editCheck.equalsIgnoreCase("e")) {
          System.out.print("Update Name? [Current: " + response.getString("name") + "]: ");
          String updateName = scan.nextLine();
        } else if (editCheck.equalsIgnoreCase("r")) {
          return null;
        } else {
          System.out.println("Invalid Selection");
        }
      }
    } catch (JSONException e) {
      System.out.println(JSONErrorConverter(e));
    }
    return null;
  }
}
