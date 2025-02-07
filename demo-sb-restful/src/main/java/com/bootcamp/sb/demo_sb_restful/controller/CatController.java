package com.bootcamp.sb.demo_sb_restful.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bootcamp.sb.demo_sb_restful.model.Cat;
import com.bootcamp.sb.demo_sb_restful.model.CatDatabase;
import com.bootcamp.sb.demo_sb_restful.service.CatService;

// Controller -> Service -> CatDatabase
@Controller
@ResponseBody
public class CatController {
  @Autowired  // field injection
  private CatService catService;    
  // ! why not use static variable?  DI?
  // ! can I use child of CatService?  Which child will be used if more than 1 childs?

  //@Autowired  // Constructor injection, @Autowired annotation is not required
  public CatController(CatService catService) {
    this.catService = catService; // ! why not new in constructor?
  }

  //insert
  @PostMapping(value = "/cat")
  public Cat createCat(@RequestBody Cat cat) {
    if (this.catService.put(cat))
      return cat;

    return null;
  }

  @GetMapping(value = "/cats")
  public List<Cat> getCats() {
    return CatDatabase.getCats();
    //return List.of(CatDatabase.HOME);
  }

  // http://localhost:8080/cat?id=1
  @GetMapping(value = "/cat")
  public Cat getCat(@RequestParam Long id) {
    return CatDatabase.find(id).orElse(null);
  }

  @DeleteMapping(value = "/cat")
  public Boolean deleteCat(@RequestParam Long id) {
    return CatDatabase.delete(id);
  }

  @PutMapping(value = "/cat")
  public Boolean updateCat(@RequestBody Long id, @RequestBody Cat cat) {
    return CatDatabase.update(id, cat);
  }

  @PatchMapping(value = "/cat/name/{name}")
  public Boolean patchCatName(@RequestParam Long id, @RequestParam String name) {
    return CatDatabase.patchName(id, name);
  }
}
