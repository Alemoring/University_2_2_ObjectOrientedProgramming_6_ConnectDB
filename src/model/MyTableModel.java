package model;

import domain.entities.*;
import view.MainForm;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class MyTableModel extends AbstractTableModel {
    private int sizeRow;
    private ArrayList<Product> products;
    private String[] qualities = new String[3];
    public MyTableModel(ArrayList<Product> pr){
        this.products = pr;
        qualities[0] = "Good";
        qualities[1] = "Normal";
        qualities[2] = "Bad";
        if (products == null){
            sizeRow = 1;
        }else {
            sizeRow = products.size();
        }
    }
    @Override
    public int getRowCount() {
        return sizeRow;
    }
    @Override
    public int getColumnCount() {
        return 8;
    }
    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Num";
            case 1:
                return "Type";
            case 2:
                return "Name";
            case 3:
                return "Date";
            case 4:
                return "Price";
            case 5:
                return "Address";
            case 6:
                return "Quality";
            case 7:
                return "Dops";
            default:
                return "default";
        }
    }
    @Override
    public Object getValueAt(int row, int column) {
        if (products.isEmpty() || row >= products.size()){
            if (column == 0){
                return row + 1;
            }
            return "-";
        }
        switch (column){
            case 0:
                return row + 1;
            case 1:
                switch (products.get(row).getId()){
                    case 1:
                        return "Food";
                    case 2:
                        return "Clothes";
                    case 3:
                        return "Technic";
                    case 4:
                        return "Milk";
                }
            case 2:
                return products.get(row).getName();
            case 3:
                return products.get(row).getPurchaseDate().get(Calendar.YEAR) + " " + (products.get(row).getPurchaseDate().get(Calendar.MONTH) + 1) + " " + products.get(row).getPurchaseDate().get(Calendar.DATE);
            case 4:
                return products.get(row).getPrice();
            case 5:
                return products.get(row).getAddress();
            case 6:
                return qualities[products.get(row).getQuality()];
            case 7:
                String result = "";
                switch (products.get(row).getId()) {
                    case 1:
                        if (((Food) products.get(row)).getStorageLife().get(Calendar.YEAR) == 1950) {
                            result += "";
                        } else {
                            result += "Storage Life: " + ((Food) products.get(row)).getStorageLife().get(Calendar.YEAR) + " " + ((Food) products.get(row)).getStorageLife().get(Calendar.MONTH) + " " + ((Food) products.get(row)).getStorageLife().get(Calendar.DATE);
                        }
                        if (!Objects.equals(((Food) products.get(row)).getComment(), "0")){
                            result += "Comment: " + ((Food) products.get(row)).getComment();
                        }
                        return result;
                    case 2:
                        if (!Objects.equals(((Clothes) products.get(row)).getComment(), "0")){
                            return ((Clothes) products.get(row)).getComment();
                        }
                        return "Nope";
                    case 3:
                        if (((Technic) products.get(row)).getGuarantee() == -1) {
                            result += "";
                        } else {
                            result += "Guarantee: " + ((Technic) products.get(row)).getGuarantee() + " months";
                        }
                        if (!Objects.equals(((Technic) products.get(row)).getComment(), "0")){
                            result += "Comment" + ((Technic) products.get(row)).getComment();
                        }
                        return result;
                    case 4:
                        if (((Milk) products.get(row)).getStorageLife().get(Calendar.YEAR) == 1950) {
                            result += "";
                        } else {
                            result += "Storage life: " + ((Milk) products.get(row)).getStorageLife().get(Calendar.YEAR) + " " + ((Milk) products.get(row)).getStorageLife().get(Calendar.MONTH) + " " + ((Milk) products.get(row)).getStorageLife().get(Calendar.DATE);
                        }
                        result += "Fat content: " + ((Milk) products.get(row)).getFatContent() + "%";
                        if (!Objects.equals(((Milk) products.get(row)).getComment(), "0")){
                            result += "Comment: " + ((Milk) products.get(row)).getComment();
                        }
                        return result;
                }
            default:
                return "-";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return true;
            case 5:
                return true;
            case 6:
                return true;
            case 7:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (rowIndex <= products.size()){
            switch (columnIndex){
                case 2: // Имя
                    try{
                        products.get(rowIndex).setName((String) aValue);
                    }catch (IndexOutOfBoundsException ex){
                        JOptionPane.showMessageDialog(null, "That wrong");
                    }
                    break;
                case 3: // Дата
                    try{
                        products.get(rowIndex).setPurchaseDate((String) aValue);
                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Wrong format");
                    }catch (IndexOutOfBoundsException ex){
                        JOptionPane.showMessageDialog(null, "That wrong");
                    }
                    break;
                case 4: // Цена
                    try{
                        String price = (String) aValue;
                        products.get(rowIndex).setPrice(Double.parseDouble(price));
                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Wrong format");
                    }catch (IndexOutOfBoundsException ex){
                        JOptionPane.showMessageDialog(null, "That wrong");
                    }
                    break;
                case 5: // адрес
                    try {
                        products.get(rowIndex).setAddress((String) aValue);
                    }catch (IndexOutOfBoundsException ex){
                        JOptionPane.showMessageDialog(null, "That wrong");
                    }
                    break;
                case 6: // Качество
                    try{
                        if ("Good".equals((String) aValue)){
                            products.get(rowIndex).setQuality(0);
                        }else if ("Normal".equals((String) aValue)){
                            products.get(rowIndex).setQuality(1);
                        }else if("Bad".equals((String) aValue)){
                            products.get(rowIndex).setQuality(2);
                        }else{
                            JOptionPane.showMessageDialog(null, "Wrong format \n Please entry 'Good' or 'Normal' or 'Bad'");
                        }
                    }catch (IndexOutOfBoundsException ex){
                        JOptionPane.showMessageDialog(null, "That wrong");
                    }
                    break;
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public void changeData(ArrayList<Product> products) {
        this.products = products;
        if (products == null){
            sizeRow = 1;
        }else {
            sizeRow = products.size();
        }
        this.fireTableDataChanged();
    }

    public void addRow() {
        sizeRow += 1;
        this.getRowCount();
        this.fireTableDataChanged();
    }

    public void deleteRow(int selectedRow) {
        this.products.remove(selectedRow);
        sizeRow -= 1;
        this.fireTableDataChanged();
    }
    public ArrayList<Product> getData(){
        return products;
    }
}