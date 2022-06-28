package dataentities.petstore;

//import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.List;

public class Pet {

    private String id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;


    public Pet(){
        id="1";

        Category category = new Category();
        this.category = category;

        name="doberman";

        List<String> photoUrls = new ArrayList<>();

        photoUrls.add("https://t2.ea.ltmcdn.com/es/razas/1/3/1/img_131_doberman-pinscher_0_600.jpg");
        photoUrls.add("https://www.nombresdeperros.eu/wp-content/uploads/2019/04/nombre-macho-doberman-julieta.jpg");

        this.photoUrls = photoUrls;

        Tag tag = new Tag();
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        this.tags = tags;

        this.status="available";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
