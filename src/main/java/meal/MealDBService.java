package meal;

import database.DbHandler;
import database.Queries;
import owner.Owner;
import pet.Pet;
import pet.PetType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MealDBService {

    DbHandler dbHandler = new DbHandler();

    public ArrayList<PetMeal> findMealsByPetType(PetType petType) throws SQLException {
        ArrayList<PetMeal> petMeals = new ArrayList<>();
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.SELECT_MEALS_BY_FIELD_NAME);
        preparedStatement.setInt(1,petType.getId());
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()){
            PetMeal petMeal = new PetMeal(
                    result.getInt("id"),
                    new PetType(result.getInt("pet_type_id"), result.getString("pet_type_value")),
                    result.getString("name"),

                    result.getDouble("weight")
            );
            petMeals.add(petMeal);
        }
        result.close();
        preparedStatement.close();
        return petMeals;
    }






}
