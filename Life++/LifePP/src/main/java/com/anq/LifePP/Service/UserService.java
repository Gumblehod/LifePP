package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.ChallengeEntity;
import com.anq.LifePP.Entity.CourseEntity;
import com.anq.LifePP.Entity.UserEntity;
import com.anq.LifePP.Repository.CourseRepository;
import com.anq.LifePP.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	@Autowired
	CourseRepository crepo;
	
	public UserEntity insertUser(UserEntity e) {
		return repo.save(e);
	}
	
	public List<UserEntity> getallUser(){
		return repo.findAll();
	}
	
	public UserEntity updateUser(int id, UserEntity c) {
		UserEntity e = new UserEntity();
			try {
				e = repo.findById(id).get();
				e.setBirthdate(c.getBirthdate());
				e.setEmail(c.getEmail());
				e.setFname(c.getFname());
				e.setGender(c.getGender());
				e.setLname(c.getLname());
				e.setPassword(c.getPassword());
				e.setPnum(c.getPnum());
				e.setType(c.getType());
				e.setUsername(c.getUsername());
			}
			catch(NoSuchElementException ex) {
				throw new NoSuchElementException("User "+id+"doesn't exist.");
			}finally {
				return repo.save(e);
			}
	}
	
	public String deleteUser(int id) {
		String msg = "";
		
			if(repo.findById(id).get()!=null) {
				if(repo.findById(id).get().isDeleted()){
					msg = "User #" + id + " is already deleted!";
				}
				else{
				UserEntity a = repo.findById(id).get();
				a.setDeleted(true);
				msg = "User #" + id + "has been deleted";
				repo.save(a);
				}
			}
			else {msg = "User #" + id + " doesn't exist";}

			return msg;
	}
	
	public String joinCourse(int userId, int courseId) {
	    UserEntity user = repo.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
	    CourseEntity course = crepo.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course not found"));

	    List<CourseEntity> userCourses = user.getJoinedCourses();
	    if (!userCourses.contains(course)) {
	        userCourses.add(course);
	        user.setJoinedCourses(userCourses);
	        repo.save(user);
	        return "User #" + userId + " successfully joined Course #" + courseId;
	    } else {
	        return "User #" + userId + " is already enrolled in Course #" + courseId;
	    }
	}
}
