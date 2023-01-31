/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsm.services;

import bsm.entities.Book;
import bsm.entities.BookList;
import bsm.entities.Publisher;
import bsm.utils.Validation;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author QUANG
 */
public class BookServices {

    private static final BookServices bs = new BookServices();
    private final BookList bl = new BookList();
    private final File f = new File("book.dat");
    String name = "";
    String id = "";

    private BookServices() {
        bl.loadFromFile(f);
    }
    
    public static BookServices getInstance() {
        return bs;
    }

    public BookList getList() {
        return bl;
    }

    public void create(List<Publisher> publisherList) {
        System.out.println("\n----CREATE A BOOK----");
        if (publisherList.isEmpty()) {    
            System.out.println("There is not any publisher, please enter  some "
                    + "publishers before create books");
            System.out.println("Fail!");
            return;
        }

        if (bl.addBook(bl.createBook(publisherList))) {
            System.out.println("Success!");
        }else{
            System.out.println("Fail!");
        }
    }

    public void search(List<Publisher> publisherList, HashMap<String, String> pIdMap) {
        System.out.println("\n----SEARCH A BOOK----");
        if (Validation.checkPublisherListIsEmpty(publisherList)) {
            System.out.println("Fail!");
            return;
        }

        System.out.println("1. Search by Publisher's ID");
        System.out.println("2. Search by a part of Book's name");
        System.out.print("Your choice: ");
        int choice = Validation.getUserChoice(1, 2);
        
        //search by publisher's ID            
        if (choice == 1) {
            System.out.print("Enter Publisher's ID: ");
            List<Book> result = bl.searchBookByPublisherId(
                    id = Validation.getInput(id));
            
            if(result != null){
                BookList bookList = new BookList(result);
                bookList.displayBook(pIdMap);
            }
            else{
                System.out.println("Have no any Book");
            }
            
        } 

        //search by name
        else {
            System.out.print("Enter a part of Book's name: ");           
            List<Book> result = bl.searchBookByName(
                    name = Validation.getInput(name));
            
            if(result != null){
                BookList bookList = new BookList(result);
                bookList.displayBook(pIdMap);
            }
            else{
                System.out.println("Have no any Book");
            }
        }
    }

    public void update(List<Publisher> publisherList, List<Book> bookList) {
        System.out.println("\n----UPDATE A BOOK----");
        if (Validation.checkBookListIsEmpty(bookList)) {
            System.out.println("Fail!");
            return;
        }

        System.out.print("Enter Book's ID: ");
        if (bl.updateBook(publisherList, bl.searchBookById(
                id = Validation.getInput(id)))) {
            System.out.println("Success!");
        } else {
            System.out.println("Fail!");
        }

    }

    public void delete(List<Publisher> publisherList, List<Book> bookList) {
        System.out.println("----DELETE A BOOK----");
        if (Validation.checkBookListIsEmpty(bookList)) {
            System.out.println("Fail!");
            return;
        }
        
        System.out.print("Enter Book's ID: ");
        if(bl.deleteBook(bl.searchBookById(id = Validation.getInput(id)))){
            System.out.println("Success!");
        }else{
            System.out.println("Fail!");
        }
    }

    public void saveTheBookList() {
        System.out.println("\n----SAVE THE BOOKS LIST TO FILE----");
        System.out.println("File name: " + f.getName());
        System.out.println("File location: " + f.getAbsolutePath());
        if (bl.saveToFile(f)) {
            System.out.println("Success");
        }
        else{
            System.out.println("Fail");
        }
    }

    public void printTheBookListFromFile(HashMap<String, String> pIdMap) {
        System.out.println("\n----PRINT THE BOOKS LIST FROM FILE----");
        BookList tmpBookList = new BookList();
        if (!tmpBookList.loadFromFile(f)) {
            System.out.println("Fail");
            return;
        }
        tmpBookList.displayBook(pIdMap);
    }

}
