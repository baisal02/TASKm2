package dao.daoimpl;

import config.HibernateConfig;
import dao.UserDetailDao;
import entities.UserDetail;
import entities.UserProfile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailDaoImpl implements UserDetailDao {
    private final EntityManagerFactory factory = HibernateConfig.getEntityManagerFactory();

    @Override
    public void createUserDetail(Long userId, UserDetail userDetail) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            UserProfile userProfile= em.find(UserProfile.class, userId);
            userProfile.setUserDetail(userDetail);
            userDetail.setUserProfile(userProfile);
            em.merge(userProfile);
            em.persist(userDetail);
            em.getTransaction().commit();
        }catch (HibernateException h){
            System.out.println(h.getMessage());;
        }
    }

    @Override
    public UserDetail getUserDetailById(Long userdetailId) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            UserDetail userDetail = em.find(UserDetail.class, userdetailId);
            em.getTransaction().commit();
            return userDetail;
        }catch (HibernateException h){
            System.out.println(h.getMessage());;
        }
        return null;
    }

    @Override
    public void updateUserDetailAdrress(Long userDetailId, String new_adress) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            UserDetail userDetail = em.find(UserDetail.class, userDetailId);
            userDetail.setAddress(new_adress);
            em.getTransaction().commit();
        }catch (HibernateException h){
            System.out.println(h.getMessage());;
        }
    }

    @Override
    public void deleteUserDetailById(Long userDetailId) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            UserDetail userDetail = em.find(UserDetail.class, userDetailId);
            em.remove(userDetail);
            em.getTransaction().commit();
        }catch (HibernateException h){
            System.out.println(h.getMessage());;
        }
    }

    @Override
    public UserDetail getUserDetailByAddress(String address) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            UserDetail userDetail = em.find(UserDetail.class, address);
            em.getTransaction().commit();
            return userDetail;
        }catch (HibernateException h){
            System.out.println(h.getMessage());;
        }
        return null;
    }

    @Override
    public List<UserDetail> getSorttedUserProfileByEmail() {
        List<UserDetail> userDetails = new ArrayList<>();
        List<UserDetail>sorttedList = new ArrayList<>();
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            userDetails = em.createQuery("from UserDetail", UserDetail.class).getResultList();
            em.getTransaction().commit();
        }catch (HibernateException h){
            System.out.println(h.getMessage());;
        }
        //sortting
        sorttedList = userDetails.stream()
                .sorted(Comparator.comparing(UserDetail::getDate_of_birth))
                .collect(Collectors.toList());

        return sorttedList;
    }
}
