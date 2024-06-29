import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Program {
    static int paramsCount = 6;
    public static void main(String[] args) {
        System.out.println("Введите следующие данные: Фамилия Имя Отчество дата рождения номер телефона пол\n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        String[] fields = input.split(" ");
        if (fields.length != paramsCount) {
            System.err.println("Неверное количество полей, вы ввели: " + fields.length + ", а нужно " + paramsCount + " полей");
        }
        String lastName = fields[0].toLowerCase();
        if (hasDigits(lastName)) {
            System.err.println("Введена некорректная фамилия");
            return;
        }
        String firstName = fields[1].toLowerCase();
        if (hasDigits(firstName)) {
            System.err.println("Введено некорректное имя");
            return;
        }
        String middleName = fields[2].toLowerCase();
        if (hasDigits(middleName)) {
            System.err.println("Введено некорректное отчество");
            return;
        }

        LocalDate birthDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            birthDate = LocalDate.parse(fields[3], formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Неверный формат даты");
            return;
        }
        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(fields[4]);
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат телефона");
            return;
        }
        String gender = fields[5];
        if ((!"m".equals((gender)) && (!"f".equals(gender)))) {
            System.err.println("Неверный формат пола, введите f или m");
            return;
        }

        String fileName = lastName + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("<" + lastName + ">" + "<" + firstName + ">" + "<" + middleName + ">" + "<" + birthDate + ">" + "<" + phoneNumber + ">" + "<" + gender + ">");
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка записи!");
        }
    }
    public static boolean hasDigits(String word) {
        boolean hasDigits = false;
        for(int i = 0; i < word.length() && !hasDigits; i++) {
            if(Character.isDigit(word.charAt(i))) {
                hasDigits = true;
            }
        }
        
        return hasDigits;
    }
}