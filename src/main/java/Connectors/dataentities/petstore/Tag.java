package Connectors.dataentities.petstore;

public class Tag {

    private int id;
    private String name;

    public Tag(){
        this.id=1;
        this.name="tag1";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
