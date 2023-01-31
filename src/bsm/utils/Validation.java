/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsm.utils;

import bsm.services.BookServices;
import bsm.entities.Book;
import bsm.entities.Publisher;
import bsm.services.PublisherServices;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author QUANG
 */
public class Validation {
    private final static Scanner sc = new Scanner(System.in);

    public static boolean checkPhone(String phone) {
        return phone.matches("\\d{10,12}");
    }
    
    public static boolean checkPIdNoneMatch(String id, List<Publisher> publisherList) {
        return publisherList.stream().noneMatch((e) -> (e.getId().equals(id)));
    }

    public static boolean checkPIdAnyMatch(String id, List<Publisher> publisherList) {
        return publisherList.stream().anyMatch((e) -> (e.getId().equals(id)));
    }
    
    public static boolean checkPId(String id){
        return id.matches("P\\d{5}");
    }
    
    public static boolean checkBIdNoneMatch(String id, List<Book> bookList){
        return bookList.stream().noneMatch((e) -> (e.getId().equals(id)));
    }
    
    public static boolean checkBId(String id){
        return id.matches("B\\d{5}");
    }
    
    public static boolean checkName(String name){
        return name.length()>=5 && name.length()<=30;
    }
    
    public static boolean checkPrice(double price) {
        return (price > 0);
    }

    public static boolean checkQuantity(int quantity) {
        return (quantity > 0);
    }
    
    public static boolean checkPublisherListIsEmpty (List<Publisher> publisherList){
        if (publisherList.isEmpty()) {
            System.out.println("There is not any publisher, please enter some"
                    + "publishers");
            return true;
        }
        return false; 
    }
    
    public static boolean checkBookListIsEmpty (List<Book> bookList){
        if (bookList.isEmpty()) {
            System.out.println("Have no any Book");
            return true;
        }
        return false; 
    }
    
    public static byte getUserChoice(int firstOpt, int lastOpt) {
        while(true){
            try {
                byte userChoice = Byte.parseByte(sc.nextLine());
                if (userChoice < firstOpt || userChoice > lastOpt) {
                    throw new Exception();
                }
                return userChoice;
            } catch (Exception e) {
                System.out.print("Please choose the correct option above: ");
            }
        }
    }        
    
    public static <T> T getInput(T input) {
        boolean cont = true;
        do {
            try {
                String inputString = sc.nextLine();

                if (input instanceof Integer) {
                    Integer intOut = Integer.parseInt(inputString);
                    return (T) intOut;
                } else if (input instanceof Double) {
                    Double doubleOut = Double.parseDouble(inputString);
                    return (T) doubleOut;
                } else if (input instanceof String) {
                    String stringOut = inputString;
                    return (T) stringOut;
                }
            } catch (NumberFormatException ne) {
                System.err.print("Wrong format, enter again: ");
            } catch (Exception e) {
                System.out.print("Again: ");
            }
        } while (cont);

        return input;
    }
    
    public static <T> T getUpdateInput(T input) {
        boolean cont = true;
        do {
            try {
                String inputString = sc.nextLine();
                if (inputString.isEmpty()) {
                    return input;
                }

                if (input instanceof Integer) {
                    Integer intOut = Integer.parseInt(inputString);
                    return (T) intOut;
                } else if (input instanceof Double) {
                    Double doubleOut = Double.parseDouble(inputString);
                    return (T) doubleOut;
                } else if (input instanceof String) {
                    String stringOut = inputString;
                    return (T) stringOut;
                }
            } catch (NumberFormatException ne) {
                System.out.print("Wrong format, enter again: ");
            } catch (Exception e) {
                System.out.print("Again: ");
            }
        } while (cont);

        return input;
    }
            
    public static int backToMainMenu(int mainChoice, boolean checkSave) {
        System.out.println("\nDo you want to go back to the main menu?");
        System.out.print("Your choice (1. Yes || 0. No): ");
        int choice = Validation.getUserChoice(0, 1);
        if ((choice == 0 && checkSave == false) || choice == 0) {
            System.out.println("\nWant to save to the file before leaving?");
            System.out.println("If you choose \"No\", your changes will not be store");
            System.out.print("Your choice(1. Yes || 0. No): ");
            int subChoice = Validation.getUserChoice(0, 1);
            if (subChoice == 1) {
                PublisherServices.getInstance().saveThePublisherList();
                BookServices.getInstance().saveTheBookList();
            }
            
            return 0;
        } else {
            return mainChoice;
        }
    }
        
        
}
