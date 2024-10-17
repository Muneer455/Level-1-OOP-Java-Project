package part01;

import java.time.LocalDate;

public class ImageRecord {
    
    private int imageId;//Identify the imageId
    private String title;// identify the imageTitle
    private String description;//  the image description
    private ImageType type;// shows what category type from ImageType the image is
    private LocalDate date;// the date that is linked with the images
    private static int nextImageId = 1;// auto-increaments from one image to another
    private String thumbnail;// the path of the image's thumbnail

    public ImageRecord(String title, String description, ImageType type, LocalDate date, String thumbnail) {// method to initialise an image record including the image details.
        this.imageId = nextImageId++;//assign and auto-increament the imageId 
        this.title = title;
        this.description = description;
        this.type = type;
        this.date = date;
        this.thumbnail = thumbnail;

    }

    public int getImageId() {// this method returns the images id
        return this.imageId;

    }

    public boolean setImageId(int id) {//this method gives a new id number to the images.
        this.imageId = id;
        return true;// it will return true every time
    }

    public String getTitle() {// this method returns the image title
        return this.title;

    }

    public void setTitle(String title) {//this method sets the image title 
        if (title != null && !title.isEmpty()) {// it also ensure that its not empty
            this.title = title;

        } else {
            System.out.println("Title cannot be empty");
        }
    }

    public String getDescription() {// this method returns the description of the image
        return this.description;
    }

    public ImageType getType() {// this method returns the type of the image
        return this.type;
    }

    public void setType(ImageType type) {//this method sets the image type 
        if (type != null) {//it also ensures the image type is not null
            this.type = type;

        } else {
            System.out.println("Date cannot be empty");
        }
    }

    public String getThumbnail() {// this method returns the  thumbnail path
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {// this method sets the image thumbnail 
        this.thumbnail = thumbnail;
    }

    public String toString() {// this method return a string that will be representing the imageRecord
        return "ID: " + imageId + "\nTitle: " + title + "\nGenre: " + type + "\nDescription: " + description + "\nDate: " + date
                + "\nthumbnail: " + thumbnail + "\n\n";
    }

    public LocalDate getDate() {// this method will return the date that is linked to each image
        return this.date;
    }
}
