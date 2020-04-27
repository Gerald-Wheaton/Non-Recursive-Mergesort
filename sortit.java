//Gerald Wheaton
//April 20, 2020

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList; 
import java.util.Queue;
import java.util.Stack;

public class sortit {
    public static void main(String[] args) {

        Queue<String> list0 = new LinkedList<String>();
        Queue<String> list1 = new LinkedList<String>();
        Queue<String> currQ = new LinkedList<String>();
        Stack<String> reversedQueue = new Stack<String>();
        
        Scanner input = new Scanner(System.in);
        String newItem = "", rear = "", userInput = "";
        String currentFront = "", alternateFront = ""; //representing the front of the current queue and also the second queue
        int list0Length, list1Length;
        boolean reverseOrder = false;

        currQ = list0; //initializing currQ
        if (args.length != 0) { //collect all filenames from args array
            for (int i = 0; i < args.length; i++) { //collects user input from given files or manual entry
            //below lines get user input
                if (args[i].equals("-r")) {
                    reverseOrder = true;
                }
                else if (args[i].equals("-")) {
                    while(!userInput.equals("q") && !userInput.equals("Q")) {
                        System.out.print("Enter a word or 'q' to exit: ");
                        userInput = input.nextLine();

                        if(!userInput.equals("q") && !userInput.equals("") && !userInput.equals("Q") && !userInput.equals(" ")) {
                            //currQ = (newItem.compareTo(rear) < 0) ? list0 : list1; //ternary operatot to switch lists when newItem is less than the rear elemnt of currQ
                            if (newItem.compareTo(rear) < 0) {
                                if(currQ == list0) {
                                    currQ = list1;
                                }
                                else {
                                    currQ = list0;
                                }
                            }
                            currQ.add(userInput);

                            rear = userInput;
                        }
                    }
                }
                else {
                    try {
                        Scanner fileLengthScan = new Scanner (new File(args[i]));
                        //currQ = list0;
                        while(fileLengthScan.hasNext()) {
                            newItem = fileLengthScan.next();

                            //currQ = (newItem.compareTo(rear) < 0) ? list1 : list0; //ternary operatot to switch lists when newItem is less than the rear elemnt of currQ
                            if (newItem.compareTo(rear) < 0) {
                                if(currQ == list0) {
                                    currQ = list1;
                                }
                                else {
                                    currQ = list0;
                                }
                            }
                            currQ.add(newItem);

                            rear = newItem;
                        }
                    }
                    catch (FileNotFoundException e) {
                        System.out.println("FileNotFoundException caught for '" + args[i] + "'");
                    }
                }
            } 
        }
        input.close(); //closing Scanner


/* -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */
        //Queue sorting process

        //end loop when ONE queue is empty
        while(!list0.isEmpty() && !list1.isEmpty()) {

            rear = ""; //reinitialize rear when switching to new Queue
            currQ = list0;
            list0Length = list0.size();
            list1Length = list1.size();

            //while at least one queue's original length is not 0
            while(list0Length > 0 || list1Length > 0) {
                if (list0Length == 0) { //if length of list0 is 0 take only elements from list1
                    //currQ = list1;
                    currentFront = list1.remove();

                    if (currentFront.compareTo(rear) > 0) {
                        rear = currentFront;
                        currQ.add(rear);
                    }
                    else {
                        currQ = (currQ == list1) ? list0 : list1;
                        rear = currentFront;
                        currQ.add(rear);
                    }

                    list1Length--;
                }
                else if (list1Length == 0) { //if length of list1 is 0 take only elements from list0
                    //currQ = list0;
                    currentFront = list0.remove();

                    if (currentFront.compareTo(rear) > 0) {
                        rear = currentFront;
                        currQ.add(rear);
                    }
                    else {
                        currQ = (currQ == list1) ? list0 : list1;
                        rear = currentFront;
                        currQ.add(rear);
                    }
                    list0Length--;
                }
                else {
                    currentFront = currQ.peek();
                    alternateFront = (currQ == list0) ? list1.peek() : list0.peek();

                    //if current front is greater than rear AND if second front is greater than rear
                    //dequeue SMALLER from specified queue and enqeueu it to currQ
                    if (currentFront.compareTo(rear) > 0 && alternateFront.compareTo(rear) > 0) {
                        if(currentFront.compareTo(alternateFront) < 0) {
                            rear = currQ.remove();
                            currQ.add(rear);
                            if(list0Length > 0 && list1Length > 0) {
                            //decrement appropriate queue size
                                if(currQ == list0) {
                                    list0Length--;
                                }
                                else {
                                    list1Length--;
                                }
                            }
                        }
                        else {
                            rear = (currQ == list0) ? list1.remove() : list0.remove();
                            currQ.add(rear);
                            if(list0Length > 0 && list1Length > 0) {
                            //decrement appropriate queue size
                                if(currQ == list0) {
                                    list1Length--;
                                }
                                else {
                                    list0Length--;
                                }
                            }
                        }   
                    }
                    //if current front is greater than rear
                    //dequeue from specified queue and enqeueu to currQ
                    else if (currentFront.compareTo(rear) > 0) {
                        rear = currQ.remove();
                        currQ.add(rear);
                        if(list0Length > 0 && list1Length > 0) {
                            if(currQ == list0) {
                                list0Length--;
                            }
                            else {
                                list1Length--;
                            }
                        }
                    }
                    //if alternateFront is greater than rear
                    //dequeue from specified queue and enqeueu to currQ
                    else if (alternateFront.compareTo(rear) > 0) {
                        rear = (currQ == list0) ? list1.remove() : list0.remove();
                        currQ.add(rear);
                        if(list0Length > 0 && list1Length > 0) {
                            if(currQ == list0) {
                                list1Length--;
                            }
                            else {
                                list0Length--;
                            }
                        }
                    }
                    //if neither are greater than rear
                    //change over to other queue and reset rear
                    else {
                        currQ = ( currQ == list0 ) ? list1 : list0;
                        rear = ""; //reset rear
                    }
                }
            }
        }

/* -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */

    //output section
        currQ = (list0.size() == 0) ? list1 : list0;
        //print Queue in reversed order if user specified
        if(reverseOrder == true) {

            while (!currQ.isEmpty()) {
                reversedQueue.push(currQ.remove());
            }
            while(reversedQueue.size() != 0) {
                System.out.println(reversedQueue.pop() + " ");
            }
        }
        else {
            while(currQ.size() != 0) {
                System.out.println(list0.remove() + " ");
            }
        }
    }
}