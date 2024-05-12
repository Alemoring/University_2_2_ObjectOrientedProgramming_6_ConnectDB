package data;
import domain.entities.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class ConnectionDB {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException
    {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:DB1.db");
        System.out.println("DataBase connected!");
    }

    // --------Заполнение таблицы--------
    public static void InsertInDB(Product product) throws SQLException
    {
        statement = connection.createStatement();
        switch (product.getId()){
            case 1: // food INSERT INTO Food (name, purchaseDate, price, address, quality) VALUES ('Test name insert', '2024-12-31', 24.5, 'Test address insert', 0)
                Food food = (Food) product;
                statement.execute("INSERT INTO Food (name, purchaseDate, price, address, quality, comment, storageLife) VALUES ('" + product.getName() + "', '" +
                        product.getPurchaseDate().get(Calendar.YEAR) + "-" + (product.getPurchaseDate().get(Calendar.MONTH) + 1) + "-" + product.getPurchaseDate().get(Calendar.DATE) +
                        "', " + product.getPrice() + ", '" + product.getAddress() + "', " + product.getQuality() + ", '" + product.getComment() +
                        "', '" + food.getStorageLife().get(Calendar.YEAR) + "-" + (food.getStorageLife().get(Calendar.MONTH) + 1) + "-" + food.getStorageLife().get(Calendar.DATE) + "'); ");
                break;
            case 2: // clothes
                statement.execute("INSERT INTO Clothes (name, purchaseDate, price, address, quality, comment) VALUES ('" + product.getName() + "', '" +
                        product.getPurchaseDate().get(Calendar.YEAR) + "-" + (product.getPurchaseDate().get(Calendar.MONTH) + 1) + "-" + product.getPurchaseDate().get(Calendar.DATE) +
                        "', " + product.getPrice() + ", '" + product.getAddress() + "', " + product.getQuality() + ", '" + product.getComment() +
                        "'); ");
                break;
            case 3: // technic
                Technic technic = (Technic) product;
                statement.execute("INSERT INTO Technic (name, purchaseDate, price, address, quality, comment,guarantee) VALUES ('" + technic.getName() + "', '" +
                        technic.getPurchaseDate().get(Calendar.YEAR) + "-" + (technic.getPurchaseDate().get(Calendar.MONTH) + 1) + "-" + technic.getPurchaseDate().get(Calendar.DATE) +
                        "', " + technic.getPrice() + ", '" + technic.getAddress() + "', " + technic.getQuality() + ", '" + technic.getComment() +
                        "', " + technic.getGuarantee() + "); ");
                break;
            case 4: // milk
                Milk milk = (Milk) product;
                statement.execute("INSERT INTO Milk (name, purchaseDate, price, address, quality, comment, storageLife, fatContent) VALUES ('" + milk.getName() + "', '" +
                        milk.getPurchaseDate().get(Calendar.YEAR) + "-" + (milk.getPurchaseDate().get(Calendar.MONTH) + 1) + "-" + milk.getPurchaseDate().get(Calendar.DATE) +
                        "', " + milk.getPrice() + ", '" + milk.getAddress() + "', " + milk.getQuality() + ", '" + milk.getComment() +
                        "', '" + milk.getStorageLife().get(Calendar.YEAR) + "-" + (milk.getStorageLife().get(Calendar.MONTH) + 1) + "-" + milk.getStorageLife().get(Calendar.DATE) + "', " +
                        milk.getFatContent() + "); ");
                break;
        }

        System.out.println("Insert Success");
    }

    public static void DeleteFromDB(Product product) throws SQLException{
        statement = connection.createStatement();
        switch (product.getId()){
            case 1:
                statement.execute("DELETE FROM Food WHERE name='" + product.getName() + "';");
                break;
            case 2:
                statement.execute("DELETE FROM Clothes WHERE name='" + product.getName() + "';");
                break;
            case 3:
                statement.execute("DELETE FROM Technic WHERE name='" + product.getName() + "';");
                break;
            case 4:
                statement.execute("DELETE FROM Milk WHERE name='" + product.getName() + "';");
                break;
        }
        System.out.println("Delete success");

    }

    public static void UpdateInDB(Product product, int columnIndex) throws SQLException{
        statement = connection.createStatement();
        switch (columnIndex){
            case 2: // Имя
                try{
                    switch (product.getId()){
                        case 1:
                            statement.execute("UPDATE Food SET name='" + product.getName() + "' WHERE address='" + product.getAddress() + "' AND price=" + product.getPrice() + ";");
                            break;
                        case 2:
                            statement.execute("UPDATE Clothes SET name='" + product.getName() + "' WHERE address='" + product.getAddress() + "' AND price=" + product.getPrice() + ";");
                            break;
                        case 3:
                            statement.execute("UPDATE Technic SET name='" + product.getName() + "' WHERE address='" + product.getAddress() + "' AND price=" + product.getPrice() + ";");
                            break;
                        case 4:
                            statement.execute("UPDATE Milk SET name='" + product.getName() + "' WHERE address='" + product.getAddress() + "' AND price=" + product.getPrice() + ";");
                            break;
                    }
                }catch (IndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(null, "That wrong");
                }
                break;
            case 3: // Дата
                try{
                    switch (product.getId()){
                        case 1:
                            statement.execute("UPDATE Food SET purchaseDate='" + product.getPurchaseDate().get(Calendar.YEAR) + "-" + (product.getPurchaseDate().get(Calendar.MONTH)) + "-" + product.getPurchaseDate().get(Calendar.DATE) + "' WHERE name='" + product.getName() + "';");
                            break;
                        case 2:
                            statement.execute("UPDATE Clothes SET purchaseDate='" + product.getPurchaseDate().get(Calendar.YEAR) + "-" + (product.getPurchaseDate().get(Calendar.MONTH)) + "-" + product.getPurchaseDate().get(Calendar.DATE) + "' WHERE name='" + product.getName() + "';");
                            break;
                        case 3:
                            statement.execute("UPDATE Technic SET purchaseDate='" + product.getPurchaseDate().get(Calendar.YEAR) + "-" + (product.getPurchaseDate().get(Calendar.MONTH)) + "-" + product.getPurchaseDate().get(Calendar.DATE) + "' WHERE name='" + product.getName() + "';");
                            break;
                        case 4:
                            statement.execute("UPDATE Milk SET purchaseDate='" + product.getPurchaseDate().get(Calendar.YEAR) + "-" + (product.getPurchaseDate().get(Calendar.MONTH)) + "-" + product.getPurchaseDate().get(Calendar.DATE) + "' WHERE name='" + product.getName() + "';");
                            break;
                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Wrong format");
                }catch (IndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(null, "That wrong");
                }
                break;
            case 4: // Цена
                try{
                    switch (product.getId()){
                        case 1:
                            statement.execute("UPDATE Food SET price='" + product.getPrice() + "' WHERE name='" + product.getName() + "';");
                            break;
                        case 2:
                            statement.execute("UPDATE Clothes SET price='" + product.getPrice() + "' WHERE name='" + product.getName() + "';");
                            break;
                        case 3:
                            statement.execute("UPDATE Technic SET price='" + product.getPrice() + "' WHERE name='" + product.getName() + "';");
                            break;
                        case 4:
                            statement.execute("UPDATE Milk SET price='" + product.getPrice() + "' WHERE name='" + product.getName() + "';");
                            break;
                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Wrong format");
                }catch (IndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(null, "That wrong");
                }
                break;
            case 5: // адрес
                try {
                    switch (product.getId()){
                        case 1:
                            statement.execute("UPDATE Food SET address='" + product.getAddress() + "' WHERE name='" + product.getName() + "';");
                            break;
                        case 2:
                            statement.execute("UPDATE Clothes SET address='" + product.getAddress() + "' WHERE name='" + product.getName() + "';");
                            break;
                        case 3:
                            statement.execute("UPDATE Technic SET address='" + product.getAddress() + "' WHERE name='" + product.getName() + "';");
                            break;
                        case 4:
                            statement.execute("UPDATE Milk SET address='" + product.getAddress() + "' WHERE name='" + product.getName() + "';");
                            break;
                    }
                }catch (IndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(null, "That wrong");
                }
                break;
            case 6: // Качество
                try{
                    switch (product.getId()){
                        case 1:
                            statement.execute("UPDATE Food SET name='" + product.getPrice() + "' WHERE address='" + product.getAddress() + "';");
                            break;
                        case 2:
                            statement.execute("UPDATE Clothes SET name='" + product.getPrice() + "' WHERE address='" + product.getAddress() + "';");
                            break;
                        case 3:
                            statement.execute("UPDATE Technic SET name='" + product.getPrice() + "' WHERE address='" + product.getAddress() + "';");
                            break;
                        case 4:
                            statement.execute("UPDATE Milk SET name='" + product.getPrice() + "' WHERE address='" + product.getAddress() + "';");
                            break;
                    }
                }catch (IndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(null, "That wrong");
                }
                break;
        }
        System.out.println("Update success");

    }
    // -------- Вывод таблицы--------
    public static ArrayList<Product> ReadDB() throws ClassNotFoundException, SQLException
    {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM Food");
        ArrayList<Product> products = new ArrayList<>();
        while(resultSet.next())
        {
            String  name = resultSet.getString("name");
            String DBpurchaseDate = resultSet.getString("purchaseDate");
            String[] splitDates = DBpurchaseDate.split("-");
            int year = Integer.parseInt(splitDates[0]);
            int month = Integer.parseInt(splitDates[1]);
            int day = Integer.parseInt(splitDates[2]);
            Calendar purchaseDate = Calendar.getInstance();
            purchaseDate.set(year, month - 1, day);
            double price = resultSet.getDouble("price");
            String address = resultSet.getString("address");
            int quality = resultSet.getInt("quality");
            String comment = resultSet.getString("comment");
            String DBstorageLife = resultSet.getString("storageLife");
            String[] splitLife = DBstorageLife.split("-");
            int yearLife = Integer.parseInt(splitLife[0]);
            int monthLife = Integer.parseInt(splitLife[1]);
            int dayLife = Integer.parseInt(splitLife[2]);
            Calendar storageLife = Calendar.getInstance();
            storageLife.set(yearLife, monthLife - 1, dayLife);
            products.add(new Food(name, purchaseDate, price, address, (short)quality, comment, storageLife));
        }
        resultSet = statement.executeQuery("SELECT * FROM Milk");
        while(resultSet.next())
        {
            String  name = resultSet.getString("name");
            String DBpurchaseDate = resultSet.getString("purchaseDate");
            String[] splitDates = DBpurchaseDate.split("-");
            int year = Integer.parseInt(splitDates[0]);
            int month = Integer.parseInt(splitDates[1]);
            int day = Integer.parseInt(splitDates[2]);
            Calendar purchaseDate = Calendar.getInstance();
            purchaseDate.set(year, month - 1, day);
            double price = resultSet.getDouble("price");
            String address = resultSet.getString("address");
            int quality = resultSet.getInt("quality");
            String comment = resultSet.getString("comment");
            String DBstorageLife = resultSet.getString("storageLife");
            String[] splitLife = DBstorageLife.split("-");
            int yearLife = Integer.parseInt(splitLife[0]);
            int monthLife = Integer.parseInt(splitLife[1]);
            int dayLife = Integer.parseInt(splitLife[2]);
            Calendar storageLife = Calendar.getInstance();
            storageLife.set(yearLife, monthLife - 1, dayLife);
            double fatContent = resultSet.getDouble("fatContent");
            products.add(new Milk(name, purchaseDate, price, address, (short)quality, comment, storageLife, fatContent));
        }
        resultSet = statement.executeQuery("SELECT * FROM Clothes");
        while(resultSet.next())
        {
            String  name = resultSet.getString("name");
            String DBpurchaseDate = resultSet.getString("purchaseDate");
            String[] splitDates = DBpurchaseDate.split("-");
            int year = Integer.parseInt(splitDates[0]);
            int month = Integer.parseInt(splitDates[1]);
            int day = Integer.parseInt(splitDates[2]);
            Calendar purchaseDate = Calendar.getInstance();
            purchaseDate.set(year, month - 1, day);
            double price = resultSet.getDouble("price");
            String address = resultSet.getString("address");
            int quality = resultSet.getInt("quality");
            String comment = resultSet.getString("comment");
            products.add(new Clothes(name, purchaseDate, price, address, (short)quality, comment));
        }
        resultSet = statement.executeQuery("SELECT * FROM Technic");
        while(resultSet.next())
        {
            String  name = resultSet.getString("name");
            String DBpurchaseDate = resultSet.getString("purchaseDate");
            String[] splitDates = DBpurchaseDate.split("-");
            int year = Integer.parseInt(splitDates[0]);
            int month = Integer.parseInt(splitDates[1]);
            int day = Integer.parseInt(splitDates[2]);
            Calendar purchaseDate = Calendar.getInstance();
            purchaseDate.set(year, month - 1, day);
            double price = resultSet.getDouble("price");
            String address = resultSet.getString("address");
            int quality = resultSet.getInt("quality");
            String comment = resultSet.getString("comment");
            int guarantee = resultSet.getInt("guarantee");
            products.add(new Technic(name, purchaseDate, price, address, (short)quality, comment, guarantee));
        }
        System.out.println("DataBase opened");
        return products;
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        connection.close();
        statement.close();
        resultSet.close();
        System.out.println("DataBase connection closed");
    }
}
