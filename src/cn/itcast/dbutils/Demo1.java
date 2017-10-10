package cn.itcast.dbutils;

import cn.itcast.utils.JdbcUtils_dbcp;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by yvettee on 2017/10/9.
 */
public class Demo1 {
    @Test
    public void insert() throws SQLException {
        //做插入时找数据库连接池
        QueryRunner runner = new QueryRunner(JdbcUtils_dbcp.getDataSource());
        String sql="insert into users(id,name,password,email,birthday) values(?,?,?,?,?)";
        Object params[] = {4,"aaa","111","aaa@sina.com",new Date()};
        runner.update(sql,params);

    }
}
