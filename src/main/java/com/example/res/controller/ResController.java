package com.example.res.controller;

import com.example.res.model.Food;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/")
public class ResController {

    ArrayList<Food> foods=new ArrayList<>();

    @GetMapping("food")
    public ResponseEntity getFoods(){
        return ResponseEntity.status(200).body(foods);
    }


    @PostMapping("food")
    public ResponseEntity addFoods(@RequestBody Food food){
        foods.add(food);
        return ResponseEntity.status(200).body("Food is added");
    }

    @GetMapping("food/expired/{id}")
    public ResponseEntity checkExpired(@PathVariable String id){
        LocalDate todayDate=LocalDate.now();

        for (int i = 0; i < foods.size(); i++) {
            if(foods.get(i).getId().equals(id)){
                LocalDate foodDate=LocalDate.parse(foods.get(i).getExpireDate());
                if(todayDate.isBefore(foodDate)||todayDate.isEqual(foodDate)){
                    return ResponseEntity.status(200).body("Food is not expired");
                }else {
                    return ResponseEntity.status(200).body("Food is expired");
                }

            }
        }
        return  ResponseEntity.status(400).body("Invalid ID");
    }

    @PostMapping("food/add-quant/{id}")
    public ResponseEntity addQunat(@PathVariable String id,@RequestBody Integer qunat){
        for (int i = 0; i < foods.size(); i++) {
            if(foods.get(i).getId().equals(id)){
                Integer oldQuant=foods.get(i).getQuant();
                foods.get(i).setQuant(oldQuant+qunat);
                return ResponseEntity.status(200).body("Quant added");
            }
        }
        return ResponseEntity.status(400).body("Invalid id");
    }

    @PostMapping("food/sub-quant/{id}")
    public ResponseEntity subQunat(@PathVariable String id,@RequestBody Integer qunat){
        for (int i = 0; i < foods.size(); i++) {
            if(foods.get(i).getId().equals(id)){
                Integer oldQuant=foods.get(i).getQuant();
                foods.get(i).setQuant(oldQuant-qunat);
                return ResponseEntity.status(200).body("Quant subtract");
            }
        }
        return ResponseEntity.status(400).body("Invalid id");
    }




}
