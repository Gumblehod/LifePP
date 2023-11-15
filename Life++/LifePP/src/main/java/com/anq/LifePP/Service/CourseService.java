package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.CourseEntity;
import com.anq.LifePP.Repository.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	CourseRepository repo;
	
	public CourseEntity insertCourse(CourseEntity e) {
		return repo.save(e);
	}
	
	public List<CourseEntity> getallCourse(){
		return repo.findAll();
	}
	
	public CourseEntity updateCourse(int id, CourseEntity c) {
		CourseEntity e = new CourseEntity();
			try {
				e = repo.findById(id).get();
				e.setName(c.getName());
				e.setMax(c.getMax());
			}
			catch(NoSuchElementException ex) {
				throw new NoSuchElementException("Course "+id+"doesn't exist.");
			}finally {
				return repo.save(e);
			}
	}
	
	public String deleteCourse(int id) {
		String msg = "";
		
			if(repo.findById(id).get()!=null) {
				repo.deleteById(id);
				msg = "Course " + id + "has been deleted";
			}
			else {msg = "Course " + id + "doesnt't exist";}
			
			return msg;
	}
}
