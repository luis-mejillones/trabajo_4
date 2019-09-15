package services;

import model.User;
import org.springframework.util.StringUtils;
import play.Logger;
import play.mvc.Result;

import java.util.List;


public class UserService {

    public UserService() {
    }

    public User create(User user) {
        user.save();
        Logger.info("User created with id: " + user.id);

        return user;
    }

    public List<User> getAll() {
        List<User> list = User.find.all();
        Logger.info("Users retrieved: " + list.size());

        return list;
    }

    public User getById(Integer id) {
        User out = User.find.ref(id);
        Logger.info("User find by id: " + out.id);

        return out;
    }

    public void delete(Integer id) {
        User.find.ref(id).delete();
        Logger.info("User delete with id: " + id);
    }

    public List<User> find(String nickname, String name) {
        if (!StringUtils.isEmpty(nickname) && StringUtils.isEmpty(name)) {
            return this.findByNickname(nickname);
        }

        if (StringUtils.isEmpty(nickname) && !StringUtils.isEmpty(name)) {
            return this.findByName(name);
        }

        return this.findByBoth(nickname, name);
    }

    private List<User> findByBoth(String nickname, String name) {
        List<User> list = User.find.query().where()
                .or()
                .ilike("nickname", "%" + nickname + "%")
                .ilike("full_name", "%" + name + "%")
                .endOr()
                .orderBy("id asc")
                .setFirstRow(0)
                .setMaxRows(100)
                .findPagedList()
                .getList();

        Logger.info("User find by nickname and name criteria '" + nickname + " or " + name + "'");
        Logger.info("User records found: " + list.size());

        return list;
    }

    private List<User> findByNickname(String nickname) {
        List<User> list = User.find.query().where()
                .ilike("nickname", "%" + nickname + "%")
                .orderBy("id asc")
                .setFirstRow(0)
                .setMaxRows(100)
                .findPagedList()
                .getList();

        Logger.info("User find by nickname criteria '" + nickname + "'");
        Logger.info("User records found: " + list.size());

        return list;
    }

    private List<User> findByName(String name) {
        List<User> list = User.find.query().where()
                .ilike("full_name", "%" + name + "%")
                .orderBy("id asc")
                .setFirstRow(0)
                .setMaxRows(100)
                .findPagedList()
                .getList();

        Logger.info("User find by name criteria '" + name + "'");
        Logger.info("User records found: " + list.size());

        return list;
    }
}
