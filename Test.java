import java.util.*;
import java.lang.*;
import java.io.*;

class Test{
    public static void main(String h[]){
        // Reading Files
        try {
            File myObj = new File("ToDo.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);
        }
        myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Creating files
        try {
            File myObj = new File("hello.txt");
            if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
            } else {
            System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Writing Files
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write("Files in Java might be tricky, ");
            myWriter.write("Files in Java might be tricky,\n but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
