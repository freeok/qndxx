package work.pcdd.qndxx.service;

import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
public interface AuthService {

    /**
     * 用户无密码登录
     */
    R unSafeLogin(String userId, HttpSession session);

    /**
     * 管理员登录
     */
    R<String> login(String userId, String pwd, HttpSession session);

}
