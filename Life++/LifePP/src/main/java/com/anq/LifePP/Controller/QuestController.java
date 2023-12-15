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

import com.anq.LifePP.Entity.QuestEntity;
import com.anq.LifePP.Service.QuestService;

@RestController
@RequestMapping("/quest")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestController {

	@Autowired
	QuestService s;

	@GetMapping("/print")
	public String hello() {
		return "It works";
	}

	@PostMapping("/insert")
	public QuestEntity insertQuest(@RequestBody QuestEntity e) {
		return s.insertQuest(e);
	}

	@GetMapping("/get")
	public List<QuestEntity> getAllQuests() {
		return s.getallQuest();
	}

	@PutMapping("/update")
	public QuestEntity updateStudent(@RequestParam int sid, @RequestBody QuestEntity n) {
		return s.updateQuest(sid, n);
	}

	@DeleteMapping("/delete/{sid}")
	public String deleteStudent(@PathVariable int sid) {
		return s.deleteQuest(sid);
	}
	
	@PostMapping("{qid}/addachievement/{aid}")
	public QuestEntity addAchievement(@PathVariable int qid, @PathVariable int aid){
		return s.addAchievement(qid,aid);
	}
}
