import domain.entities.*;
import data.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Menu {
    static private Scanner scanner = new Scanner(System.in);
    static private int choicetodo = -1;
    static private int choiceid = -1;
    public static int readValue(int readedId){
        // readedID = 1 - readValue, 2 - readChoice, 3 - readYesOrNot, 4 - readID, 5 - readYear, 6 - readMonth, 7 - readDay
        scanner = new Scanner(System.in);
        int readed = -1;
        try {
            if (readedId == 1){
                readed = Integer.parseInt(scanner.nextLine());
            } else if (readedId == 2){
                readed = Integer.parseInt(scanner.nextLine());
                if(readed != 1 && readed != 2 && readed != 3 && readed != 4 && readed != 5 && readed != 6){
                    readed = readValue(2);
                }
            } else if (readedId == 3) {
                readed = Integer.parseInt(scanner.nextLine());
                if(readed != 1 && readed != 2){
                    readed = readValue(3);
                }
            } else if (readedId == 4){
                readed = Integer.parseInt(scanner.nextLine());
                if(readed > 4 || readed < 1){
                    readed = readValue(4);
                }
            } else if (readedId == 5) {
                readed = Integer.parseInt(scanner.nextLine());
                if(readed < 2000 || readed > 3000){
                    readed = readValue(5);
                }
            } else if (readedId == 6) {
                readed = Integer.parseInt(scanner.nextLine());
                if(readed < 1 || readed > 12){
                    readed = readValue(6);
                }
            } else if (readedId == 7) {
                readed = Integer.parseInt(scanner.nextLine());
                if(readed < 1 || readed > 31){
                    readed = readValue(7);
                }
            }
        }
        catch (NumberFormatException e){
            if (readedId == 1){
                System.out.println("Введено неверное значение :(");
                System.out.print("Попробуйте снова, у вас всё получится: ");
                readed = readValue(1);
            } else if (readedId == 2){
                System.out.println("Пожалуйста вводите только существующие пункты меню ;)");
                readed = readValue(2);
            } else if (readedId == 3) {
                System.out.println("Введено неверное значение :(");
                System.out.print("Попробуйте снова, у вас всё получится: ");
                readed = readValue(3);
            } else if (readedId == 4){
                System.out.println("Введено неверное значение :(");
                System.out.print("Попробуйте снова, у вас всё получится: ");
                readed = readValue(4);
            } else if (readedId == 5) {
                System.out.println("Введено неверное значение :(");
                System.out.print("Попробуйте снова, у вас всё получится: ");
                readed = readValue(5);
            }else if (readedId == 6) {
                System.out.println("Введено неверное значение :(");
                System.out.print("Попробуйте снова, у вас всё получится: ");
                readed = readValue(6);
            }else {
                System.out.println("Введено неверное значение :(");
                System.out.print("Попробуйте снова, у вас всё получится: ");
                readed = readValue(7);
            }
        }
        return readed;
    }
    public static double readDoubleValue(){
        scanner = new Scanner(System.in);
        double readed = -1;
        try {
            readed = Double.parseDouble(scanner.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("Введено неверное значение :(");
            System.out.print("Попробуйте снова, у вас всё получится: ");
            readed = readDoubleValue();
        }
        return readed;
    }
    public static String readLine(){
        scanner = new Scanner(System.in);
        String readed = "";
        try {
            readed = scanner.nextLine();
        }
        catch (NumberFormatException e){
            System.out.println("Что-то пошло не так :(");
            System.out.print("Попробуйте снова, у вас всё получится: ");
            readed = readLine();
        }
        return readed;
    }
    public static String readName(String name){
        boolean flag = Txt.setFilename(name);
        while(!flag){
            System.out.print("Такого файла не существует, введите существующий файл: ");
            name = readLine();
            flag = Txt.setFilename(name);
        }
        return name;
    }
    public static Calendar setDate(){
        Calendar date = Calendar.getInstance();
        System.out.print("Введите год покупки: ");
        int year = Menu.readValue(5);
        System.out.print("Введите месяц покупки: ");
        int month = Menu.readValue(6);
        System.out.print("Введите день покупки: ");
        int day = Menu.readValue(7);
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, (month - 1));
        date.set(Calendar.DATE, day);
        return date;
    }


     public static void start() {
        System.out.println("Приветствую!");
        System.out.println("Данное приложение поможет мониторить цены на товары и не только, что будем делать?");
        System.out.println("1) Добавить новую покупку");
        System.out.println("2) Вывести 5 магазинов, где товар дешевле всего");
        System.out.println("3) Вывести все магазины, где есть товар");
        System.out.println("4) Узнать информацию о конкретном товаре");
        System.out.println("5) Вывести все товары, которые есть в магазине (Разрабатывается)");
        System.out.println("5) Выйти из программы");
        choicetodo = readValue(2);
    }

     public static void chooseFindCharacteristics(ArrayList<Product> products) throws IOException { // Если выбрали 4 пункт в начале
        choiceid = -1;
        do{
            System.out.println("Введите тип покупки: ");
            System.out.println("1) Еда");
            System.out.println("2) Одежда");
            System.out.println("3) Техника");
            System.out.println("4) Молоко");
            choiceid = Menu.readValue(4);
            String findName;
            switch (choiceid){
                case 1: // Еда
                    System.out.print("Введите название товара, у которого хотите узнать срок годности: ");
                    findName = Menu.readLine();
                    if (products != null) {
                        for (Product product : products) {
                            if (product instanceof Food){
                                if (product.getName().toLowerCase().contains(findName.toLowerCase())){
                                    System.out.println("У товара " + product.getName() + ", который стоит " + product.getPrice() + " рублей, купленного по адресу " + product.getAddress() + ", срок годности до " + ((Food) product).getStorageLife().get(Calendar.YEAR) + " года " + (((Food) product).getStorageLife().get(Calendar.MONTH) + 1) + " месяца " + ((Food) product).getStorageLife().get(Calendar.DATE) + " дня");
                                }
                            }
                        }
                    }else {
                        System.out.println("Список пуст");
                    }
                    choiceid = 5;
                    break;
                case 2: // Одежда
                    System.out.println("К сожалению, у одежды ещё нет уникальной характеристики");
                    choiceid = 5;
                    break;
                case 3: // Техника
                    System.out.print("Введите название товара, у которого хотите узнать срок гарантии: ");
                    findName = Menu.readLine();
                    if (products != null) {
                        for (Product product: products) {
                            if (product instanceof Technic){
                                if (product.getName().toLowerCase().contains(findName.toLowerCase())){
                                    System.out.println("У товара " + product.getName() + ", который стоит " + product.getPrice() + " рублей, купленного по адресу " + product.getAddress() + ", " + product.getPurchaseDate().get(Calendar.YEAR) + " года " + (product.getPurchaseDate().get(Calendar.MONTH) + 1) + " месяца " + product.getPurchaseDate().get(Calendar.DATE) + " числа " + ", гарантия " + ((Technic) product).getGuarantee() + " месяцев");
                                }
                            }
                        }
                    }else {
                        System.out.println("Список пуст");
                    }
                    choiceid = 5;
                    break;
                case 4: // Молоко
                    System.out.print("Введите название товара, у которого хотите узнать срок годности: ");
                    findName = Menu.readLine();
                    if (products != null) {
                        for (Product product : products) {
                            if (product instanceof Milk){
                                if (product.getName().toLowerCase().contains(findName.toLowerCase())){
                                    System.out.println("У товара " + product.getName() + ", который стоит " + product.getPrice() + " рублей, купленного по адресу " + product.getAddress() + ", срок годности до " + ((Milk) product).getStorageLife().get(Calendar.YEAR) + " года " + (((Milk) product).getStorageLife().get(Calendar.MONTH) + 1) + " месяца " + ((Milk) product).getStorageLife().get(Calendar.DATE) + " дня");
                                }
                            }
                        }
                    }else {
                        System.out.println("Список пуст");
                    }
                    choiceid = 5;
                    break;
            }
        }while (choiceid != 5);
    }
     public static int getChoicetodo(){
        return choicetodo;
    }

     public static int getChoiceid() {
        return choiceid;
    }

}