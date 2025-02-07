package com.bootcamp.sb.demo_sb_restful.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatDatabase {
  public static final Cat[] HOME = new Cat[5];

  public static boolean put(Cat cat) {
    for (int i = 0; i < HOME.length; i++) {
      if (HOME[i] == null) {
        HOME[i] = cat;
        return true;
      }
    }
    return false;
  }

  public static List<Cat> getCats() {
    List<Cat> cats = new ArrayList<>();
    for (Cat cat : HOME) {
      if (cat != null)
        cats.add(cat);
    }
    return cats;
  }

  public static Optional<Cat> find(Long id) {
    for (Cat cat : HOME) {
      if (cat != null && cat.getId().equals(id))
        return Optional.of(cat);
    }
    return Optional.empty();
  }

  public static Boolean delete(Long id) {
    for (int i = 0; i < HOME.length; i++) {
      if (HOME[i] != null && HOME[i].getId().equals(id)) {
        HOME[i] = null;
        return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }

  public static Boolean update(Long id, Cat cat) {
    for (int i = 0; i < HOME.length; i++) {
      if (HOME[i] != null && HOME[i].getId().equals(id)) {
        HOME[i] = cat;
        return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }

  public static Boolean patchName(Long id, String name) {
    Cat cat = CatDatabase.find(id).orElse(null);
    if (cat == null)
      return Boolean.FALSE;

    cat.setName(name);
    return Boolean.TRUE;
  }
}
