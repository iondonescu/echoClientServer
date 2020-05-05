package com.donescuion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader echoes = new BufferedReader(
                   new InputStreamReader(socket.getInputStream())
            );
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;
            // citim de la consola , cel putin odata,
            do{
                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();
                stringToEcho.println(echoString);// este transmis
                if(!echoString.equals("exit"))
                {
                    response = echoes.readLine();// este primit
                    System.out.println(response);
                }
            }while (!echoString.equals("exit"));
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
