package part01;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QUBImages {
    private static final Scanner scanner = new Scanner(System.in);// the scanner is used for input and for inisilisting of the ImageManager and the ImageAlbum 
    private static ImageAlbum imageAlbum = new ImageAlbum();
    private static ImageManager imageManager = new ImageManager(imageAlbum);
    private static ArrayList<ImageRecord> imageRecord = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            try {// a try and catch to find any input mismatch error
                System.out.println("\n * -----QUBImage Menu ---- *");
                System.out.println("1. Add Images");
                System.out.println("2. Search");
                System.out.println("3. Display All");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {// a switch to process the user's choice
                    case 1:
                        addImage();
                        break;
                    case 2:
                        searchMenu();
                        break;
                    case 3:
                        System.out.println(imageManager.getAllImages());
                        break;
                    case 4:
                        exit = true;
                        System.out.println("*-------EXITING-----*");
                        System.out.println("Thank you for using QUBImages");
                        break;
                    default:
                        System.out.println("Not a recognized number. Please enter a number between 1 to 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                scanner.nextLine(); // it will consume the incorrect input
            }
        }
    }

    private static void addImage() {// method for adding images.
        boolean tryAgain = false;
        while (!tryAgain) {
            try {
                System.out.println("\n * ---Add Image---*");
                System.out.print("Enter title: ");
                String title = scanner.next();
                scanner.nextLine();
                System.out.print("Enter Description: ");
                String description = scanner.nextLine();
                ImageType type = null;
                System.out.print("Enter your Genre: ");
                while (!tryAgain) {
                    try {
                        type = ImageType.valueOf(scanner.nextLine().toUpperCase());
                        tryAgain = true;
                    } catch (Exception e) {
                        System.out.print("You have entered incorrectly, please try again: ");
                    }
                }
                LocalDate date = LocalDate.now();
                System.out.print("Enter your thumbnail: ");
                String thumbnail = scanner.nextLine();

                ImageRecord newImage = new ImageRecord(title, description, type, date, thumbnail);
                imageManager.addImage(newImage);
                System.out.println("Image added successfully.");
                tryAgain = true;
            } catch (Exception e) {
                System.out.println("You have entered incorrectly, please try again.");
                scanner.nextLine(); // this will consume the incorrect input
            }
        }
    }

    private static void searchMenu() {//this method is for the search menu to search options
        boolean backToStart = false;
        while (!backToStart) {
            System.out.println("\n * -----Search Menu---- *");
            System.out.println("1. Search by ID");
            System.out.println("2. Search by Title");
            System.out.println("3. Search by Description");
            System.out.println("4. Search by Type");
            System.out.println("5. Search by Date Range");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {// a switch to process the user's choice 
                case 1:
                    searchById();
                    break;
                case 2:
                    searchByTitle();
                    break;
                case 3:
                    searchByDescription();
                    break;
                case 4:
                    searchByType();
                    break;
                case 5:
                    searchByDateRange();
                    break;
                case 6:
                    backToStart = true;
                    break;
                default:
                    System.out.println("Invalid choice, please enter a number between 1 to 5.");
            }
        }
    }

    // Dummy implementations for compilation
    private static void searchById() {
        boolean exit = false;
        System.out.println("Searching by ID...");
        // Implement search functionality here
        System.out.print("Enter Id: ");
        while (!exit) {
            try {
                int id = scanner.nextInt();
                scanner.nextLine();
                ImageRecord result = imageManager.searchId(id);
                if (result != null) {
                    System.out.print(result);
                } else {
                    System.out.println("No image found with the given ID.");
                }
                exit = true;
            } catch (Exception e) {
                System.out.print("Invalid input, please enter a number: ");
                scanner.nextLine();
            }
        }
    }

    private static void searchByTitle() {
        System.out.println("Searching by Title...");
        // Implement search functionality here
        System.out.print("Enter title: ");
        String title = scanner.next();
        scanner.nextLine();
        ImageAlbum result = imageManager.searchTitle(title);
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("No images found with the given title.");
        }
    }

    private static void searchByDescription() {
        System.out.println("Searching by Description...");
        System.out.print("Enter description: ");
        String description = scanner.next();
        scanner.nextLine();
        ImageAlbum result = imageManager.searchDescription(description);
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("No images found with the given description.");
        }
    }

    private static void searchByType() {
        boolean exit = false;
        System.out.println("Searching by Type...");
        // in here it implements the search functionality 
        System.out.print("Enter type: ");
        while (!exit)
            try {
                ImageType type = ImageType.valueOf(scanner.next().toUpperCase());
                ImageAlbum result = imageManager.searchGenre(type);
                if (result != null) {
                    System.out.println(result);
                } else {
                    System.out.println("No images found with the given genre.");
                }
                exit = true;
            } catch (Exception e) {
                System.out.print("Invalid input, please enter a valid image type: ");
                scanner.nextLine();
            }
    }

    private static void searchByDateRange() {
        boolean exit = false;
        System.out.println("Searching by Date Range...");
        LocalDate startDate = null;
        LocalDate endDate = null;
        while (!exit) {
            try {
                System.out.print("Enter start date (YYYY-MM-DD): ");
                String startDateString = scanner.next();
                scanner.nextLine();
                startDate = LocalDate.parse(startDateString);
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input. Try again.");
            }
        }
        while (!exit) {
            try {
                System.out.print("Enter end date (YYYY-MM-DD): ");
                String endDateString = scanner.next();
                endDate = LocalDate.parse(endDateString);
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input. Try again.");
            }
        }
        ImageAlbum result = imageManager.searchDates(startDate, endDate);
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("No images found within the given date range.");
        }
    }

}

