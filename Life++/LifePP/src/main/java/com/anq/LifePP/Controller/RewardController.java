package com.anq.LifePP.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anq.LifePP.Entity.RewardEntity;
import com.anq.LifePP.Service.RewardService;

@RestController
@RequestMapping("/reward")
public class RewardController {
	
	@Autowired
	RewardService s;
	
	@GetMapping("/print")
	public String hello(){
		return "It works";
	}
	
	@PostMapping("/insert")
	public RewardEntity insertReward(@RequestBody RewardEntity e) {
		return s.insertReward(e);
	}
	
    @GetMapping("/get")
    public List<RewardEntity> getAllRewards(){
    	return s.getallReward();
    }
    
    @PutMapping("/update")
    public RewardEntity updateStudent(@RequestParam int sid, @RequestBody RewardEntity n){
    	return s.updateReward(sid, n);
    }
    
    @DeleteMapping("/delete/{sid}")
    public String deleteStudent(@PathVariable int sid) {
    	return s.deleteReward(sid);
    }
}
