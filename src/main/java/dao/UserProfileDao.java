package dao;

import entities.UserDetail;
import entities.UserProfile;

import java.time.LocalDate;
import java.util.List;

public interface UserProfileDao {

    void createUserProfile(UserProfile userProfile);
    UserProfile getUserProfileById(Long id);
    void updateUserProfileEmail(Long userId ,String email);
    void deleteUserProfileById(Long id);
    UserProfile findUserProfileByEmail(String email);
    List<UserProfile> getUserAfterRegisteredDate(LocalDate date);

}
