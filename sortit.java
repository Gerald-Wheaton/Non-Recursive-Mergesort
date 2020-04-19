import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList; 

public class sortit {
    public static void main(String[] args) {

        Queue<String> list0 = new LinkedQueue<String>();
        Queue<String> list1 = new LinkedQueue<String>();
        Queue<String> currQ = new LinkedQueue<String>();
        Scanner input = new Scanner(System.in);
        String newItem = "", rear = "", userInput = "";
        //Scanner input = new Scanner(System.in);

       if (args.length != 0) { //collect all filenames from args array
        currQ = list0; //initializing currQ
            for (int i = 0; i < args.length; i++) { //collects user input from given files or manual entry
                //below lines get user input
                if (args[i].equals("-")){
                    while(!userInput.equals("q")) {
                        System.out.print("Enter a word or 'q' to exit: ");
                        userInput = input.nextLine();
                        if(!userInput.equals("q") && !userInput.equals("")) {

                            currQ = (newItem.compareTo(rear) < 0) ? list0 : list1; //ternary operatot to switch lists when newItem is less than the rear elemnt of currQ
                            currQ.insert(userInput);

                            rear = userInput;
                        }
                    }
                }
                else {
                    try {
                        Scanner fileLengthScan = new Scanner (new File(args[i]));
                        while(fileLengthScan.hasNext()) {
                            newItem = fileLengthScan.next();
    
                            currQ = (newItem.compareTo(rear) < 0) ? list0 : list1; //ternary operatot to switch lists when newItem is less than the rear elemnt of currQ
                            currQ.insert(newItem);
    
                            rear = newItem;
                        }
                        //SortInput(fileLengthScan);
                    }
                    catch (FileNotFoundException e) {
                        System.out.println("FileNotFoundException caught");
                    }
                }
                
            } 
        }

        while(list0.size() != 0) {
            System.out.print(list0.delete() + " ");
        }

        System.out.println();

        while(list1.size() != 0) {
            System.out.print(list1.delete() + " ");
        }

        System.out.println();

    }


    public static void SortInput(Scanner file) { //sorts then prints input

    }

}