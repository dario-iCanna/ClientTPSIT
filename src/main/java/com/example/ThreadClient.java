package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadClient extends Thread {
    BufferedReader in;

    public ThreadClient(Socket s, BufferedReader in) throws IOException {
        super();
        this.in = new BufferedReader(new InputStreamReader((s.getInputStream())));
    }

    public void run() {
        boolean connessione = true;
        try {
            while (connessione) {
                boolean run = true;
                String[] invio = in.readLine().split("@", 2);
                switch (invio[0]) {
                    case "L":
                        while (run) {
                            String msg = in.readLine();
                            if (msg.charAt(0) != 'L') {
                                System.out.println("-" + msg);
                            } else
                                run = false;
                        }
                        break;
                    case "LG":
                        while (run) {
                            String msg = in.readLine();
                            if (msg != "LG") {
                                System.out.println("-" + msg);
                            } else
                                run = false;
                        }
                        break;
                    case "PRIVATE":
                        invio = invio[1].split(":",2);
                        System.out.println(invio[0] + " a Te: " + invio[1]);
                        break;
                    case "NPr":
                        System.out.println("utente non esistente");
                        break;
                    case "ALL":
                        System.out.println(invio[1]);
                        break;
                    case "EXIT":
                        System.out.println(invio[1]+" si è disconnesso");
                    default:
                        break;
                }
            }
        } catch (Exception e) {

        }

    }
}
