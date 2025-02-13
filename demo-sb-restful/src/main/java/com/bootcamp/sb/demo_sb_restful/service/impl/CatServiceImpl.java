package com.bootcamp.sb.demo_sb_restful.service.impl;

import org.springframework.stereotype.Service;
import com.bootcamp.sb.demo_sb_restful.model.Cat;
import com.bootcamp.sb.demo_sb_restful.model.CatDatabase;
import com.bootcamp.sb.demo_sb_restful.service.CatService;

@Service
public class CatServiceImpl implements CatService {
  @Override
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
