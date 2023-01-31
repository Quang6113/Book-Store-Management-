package bsm.main;

import bsm.services.BookServices;
import bsm.services.PublisherServices;
import bsm.utils.Validation;

/**
 *
 * @author QUANG
 */
public class BookStoreManagement {

    public static void main(String[] args) {
        int choice = 0;
        int subChoice = 0;
        boolean checkSave = true;
        PublisherServices ps = PublisherServices.getInstance();
        BookServices bs = BookServices.getInstance();

        do {
            System.out.println("\n====== BOOK STORE MANAGEMENT ======\n"
                    + "1. Publishers' management\n"
                    + "2. Books' management\n"
                    + "3. Quit.");
            System.out.print("Your choice: ");
            choice = Validation.getUserChoice(1, 3);
            switch (choice) {
                case 1:
                    System.out.println("\n----- Publishers' management -----\n"
                            + "1. Create a Publisher\n"
                            + "2. Delete the Publisher\n"
                            + "3. Save the Publishers list to file\n"
                            + "4. Print the Publisher list from the file");
                    System.out.print("Your choice: ");
                    subChoice = Validation.getUserChoice(1, 4);
                    switch (subChoice) {
                        case 1:
                            ps.create();
                            checkSave = false;
                            choice = Validation.backToMainMenu(choice,checkSave);
                            break;
                        case 2:
                            ps.delete(ps.getList().getPublisherList());
                            checkSave = false;
                            choice = Validation.backToMainMenu(choice,checkSave);
                            break;
                        case 3:
                            ps.saveThePublisherList();
                            checkSave = true;
                            choice = Validation.backToMainMenu(choice,checkSave);
                            break;
                        case 4:
                            ps.printThePublisherListFromFiles();
                            choice = Validation.backToMainMenu(choice,checkSave);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\n----- Books' management -----\n"
                            + "1. Create a Book\n"
                            + "2. Search the Book \n"
                            + "3. Update a Book\n"
                            + "4. Delete the Book\n"
                            + "5. Save the Books list to file.\n"
                            + "6. Print the Books list from the file.");
                    System.out.print("Your choice: ");
                    subChoice = Validation.getUserChoice(1, 6);
                    switch (subChoice) {
                        case 1:
                            bs.create(ps.getList().getPublisherList());
                            choice = Validation.backToMainMenu(choice,checkSave);
                            checkSave = false;
                            break;
                        case 2:
                            bs.search(ps.getList().getPublisherList(),
                                    ps.getList().getPIdMap());
                                    choice = Validation.backToMainMenu(choice,checkSave);
                            break;
                        case 3:
                            bs.update(ps.getList().getPublisherList(), 
                                    bs.getList().getBookList());
                                    checkSave = false;
                                    choice = Validation.backToMainMenu(choice,checkSave);
                            break;
                        case 4:
                            bs.delete(ps.getList().getPublisherList(), 
                                    bs.getList().getBookList());
                                    checkSave = false;
                                    choice = Validation.backToMainMenu(choice,checkSave);
                            break;
                        case 5:
                            bs.saveTheBookList();
                            checkSave = true;
                            choice = Validation.backToMainMenu(choice,checkSave);
                            break;
                        case 6:
                            bs.printTheBookListFromFile(ps.getList().getPIdMap());
                            choice = Validation.backToMainMenu(choice,checkSave);
                            break;
                    }
                    break;
            }
        } while (choice >= 1 && choice <= 2);
    }
}
