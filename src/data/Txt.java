package data;

import domain.entities.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Txt {
    private static File file;
    private static String name;

    public static ArrayList<Product> readProductsFromFile() {
        try {
            if(file.length() != 0){
                ArrayList<Product> products = new ArrayList<>();
                BufferedReader reader;
                String line;
                reader = new BufferedReader(new FileReader(file));
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.trim().split(" ");
                    int id = Integer.parseInt(values[0]);
                    String productName = values[1].replace("_", " ");
                    Calendar purchaseDate = Calendar.getInstance();
                    purchaseDate.set(Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]));
                    double price = Double.parseDouble(values[5]);
                    String address = values[6].replace("_", " ");
                    short quality = Short.parseShort(values[7]);
                    String comment = values[8].replace("_", " ");
                    switch (id){
                        case 1: // Food
                            Calendar storageLife = Calendar.getInstance();
                            storageLife.set(Integer.parseInt(values[9]), Integer.parseInt(values[10]), Integer.parseInt(values[11]));
                            Food food = new Food(productName, purchaseDate, price, address, quality, storageLife);
                            products.add(food);
                            break;
                        case 2: // Clothes
                            Clothes cloth = new Clothes(productName, purchaseDate, price, address, quality);
                            products.add(cloth);
                            break;
                        case 3: // Technic
                            int guarantee = Integer.parseInt(values[9]);
                            Technic technic = new Technic(productName, purchaseDate, price, address, quality, guarantee);
                            products.add(technic);
                            break;
                        case 4: // Milk
                            Calendar mStorageLife = Calendar.getInstance();
                            mStorageLife.set(Integer.parseInt(values[9]), Integer.parseInt(values[10]), Integer.parseInt(values[11]));
                            double fatContent = Double.parseDouble(values[12]);
                            Milk milk = new Milk(productName, purchaseDate, price, address, quality, mStorageLife, fatContent);
                            products.add(milk);
                            break;
                    }
                    i++;
                }
                reader.close();
                return products;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Такого файла не существует");
            return null;
        }
        catch (IOException e){
            System.out.println("Что-то пошло не так");
            return null;
        }
        return null;
    }
    public static void writeArrayToFile(ArrayList<Product> products){
        try{
            FileWriter writer = new FileWriter(name);
            for (Product product : products) {
                switch (product.getId()){
                    case 1: // Food
                        Food food = (Food) product;
                        writer.write(product.getId() + " " + food.getName().replace(" ", "_") + " " + food.getPurchaseDate().get(Calendar.YEAR) + " " + food.getPurchaseDate().get(Calendar.MONTH) + " " + food.getPurchaseDate().get(Calendar.DATE) + " " + food.getPrice() + " " + food.getAddress().replace(" ", "_") + " " + food.getQuality() + " " + food.getComment() + " " + food.getStorageLife().get(Calendar.YEAR) + " " + food.getStorageLife().get(Calendar.MONTH) + " " + food.getStorageLife().get(Calendar.DATE) + "\n");
                        break;
                    case 2: // Clothes
                        writer.write(product.getId() + " " + product.getName().replace(" ", "_") + " " + product.getPurchaseDate().get(Calendar.YEAR) + " " + product.getPurchaseDate().get(Calendar.MONTH) + " " + product.getPurchaseDate().get(Calendar.DATE) + " " + product.getPrice() + " " + product.getAddress().replace(" ", "_") + " " + product.getQuality() + " " + product.getComment() + "\n");
                        break;
                    case 3: // Technic
                        Technic technic = (Technic) product;
                        writer.write(technic.getId() + " " + technic.getName().replace(" ", "_") + " " + technic.getPurchaseDate().get(Calendar.YEAR) + " " + technic.getPurchaseDate().get(Calendar.MONTH) + " " + technic.getPurchaseDate().get(Calendar.DATE) + " " + technic.getPrice() + " " + technic.getAddress().replace(" ", "_") + " " + technic.getQuality() + " " + technic.getComment() + " " + technic.getGuarantee() + "\n");
                        System.out.println("Данные успешно добавлены");
                        break;
                    case 4: // Milk
                        Milk milk = (Milk) product;
                        writer.write(milk.getId() + " " + milk.getName().replace(" ", "_") + " " + milk.getPurchaseDate().get(Calendar.YEAR) + " " + milk.getPurchaseDate().get(Calendar.MONTH) + " " + milk.getPurchaseDate().get(Calendar.DATE) + " " + milk.getPrice() + " " + milk.getAddress().replace(" ", "_") + " " + milk.getQuality() + " " + milk.getComment() + " " +  milk.getStorageLife().get(Calendar.YEAR) + " " + milk.getStorageLife().get(Calendar.MONTH) + " " + milk.getStorageLife().get(Calendar.DATE) + milk.getFatContent() + " " + '\n');
                        break;

                }
            }
            writer.close();
            System.out.println("Данные успешно добавлены");
        }catch (FileNotFoundException e){
            System.out.println("Такого файла не существует");
        }
        catch (IOException e){
            System.out.println("Что-то пошло не так");
        }
    }
    public static void writeProductToFile(Product product) {
        try {
            FileWriter writer = new FileWriter(name, true);
            writer.write(product.getId() + " " + product.getName().replace(" ", "_") + " " + product.getPurchaseDate().get(Calendar.YEAR) + " " + product.getPurchaseDate().get(Calendar.MONTH) + " " + product.getPurchaseDate().get(Calendar.DATE) + " " + product.getPrice() + " " + product.getAddress().replace(" ", "_") + " " + product.getQuality() + " " + product.getComment() + "\n");
            writer.close();
            System.out.println("Данные успешно добавлены");
        }
        catch (FileNotFoundException e){
            System.out.println("Такого файла не существует");
        }
        catch (IOException e){
            System.out.println("Что-то пошло не так");
        }
    }
    public static void writeFoodToFile(Food food) {
        try {
            FileWriter writer = new FileWriter(name, true);
            writer.write(food.getId() + " " + food.getName().replace(" ", "_") + " " + food.getPurchaseDate().get(Calendar.YEAR) + " " + food.getPurchaseDate().get(Calendar.MONTH) + " " + food.getPurchaseDate().get(Calendar.DATE) + " " + food.getPrice() + " " + food.getAddress().replace(" ", "_") + " " + food.getQuality() + " " + food.getComment() + " " + food.getStorageLife().get(Calendar.YEAR) + " " + food.getStorageLife().get(Calendar.MONTH) + " " + food.getStorageLife().get(Calendar.DATE) + "\n");
            writer.close();
            System.out.println("Данные успешно добавлены");
        }
        catch (FileNotFoundException e){
            System.out.println("Такого файла не существует");
        }
        catch (IOException e){
            System.out.println("Что-то пошло не так");
        }
    }
    public static void writeMilkToFile(Milk milk, boolean isStorageLife) {
        try {
            FileWriter writer = new FileWriter(name, true);
            if(isStorageLife){
                writer.write(milk.getId() + " " + milk.getName().replace(" ", "_") + " " + milk.getPurchaseDate().get(Calendar.YEAR) + " " + milk.getPurchaseDate().get(Calendar.MONTH) + " " + milk.getPurchaseDate().get(Calendar.DATE) + " " + milk.getPrice() + " " + milk.getAddress().replace(" ", "_") + " " + milk.getQuality() + " " + milk.getComment() + " " + milk.getFatContent() + " " + milk.getStorageLife().get(Calendar.YEAR) + " " + milk.getStorageLife().get(Calendar.MONTH) + " " + milk.getStorageLife().get(Calendar.DATE) + '\n');
            }else{
                writer.write(milk.getId() + " " + milk.getName().replace(" ", "_") + " " + milk.getPurchaseDate().get(Calendar.YEAR) + " " + milk.getPurchaseDate().get(Calendar.MONTH) + " " + milk.getPurchaseDate().get(Calendar.DATE) + " " + milk.getPrice() + " " + milk.getAddress().replace(" ", "_") + " " + milk.getQuality() + " " + milk.getComment() + " " + milk.getFatContent() + "\n");
            }
            writer.close();
            System.out.println("Данные успешно добавлены");
        }
        catch (FileNotFoundException e){
            System.out.println("Такого файла не существует");
        }
        catch (IOException e){
            System.out.println("Что-то пошло не так");
        }
    }
    public static void writeTechnicToFile(Technic technic) {
        try {
            FileWriter writer = new FileWriter(name, true);
            writer.write(technic.getId() + " " + technic.getName().replace(" ", "_") + " " + technic.getPurchaseDate().get(Calendar.YEAR) + " " + technic.getPurchaseDate().get(Calendar.MONTH) + " " + technic.getPurchaseDate().get(Calendar.DATE) + " " + technic.getPrice() + " " + technic.getAddress().replace(" ", "_") + " " + technic.getQuality() + " " + technic.getComment() + " " + technic.getGuarantee() + "\n");
            writer.close();
            System.out.println("Данные успешно добавлены");
        }
        catch (FileNotFoundException e){
            System.out.println("Такого файла не существует");
        }
        catch (IOException e){
            System.out.println("Что-то пошло не так");
        }
    }

    public static boolean setFilename(String name){
        Pattern pattern = Pattern.compile(".txt");
        Matcher matcher = pattern.matcher(name);
        if(!matcher.find()){
            name += ".txt";
        }
        file = new File(name);
        Txt.name = name;
        try {
            if (file.exists()){
                System.out.println("File exist");
            } else{
                file.createNewFile();
                System.out.println("file create");
            }
            return true;
        }catch (IOException e){
            System.out.println("Fail with create the file " + e);
            return false;
        }
    }
    public static boolean setFilename(File name, String dir){
        file = new File(dir, name.getName());
        Txt.name = String.valueOf(name);
        try {
            if (file.exists()){
                System.out.println("File exist");
            } else{
                file.createNewFile();
                System.out.println("file create");
            }
            return true;
        }catch (IOException e){
            System.out.println("Fail with create the file " + e);
            return false;
        }
    }
    public static boolean nullFileOrNotNull(){
        if (file.length() == 0){
            System.out.println("File is empty");
            return false;
        }
        else {
            System.out.println("File has the data");
            return true;
        }
    }
}