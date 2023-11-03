package org.velezreyes.quiz.question6;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class DrinkImp implements Drink {

  String name;

  boolean isFizzy;

  int price;

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public boolean isFizzy() {

    return this.isFizzy;
  }
  
  @Override
  public int getPrice() {

    return this.price;
  }
}
