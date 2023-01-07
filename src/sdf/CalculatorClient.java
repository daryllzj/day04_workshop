package sdf;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CalculatorClient {

    public static void main(String[] args) throws IOException, java.io.EOFException{

        Console cons = System.console();

        while (true) {
            Socket conn = new Socket("localhost", 3000);
            String input = cons.readLine("What would you like to calculate?: ");

            if (input.toLowerCase().equals("exit")) {
                conn.close();
                break;
            }

            OutputStream os = conn.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            System.out.printf("The input is %s\n", input);
            oos.writeUTF(input);
            oos.flush();

            InputStream is = conn.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            String result = ois.readUTF();
            System.out.printf("Your result is %s\n", result);

            conn.close();
        }
        
    }
    
}
