package cn.itcast.dao;

import cn.itcast.domain.Account;
import cn.itcast.utils.JdbcUtils_dbcp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yvettee on 2017/10/9.
 */
public class AccountDao {
    private Connection conn;

    public AccountDao() {

    }

    //根据传进来的连接操作数据库
    public AccountDao(Connection conn) {
        this.conn = conn;
    }

    public void update(Account a) {
        try {
            QueryRunner runner = new QueryRunner();
            String sql = "update account set money=? where id=?";
            Object params[] = {a.getMoney(), a.getId()};
            runner.update(JdbcUtils_dbcp.getConnection(), sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account find(int id) {
        try {
            QueryRunner runner = new QueryRunner();
            String sql = "select * from account where id=?";
            return (Account) runner.query(JdbcUtils_dbcp.getConnection(), sql, id, new BeanHandler(Account.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
