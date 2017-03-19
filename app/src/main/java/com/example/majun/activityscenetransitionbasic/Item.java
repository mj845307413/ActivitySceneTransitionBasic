package com.example.majun.activityscenetransitionbasic;

/**
 * Created by majun on 17/3/5.
 */
public class Item {
    public Item(String mName, String mAuthor, String mFileName) {
        this.mName = mName;
        this.mAuthor = mAuthor;
        this.mFileName = mFileName;
    }

    private final String mName;
    private final String mAuthor;
    private final String mFileName;

    public String getAuthor() {
        return mAuthor;
    }

    public String getName() {
        return mName;
    }

    public String getPhotoUrl() {
        return LARGE_BASE_URL + mFileName;
    }

    public String getThumbnailUrl() {
        return THUMB_BASE_URL + mFileName;
    }

    public int getId() {
        return mName.hashCode() + mFileName.hashCode();
    }

    private static final String LARGE_BASE_URL = "http://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/large/";
    private static final String THUMB_BASE_URL = "http://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/thumbs/";

    public static Item[] ITEMS = new Item[]{
            new Item("Flying in the Light", "Romain Guy", "flying_in_the_light.jpg"),
            new Item("Caterpillar", "Romain Guy", "caterpillar.jpg"),
            new Item("Look Me in the Eye", "Romain Guy", "look_me_in_the_eye.jpg"),
            new Item("Flamingo", "Romain Guy", "flamingo.jpg"),
            new Item("Rainbow", "Romain Guy", "rainbow.jpg"),
            new Item("Over there", "Romain Guy", "over_there.jpg"),
            new Item("Jelly Fish 2", "Romain Guy", "jelly_fish_2.jpg"),
            new Item("Lone Pine Sunset", "Romain Guy", "lone_pine_sunset.jpg"),
    };

    public static Item getItem(int id) {
        for (Item item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

}
