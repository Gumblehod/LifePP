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

import com.anq.LifePP.Entity.ChallengeEntity;
import com.anq.LifePP.Service.ChallengeService;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
	
	@Autowired
	ChallengeService s;
	
	@GetMapping("/print")
	public String hello(){
		return "It works";
	}
	
	@PostMapping("/insert")
	public ChallengeEntity insertChallenge(@RequestBody ChallengeEntity e) {
		return s.insertChallenge(e);
	}
	
    @GetMapping("/get")
    public List<ChallengeEntity> getAllChallenges(){
    	return s.getallChallenge();
    }
    
    @PutMapping("/update")
    public ChallengeEntity updateStudent(@RequestParam int sid, @RequestBody ChallengeEntity n){
    	return s.updateChallenge(sid, n);
    }
    
    @DeleteMapping("/delete/{sid}")
    public String deleteStudent(@PathVariable int sid) {
    	return s.deleteChallenge(sid);
    }
}
