/**
 * Description: This is a command line interface for a Project 2. The interface has a menu with several 
 * options ot choose from. The interface parses user input and displays an informative line in regards to that option.
 * 
 * @author Nikki Johnson
 * @version 2.2.2
 */

import java.util.Scanner;
public class MenuDriver {

      /**
       * This method displays the command options menu
       */
      private static void printMenu() {
            System.out.println("I string_to_insert (insert a string at the current position)" + 
            "\nA string_to_append (append a string at the end)" + 
            "\nR (remove the string at the current position)" + 
            "\nS (move the current position to the start)" + 
            "\nE (move the current position to the end)" + 
            "\nP (move the current position backward)" + 
            "\nF (move the current position forward)" + 
            "\nL (display the length of the list)" + 
            "\nC (display the current position number)" + 
            "\nM number (move the current position to specific position)" + 
            "\nD (display the string at the current position)" + 
            "\nV (display all strings)" + 
            "\nO (display these options)" + 
            "\nQ (quit)"); 
      }

      /**
       * Main method
       */
      public static void main(String[] args){
            // create scanner object to parse input
            Scanner input = new Scanner(System.in);

            // display the welcome messsage
            System.out.println("Welcome to the Menu Driver for a bare bones list!\n");
            
            // print out command options
            System.out.println("Command options: \n");
            printMenu();

            // create an ArrayList of Strings to be added to 
            BasicDLList<String> stringList = new BasicDLList<String>();

            // create a String to keep track of the command 
            String command;

            // create a boolean object to keep track of if the user wants to quit or not
            boolean cont = true;

            // repeatedly ask for command until Q is entered and get user input
            while(cont){
                  // get the user input to parse
                  System.out.println("\nPlease enter a command (or 'O' for all of the options): ");
                  command = input.nextLine();
                  // create a switch command for each command option
                  switch (command.charAt(0)){
                        // parse input and handle commands
                        case 'I': 
                              // determine the string after the command 'I' to add to the list
                              if (command.length() > 1){
                                    // get the string to add to the list
                                    String str = command.substring(2);
                                    // add the string to the list at the current position
                                    stringList.insert(str);
                                    // display informative line
                                    System.out.println("Inserting '" + str + "' ..."); break;
                              }
                              else {System.out.println(command + " is not a valid command!"); break;}
                        case 'A': 
                              // determine the string after the command 'A' to add to the list
                              if (command.length() > 1){
                                    // get the string to add to the list
                                    String str = command.substring(2);
                                    // add the string to the list at the current position
                                    stringList.append(str);
                                    // display informative line
                                    System.out.println("Appending '" + str + "' ..."); break;
                              }
                              else {System.out.println(command + " is not a valid command!"); break;}
                        case 'R': 
                              System.out.println("Removing the string at the current position..."); 
                              // remove the string from the list at the current position if there is something at the position and the list is not empty
                              String removed = stringList.remove();
                              System.out.println("Removed '" + removed + "'");
                              break;
                        case 'S': 
                              // change the current position to the start position (0)
                              System.out.println("Moving the current position to start ..."); 
                              stringList.moveToStart();
                              break;
                        case 'E': 
                              // change the current position to the end position (the length of the list minus 1)
                              System.out.println("Moving the current position to the end ..."); 
                              stringList.moveToEnd();
                              break;
                        case 'P': 
                              // move the current position backwards by one
                              System.out.println("Moving the current position backward ..."); 
                              stringList.prev();
                              break;
                        case 'F': 
                              // move the current position forward by one
                              System.out.println("Moving the current position forward ..."); 
                              stringList.next();
                              break;
                        case 'L': 
                              // display the length of the list
                              System.out.println("Displaying the length of the list ..."); 
                              System.out.println("Length of list = " + stringList.length());
                              break;
                        case 'C': 
                              // display the current position
                              System.out.println("Displaying the current position number ..."); 
                              System.out.println("The current position is " + stringList.currPos());
                              break;
                        case 'M': 
                              if (command.length() > 1){
                                    // get the number to change the position to 
                                    String str = command.substring(2);
                                    int number = Integer.parseInt(str);
                                    System.out.println("Moving current position to " + number + " ...");
                                    // update the position
                                    boolean moved = stringList.moveToPos(number);
                                    if (moved == false){
                                          System.out.println("Failed to move position to " + number); break;
                                    }
                                    else {
                                          System.out.println("Successfully moved position to " + number); break;  
                                    }
                              }
                              else {
                                    System.out.println(command + " is not a valid command!"); 
                                    break;
                              }
                        case 'D': 
                              // display a string at the current position
                              System.out.println("Displaying the string at the current position ..."); 
                              System.out.println(stringList.getValue());
                              break;
                        case 'V': 
                              // display all the strings in the list
                              System.out.println(stringList.toString()); 
                              break;
                        case 'O': 
                              // display the menu 
                              printMenu(); break;
                        // if Q is entered print goodbye message and exit the program
                        case 'Q': 
                              input.close();
                              System.out.println("Good-bye"); 
                              cont = false;
                              break;
                        // display error message if command is not a valid command
                        default: System.out.println(command + " is not a valid command!"); break;
                              
                  }
            }
            System.exit(-1);
      }
}