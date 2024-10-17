package part02;

import part01.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import console.Console;

import javax.swing.*;

import static part02.ImageManager.imagePath;

public class QUBMediaImages {
	private static ImageAlbum imageAlbum = new ImageAlbum();
	private static ImageManager imageManager = new ImageManager();
	private static ArrayList<ImageRecord> imageRecord = new ArrayList<>();
	static Console console = new Console(true);

	public static void main(String[] args) {
		console.setVisible(true);
		boolean exit = false;
		while (!exit) {
			try {// a try and catch to find any input mismatch error
				console.println("\n * -----QUBImage Menu ---- *");
				console.println("1. Add Image");
				console.println("2. Search");
				console.println("3. Display All");
				console.println("4. Exit");
				console.print("Enter your choice: ");
				int choice = Integer.parseInt(console.readLn());

				switch (choice) {// a switch to process the user's choice
				case 1:
					addImage();
					break;
				case 2:
					searchMenu();
					break;
				case 3:
					imageManager.getAllImages();
					break;
				case 4:
					exit = true;
					console.println("*-------EXITING-----*");
					console.println("Thank you for using QUBImages");
					break;
				default:
					console.println("Not a recognized number. Please enter a number between 1 to 6.");
				}
			} catch (InputMismatchException e) {
				console.println("Invalid input, please enter a number.");
				// scanner.nextLine(); // it will consume the incorrect input
			}
		}
	}

	private static void addImage() {// method for adding images.
		boolean tryAgain = false;
		while (!tryAgain) {
			try {
				console.println("\n * ---Add Image---*");
				console.print("Enter title: ");
				String title = console.readLn();
				console.print("Enter Description: ");
				String description = console.readLn();
				ImageType type = null;
				console.print("Enter your Genre: ");
				while (!tryAgain) {
					try {
						type = ImageType.valueOf(console.readLn().toUpperCase());
						tryAgain = true;
					} catch (Exception e) {
						console.print("You have entered incorrectly, please try again: ");
					}
				}
				LocalDate date = LocalDate.now();
				console.print("Enter your thumbnail: ");
				String thumbnail = console.readLn();

				ImageRecord newImage = new ImageRecord(title, description, type, date, thumbnail);
				imageManager.addImage(newImage);
				console.println("Image added successfully.");
				tryAgain = true;
			} catch (Exception e) {
				console.println("You have entered incorrectly, please try again.");
			}
		}
	}

	private static void searchMenu() {// this method is for the search menu to search options
		boolean backToStart = false;
		while (!backToStart) {
			console.println("\n * -----Search Menu---- *");
			console.println("1. Search by ID");
			console.println("2. Search by Title");
			console.println("3. Search by Description");
			console.println("4. Search by Type");
			console.println("5. Search by Date Range");
			console.println("6. Back to Main Menu");
			console.print("Enter your choice: ");
			int choice = Integer.parseInt(console.readLn());

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
				console.println("Invalid choice, please enter a number between 1 to 5.");
			}
		}
	}

	private static void searchById() {
		boolean exit = false;
		console.println("Searching by ID...");
		console.print("Enter Id: ");
		while (!exit) {
			try {
				int id = Integer.parseInt(console.readLn());
				ImageRecord result = imageManager.searchId(id);
				if (result != null) {
					for (int i = 0; i < imageManager.imageRecord.size(); i++) {
						if (imageManager.imageRecord.get(i).getImageId() == id) {
							ImageRecord image = imageManager.imageRecord.get(i);
							ImageIcon imageIcon = imagePath(image.getThumbnail());
							QUBMediaImages.console.println(imageIcon);
							QUBMediaImages.console.println(image.toString());
						}
					}
				}
				exit = true;
			} catch (Exception e) {
				console.print("Invalid input, please enter a number: ");
				console.readLn();
			}
		}
	}

	private static void searchByTitle() {
		console.println("Searching by Title...");
		console.print("Enter title: ");
		String title = console.readLn();
		ImageAlbum result = imageManager.searchTitle(title);
		for (int i = 0; i < imageManager.imageRecord.size(); i++) {
			if (imageManager.imageRecord.get(i).getTitle().contains(title)) {
				ImageRecord image = imageManager.imageRecord.get(i);
				ImageIcon imageIcon = imagePath(image.getThumbnail());
				QUBMediaImages.console.println(imageIcon);
				QUBMediaImages.console.println(image.toString());
			}
		}
	}

	private static void searchByDescription() {
		console.println("Searching by Description...");
		console.print("Enter description: ");
		String description = console.readLn();
		ImageAlbum result = imageManager.searchDescription(description);
		for (int i = 0; i < imageManager.imageRecord.size(); i++) {
			if (imageManager.imageRecord.get(i).getDescription().contains(description)) {
				ImageRecord image = imageManager.imageRecord.get(i);
				ImageIcon imageIcon = imagePath(image.getThumbnail());
				QUBMediaImages.console.println(imageIcon);
				QUBMediaImages.console.println(image.toString());
			}
		}
	}

	private static void searchByType() {
		boolean exit = false;
		console.println("Searching by Type...");
		// in here it implements the search functionality
		console.print("Enter type: ");
		while (!exit)
			try {
				ImageType type = ImageType.valueOf(console.readLn().toUpperCase());
				ImageAlbum result = imageManager.searchGenre(type);
				for (int i = 0; i < imageManager.imageRecord.size(); i++) {
					if (imageManager.imageRecord.get(i).getType().equals(type)) {
						ImageRecord image = imageManager.imageRecord.get(i);
						ImageIcon imageIcon = imagePath(image.getThumbnail());
						QUBMediaImages.console.println(imageIcon);
						QUBMediaImages.console.println(image.toString());
					}
				}
				exit = true;
			} catch (Exception e) {
				console.print("Invalid input, please enter a valid image type: ");
			}
	}

	private static void searchByDateRange() {
		boolean exit = false;
		console.println("Searching by Date Range...");
		LocalDate startDate = null;
		LocalDate endDate = null;
		while (!exit) {
			try {
				console.print("Enter start date (YYYY-MM-DD): ");
				String startDateString = console.readLn();
				startDate = LocalDate.parse(startDateString);
				break;
			} catch (Exception e) {
				console.println("Invalid Input. Try again.");
			}
		}
		while (!exit) {
			try {
				console.print("Enter end date (YYYY-MM-DD): ");
				String endDateString = console.readLn();
				endDate = LocalDate.parse(endDateString);
				break;
			} catch (Exception e) {
				console.println("Invalid Input. Try again.");
			}
		}
		ImageAlbum result = imageManager.searchDates(startDate, endDate);
		for (int i = 0; i < imageManager.imageRecord.size(); i++) {
			if ((imageManager.imageRecord.get(i).getDate().isEqual(endDate)
					|| imageManager.imageRecord.get(i).getDate().isBefore(endDate))
					&& (imageManager.imageRecord.get(i).getDate().isEqual(startDate)
							|| imageManager.imageRecord.get(i).getDate().isAfter(endDate))) {
				ImageRecord image = imageManager.imageRecord.get(i);
				ImageIcon imageIcon = imagePath(image.getThumbnail());
				QUBMediaImages.console.println(imageIcon);
				QUBMediaImages.console.println(image.toString());
			}
		}
	}

}
