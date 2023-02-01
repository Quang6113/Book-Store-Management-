/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsm.entities;

/**
 *
 * @author QUANG
 */
public class Publisher {
    public String id;
    private String name;
    private String phoneNum;

    public Publisher() {
    }

    public Publisher(String id, String name, String phoneNum) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void display() {
        System.out.printf("|%6s|", id);
        System.out.printf("%30s|", name);
        System.out.printf("%12s|\n", phoneNum);
        System.out.println("+------+------------------------------+------------+");
    }
}
