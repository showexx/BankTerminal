import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainProcess {

    static Account acc = new Account();

    public static void main(String args[]) {
        selection();
    }

    public static void selection() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Вас приветствует УМНЫЙ БАНК!");
        System.out.println("Выберите что вы хотите сделать: ");
        System.out.println("1.Создать аккаунт");
        System.out.println("2.Войти в аккаунт");

        int selection = scanner.nextInt();

        if (selection == 1) {
            acc.createAccount();
        } else if (selection == 2) {
            acc.loginAccount();
        } else {
            System.out.println("Проверьте правильность выбора.");
            selection();
        }
    }

    public static void mainOptins() {
        UserNumber un = new UserNumber();
        Transfer tn = new Transfer();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите что вы хотите сделать: ");
        System.out.println("1.Привязать номер.");
        System.out.println("2.Посмотреть информацию.");
        System.out.println("3.Управление балансом.");

        int select = scanner.nextInt();

        if (select == 1) {
            un.createNumber();
        } else if (select == 2) {
            un.printInfo();
        } else if (select == 3) {
            try {
                ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(acc.getLogin() + ".txt")));
                if (lines.contains("haveNumber: true")) {
                    System.out.println("Что вы хотите сделать с балансом: ");
                    System.out.println("1.Пополнить счет.");
                    System.out.println("2.Списать со счет.");
                    System.out.println("3.Перевод на счет.");

                    select = scanner.nextInt();
                    if (select == 1) {
                        un.volumeUp();
                    } else if (select == 2) {
                        un.volumeDown();
                    } else if (select == 3) {
                        tn.createTransfer();
                    }

                } else {
                    System.out.println("Сначала создайте номер.");
                    mainOptins();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            System.out.println("Проверьте правильность выбора.");
        }
    }
}
