package com.racobos.manhattan20.mappers;

import com.racobos.manhattan20.entities.BaseEntity;
import com.racobos.manhattan20.models.BaseModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raulcobos on 14/9/16.
 */
public abstract class BaseMapper<M extends BaseModel, E extends BaseEntity> {
  public abstract M map(E entity);

  public abstract E map(M model);

  public List<M> map(E[] entities) {
    List<M> modelsList = new ArrayList<>();
    for (E entity : entities) {
      modelsList.add(map(entity));
    }
    return modelsList;
  }

  public List<E> map(M[] models) {
    List<E> entitiesList = new ArrayList<>();
    for (M model : models) {
      entitiesList.add(map(model));
    }
    return entitiesList;
  }
}