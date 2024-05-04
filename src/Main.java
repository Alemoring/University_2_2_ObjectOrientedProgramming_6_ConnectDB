/*
 *
 *  Программа, помогающая подопрать самый дешёвый вариант товара,
 *  мониторящая данные о купленных товарах
 *
 */

import view.MainForm;


public class Main {
    public static void main(String[] args) throws Exception {
        MainForm mainForm = new MainForm();
        // Загрузка из текстового файла массива разнообразных товаров
        /*System.out.print("Введите название файла, в котором хранятся или будут храниться данные о покупках: ");
        String filename = Menu.readLine();
        filename = Menu.readName(filename);
        ArrayList<Product> products = new ArrayList<>();
        if (Txt.nullFileOrNotNull()){
            products = Txt.readProductsFromFile();
            System.out.println("Данные успешно загружены");
        }
        //NullPointerException
        // Массив для хранения соответствия между значением и качеством
        String[] qualities = new String[3];
        qualities[0] = "Хорошо";
        qualities[1] = "Нормально";
        qualities[2] = "Плохо";
        while (Menu.getChoicetodo() != 5) {
            Menu.start();
            switch (Menu.getChoicetodo()) {
                case 1: // Добавить новую покупку
                    System.out.println("Введите тип покупки: ");
                    System.out.println("1) Еда");
                    System.out.println("2) Одежда");
                    System.out.println("3) Техника");
                    System.out.println("4) Молоко");
                    int id;
                    id = Menu.readValue(4);
                    Food food;
                    Milk milk;
                    Clothes clothes;
                    Technic technic;
                    System.out.print("Введите название товара: ");
                    String name = Menu.readLine();
                    Calendar date = Menu.setDate();
                    System.out.print("Введите цену покупки: ");
                    double price = Menu.readDoubleValue();
                    System.out.print("Введите адрес и название магазина, где был куплен товар: ");
                    String address = Menu.readLine();
                    System.out.println("Введите качество товара: ");
                    System.out.println("1) Хорошо");
                    System.out.println("2) Нормально");
                    System.out.println("3) Плохо");
                    short quality = (short) Menu.readValue(1);
                    quality -= 1;
                    System.out.println("Хотите ли вы добавить комментарий о товаре?");
                    System.out.println("1) Да");
                    System.out.println("2) Нет");
                    int choice = Menu.readValue(3);
                    String comment;
                    if (choice == 1){
                        comment = Menu.readLine();
                    }else {
                        comment = "0";
                    }
                    boolean flag = false;
                    switch (id){
                        case 1: // Food
                            System.out.println("Хотите ли вы добавить информацию о сроке годности товара?");
                            System.out.println("1) Да");
                            System.out.println("2) Нет");
                            choice = Menu.readValue(3);
                            if (choice == 1){
                                Calendar endDate = Menu.setDate();
                                food = new Food(name, date, price, address, quality, comment, endDate);
                                Txt.writeFoodToFile(food);
                                flag = true;
                            }else {
                                food = new Food(name, date, price, address, quality, comment);
                                Txt.writeFoodToFile(food);
                                flag = true;
                            }
                            break;
                        case 2: // Clothes
                            clothes = new Clothes(name, date, price, address, quality, comment);
                            Txt.writeProductToFile(clothes);
                        case 3: // Technic
                            System.out.println("Хотите ли вы добавить информацию о гарантии товара?");
                            System.out.println("1) Да");
                            System.out.println("2) Нет");
                            choice = Menu.readValue(3);
                            if (choice == 1){
                                System.out.print("Введите количество месяцев гарантии: ");
                                int guarantee = Menu.readValue(1);
                                technic = new Technic(name, date, price, address, quality, comment, guarantee);
                                Txt.writeTechnicToFile(technic);
                                flag = true;
                            }else {
                                technic = new Technic(name, date, price, address, quality, comment);
                                Txt.writeProductToFile(technic);
                                flag = true;
                            }
                            break;
                        case 4: // Milk
                            System.out.print("Введите жирность молока: ");
                            double fatContent = Menu.readDoubleValue();
                            System.out.println("Хотите ли вы добавить информацию о сроке годности товара?");
                            System.out.println("1) Да");
                            System.out.println("2) Нет");
                            choice = Menu.readValue(3);
                            if (choice == 1){
                                Calendar endDate = Menu.setDate();
                                milk = new Milk(name, date, price, address, quality, comment, endDate, fatContent);
                                Txt.writeMilkToFile(milk, true);
                                flag = true;
                            }else {
                                milk = new Milk(name, date, price, address, quality, comment, fatContent);
                                Txt.writeMilkToFile(milk, false);
                                flag = true;
                            }
                            break;
                    }
                    break;
                case 2: // Вывести 5 магазинов, где товар дешевле всего
                    System.out.println("Введите тип покупки: ");
                    System.out.println("1) Еда");
                    System.out.println("2) Одежда");
                    System.out.println("3) Техника");
                    System.out.println("4) Молоко");
                    int pid = Menu.readValue(4);
                    System.out.print("Введите название товара: ");
                    String pName = Menu.readLine();
                    if (pid == 1) {
                        if (!(products.isEmpty())) {
                            double[] prices = new double[products.size()];
                            for (int i = 0; i < prices.length; i++){
                                prices[i] = 1000000000;
                            }
                            int i = 0;
                            for (Product product : products) {
                                if(product instanceof Food){
                                    if (product.getName().toLowerCase().contains(pName.toLowerCase())){
                                        prices[i] = product.getPrice();
                                        i++;
                                    }
                                }
                            }
                            Arrays.sort(prices);
                            for (Product product : products) {
                                if (product instanceof Food){
                                    if (product.getPrice() == prices[0] || product.getPrice() == prices[1] || product.getPrice() == prices[2] || product.getPrice() == prices[3] || product.getPrice() == prices[4]){
                                        System.out.println("Товар " + product.getName() + ", продаётся по цене " + product.getPrice() + " рублей, по адресу " + product.getAddress());
                                    }
                                }
                            }
                        }else {
                            System.out.println("Список пуст");
                        }
                    } else if (pid == 2) {
                        if(products != null) {
                            double[] prices = new double[products.size()];
                            int i = 0;
                            for (Product product : products) {
                                if (product instanceof Clothes){
                                    if (product.getName().toLowerCase().contains(pName.toLowerCase())){
                                        prices[i] = product.getPrice();
                                        i++;
                                    }
                                }
                            }
                            Arrays.sort(prices);
                            for (Product product : products) {
                                if (product instanceof Clothes){
                                    if (product.getPrice() == prices[0] || product.getPrice() == prices[1] || product.getPrice() == prices[2] || product.getPrice() == prices[3] || product.getPrice() == prices[4]){
                                        System.out.println("Товар " + product.getName() + ", продаётся по цене " + product.getPrice() + " рублей, по адресу " + product.getAddress());
                                    }
                                }
                            }
                        }else {
                            System.out.println("Список пуст");
                        }
                    }else if (pid == 3) {
                        if (products != null) {
                            double[] prices = new double[products.size()];
                            int i = 0;
                            for (Product product : products) {
                                if (product instanceof Technic){
                                    if (product.getName().toLowerCase().contains(pName.toLowerCase())){
                                        prices[i] = product.getPrice();
                                        i++;
                                    }
                                }
                            }
                            Arrays.sort(prices);
                            for (Product product : products) {
                                if (product instanceof Technic){
                                    if (product.getPrice() == prices[0] || product.getPrice() == prices[1] || product.getPrice() == prices[2] || product.getPrice() == prices[3] || product.getPrice() == prices[4]){
                                        System.out.println("Товар " + product.getName() + ", продаётся по цене " + product.getPrice() + " рублей, по адресу " + product.getAddress());
                                    }
                                }
                            }
                        }else {
                            System.out.println("Список пуст");
                        }
                    }else {
                        if (products != null) {
                            double[] prices = new double[products.size()];
                            int i = 0;
                            for (Product product : products) {
                                if (product instanceof Milk){
                                    if (product.getName().toLowerCase().contains(pName.toLowerCase())){
                                        prices[i] = product.getPrice();
                                        i++;
                                    }
                                }
                            }
                            Arrays.sort(prices);
                            for (Product product : products) {
                                if (product instanceof Milk){
                                    if (product.getPrice() == prices[0] || product.getPrice() == prices[1] || product.getPrice() == prices[2] || product.getPrice() == prices[3] || product.getPrice() == prices[4]){
                                        System.out.println("Товар " + product.getName() + ", продаётся по цене " + product.getPrice() + " рублей, по адресу " + product.getAddress());
                                    }
                                }
                            }
                        }else {
                            System.out.println("Список пуст");
                        }
                    }
                    break;
                case 3: // Вывести все магазины, где есть товар
                    System.out.println("Введите тип покупки: ");
                    System.out.println("1) Еда");
                    System.out.println("2) Одежда");
                    System.out.println("3) Техника");
                    System.out.println("4) Молоко");
                    int findid = Menu.readValue(4);
                    System.out.print("Введите название товара: ");
                    String findName = Menu.readLine();
                    if (findid == 1) {
                        if (products != null) {
                            for (Product product : products) {
                                if (product instanceof Food){
                                    if (product.getName().toLowerCase().contains(findName.toLowerCase())){
                                        System.out.println(product.getName() + " " + product.getPurchaseDate().get(Calendar.YEAR) + " " + (product.getPurchaseDate().get(Calendar.MONTH) + 1) + " " + product.getPurchaseDate().get(Calendar.DATE) + " " + product.getPrice() + " " + product.getAddress() + " " + qualities[product.getQuality()]);
                                    }
                                }
                            }
                        }else {
                            System.out.println("Список пуст");
                        }
                    } else if (findid == 2) {
                        if(products != null) {
                            for (Product product : products) {
                                if (product instanceof Clothes){
                                    if (product.getName().toLowerCase().contains(findName.toLowerCase())){
                                        System.out.println(product.getName() + " " + product.getPurchaseDate().get(Calendar.YEAR) + " " + (product.getPurchaseDate().get(Calendar.MONTH) + 1) + " " + product.getPurchaseDate().get(Calendar.DATE) + " " + product.getPrice() + " " + product.getAddress() + " " + qualities[product.getQuality()]);
                                    }
                                }
                            }
                        }else {
                            System.out.println("Список пуст");
                        }
                    }else if (findid == 3) {
                        if (products != null) {
                            for (Product product : products) {
                                if (product instanceof Technic){
                                    if (product.getName().toLowerCase().contains(findName.toLowerCase())){
                                        System.out.println(product.getName() + " " + product.getPurchaseDate().get(Calendar.YEAR) + " " + (product.getPurchaseDate().get(Calendar.MONTH) + 1) + " " + product.getPurchaseDate().get(Calendar.DATE) + " " + product.getPrice() + " " + product.getAddress() + " " + qualities[product.getQuality()]);
                                    }
                                }
                            }
                        }else {
                            System.out.println("Список пуст");
                        }
                    }else {
                        if (products != null) {
                            for (Product product : products) {
                                if (product instanceof Milk){
                                    if (product.getName().toLowerCase().contains(findName.toLowerCase())){
                                        System.out.println(product.getName() + " " + product.getPurchaseDate().get(Calendar.YEAR) + " " + (product.getPurchaseDate().get(Calendar.MONTH) + 1) + " " + product.getPurchaseDate().get(Calendar.DATE) + " " + product.getPrice() + " " + product.getAddress() + " " + qualities[product.getQuality()] + " " + ((Milk) product).getFatContent());
                                    }
                                }
                            }
                        }else {
                            System.out.println("Список пуст");
                        }
                    }
                    break;
                case 4: // Узнать информацию о конкретном товаре
                    Menu.chooseFindCharacteristics(products);
                    break;
                case 5: // Выход
                    System.out.println("Производится выход из программы");
                    break;
                default:
                    System.out.println("Что-то не то");
                    break;
            }
        }*/
    }
}