package com.donescuion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
	//create server socket
        try(ServerSocket serverSocket = new ServerSocket(5000)){ // 5000 nr portului
            //socket use to comunicate with the client
            Socket socket = serverSocket.accept();//waits for a client to connect
            System.out.println("Client connected");
            //
            BufferedReader inputuri = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter outputuri = new PrintWriter(socket.getOutputStream(),true);//transmite automat raspuns
            while (true){
                
                String echoString = inputuri.readLine();
                if(echoString.equals("exit")){
                    break;
                }
                System.out.println("Client asks: " + echoString);
                outputuri.println("Echo from server " + echoString);
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
