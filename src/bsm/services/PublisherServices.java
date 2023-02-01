/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsm.services;

import bsm.entities.Publisher;
import bsm.entities.PublisherList;
import java.io.File;
import java.util.List;

/**
 *
 * @author QUANG
 */
public class PublisherServices {

    private static final PublisherServices ps = new PublisherServices();
    private final PublisherList pl = new PublisherList();
    private final File f = new File("publisher.dat");

    private PublisherServices() {
        pl.loadFromFile(f);
    }

    public static PublisherServices getInstance() {
        return ps;
    }

    public PublisherList getList() {
        return pl;
    }

    public void create() {
        System.out.println("\n---CREATE A PUBLISHER---");
        if (pl.addPublisher(pl.createPublisher())) {
            System.out.println("Success!");
        } else {
            System.out.println("Fail!");
        }
    }

    public void delete(List<Publisher> publisherList) {
        System.out.println("\n---DELETE PUBLISHER INFORMATION---");

        System.out.println("Valid publisher");
        publisherList.forEach((e) -> {
            System.out.println(e.getId() + " - " + e.getName());
        });

        if (pl.deletePublisher(pl.searchById(publisherList))) {
            System.out.println("Success!");
        } else {
            System.out.println("Fail!");
        }
    }

    public void saveThePublisherList() {
        System.out.println("\n---SAVE THE PUBLISHERS LIST---");
        if (pl.saveToFile(f)) {
            System.out.println("File name: " + f.getName());
            System.out.println("File location: " + f.getAbsolutePath());
        } else {
            System.out.println("Fail!");
        }
    }

    public void printThePublisherListFromFiles() {
        System.out.println("\n---PRINT THE PUBLISHERS LIST FROM FILE---");
        PublisherList tmpPublisherList = new PublisherList();
        if (!tmpPublisherList.loadFromFile(f)) {
            System.out.println("Cannot load!");
            return;
        }

        // sort & display
        tmpPublisherList.displayPublisher();
    }
}
