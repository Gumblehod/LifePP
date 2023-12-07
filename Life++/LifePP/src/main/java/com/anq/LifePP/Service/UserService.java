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

	public UserEntity updateUser(int id, UserEntity updatedUser) {
		UserEntity existingUser = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("User " + id + " doesn't exist."));

		if (updatedUser.getBirthdate() != null) {
			existingUser.setBirthdate(updatedUser.getBirthdate());
		}
		if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
			existingUser.setEmail(updatedUser.getEmail());
		}
		if (updatedUser.getFname() != null && !updatedUser.getFname().isEmpty()) {
			existingUser.setFname(updatedUser.getFname());
		}
		if (updatedUser.getLname() != null && !updatedUser.getLname().isEmpty()) {
			existingUser.setLname(updatedUser.getLname());
		}
		if (updatedUser.getGender() != null && !updatedUser.getGender().isEmpty()) {
			existingUser.setGender(updatedUser.getGender());
		}
		if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
			existingUser.setPassword(updatedUser.getPassword());
		}
		if (updatedUser.getPnum() != null && !updatedUser.getPnum().isEmpty()) {
			existingUser.setPnum(updatedUser.getPnum());
		}
		if (updatedUser.getType() != 0) {
			existingUser.setType(updatedUser.getType());
		}
		if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty()) {
			existingUser.setUsername(updatedUser.getUsername());
		}

		return repo.save(existingUser);
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
			int currentParticipants = course.getParticipants();
			int maxParticipants = course.getMax();

			if (currentParticipants < maxParticipants) {
				userCourses.add(course);
				user.setJoinedCourses(userCourses);

				course.setParticipants(currentParticipants + 1);

				repo.save(user);
				crepo.save(course);

				return "User #" + userId + " successfully joined Course #" + courseId + ". Participants: "
						+ (currentParticipants + 1) + "/" + maxParticipants;
			} else {
				return "Course #" + courseId + " is already full. Cannot enroll more participants.";
			}
		} else {
			return "User #" + userId + " is already enrolled in Course #" + courseId;
		}
	}

	 public UserEntity forgotPassword(String usernameOrEmail, String newPassword) {
        UserEntity user = repo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        user.setPassword(newPassword);
        return repo.save(user);
    }

}
