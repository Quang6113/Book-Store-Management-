/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsm.entities;

import java.util.HashMap;

/**
 *
 * @author QUANG
 */
public class Book {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String status;
    private String pId;

    public Book() {
    }

    public Book(String id, String name, double price, int quantity,
            String status, String pId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.pId = pId;
    }

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String PID) {
        this.pId = PID;
    }

    public double SubTotal(double price, int quantity) {
        double SubTotal = price * quantity;
        return SubTotal;
    }

    public void display(HashMap<String, String> PIdMap) {
        System.out.printf("|%6s|", id);
        System.out.printf("%30s|", name);
        System.out.printf("%12.2f|", price);
        System.out.printf("%10d|", quantity);
        System.out.printf("%20.2f|", SubTotal(price, quantity));
        System.out.printf("%30s|", PIdMap.get(pId));
        System.out.printf("%13s|\n", status);
        System.out.println("+------+------------------------------+------------+-"
                + "---------+--------------------+------------------------------"
                + "+-------------+");
    }
}
