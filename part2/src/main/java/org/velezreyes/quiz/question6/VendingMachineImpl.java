package org.velezreyes.quiz.question6;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {

  int quarters = 0;
  
  static List<Drink> drinks = new ArrayList<>();

  private static VendingMachine instance;

  public static VendingMachine getInstance() {
    // Setting the default values for the machine
    // As the tests say, the machine starts with 0 quarters, and 2 drinks, also
    // ScottCola is fizzy and KarenTea is not, ScottCola costs 3 quarters and
    // KarenTea costs 4 quarters
    drinks = List.of(new DrinkImp("ScottCola", true, 3), new DrinkImp("KarenTea", false, 4));

    if(instance == null)
      instance = new VendingMachineImpl();

    return instance;
  }

  /**
   * Inserts a quarter into the machine, incrementing the available balance.
   */
  @Override
  public void insertQuarter() {
    quarters++;
  }

  /**
   * Charges a specified number of quarters from the available balance.
   *
   * @param quarters The number of quarters to be charged.
   */
  private void chargeQuarters(int quarters) {
    this.quarters -= quarters;
  }

  /**
   * Presses a button to select and dispense a drink with the specified name.
   * Validates whether there is enough money to make the purchase and retrieves
   * the selected drink.
   *
   * @param name The name of the drink to be selected and dispensed.
   * @return The selected Drink object.
   * @throws NotEnoughMoneyException If there are not enough quarters to cover the
   *                                 selected drink's price.
   * @throws UnknownDrinkException   If a drink with the specified name is not
   *                                 found in the available drinks.
   */
  @Override
  public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException {

    Drink drink = validateDrink(name);
    validateMoney(drink.getPrice());

    return drink;
  }

  /**
   * Validates and retrieves a drink by its name from the list of available
   * drinks.
   * 
   * @param name The name of the drink to validate and retrieve.
   * @return The Drink object with the specified name.
   * @throws UnknownDrinkException If a drink with the specified name is not
   *                               found.
   */
  private Drink validateDrink(String name) throws UnknownDrinkException {
    return drinks.stream()
        .filter(drink -> drink.getName().equals(name))
        .findFirst()
        .orElseThrow(UnknownDrinkException::new);
  }

  /**
   * Validates whether there is enough money (quarters) to make a purchase.
   * Throws a NotEnoughMoneyException if the provided price is greater than
   * the available quarters.
   *
   * @param price The price of the item or service to be purchased.
   * @throws NotEnoughMoneyException If there are not enough quarters to cover
   *                                 the specified price.
   */
  private void validateMoney(int price) throws NotEnoughMoneyException {
    if (price > quarters)
      throw new NotEnoughMoneyException();
    chargeQuarters(price);
  }
}
