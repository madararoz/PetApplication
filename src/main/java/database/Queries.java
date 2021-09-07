package database;

public class Queries {


    public final static String SELECT_PETS = "SELECT pets.id, pets.name as pet_name, pets.age as pet_age, " +
            "owners.id as owner_id, owners.name as owner_name, pettypes.id as pet_type_id, pettypes.type as pet_type_value FROM owners " +
            "RIGHT JOIN pets " +
            "ON pets.ownerId = owners.id " +
            "INNER JOIN pettypes " +
            "ON pets.pet_type_id = pettypes.id " +
            "ORDER BY id asc;";


    public static final String SELECT_PET = "SELECT pets.id, pets.name as pet_name,  pets.age as pet_age, \n"
            + "owners.id as owner_id, owners.name as owner_name,\n"
            + "pettypes.id as pet_type_id, pettypes.type as pet_type_value \n"
            + "FROM pets\n"
            + "left JOIN owners ON pets.ownerId = owners.id \n"
            + "INNER JOIN pettypes ON pets.pet_type_id = pettypes.id \n"
            + "where pets.id = ?\n"
            + "LIMIT 1;";

    public static final String SELECT_PETS_BY_OWNER_ID = "SELECT pets.id, pets.name as pet_name,  pets.age as pet_age, pettypes.id as pet_type_id, pettypes.type as pet_type_value FROM owners RIGHT JOIN pets ON pets.ownerId = owners.id INNER JOIN pettypes ON pets.pet_type_id = pettypes.id WHERE pets.ownerId = ? ORDER BY id asc;";
    public static final String SELECT_MEALS_BY_FIELD_NAME = "SELECT meals.id, meals.name, meals.weight, \n" +
            "pettypes.id as pet_type_id, pettypes.type as pet_type_value\n" +
            "FROM MEALS\n" +
            "INNER JOIN pettypes ON meals.pet_type_id = pettypes.id\n" +
            "WHERE meals.pet_type_id = ?;";


    public static final String SELECT_TOYS_BY_FIELD_NAME = "SELECT toys.id, toys.material, toys.price, \n" +
            "pettypes.id as pet_type_id, pettypes.type as pet_type_value\n" +
            "FROM TOYS\n" +
            "INNER JOIN pettypes ON toys.pet_type_id = pettypes.id\n" +
            "WHERE toys.pet_type_id = ?;";

    public static final String INSERT_OWNER = "INSERT INTO owners(name) VALUES(?)";
    public static final String DELETE_OWNER = "DELETE from owners WHERE id = ?";
    public static final String SELECT_OWNERS = "SELECT * FROM OWNERS";
    public static final String SELECT_OWNER = "SELECT * FROM OWNERS WHERE id= ?";
    public static final String REMOVE_PET_OWNER = "UPDATE pets SET ownerId = null where id = ?";
    public static final String SET_PET_OWNER = "UPDATE pets SET ownerId = ? where id = ?";

    public static final String INSERT_NEW_PET = "INSERT into pets(name, age, pet_type_id) VALUES (?,?,?)";
    public static final String INSERT_NEW_PET_TYPE = "INSERT into pettypes(type) VALUES (?)";
    public static final String DELETE_PET_TYPE = "DELETE from pettypes where id = ?";
    public static final String SELECT_PET_TYPES = "SELECT * FROM pettypes";
}



