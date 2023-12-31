package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.CoachEntity;
import com.anq.LifePP.Entity.CourseEntity;
import com.anq.LifePP.Entity.QuestEntity;
import com.anq.LifePP.Repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository repo;
	@Autowired
	CoachService cs;
	@Autowired
	private QuestService qs;

	public CourseEntity insertCourse(CourseEntity course) {
		CoachEntity c = course.getCoach();
		c.addCourseToCourses(course);
		return repo.save(course);
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

	public CourseEntity addQuestToCourse(int courseId, QuestEntity quest) {
		CourseEntity course = repo.findById(courseId)
				.orElseThrow(() -> new NoSuchElementException("Course " + courseId + " doesn't exist."));

		quest.setCourse(course);
		course.getQuests().add(quest);
		qs.insertQuest(quest);

		return repo.save(course);
	}

	public CourseEntity getCourseById(int id) {
		return repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Course with ID " + id + " not found."));
	}

	public List<QuestEntity> getQuestsByCourseId(int id) {
		CourseEntity course = getCourseById(id);
		return course.getQuests();
	}
}
