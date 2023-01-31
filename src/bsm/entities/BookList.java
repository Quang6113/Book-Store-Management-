/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsm.entities;

import bsm.utils.Validation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author QUANG
 */

public class BookList {

    private final List<Book> bookList;
    private String id = "";
    private String pId = "";
    private String name = "";
    private double price = 0;
    private int quantity = 0;
    private String status = "";
    
    public BookList() {
        bookList = new ArrayList<Book>();
    }

    public BookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public Book createBook(List<Publisher> publisherList) {
        //pId
        System.out.println("Make sure the publisher is created before adding "
                + "this book!");
        
        System.out.println("Valid publisher");
            publisherList.forEach((e) -> {
                System.out.println(e.getId() + " - " + e.getName());
            });
            
        System.out.print("\nPublisher's ID (Pxxxxx): ");
        while (!Validation.checkPId(pId = Validation.getInput(pId))) {
            System.err.println("Invalid!");
            System.out.print("Enter again: ");
        }
        if (Validation.checkPIdNoneMatch(pId, publisherList)) {
            System.err.println("Publisher's Id is not found");
        } //noneMatch true, return null
        else {
            //Id
            System.out.print("Book's ID (Bxxxxx): ");
            while (!(Validation.checkBIdNoneMatch((id = Validation.getInput(id)), bookList))
                    && Validation.checkBId(id)) {
                System.err.println("Invalid!");
                System.out.print("Enter again: ");
            }

            //Name
            System.out.print("Name (5-30 characters): ");
            while (!Validation.checkName(name = Validation.getInput(name))) {
                System.err.println("Invalid!");
                System.out.print("Enter again: ");
            }

            //Price
            System.out.print("Price (greater than 0): ");
            while (!Validation.checkPrice(price = Validation.getInput(price))) {
                System.err.println("Invalid!");
                System.out.print("Enter again: ");
            }

            //Quantity
            System.out.print("Quantity (greater than 0): ");
            while (!Validation.checkPrice(quantity = Validation.getInput(quantity))) {
                System.err.println("Invalid!");
                System.out.print("Enter again: ");
            }

            //Status
            System.out.print("Status (1. Available, 2. Not Available): ");
            int choice = Validation.getUserChoice(1, 2);
            if (choice == 1) {
                status = "Available";
            } else {
                status = "Not Available";
            }

            return new Book(id, name, price, quantity, status, pId);
        }
        return null;
    }

    public boolean addBook(Book b) {
        if (b != null) {
            bookList.add(b);
            return true;
        }
        return false;
    }

    public Book searchBookById(String id) {
        while (!Validation.checkBId(id)) {
            System.out.println("Invalid!");
            System.out.println("Enter gain: ");
        }

        for (Book x : bookList) {
            if (x.getId().equals(id)) {
                System.out.println("---" + x.getId() + " - " 
                        + x.getName()+ "---\n");
                return x;
            }
        }
        System.out.println("Book's Name does not exist");
        return null;
    }

    public List<Book> searchBookByPublisherId(String id) {   
        while (!Validation.checkPId(id)) {
            System.out.println("Invalid!");
            System.out.println("Enter gain: ");
        }

        List<Book> result = new ArrayList<>();
        for (Book x : bookList) {
            if (x.getPId().equals(id)) {
                result.add(x);
            }
        }

        if (!result.isEmpty()) {
            result.sort((Book b1, Book b2) -> 
                    b1.getName().compareToIgnoreCase(b2.getName()));
            return result;
        }
        return null;
    }

    public List<Book> searchBookByName(String name) {
        List<Book> result = new ArrayList<>();
        for (Book x : bookList) {
            if (x.getName().toUpperCase().contains(name.toUpperCase())) {
                result.add(x);
            }
        }

        if (!result.isEmpty()) {
            result.sort((Book b1, Book b2) -> 
                    b1.getName().compareToIgnoreCase(b2.getName()));
            return result;
        }
        return null;
    }

    public boolean deleteBook(Book b) {
        if (b != null) {
            bookList.remove(b);
            return true;
        }
        return false;
    }

    public boolean updateBook(List<Publisher> publisherList, Book b) {
        if (b != null) {
            id = b.getId();
            name = b.getName();
            status = b.getStatus();
            pId = b.getPId();
            quantity = b.getQuantity();
            price = b.getPrice();

            System.out.println("Valid publisher");
            publisherList.forEach((e) -> {
                System.out.println(e.getId() + " - " + e.getName());
            });

            System.out.println("\nPlease create publisher before update this book!");
            System.out.print("Publisher's ID (Pxxxxx): ");
            while (!Validation.checkPId(pId = Validation.getUpdateInput(pId))) {
                System.err.println("Invalid!");
                System.out.print("Enter again: ");
            }
            
            if (Validation.checkPIdAnyMatch(id, publisherList)) {
                System.err.println("Please create publisher before update this book!");
            } 
            else {
                //Name
                System.out.print("Name (5-30 characters): ");
                while (!Validation.checkName(name = Validation.getUpdateInput(name))) {
                    System.err.println("Invalid!");
                    System.out.print("Enter again: ");
                }

                //Price
                System.out.print("Price (greater than 0): ");
                while (!Validation.checkPrice(price = Validation.getUpdateInput(price))) {
                    System.err.println("Invalid!");
                    System.out.print("Enter again: ");
                }

                //Quantity
                System.out.print("Quantity (greater than 0): ");
                while (!Validation.checkPrice(quantity = Validation.getUpdateInput(quantity))) {
                    System.err.println("Invalid!");
                    System.out.print("Enter again: ");
                }

                //Status
                System.out.print("Status (1. Available, 2. Not Available, 0. Not update): ");
                int choice = Validation.getUserChoice(0, 2);
                switch (choice) {
                    case 1:
                        status = "Available";
                        break;
                    case 2:
                        status = "Not availble";
                        break;
                    case 0:
                        status = b.getStatus();
                }
                
                b.setName(name);
                b.setPrice(price);
                b.setPId(pId);
                b.setQuantity(quantity);
                b.setStatus(status);
                
                return true;
            }
        }
        return false;
    }

    public void displayBook(HashMap<String, String> PIdMap) {
        System.out.println("+------+------------------------------+------------+--"
                + "--------+--------------------+------------------------------+-"
                + "------------+");
        System.out.printf("|%s%4s|", "Id", " ");
        System.out.printf("%s%26s|", "Name", " ");
        System.out.printf("%s%7s|", "Price", " ");
        System.out.printf("%s%2s|", "Quantity", " ");
        System.out.printf("%s%12s|", "Subtotal", " ");
        System.out.printf("%s%14s|", "Publisher's Name", " ");
        System.out.printf("%s%7s|\n", "Status", " ");
        System.out.println("+------+------------------------------+------------+--"
                + "--------+--------------------+------------------------------+-"
                + "------------+");
        bookList.sort((Book b1, Book b2) -> b1.getQuantity() - b2.getQuantity());

        bookList.forEach((e) -> {
            e.display(PIdMap);
        });
    }

    public boolean saveToFile(File f) {
        boolean append = false;

        try (FileWriter fw = new FileWriter(f, append);
                PrintWriter pw = new PrintWriter(fw)) {
            for (Book e : bookList) {
                pw.println(e.getId() + "," + e.getName() + "," + e.getPrice()
                        + "," + e.getQuantity() + "," + e.getStatus() + "," + e.getPId());
            }

            return true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    public boolean loadFromFile(File f) {
        if (!f.exists()) {
            return false;
        }

        try (FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr)) {
            String details;

            while ((details = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                bookList.add(new Book(stk.nextToken(), stk.nextToken(),
                        Double.parseDouble(stk.nextToken()), Integer.parseInt(stk.nextToken()),
                        stk.nextToken(), stk.nextToken()));
            }

            return true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }
}
