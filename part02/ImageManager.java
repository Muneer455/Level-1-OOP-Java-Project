package part02;

import part01.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ImageManager {

    static ArrayList<ImageRecord> imageRecord;// this is a list of all the imageRecors
    private static ImageAlbum imageAlbum;// this is an album to manage all the images.

    private void displayAll() {// adding all the images including there details
        addImage(new ImageRecord("Andromeda Galaxy", "Image of the Andromeda galaxy.", ImageType.ASTRONOMY, LocalDate.of(2023, 1, 1), "Andromeda.png"));
        addImage(new ImageRecord("Lanyon QUB", "An image of the QUB Lanyon building.", ImageType.ARCHITECTURE, LocalDate.of(2023, 2, 1), "LanyonQUB.png"));
        addImage(new ImageRecord("Kermit Plays Golf", "An image of Kermit the frog playing golf.", ImageType.SPORT, LocalDate.of(2023, 3, 1), "KermitGolf.png"));
        addImage(new ImageRecord("Mourne Mountains", "A panoramic view of the Mourne mountains.", ImageType.LANDSCAPE, LocalDate.of(2023, 4, 1), "Mournes.png"));
        addImage(new ImageRecord("Homer Simpson", "Homer Simpson- A portrait of the man.", ImageType.PORTRAIT, LocalDate.of(2023, 3, 1), "Homer.png"));
        addImage(new ImageRecord("Red Kite", "A Red Kite bird of prey in flight.", ImageType.NATURE, LocalDate.of(2023, 4, 1), "RedKite.png"));
        addImage(new ImageRecord("Central Park", "An overhead view of Central Park New York USA.", ImageType.AERIAL, LocalDate.of(2023, 5, 1), "CentralPark.png"));
        addImage(new ImageRecord("Apples", "A bunch of apples.", ImageType.FOOD, LocalDate.of(2023, 6, 1), "Apples.png"));
        addImage(new ImageRecord("Programming Meme", "A Chat GPT programming meme.", ImageType.OTHER, LocalDate.of(2023, 7, 1), "ChatGPT.png"));

    }
    public ImageManager() {//this method initialise the imageManager with an empty list of imageRecords and give image album.
        imageRecord = new ArrayList<>();
        displayAll();// display all images in the album
    }

    public void addImage(ImageRecord image) {// adds an image to the list of imageRecords
        imageRecord.add(image);
    }

    public void displayNextPic() {//this method will display the next image in the album, only if it was available and was not null.
        ImageRecord nextImage = imageAlbum.getNext();
        if (nextImage != null) {
            System.out.println("Image ID: " + nextImage.getImageId());
            System.out.println("Image Name: " + nextImage.getTitle());
            //  any other properties of nextImage will be displayed here
        } else {
            System.out.println("There are no more images to display.");
        }
    }

    public ImageRecord searchId(int id) {// this method will search for an image by its unique id and if found or exists will return it.
        for (int i = 0; i < imageRecord.size(); i++) {// the loop here will go through the image record to try and find a match of the image Id
            if (imageRecord.get(i).getImageId() == id) {
                return imageRecord.get(i);
            }
        }
        return null;// it will return null in the case of not finding a matching Id
    }

    public ImageAlbum searchTitle(String str) {// this method looks for images by their title and return an album with the matching images in.
        ImageAlbum album = new ImageAlbum();
        for (int i = 0; i < imageRecord.size(); i++) {// this loops through the image records and add all the matchings to the album
            if (imageRecord.get(i).getTitle().contains(str)) {
                album.addImage(imageRecord.get(i));
            }
        }
        return album;
    }

    public ImageAlbum searchDescription(String str) {// this method looks for images using their description and return an album of the matching images.
        ImageAlbum album = new ImageAlbum();
        for (int i = 0; i < imageRecord.size(); i++) {// this loops through the image record and add all the matchings to the album
            if (imageRecord.get(i).getDescription().contains(str)) {
                album.addImage(imageRecord.get(i));
            }
        }
        return album;
    }

    public ImageAlbum searchGenre(ImageType type) {// this method looks for the images using their type or genre and return an album of all the matching images
        ImageAlbum album = new ImageAlbum();
        for (int i = 0; i < imageRecord.size(); i++) {// this loops through the images record and add all the matchings to the album
            if (imageRecord.get(i).getType().equals(type)) {
                album.addImage(imageRecord.get(i));
            }
        }
        return album;
    }


    public ImageAlbum searchDates(LocalDate start, LocalDate end) {// this method looks for images in a specific date range and returns an album of the matching images
        ImageAlbum album = new ImageAlbum();
        for (int i = 0; i < imageRecord.size(); i++) {// this loops through the imageRecords and adds the images that are within the date range to the album.
            ImageRecord image = imageRecord.get(i);
            LocalDate date = imageRecord.get(i).getDate();
            if ((image.getDate().isEqual(start) || image.getDate().isAfter(start)) && (image.getDate().isEqual(end) || image.getDate().isBefore(end))) {
                album.addImage(image);
            }
        }
        return album;
    }

    public static ImageIcon imagePath (String name) {
        String userdir = System.getProperty("user.dir");
        String path = userdir + "/Images/" + name;
        ImageIcon image = new ImageIcon(path);
        return image;
    }

    public static void getAllImages(){// returns an album with all the images inside
        for (int i = 0; i < imageRecord.size(); i++) {
            ImageRecord image = imageRecord.get(i);
            ImageIcon imageIcon = imagePath(image.getThumbnail());
            QUBMediaImages.console.println(imageIcon);
            QUBMediaImages.console.println(image.toString());
        }
    }
}
