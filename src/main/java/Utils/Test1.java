package Utils;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.java.dao.UserMapper;
import com.java.utils.User;

public class Test1 {
	SqlSessionFactory sqlSessionFactory;

	@Test
	public void testMybatis() {
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        try {
            sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
            SqlSession session = sqlSessionFactory.openSession();
            UserMapper userMapper = session.getMapper(UserMapper.class);
            List<User> users = userMapper.selectUsers();
            for (User user : users) {
				System.out.println(user);
			}
     } catch (Exception e) {
		e.printStackTrace();
	}
	}
}
