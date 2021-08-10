package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      String hql = "SELECT user FROM User user INNER JOIN user.car car WHERE car.model = :model and car.series = :series";
      //Query<User> query = session.createQuery(hql, User.class).
      User user = session.createQuery(hql, User.class).setParameter("model", model).setParameter("series", series).uniqueResult();
      //query.setParameter("model", model);
      //query.setParameter("series", series);
      //User user = query.
      //List<User> users = query.getResultList();
      return user;
   }


}
