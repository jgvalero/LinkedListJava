//  Description: The main class displays a menu of choices to a user
//               and performs the chosen task. It will keep asking a user to
//               enter the next choice until the choice of 'Q' (Quit) is
//               entered.

import java.io.*;

public class Assignment10
{
   public static void main(String[] args)
   {
       char input1;
       String inputInfo = new String();
       String line = new String();
       String str1 = new String();

       //create a linked list to be used in this method.
       LinkedList list1 = new LinkedList();

       try
        {
         // print out the menu
         printMenu();

         // create a BufferedReader object to read input from a keyboard
         InputStreamReader isr = new InputStreamReader (System.in);
         BufferedReader stdin = new BufferedReader (isr);

         do
          {
           System.out.print("What action would you like to perform?\n");
           line = stdin.readLine().trim();  //read a line
           input1 = line.charAt(0);
           input1 = Character.toUpperCase(input1);

           if (line.length() == 1)   //check if a user entered only one character
            {
             switch (input1)
              {
               case 'A':   //Add String
                 System.out.print("Please enter a string to add:\n");
                 str1 = stdin.readLine().trim();
                 list1.addElement(str1);
                 break;
               case 'C':   //Count strings
                 int count = list1.size();
                 System.out.print("The total number of elements is " + count + "\n");
                 break;
               case 'D':   //Duplicate Each
                 System.out.print("Duplicating elements\n");
                 list1.duplicateEach();
                 break;
               case 'H':   //count how many elements before an element
                 System.out.print("Please enter a string to find:\n");
                 str1 = stdin.readLine().trim();
                 int howMany = list1.howManyAppearBefore(str1);
                 if (howMany < 0)
                     System.out.print("The string " + str1 + " was not found\n");
                 else
                     System.out.print("The number of elements before the string " + str1 + " is " + howMany + "\n");
                 break;
                case 'I':   //Find the index of the last occurrence of a given string
                 System.out.print("Please enter a string to find:\n");
                 str1 = stdin.readLine().trim();
                 int index2 = list1.indexOfLast(str1);
                 if (index2 >= 0)
                 {
                     System.out.print("The last occurrence of the string " + str1 + " was at " + index2 + "\n");
                 }
                 else
                 {
                     System.out.print("The string " + str1 + " was not found\n");
                 }
                 break;
               case 'L':   //List Strings
                 System.out.print(list1.toString());
                 break;
               case 'Q':   //Quit
                 break;
               case 'R':  //Remove each element at an even index
                 System.out.print("Removing elements at even indices:\n");
                 list1.removeElementsAtEvenIndices();
                 break;
               case 'S':  //Remove the element at a given index
                 System.out.print("Please enter an index of a string to remove:\n");
                 inputInfo = stdin.readLine().trim();
                 int index = Integer.parseInt(inputInfo);
                 try
                  {
                    Object removed = list1.removeElementAt(index);
                    System.out.print("The string " + removed + " was removed\n");
                  }
                 catch(IndexOutOfBoundsException ex)
                  {
                      System.out.print("The index is out of bounds\n");
                  }
                 break;
               case '?':   //Display Menu
                 printMenu();
                 break;
               default:
                 System.out.print("Unknown action\n");
                 break;
              }
           }
          else
           {
             System.out.print("Unknown action\n");
            }
          } while (input1 != 'Q' || line.length() != 1);
        }
       catch (IOException exception)
        {
          System.out.print("IO Exception\n");
        }
    }

    /** The method printMenu displays the menu to a user **/
    public static void printMenu()
     {
       System.out.print("Choice\t\tAction\n" +
                        "------\t\t------\n" +
                        "A\t\tAdd String\n" +
                        "C\t\tCount Strings\n" +
                        "D\t\tDuplicate Each\n" +
                        "H\t\tHow Many Appear Before\n" +
                        "I\t\tIndex Of Last\n" +
                        "L\t\tList Strings\n" +
                        "Q\t\tQuit\n" +
                        "R\t\tRemove from Even Indices\n" +
                        "S\t\tRemove String at some Index\n" +
                        "?\t\tDisplay Help\n\n");
    } //end of printMenu()
}
