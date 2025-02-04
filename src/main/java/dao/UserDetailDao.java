package dao;

import entities.UserDetail;
import entities.UserProfile;

import java.util.List;

public interface UserDetailDao {
    void createUserDetail(Long userId, UserDetail userDetail);
    UserDetail getUserDetailById(Long userdetailId);
    void updateUserDetailAdrress(Long userDetailId, String new_adress);
    void deleteUserDetailById(Long userDetailId);
    UserDetail getUserDetailByAddress(String address);
   List<UserDetail> getSorttedUserProfileByEmail();
}
