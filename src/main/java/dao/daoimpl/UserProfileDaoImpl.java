package dao.daoimpl;

import config.HibernateConfig;
import dao.UserProfileDao;
import entities.UserDetail;
import entities.UserProfile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserProfileDaoImpl implements UserProfileDao {
    private final EntityManagerFactory factory = HibernateConfig.getEntityManagerFactory();
    @Override
    public void createUserProfile(UserProfile userProfile) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(userProfile);
            em.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
    }

    @Override
    public UserProfile getUserProfileById(Long id) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            UserProfile userProfile = em.find(UserProfile.class, id);
            em.getTransaction().commit();
            return userProfile;
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
        return null;
    }

    @Override
    public void updateUserProfileEmail(Long userId, String email) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            UserProfile userFromDataBase = em.find(UserProfile.class, userId);
            userFromDataBase.setEmail(email);
            em.merge(userFromDataBase);
            em.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
    }

    @Override
    public void deleteUserProfileById(Long id) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            UserProfile userFromDataBase = em.find(UserProfile.class, id);
            em.remove(userFromDataBase);
            em.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
    }

    @Override
    public UserProfile findUserProfileByEmail(String email) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            UserProfile userProfile = em.find(UserProfile.class, email);
            em.getTransaction().commit();
            return userProfile;
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
        return null;
    }

    @Override
    public List<UserProfile> getUserAfterRegisteredDate(LocalDate date) {
        List<UserProfile> newAfterList = new ArrayList<>();
        List<UserProfile> userProfiles = newAfterList;
        //all userprofiles
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            userProfiles = em.createQuery("SELECT u FROM UserProfile u",
                            UserProfile.class)
                    .getResultList();
            em.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
        //needed userprofiles
        for (UserProfile userProfile : userProfiles) {
            if (userProfile.getRegistration_date().isAfter(date)) {
                newAfterList.add(userProfile);
            }
        }
        return newAfterList;
    }

}
