package toys;

import database.DbHandler;
import database.Queries;
import pet.PetType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToyDBService {
    DbHandler dbHandler = new DbHandler();

    public ArrayList<PetToy> findToysByPetType(PetType petType) throws SQLException {
        ArrayList<PetToy> petToys = new ArrayList<>();
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.SELECT_TOYS_BY_FIELD_NAME);
        preparedStatement.setInt(1,petType.getId());
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()){
            PetToy petToy = new PetToy(
                    result.getInt("id"),
                    result.getString("material"),
                    new PetType(result.getInt("pet_type_id"), result.getString("pet_type_value")),
                    result.getDouble("price")
            );
            petToys.add(petToy);
        }
        result.close();
        preparedStatement.close();
        return petToys;
    }
}
