package com.racobos.manhattan20.usecases;

import com.racobos.rosie.domain.usecase.annotation.UseCase;
import java.util.Arrays;
import javax.inject.Inject;

/**
 * Created by raulcobos on 14/9/16.
 */
public class ListElements extends BaseUseCase {
  private String[][] elements = new String[][] {
          { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l" },
          { "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w" }, { "x", "y", "z" }
  };

  @Inject
  public ListElements() {
    //Empty constructor declared to be injected
  }

  @UseCase
  public void getElements(int page) {
    notifySuccess(Arrays.asList(elements[page]), page + 1, elements.length);
  }
}
