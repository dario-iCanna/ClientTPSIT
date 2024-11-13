package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        
        System.out.println("inserici IP");
        
        Socket s = new Socket(input.nextLine(), 3000);
        BufferedReader in = new BufferedReader(new InputStreamReader((s.getInputStream())));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        
        boolean ins = true;
        while(ins){
            System.out.println("inserisci nome senza @");
            String nome = input.nextLine();
            for(int i = 0; i < nome.length(); i++){
                if(nome.charAt(i)=='@'){
                    break;
                }                
            }
            out.writeBytes("USERNAME@" + nome + '\n');
            if(in.readLine().equals("SUs"))
                ins = false;
            else
                System.out.println("nome già inserito");
        }

        ThreadClient td = new ThreadClient(s, in);
        td.start();
        
        boolean connessione = true;        
        while(connessione){

        }
    }
}