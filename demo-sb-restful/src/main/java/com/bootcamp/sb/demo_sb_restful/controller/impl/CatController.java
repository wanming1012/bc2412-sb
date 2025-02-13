package com.bootcamp.sb.demo_sb_restful.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.demo_sb_restful.controller.CatOperation;
import com.bootcamp.sb.demo_sb_restful.model.Cat;
import com.bootcamp.sb.demo_sb_restful.model.CatDatabase;
import com.bootcamp.sb.demo_sb_restful.service.CatService;
import com.bootcamp.sb.demo_sb_restful.service.impl.CatServiceImpl;

// Controller -> Service -> CatDatabase
@RestController
public class CatController implements CatOperation {
  @Autowired  // field injection
  private CatService catService;    
  // ! why not use static variable?  DI?
  // ! can I use child of CatService?  Which child will be used if more than 1 childs?

  //@Autowired  // Constructor injection, @Autowired annotation is not required
  // public CatController(CatServiceImpl catService) {
  //   this.catService = catService; // ! why not new in constructor?
  // }

  //insert
  @Override
  public Cat createCat(Cat cat) {
    if (this.catService.put(cat))
      return cat;

    return null;
  }

  @Override
  public List<Cat> getCats() {
    return CatDatabase.getCats();
    //return List.of(CatDatabase.HOME);
  }

  // http://localhost:8080/cat?id=1
  @Override
  public Cat getCat(Long id) {
    return CatDatabase.find(id).orElse(null);
  }

  @Override
  public Boolean deleteCat(Long id) {
    return CatDatabase.delete(id);
  }

  @Override
  public Boolean updateCat(Long id, Cat cat) {
    return CatDatabase.update(id, cat);
  }

  @Override
  public Boolean patchCatName(Long id, String name) {
    return CatDatabase.patchName(id, name);
  }
}
