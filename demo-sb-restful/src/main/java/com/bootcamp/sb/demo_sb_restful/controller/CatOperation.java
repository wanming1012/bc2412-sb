package com.bootcamp.sb.demo_sb_restful.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.sb.demo_sb_restful.model.Cat;

public interface CatOperation {
  //insert
  @PostMapping(value = "/cat")
  public Cat createCat(@RequestBody Cat cat);

  @GetMapping(value = "/cats")
  public List<Cat> getCats();

  // http://localhost:8080/cat?id=1
  @GetMapping(value = "/cat")
  public Cat getCat(@RequestParam Long id);

  @DeleteMapping(value = "/cat")
  public Boolean deleteCat(@RequestParam Long id);

  @PutMapping(value = "/cat")
  public Boolean updateCat(@RequestBody Long id, @RequestBody Cat cat);

  @PatchMapping(value = "/cat/name/{name}")
  public Boolean patchCatName(@RequestParam Long id, @RequestParam String name);
}
