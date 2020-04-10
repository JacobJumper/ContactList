package jacob.fragile.contactlist;

public class Contacts {
    private String name;
    private String age;
    private String color;
    private final int imageResource;

    Contacts(String name, String age, String color, int imageResource){
        this.name = name;
        this.age = age;
        this.color = color;
        this.imageResource = imageResource;
    }
    /**
     * Gets the image of the contact.
     *
     * @return The imageResource.
     */

    public int getImageResource(){ return imageResource; }
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

    String getColor() { return color; }
}


