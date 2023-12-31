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

import com.anq.LifePP.Entity.CourseEntity;
import com.anq.LifePP.Entity.QuestEntity;
import com.anq.LifePP.Service.CourseService;


@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {
	
	@Autowired
	CourseService s;

	@GetMapping("/print")
	public String hello(){
		return "It works";
	}
	
	@PostMapping("/insert")
    public CourseEntity insertCourse(@RequestBody CourseEntity courseData) {
        return s.insertCourse(courseData);
    }
	
    @GetMapping("/get")
    public List<CourseEntity> getAllCourses(){
    	return s.getallCourse();
    }
    
    @PutMapping("/update")
    public CourseEntity updateStudent(@RequestParam int sid, @RequestBody CourseEntity n){
    	return s.updateCourse(sid, n);
    }
    
    @DeleteMapping("/delete/{sid}")
    public String deleteStudent(@PathVariable int sid) {
    	return s.deleteCourse(sid);
    }

    @PostMapping("/{courseId}/addquest")
    public CourseEntity addQuestToCourse(@PathVariable int courseId, @RequestBody QuestEntity quest) {
        CourseEntity updatedCourse = s.addQuestToCourse(courseId, quest);
        return updatedCourse;
    }

    @GetMapping("/{id}/getquests")
    public List<QuestEntity> getQuestsByCourseId(@PathVariable int id) {
        return s.getQuestsByCourseId(id);
    }
}
