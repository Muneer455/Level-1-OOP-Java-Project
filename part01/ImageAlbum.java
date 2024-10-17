package part01;

import java.util.ArrayList;

public class ImageAlbum {
    private static ArrayList <ImageRecord> imageRecord;//This ArrayList is static to store the image records.
    private int currentImage;// follows the image in the index for navigation

    public ImageAlbum() {
        this.imageRecord = new ArrayList<>(); // this part initialise the imageRecord.
        this.currentImage=0;//  set the current image to 0
    }
    
    public void addImage(ImageRecord image) { // this method adds images to the imageRecord
        imageRecord.add(image);

    }
    public ImageRecord getFirst() { // this method will return the first image that exist in the album,
        if (imageRecord.isEmpty()) {  //other wise it will return null if its empty
            return null;
        }
        return imageRecord.get(0);
    }

    public ImageRecord getNext() { // this method will return the Next image within the album 
        if (currentImage >= imageRecord.size ()) {//it will increment the index and will return null in the end of the album
            return null;
        }
        return imageRecord.get(currentImage++);
    }
    public ImageRecord getPrevious () { //this method will return the previous image that is inside the album 
        if (currentImage>0) {
            currentImage--;/// and decrements the currentImage index
            return imageRecord.get(currentImage);// and it will return null in case the currentImage is at the start of the album

        }
        return null;
    }
    public String toString() {// overrides the toString method so it returns a string that will represent the images in the album 
        String album = "";

        for (int i = 0; i < imageRecord.size(); i++) {
            album += imageRecord.get(i);
        }
        return album;
    }

}



