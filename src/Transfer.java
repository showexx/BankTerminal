import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Transfer {
    private Double sum;
    private Double balSender;
    private Double balAddressee;
    private static String addressee;


    public void createTransfer() {
        Scanner scanner = new Scanner(System.in);
        Account ac = new Account();
        MainProcess mp = new MainProcess();

        System.out.println("Введите логин получателя:");
        addressee = scanner.nextLine();

        File findFile = new File(addressee + ".txt");
        boolean haveAccount = findFile.exists();

        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(addressee + ".txt")));

            if (haveAccount) {

                if (lines.contains("haveNumber: true")) {
                    System.out.println("Введите сумму которую вы хотите перевести.");
                    sum = scanner.nextDouble();

                    String balanceSender = Files.readAllLines(Paths.get(ac.getLogin() + ".txt")).get(4);
                    balanceSender = balanceSender.substring(9, balanceSender.length());
                    balSender = Double.valueOf(balanceSender);

                    String balanceAddressee = Files.readAllLines(Paths.get(addressee + ".txt")).get(4);
                    balanceAddressee = balanceAddressee.substring(9, balanceAddressee.length());
                    balAddressee = Double.valueOf(balanceAddressee);

                    if (sum > balSender) {
                        System.out.println("У вас недостаточно средств.");
                        mp.mainOptins();
                    } else {
                        balSender = balSender - sum;
                        balAddressee = balAddressee + sum;

                        String[] linesSender = new String[6];
                        String[] linesAddressee = new String[6];

                        File file = new File(ac.getLogin() + ".txt");
                        FileReader fr = new FileReader(file);
                        BufferedReader reader = new BufferedReader(fr);

                        File file1 = new File(addressee + ".txt");
                        FileReader fr1 = new FileReader(file1);
                        BufferedReader reader1 = new BufferedReader(fr1);

                        for (int i = 0; i < linesSender.length; i++) {
                            linesSender[i] = reader.readLine();
                            linesSender[4] = "balance: " + balSender;

                            linesAddressee[i] = reader1.readLine();
                            linesAddressee[4] = "balance: " + balAddressee;

                        }

                        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ac.getLogin() + ".txt")));
                        PrintWriter ou = new PrintWriter(new BufferedWriter(new FileWriter(addressee + ".txt")));

                        for (int j = 0; j <= 5; j++) {
                            out.println(linesSender[j]);
                            ou.println(linesAddressee[j]);

                        }
                        out.close();
                        ou.close();
                        System.out.println("Вы успешно перевели деньги.");
                        mp.selection();
                    }
                } else {
                    System.out.println("У аккаунта не привязан номер");
                    mp.mainOptins();
                }

            } else {
                System.out.println("Такой аккаунт не найден.");
                mp.mainOptins();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



