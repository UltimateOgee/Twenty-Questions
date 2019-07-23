package twenty.questions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TwentyQuestions {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner readAns = new Scanner(System.in);
        File f = new File("gameData.txt");
        QuestionOrAnswerBank bank = new QuestionOrAnswerBank();
        //this do while sets up the game
        do {
            System.out.println("Welcome to Twenty Questions!"
                    + "\nType 'quit' to stop playing."
                    + "\n"
                    + "\nWould you like to start a new game? (y/n)");
            if (readAns.next().equals("y")) {
                System.out.println();
                bank = new QuestionOrAnswerBank(createGameSession(f), 1);
                break;
            } else {
                System.out.println("Game over!");
                break;
            }
        } while (!readAns.next().toLowerCase().equals("quit"));
        //at this point there is a bank with one of three questions in it as 
        //the root node (QuestionOrAnswer)
        
        //pick correct file
        String file = "things.txt";
        System.out.println("bank.root.data " + bank.root.data);
        if(bank.root.data.contains("thing")) {
            file = "things.txt";
        }if(bank.root.data.contains("place")) {
            file = "places.txt";
        }if(bank.root.data.contains("people")) {
            file = "people.txt";
        }
        File bankFile = new File(file);
        
        //Now time to add all known questions/answers (in file) to the bank for 
        //the game session
        Scanner QorAReader = new Scanner(bankFile);
        while (QorAReader.hasNextLine()) {
            String QorA = QorAReader.nextLine();
            Scanner lineReader = new Scanner(QorA);
            if (lineReader.next().equals("yes")) {
                //if the user said yes to a question, this is the quesiton/answer to goto next
                bank.add(lineReader.next(), true);
            } else if (lineReader.next().equals("no")) {
                bank.add(lineReader.next(), false);
            } else {
                System.out.println("Warning: Your file has an error in formatting.");
                System.out.println("Ending Game, please check formatting and try again");
            }
        }
        QuestionOrAnswer currentQorA = bank.root;
        System.out.println(bank.root.data); //asks first question
        while (!(currentQorA.right == null && currentQorA.left == null) && !readAns.next().toLowerCase().equals("quit")) {
            Scanner response = new Scanner(System.in);
            String resp = response.next();
            if(resp.toLowerCase().equals("y")){
                currentQorA = currentQorA.right;
            } else if (resp.toLowerCase().equals("n")){
                currentQorA = currentQorA.left;
            } else {
                System.out.println("invalid response, try again");
            }
        }
        //now done cycling through all logged questions, arrived right before an answer
        //time to ask them if the answer is right:
        Scanner response = new Scanner(System.in);
        System.out.println("Is this your answer: " + currentQorA.data + "?");
        String resp = response.next();
        if(resp.toLowerCase().equals("y")){
            System.out.println("Awesome!\nEnding Game Session");
        } else {
            //add new branches to tree... still need to work out this portion
        }
    }

    public static QuestionOrAnswer createGameSession(File f) throws FileNotFoundException {
        Scanner readInit = new Scanner(f);
        System.out.println("Starting new game...");
        String question = "";
        String futureTreeAns = "";

        while (readInit.hasNextLine() && !futureTreeAns.equals("y")) {
            System.out.println(readInit.nextLine());
            Scanner ansRead = new Scanner(System.in);
            question = ansRead.next();
            if (question.toLowerCase().equals("y")) {
                futureTreeAns = "y";
                System.out.println();
                break;
            }
        }
        return new QuestionOrAnswer(question);
    }

}
