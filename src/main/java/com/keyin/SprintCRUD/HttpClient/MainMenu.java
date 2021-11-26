package com.keyin.SprintCRUD.HttpClient;

import java.util.Scanner;

public class MainMenu {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    while (true) {
      System.out.println("Welcome to to The Golf Club - Main Menu");
      System.out.print("Press \"M\" for Members, ");
      System.out.print("Press \"T\" for Tournaments, ");
      System.out.println("Press \"Q\" for Quit");
      String categorySelection = scan.nextLine();

      if (categorySelection.equalsIgnoreCase("m")) {
        while (true) {
          System.out.println("Members Section");
          System.out.println("Press \"R\" to return to previous menu");
          String nextSelection = scan.nextLine();
          if (nextSelection.equalsIgnoreCase("r")) {
            break;
          } else {
            System.out.println("Invalid selection");
            continue;
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
            continue;
          }
        }
      } else if (categorySelection.equalsIgnoreCase("q")){
        break;
      } else {
        System.out.println("Invalid Selection, Try Again");
        continue;
      }
    }
    scan.close();
  }
}
