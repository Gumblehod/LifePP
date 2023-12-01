package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.CourseEntity;
import com.anq.LifePP.Entity.UserEntity;
import com.anq.LifePP.Repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository repo;

	public CourseEntity insertCourse(CourseEntity e) {
		return repo.save(e);
	}

	public List<CourseEntity> getallCourse() {
		return repo.findAll();
	}

	public CourseEntity updateCourse(int id, CourseEntity c) {
		CourseEntity e = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Course " + id + "doesn't exist."));

		e.setName(c.getName());
		e.setMax(c.getMax());

		return repo.save(e);
	}

	public String deleteCourse(int id) {
		CourseEntity c = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Course " + id + "does not exist"));

		if (c.isDeleted()) {
			return "Course #" + id + " is already deleted!";
		} else {
			c.setDeleted(true);
			repo.save(c);
			return "Course #" + id + "has been deleted";
		}
	}

	public List<UserEntity> getUsersEnrolledInCourse(int courseId) {
        CourseEntity course = repo.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course " + courseId + " doesn't exist."));

        return course.getEnrolledUsers();
    }
}
