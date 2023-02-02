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
import java.util.stream.Collectors;

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
        // pId
        System.out.println("Make sure the publisher is created before adding "
                + "this book!");

        System.out.println("\n--Created publishers--");
        publisherList.forEach((e) -> {
            System.out.println(e.getId() + " - " + e.getName());
        });

        System.out.print("\nPublisher's ID (Pxxxxx): ");
        while (!Validation.checkPId(pId = Validation.getInput(pId))) {
            System.err.println("Wrong format!");
            System.out.print("Enter again: ");
        }
        if (!Validation.checkPIdAnyMatch(pId, publisherList)) {
            System.err.println("Publisher's Id is not found!");
        } // anyMatch false, return null
        else {
            // Id
            System.out.print("Book's ID (Bxxxxx): ");
            while (Validation.checkBIdAnyMatch((id = Validation.getInput(id)), bookList)
                    || !Validation.checkBId(id)) {
                if (Validation.checkBId(id) == false) {
                    System.err.println("Wrong format!");
                }

                if (Validation.checkBIdAnyMatch(id, bookList) == true) {
                    System.err.println("The Id has been used");
                }

                System.out.print("Enter again: ");
            }

            // Name
            System.out.print("Name (5-30 characters): ");
            while (!Validation.checkName(name = Validation.getInput(name))) {
                System.err.println("Wrong format!");
                System.out.print("Enter again: ");
            }
            name = name.substring(0,1).toUpperCase() 
            + name.substring(1, name.length());

            // Price
            System.out.print("Price (greater than 0): ");
            while (!Validation.checkPrice(price = Validation.getInput(price))) {
                System.err.println("Wrong format!");
                System.out.print("Enter again: ");
            }

            // Quantity
            System.out.print("Quantity (greater than 0): ");
            while (!Validation.checkQuantity(quantity = Validation.getInput(quantity))) {
                System.err.println("Wrong format!");
                System.out.print("Enter again: ");
            }

            // Status
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

    public Book searchBookById(List<Book> bookList) {
        // Id
        System.out.print("Enter Book's ID: ");
        while (!Validation.checkBId(id = Validation.getInput(id))) {
            System.out.println("Wrong format!");
            System.out.print("Enter again: ");
        }

        // search
        Book b = bookList.stream()
                .filter((x) -> x.getId().equals(id))
                .findAny()
                .orElse(null);

        // print
        if (b != null) {
            System.out.println("CURRENT BOOK: " + b.getId() + " - " + b.getName() + " - " + b.getPId());
        } else {
            System.out.println("Book's Name does not exist");
        }
        return b;
    }

    public List<Book> searchBookByPublisherId(List<Book> bookList, List<Publisher> publisherList) {
        // PId
        System.out.println("\n--Created publishers--");
        publisherList.forEach((e) -> {
            System.out.println(e.getId() + " - " + e.getName());
        });

        System.out.print("\nEnter Publisher's ID: ");
        while (!Validation.checkPId(id = Validation.getInput(id))) {
            System.out.println("Wrong format!");
            System.out.print("Enter again: ");
        }

        // list of search results
        List<Book> result = bookList.stream()
                .filter((x) -> (x.getPId().equalsIgnoreCase(id)))
                .collect(Collectors.toList());

        if (!result.isEmpty()) {
            return result;
        }
        return null;
    }

    public List<Book> searchBookByName(List<Book> bookList) {
        // Name
        System.out.print("\nEnter a part of Book's name: ");
        while ((name = Validation.getInput(name)).isEmpty()) {
            System.out.println("Wrong format!");
            System.out.print("Enter again: ");
        }

        // list of search results
        List<Book> result = bookList.stream()
                .filter((x) -> (x.getName().toUpperCase().contains(name.toUpperCase())))
                .collect(Collectors.toList());

        if (!result.isEmpty()) {
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
            // get old book
            id = b.getId();
            name = b.getName();
            status = b.getStatus();
            pId = b.getPId();
            quantity = b.getQuantity();
            price = b.getPrice();

            System.out.println("\n--Created publishers--");
            publisherList.forEach((e) -> {
                System.out.println(e.getId() + " - " + e.getName());
            });

            // Update
            System.out.println("\nPlease create publisher before update this book!");
            System.out.print("Publisher's ID (Pxxxxx): ");
            while (((pId = Validation.getUpdateInput(pId)) != b.getPId())
                    && !Validation.checkPId(pId)) {
                System.err.println("Wrong format!");
                System.out.print("Enter again: ");
            }

            if ((pId != b.getPId()) && !Validation.checkPIdAnyMatch(pId, publisherList)) {
                System.err.println("Publisher's Id is not found!");
            } else {
                // Id
                System.out.print("Book's ID (Bxxxxx): ");
                while (((id = Validation.getUpdateInput(id)) != b.getId())
                        && (Validation.checkBIdAnyMatch(id, bookList) || !Validation.checkBId(id))) {
                    if (Validation.checkBIdAnyMatch(id, bookList) == false) {
                        System.err.println("Id has been used");
                    }
                    if (Validation.checkBId(id) == false) {
                        System.err.println("Wrong format!");
                    }
                    System.out.print("Enter again: ");
                }

                // Name
                System.out.print("Name (5-30 characters): ");
                while (!Validation.checkName(name = Validation.getUpdateInput(name))) {
                    System.err.println("Wrong format!");
                    System.out.print("Enter again: ");
                }
                name = name.substring(0,1).toUpperCase() 
                + name.substring(1, name.length());

                // Price
                System.out.print("Price (greater than 0): ");
                while (!Validation.checkPrice(price = Validation.getUpdateInput(price))) {
                    System.err.println("Wrong format!");
                    System.out.print("Enter again: ");
                }

                // Quantity
                System.out.print("Quantity (greater than 0): ");
                while (!Validation.checkPrice(quantity = Validation.getUpdateInput(quantity))) {
                    System.err.println("Wrong format!");
                    System.out.print("Enter again: ");
                }

                // Status
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

                // set new book
                b.setId(id);
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

        bookList.forEach((e) -> {
            e.display(PIdMap);
        });
    }

    public boolean saveToFile(File f) {
        boolean append = false;

        try (FileWriter fw = new FileWriter(f, append);
                PrintWriter pw = new PrintWriter(fw)) {
            bookList.forEach(x -> {
                pw.println(x.getId() + "," + x.getName() + "," + x.getPrice()
                        + "," + x.getQuantity() + "," + x.getStatus() + "," + x.getPId());
            });

            return true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    public boolean loadFromFile(File f) {
        if (!f.exists() || f.length() == 0) {
            return false;
        }

        try (FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr)) {
            String line;

            while ((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
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
