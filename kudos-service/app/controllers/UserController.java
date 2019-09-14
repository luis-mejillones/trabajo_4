//package controllers;
//
//import com.impetus.client.cassandra.common.CassandraConstants;
//import model.User;
//import play.mvc.Controller;
//import play.mvc.Result;
//
//import javax.inject.Singleton;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.util.HashMap;
//import java.util.Map;
//
//@Singleton
//public class UserController extends Controller {
//    private EntityManagerFactory emf;
//
//    public Result persist() {
//        EntityManager em = getEmf().createEntityManager();
//
//        User user = new User();
//        user.setUserId("0001");
//        user.setFirstName("John");
//        user.setLastName("Smith");
//        user.setCity("London");
//
//        em.persist(user);
//        em.close();
//
//        return ok("User 0001 record persisted for persistence unit cassandra_pu");
//    }
//
//    public Result find() {
//        EntityManager em = getEmf().createEntityManager();
//        User user = em.find(User.class, "0001");
//        em.close();
//
//        return ok("Found User in database with the following details:" + printUser(user));
//    }
//
//    public Result update() {
//        EntityManager em = getEmf().createEntityManager();
//        User user = em.find(User.class, "0001");
//        user.setCity("New York");
//        em.merge(user);
//        user = em.find(User.class, "0001");
//
//        return ok("Record updated:" + printUser(user));
//    }
//
//    public Result delete() {
//        EntityManager em = getEmf().createEntityManager();
//        User user = em.find(User.class, "0001");
//
//        em.remove(user);
//        user = em.find(User.class, "0001");
//
//        return ok("Record deleted:" + printUser(user));
//    }
//
//    private EntityManagerFactory getEmf() {
//        Map<String, String> props = new HashMap<>();
//        props.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
//
//        if (emf == null) {
//            emf = Persistence.createEntityManagerFactory("cassandra_pu", props);
//        }
//
//        return emf;
//    }
//
//    private String printUser(User user) {
//        if (user == null) {
//            return "Record not found";
//        }
//
//        return "\n--------------------------------------------------" + "\nuserId:" + user.getUserId() + "\nfirstName:"
//                + user.getFirstName() + "\nlastName:" + user.getLastName() + "\ncity:" + user.getCity();
//    }
//}
