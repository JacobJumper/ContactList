package jacob.fragile.contactlist;

import android.net.Uri;

public class Contacts {
    private String name;
    private String age;
    private String color;
    private  String image;

    Contacts(String name, String age, String color, String image){
        this.name = name;
        this.age = age;
        this.color = color;
        this.image = image;
    }
    /**
     * Gets the image of the contact.
     *
     * @return The imageResource.
     */

    String getImage(){ return image; }
    /**
     * Gets the name of the contact.
     *
     * @return The name.
     */
    String getName() { return name; }

    /**
     * Gets the age of the contact.
     *
     * @return The age.
     */
    String getAge() {
        return age;
    }

    /**
     * Gets the  color of the contact.
     *
     * @return The color.
     */

    String getColor() { return color; }
}


