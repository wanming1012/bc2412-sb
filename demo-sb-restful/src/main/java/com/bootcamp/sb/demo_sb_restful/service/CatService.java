package com.bootcamp.sb.demo_sb_restful.service;

import org.springframework.stereotype.Service;
import com.bootcamp.sb.demo_sb_restful.model.Cat;
import com.bootcamp.sb.demo_sb_restful.model.CatDatabase;

@Service
public class CatService {
  public boolean put(Cat cat) {
    for (int i = 0; i < CatDatabase.HOME.length; i++) {
      if (CatDatabase.HOME[i] == null) {
        CatDatabase.HOME[i] = cat;
        return true;
      }
    }
    return false;
  }
}
