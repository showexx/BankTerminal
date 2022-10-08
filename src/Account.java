import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Account {
    private short userАge;
    private String password;
    private static String login;

    Scanner scanner = new Scanner(System.in);
    MainProcess mp = new MainProcess();

    public Account(){

    }

    public void createAccount() {

        System.out.println("Придумайте логин.");
        login = scanner.nextLine();

        if (login.length() < 3) {
            System.out.println("Логин должен быть больше 3 символов.");
            createAccount();
        } else {
                File userData = new File(login + ".txt");
                boolean exists = userData.exists();
                if (exists == true) {
                    System.out.println("Такой аккаунт уже существует.");
                    mp.selection();
                }
        }

        System.out.println("Придумайте пароль. ");
        password = scanner.nextLine();
        if (password.length() < 6) {
            System.out.println("Убедитесь что длина пороля больше или равна 6 символам.");
            createAccount();
        }

        System.out.println("Введите возраст: ");
        userАge = scanner.nextShort();

        if (userАge < 18) {
            System.out.println("Открыть счет возможно только с 18 лет.");
            createAccount();
        }

        writeData();
        mp.selection();
    }

    public void loginAccount() {
        System.out.println("Для входа в аккаунт укажите логин: ");
        login = scanner.nextLine();

        File findAccount = new File(login + ".txt");
        boolean haveAccount = findAccount.exists();

        if (haveAccount == true) {
            try {
                System.out.println("Введите пароль: ");
                password = scanner.nextLine();

                String passwordInFile = Files.readAllLines(Paths.get(login + ".txt")).get(1);

                passwordInFile = passwordInFile.substring(10, passwordInFile.length());
                boolean equal = passwordInFile.equals(password);

                if (equal) {
                    System.out.println("Вы успешно вошли в аккаунт!");
                    mp.mainOptins();
                } else {
                    System.out.println("Проверьте правильность пароля.");
                    mp.selection();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Аккаунт не найден.");
            mp.selection();
        }
    }


    public void writeData() {
        try {
            File userData = new File(login + ".txt");
            FileWriter writerUser = new FileWriter(userData, true);
            BufferedWriter bufferWriter = new BufferedWriter(writerUser);

            bufferWriter.write( "login: " + login + "\n" + "password: " + password + "\n" + "age: " + userАge + "\n");
            bufferWriter.close();


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getLogin() {
        return login;
    }

}