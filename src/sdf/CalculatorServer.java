package sdf;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {

    public static void main(String[] args) throws IOException, java.io.EOFException {

    ServerSocket server = new ServerSocket(3000);

    while (true) {
        
    Socket conn = server.accept();
    System.out.println("\nSocket created. Got a connection\n");

    try {
        InputStream is = conn.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        String input = ois.readUTF();

        String[] columns = input.split(" ");
        System.out.printf("First number is %s\n",columns[0]);
        System.out.printf("The Operand is %s\n", columns[1]);
        System.out.printf("Second number is %s\n",columns[2]);

        Float firstNum = Float.parseFloat(columns[0]);
        Float secondNum = Float.parseFloat(columns[2]);
        System.out.printf("The numbers are %f and %f\n",firstNum,secondNum);

        Float result = 0f;

        switch (columns[1]) {
            case "+":
                result = firstNum + secondNum;
                break;
            case "-":
                result = firstNum - secondNum;
                break;
            case "/":
                result = firstNum / secondNum;
                break;
            case "*":
                result = firstNum * secondNum;
                break;
            default:
                break;
        }

        System.out.printf("The result is %f\n",result);

        OutputStream os = conn.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);

        String resultString = result.toString();

        oos.writeUTF(resultString);
        oos.flush();

        conn.close();
        
    } catch (java.io.EOFException e) {
        System.out.println("End of Programme");
        break;
    }

    }

        
    }

}