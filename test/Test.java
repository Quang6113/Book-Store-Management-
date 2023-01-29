
import bsm.entities.Book;
import bsm.entities.Publisher;
import bsm.services.BookServices;
import bsm.services.PublisherServices;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author QUANG
 */
public class Test {

    public static void main(String[] args) {
        PublisherServices ps = PublisherServices.getInstance();
        BookServices bs = BookServices.getInstance();
        
        Publisher p = new Publisher("P00001", "quang", "0123456789");
        ps.getList().addPublisher(p);
        p = new Publisher("P00002", "luan", "0123456789");
        ps.getList().addPublisher(p);
        p = new Publisher("P00003", "an", "0123456789");
        ps.getList().addPublisher(p);
        ps.getList().displayPublisher();

        Book b = new Book("B00001", "Learn to learn", 100000, 1000, "Not Available", "P00001");
        bs.getList().addBook(b);
        b = new Book("B00002", "Design Pattern", 200000, 100, " Available", "P00002");
        bs.getList().addBook(b);
        b = new Book("B00003", "Anata no namae", 70000, 2000, " Available", "P00003");
        bs.getList().addBook(b);
        b = new Book("B00004", "How to fly", 50000, 200, " Available", "P00002");
        bs.getList().addBook(b);
        bs.getList().displayBook(ps.getList().getPIdMap());
        
        bs.search(ps.getList().getPublisherList(), ps.getList().getPIdMap());
        bs.search(ps.getList().getPublisherList(), ps.getList().getPIdMap());
        //name, null
        
    }
}
