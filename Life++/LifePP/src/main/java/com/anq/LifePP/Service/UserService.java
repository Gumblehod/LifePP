package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<UserEntity> getallUser() {
		return repo.findAll();
	}

	public UserEntity updateUser(int id, UserEntity c) {
		UserEntity e = repo.findById(id).orElseThrow(() -> new NoSuchElementException("User " + id + "doesn't exist."));

		e.setBirthdate(c.getBirthdate());
		e.setEmail(c.getEmail());
		e.setFname(c.getFname());
		e.setGender(c.getGender());
		e.setLname(c.getLname());
		e.setPassword(c.getPassword());
		e.setPnum(c.getPnum());
		e.setType(c.getType());
		e.setUsername(c.getUsername());

		return repo.save(e);
	}

	public String deleteUser(int id) {
		UserEntity c = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("User " + id + "does not exist"));

		if (c.isDeleted()) {
			return "User #" + id + " is already deleted!";
		} else {
			c.setDeleted(true);
			repo.save(c);
			return "User #" + id + "has been deleted";
		}
	}

	public String joinCourse(int userId, int courseId) {
		UserEntity user = repo.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
		CourseEntity course = crepo.findById(courseId)
				.orElseThrow(() -> new NoSuchElementException("Course not found"));

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
