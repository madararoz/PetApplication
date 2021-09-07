package pet;

public class PetType {
    private int id;
    private String value;

    public PetType() {
    }

    public PetType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public PetType setValue(String value) {
        this.value = value;
        return null;
    }

    @Override
    public String toString() {
        return "\n" + id + " " + value;
    }
}
