package other.dao;

import other.bean.User;

/**
 * Created by willj on 2017/2/15.
 */

public interface UserDao {
    public void register(User user);
    public boolean login(User user);
    public void loginOut(String username);
}
