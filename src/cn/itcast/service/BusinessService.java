package cn.itcast.service;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import cn.itcast.utils.JdbcUtils_dbcp;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yvettee on 2017/10/9.
 */
public class BusinessService {
    @Test
    public void test() throws SQLException {
        transfer(1, 2, 100);
    }

    /*public void transfer(int sourceId, int targetId, double money) throws SQLException {
        Connection conn = null;
        try {
            conn = JdbcUtils_dbcp.getConnection();
            //确保查询和修改都执行了再提交
            conn.setAutoCommit(false);

            AccountDao dao = new AccountDao(conn);
            //找到a账户
            Account a = dao.find(sourceId);//select
            //找到b账户
            Account b = dao.find(targetId);//select

            a.setMoney(a.getMoney() - money);
            b.setMoney(b.getMoney() + money);

            dao.update(a);//update
            dao.update(b);//update
            conn.commit();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }*/

    //用上ThreadLocal的事务管理
    public void transfer(int sourceId, int targetId, double money) throws SQLException {
        Connection conn = null;
        try {
            JdbcUtils_dbcp.startTransaction();
            AccountDao dao = new AccountDao();
            //找到a账户
            Account a = dao.find(sourceId);//select
            //找到b账户
            Account b = dao.find(targetId);//select

            a.setMoney(a.getMoney() - money);
            b.setMoney(b.getMoney() + money);

            dao.update(a);//update
            dao.update(b);//update
            JdbcUtils_dbcp.commitTransaction();
        } finally {
            JdbcUtils_dbcp.closeConnection();
        }
    }


}
