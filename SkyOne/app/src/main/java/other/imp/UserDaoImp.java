package other.imp;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import other.bean.User;
import other.dao.UserDao;
import other.util.PostUtils;

/**
 * Created by willj on 2017/2/15.
 */

public class UserDaoImp implements UserDao{

    @Override
    public void register(User user) {

    }

    @Override
    public boolean login(User user) {

        return false;
    }

    @Override
    public void loginOut(String username) {

    }
}
