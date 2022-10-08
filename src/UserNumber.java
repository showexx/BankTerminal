import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;

public class UserNumber {
    private String number;
    private static double balance;

    public void createNumber() {
        int num = 1 + (int) (Math.random() * MAX_VALUE);
        number = Integer.toString(num);

        writeDataNumber();
    }

    public void printInfo() {
        Account ac = new Account();
        MainProcess mp = new MainProcess();

        try {
            File file = new File(ac.getLogin() + ".txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();

            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mp.mainOptins();
    }

    public void volumeUp() {
        Account ac = new Account();
        MainProcess mp = new MainProcess();
        Scanner scanner = new Scanner(System.in);

        System.out.println("На какую сумму вы хотите пополнить баланс: ");
        double volume = scanner.nextDouble();

        balance = balance + volume;

        System.out.println("Вы успешно пополнили счет. Ваш текущий баланс: " + balance);
        String[] lines = new String[6];

        try {
            File file = new File(ac.getLogin() + ".txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            for (int i = 0; i < lines.length; i++) {
                lines[i] = reader.readLine();
                lines[4] = "balance: " + balance;
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ac.getLogin() + ".txt")));

                for (int j = 0; j <= 5; j++) {
                    out.println(lines[j]);
                }
                out.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        mp.mainOptins();
    }

    public void volumeDown() {
        Account ac = new Account();
        MainProcess mp = new MainProcess();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Какую сумму вы хотите снять: ");
        double volume = scanner.nextDouble();

        balance = balance - volume;

        if (balance < 0) {
            System.out.println("Укажите другую сумму.");
            mp.mainOptins();
        } else {
            System.out.println("Вы сняли деньги. Ваш текущий баланс: " + balance);
            String[] lines = new String[6];

            try {
                File file = new File(ac.getLogin() + ".txt");
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);

                for (int i = 0; i < lines.length; i++) {
                    lines[i] = reader.readLine();
                    lines[4] = "balance: " + balance;
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ac.getLogin() + ".txt")));

                    for (int j = 0; j <= 5; j++) {
                        out.println(lines[j]);
                    }
                    out.close();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            mp.mainOptins();
        }
    }


    public void writeDataNumber() {
        Account ac = new Account();
        MainProcess mp = new MainProcess();

        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(ac.getLogin() + ".txt")));

            if (lines.contains("haveNumber: true")) {
                String numberInFile = Files.readAllLines(Paths.get(ac.getLogin() + ".txt")).get(3);
                numberInFile = numberInFile.substring(8, numberInFile.length());
                System.out.println("Ваш номер: " + numberInFile);
                mp.mainOptins();
            } else {
                String numberWrite = "number: " + number;
                String balanceWrite = "balance: " + balance;
                String haveNumberWrite = "haveNumber: " + true;

                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ac.getLogin() + ".txt", true)));
                out.println(numberWrite + "\n" + balanceWrite + "\n" + haveNumberWrite);
                out.close();

                System.out.println("Ваш номер был успешно сгенерирован: " + number);
                mp.mainOptins();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}