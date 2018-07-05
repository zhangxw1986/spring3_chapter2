/**
 * 对于bean的配置，注解配置的方式比xml配置的方式更简单明显，已成为一种趋势。
 * @Repository定义了一个DAO Bean，@Autowired将spring容器中的bean注入进来
 * 传统JDBC API底层，对于最简单的sql查询也要经过：获取连接-创建Statement-执行数据操作-获取结果-关闭Statement-关闭结果集-关闭连接，重复操作太多
 * spring jdbc通过模板类-org.springframework.jdbc.core.JdbcTemplate封装了样板式代码，简化数据访问操作
 * 
 */

package com.baobaotao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.baobaotao.domain.User;

/**
 * @author Administrator
 *功能：该类是用来访问User表（数据库表为t_user）的DAO，通过jdbc技术实现这个DAO类，包括三个方法：
 *
 */
@Repository   //通过Spring注解定义一个DAO
public class UserDao {

	@Autowired   //自动注入JdbcTemplate的Bean
	private JdbcTemplate jdbcTemplate;

	/**
	 *功能：根据用户名和密码获取匹配的用户数。
	 *这是最简单的用户身份认证方法，在实际应用中需要采用诸如密码加密等安全策略。
	 *这个方法仅提供一个查询方法，可直接调用模板的queryForInt方法就可以获取查询，用户不用担心获取连接、关闭连接、异常处理等事。
	 * @param userName 输入的用户名
	 * @param password 输入的密码
	 * @return 返回查询结果int值：1表示用户名/密码正确。0表示用户名或密码错误。
	 */
	public int getMatchCount(String userName, String password){
		String sqlStr="SELECT count(*) FROM t_user "
				+ " WHERE user_name=? and password=?";
		return jdbcTemplate.queryForInt(sqlStr,new Object[]{userName,password});
	}

	/**
	 * 功能：根据用户名获取User对象
	 * 
	 * @param userName 输入的用户名
	 * @return 返回User对象
	 */
	public User findUserByUserName(final String userName){
		//根据用户名查询用户的sql语句
		String sqlStr="SELECT user_id,user_name,credits "
				+ " FROM t_user WHERE user_name=? ";
		final User user=new User();
/**
 * jdbcTemplate#query()方法为 query(String sql, Object[] args,RowCallBackHandler rch),三个参数：
 * @param sqlStr是查询的sql语句，允许使用带"?"的参数占位符
 * @param args sql语句中占位符对应的参数数组
 * @param RowCallbackHandler 查询结果的处理回调接口，该回调接口有一个方法processRow（ResultSet rs）,
 *        负责将查询的结果从ResultSet装载到类似于领域对象的对象实例中。
 */		
		jdbcTemplate.query(sqlStr,new Object[] {userName},
				new RowCallbackHandler(){
			//匿名类方式实现了一个回调接口RowCallbackHandler实例，将ResultSet转化为User对象
			public void processRow(ResultSet rs) throws SQLException{
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(userName);
				user.setCredits(rs.getInt("credits"));
			}
		});
		return user;
	}
	
	/**
	 * 功能：更新用户积分、最后登录IP以及最后登录时间
	 * @param user 用户对象
	 */
	public void updateLoginInfo(User user){
		String sqlStr="UPDATE t_user SET last_visit=?,last_ip=?,credits=? "
				+ "WHERE user_id=? ";
		
	/**
	 * 通过JdbcTemplate#update(String sql,Object[])进行的数据更换操作
	 */
		jdbcTemplate.update(sqlStr,new Object[] {user.getLastVisit(),
				user.getLastIp(),user.getCredits(),user.getUserId()});

	}

}
