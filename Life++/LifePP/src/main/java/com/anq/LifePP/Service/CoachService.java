package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.CoachEntity;
import com.anq.LifePP.Entity.CourseEntity;
import com.anq.LifePP.Repository.CoachRepository;
import com.anq.LifePP.Repository.CourseRepository;

@Service
public class CoachService {

	@Autowired
	CoachRepository repo;
    @Autowired
    CourseRepository courseRepo;

	public CoachEntity insertCoach(CoachEntity e) {
		return repo.save(e);
	}

	public List<CoachEntity> getallCoach() {
		return repo.findAll();
	}

	public CoachEntity updateCoach(int id, CoachEntity updatedCoach) {
		CoachEntity existingCoach = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Coach " + id + " doesn't exist."));
	
		if (updatedCoach.getEmail() != null && !updatedCoach.getEmail().isEmpty()) {
			existingCoach.setEmail(updatedCoach.getEmail());
		}
		if (updatedCoach.getName() != null && !updatedCoach.getName().isEmpty()) {
			existingCoach.setName(updatedCoach.getName());
		}
		if (updatedCoach.getUsername() != null && !updatedCoach.getUsername().isEmpty()) {
			existingCoach.setUsername(updatedCoach.getUsername());
		}
	
		return repo.save(existingCoach);
	}
	

	public String deleteCoach(int id) {
		CoachEntity c = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Coach " + id + "does not exist"));

		if (c.isDeleted()) {
			return "Coach #" + id + " is already deleted!";
		} else {
			c.setDeleted(true);
			repo.save(c);
			return "Coach #" + id + "has been deleted";
		}
	}
	
    public CoachEntity getCoachByUsername(String username) {
        return repo.findByUsername(username);
    }

	public List<CourseEntity> getCoursesByCoachId(int coachId) {

        CoachEntity c = repo.findById(coachId)
				.orElseThrow(() -> new NoSuchElementException("Coach " + coachId + "does not exist"));

        if (c != null) {
            return c.getCourses();
        }

		return null;
    }
}
