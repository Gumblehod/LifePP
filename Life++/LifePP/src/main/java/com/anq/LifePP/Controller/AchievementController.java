package com.anq.LifePP.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anq.LifePP.Entity.AchievementEntity;
import com.anq.LifePP.Service.AchievementService;

@RestController
@RequestMapping("/achievement")
@CrossOrigin(origins = "http://localhost:3000")
public class AchievementController {
	
	@Autowired
	AchievementService as;
	
	@GetMapping("/print")
	public String hello(){
		return "It works";
	}
	
	@PostMapping("/insert")
	public AchievementEntity insertAchievement(@RequestBody AchievementEntity e) {
		return as.insertAchievement(e);
	}
	
    @GetMapping("/get")
    public List<AchievementEntity> getAllAchievements(){
    	return as.getallAchievement();
    }
    
    @PutMapping("/update")
    public AchievementEntity updateStudent(@RequestParam int sid, @RequestBody AchievementEntity n){
    	return as.updateAchievement(sid, n);
    }
    
    @DeleteMapping("/delete/{sid}")
    public String deleteStudent(@PathVariable int sid) {
    	return as.deleteAchievement(sid);
    }
}
