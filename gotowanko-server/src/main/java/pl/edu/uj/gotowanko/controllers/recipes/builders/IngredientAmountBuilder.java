package pl.edu.uj.gotowanko.controllers.recipes.builders;

import pl.edu.uj.gotowanko.entities.Ingredient;
import pl.edu.uj.gotowanko.entities.IngredientAmount;
import pl.edu.uj.gotowanko.entities.IngredientUnit;
import pl.edu.uj.gotowanko.exceptions.businesslogic.NoSuchIngredient;
import pl.edu.uj.gotowanko.exceptions.businesslogic.InvalidIngredientAmount;
import pl.edu.uj.gotowanko.exceptions.businesslogic.NoSuchIngredientUnit;
import pl.edu.uj.gotowanko.repositories.IngredientRepository;
import pl.edu.uj.gotowanko.repositories.IngredientUnitRepository;

/**
 * Created by michal on 18.04.15.
 * <p/>
 * Creates non persisted, well defined IngredientAmount object.
 */
public class IngredientAmountBuilder {

    private IngredientRepository ingredientRepository;

    private IngredientUnitRepository ingredientUnitRepository;

    private IngredientAmount ingredientAmount;

    public IngredientAmountBuilder(IngredientRepository ingredientRepository, IngredientUnitRepository ingredientUnitRepository) {
        this(ingredientRepository, ingredientUnitRepository, new IngredientAmount());
    }

    public IngredientAmountBuilder(IngredientRepository ingredientRepository, IngredientUnitRepository ingredientUnitRepository, IngredientAmount ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
        this.ingredientUnitRepository = ingredientUnitRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public IngredientAmountBuilder withIngredient(Long ingredientId) throws NoSuchIngredient {
        Ingredient ingredient = ingredientRepository.findOne(ingredientId);
        if (ingredient == null)
            throw new NoSuchIngredient(ingredientId);
        ingredientAmount.setIngredient(ingredient);
        return this;
    }

    public IngredientAmountBuilder withIngredientUnit(Long unitId) throws NoSuchIngredientUnit {
        IngredientUnit ingredientUnit = ingredientUnitRepository.findOne(unitId);
        if (ingredientUnit == null)
            throw new NoSuchIngredientUnit(unitId);
        ingredientAmount.setIngredientUnit(ingredientUnit);
        return this;
    }

    public IngredientAmountBuilder withAmount(Double amount) throws InvalidIngredientAmount {
        if (amount <= 0)
            throw new InvalidIngredientAmount(amount);
        ingredientAmount.setAmount(amount);
        return this;
    }

    public IngredientAmount build() {
        if (ingredientAmount == null)
            throw new IllegalStateException("IngredientAmountBuilder may not be reused");
        if (ingredientAmount.getIngredient() == null)
            throw new IllegalStateException("Ingredient must be set before building ingredient amount");
        if (ingredientAmount.getAmount() == null)
            throw new IllegalStateException("Ingredient amount must be set before building ingredient amount");
        if (ingredientAmount.getIngredientUnit() == null)
            throw new IllegalStateException("Ingredient unit must be set before building ingredient amount");
        IngredientAmount result = this.ingredientAmount;
        this.ingredientAmount = null;
        return result;
    }


}
