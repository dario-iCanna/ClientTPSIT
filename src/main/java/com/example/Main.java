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
        while (ins) {
            System.out.println("inserisci nome senza @");
            String nome = input.nextLine();
            for (int i = 0; i < nome.length(); i++) {
                if (nome.charAt(i) == '@') {
                    break;
                }
            }
            out.writeBytes("USERNAME@" + nome + '\n');
            if (in.readLine().equals("SUs"))
                ins = false;
            else
                System.out.println("nome già inserito");
        }

        ThreadClient td = new ThreadClient(s, in);
        td.start();

        boolean connessione = true;
         while(connessione){
        System.out.println("1)lista utenti");
        System.out.println("2)lista gruppi");
        System.out.println("3)lista utenti del gruppo");
        System.out.println("4)messaggio privato");
        System.out.println("5)messaggio pubblico");
        System.out.println("6)messaggio a gruppo");
        System.out.println("7)esci");

        switch (input.nextLine()) {
            case "1":
                out.writeBytes("LIST" + '\n');
                break;
            case "2":
                out.writeBytes("LISTGR" + '\n');
                break;
            case "3":
                System.out.println("inserire nome gruppo da visualizzare");
                out.writeBytes("LISTUGR@" + input.nextLine() + '\n');
                break;
            case "4":
                System.out.println("inserire nome destinatario");
                String dest = input.nextLine();
                System.out.println("inserire messaggio");
                out.writeBytes("PRIVATE@" + input.nextLine() + "@" + dest + '\n');
                break;
            case "5":
                System.out.println("inserire messaggio");
                out.writeBytes("ALL@" + input.nextLine() + '\n');
                break;
            case "6":
                System.out.println("inserire id gruppo");
                String gr = input.nextLine();
                System.out.println("inserire messaggio");
                out.writeBytes("GROUP@" + input.nextLine() + "@" + gr + '\n');
                break;
            case "7":
                out.writeBytes("EXIT" + '\n');
                connessione=false;
                break;
        }

        }
    }
}