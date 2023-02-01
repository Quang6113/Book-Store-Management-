/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsm.entities;

import java.util.ArrayList;
import bsm.utils.Validation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author QUANG
 */
public class PublisherList {

    private final List<Publisher> publisherList;
    private String id = "";
    private String name = "";
    private String phoneNum = "";

    public PublisherList() {
        publisherList = new ArrayList<>();
    }

    public PublisherList(List<Publisher> publisherList) {
        this.publisherList = publisherList;
    }

    public List<Publisher> getPublisherList() {
        return publisherList;
    }

    public boolean addPublisher(Publisher p) {
        publisherList.add(p);
        return true;
    }

    public HashMap<String, String> getPIdMap() {
        HashMap<String, String> pIdMap = new HashMap<>();
        publisherList.forEach((e) -> {
            pIdMap.put(e.getId(), e.getName());
        });
        return pIdMap;
    }

    public Publisher createPublisher() {

        // Id
        System.out.print("Publisher's ID (Pxxxxx): ");
        while (Validation.checkPIdAnyMatch((id = Validation.getInput(id)), publisherList)
                || !Validation.checkPId(id)) {
            if (Validation.checkPIdAnyMatch(id, publisherList) == true) {
                System.out.println("Id has been used");
            }

            if (Validation.checkPId(id) == false) {
                System.err.println("Wrong format!");
            }
            System.out.print("Enter again: ");
        }

        // Name
        System.out.println("Publisher's Name (5-30 characters): ");
        while (!Validation.checkName(name = Validation.getInput(name))) {
            System.err.println("Wrong format!");
            System.out.print("Enter again: ");
        }

        // Phone
        System.out.println("Phone number (10-12 digits): ");
        while (!Validation.checkPhone(phoneNum = Validation.getInput(phoneNum))) {
            System.err.println("Wrong format!");
            System.out.print("Enter again: ");
        }

        return new Publisher(id, name, phoneNum);
    }

    public Publisher searchById(List<Publisher> publisherList) {
        System.out.print("\nEnter Publisher's ID: ");
        while (!Validation.checkPId(id = Validation.getInput(id))) {
            System.out.println("Wrong format!");
            System.out.println("Enter gain: ");
        }

        Publisher p = publisherList.stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .orElse(null);

        if (p != null) {
            System.out.println("CURRENT PUBLISHER: " + p.getId() + " - " + p.getName());
        }

        else {
            System.out.println("Publisher's Id does not exist");
        }

        return p;
    }

    public boolean deletePublisher(Publisher p) {
        if (p != null) {
            return publisherList.remove(p);
        }
        return false;
    }

    public boolean saveToFile(File f) {

        boolean append = false;

        try (FileWriter fw = new FileWriter(f, append); // override: append
                PrintWriter pw = new PrintWriter(fw)) {
            publisherList.forEach((e) -> pw.println(e.getId() + "," + e.getName() + "," + e.getPhoneNum()));

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
            String line;

            while ((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                publisherList.add(new Publisher(stk.nextToken(), stk.nextToken(), stk.nextToken()));
            }

            return true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    public void displayPublisher() {
        System.out.println("+------+------------------------------+------------+");
        System.out.printf("|%s%4s|", "Id", " ");
        System.out.printf("%s%26s|", "Name", " ");
        System.out.printf("%s%7s|\n", "Phone", " ");
        System.out.println("+------+------------------------------+------------+");

        // sort by name

        publisherList.forEach((e) -> {
            e.display();
        });
    }
}
