package revature.com.test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.exception.CheckException;
import com.revature.service.UserDAOImpl;


public class UserDaoTest {

	private static final UserDAOImpl  daotest_1 = new UserDAOImpl();
	private static final CheckException  daotest_2 = new CheckException();

	@Test
	public void testUserBalance() {
		assertEquals(89, daotest_1.UpdateBalance(89,"user3"));
	}
	@Test
	public void testUseridAvailability() {
		assertEquals(true, daotest_2.checkAvailabilty("user3"));
	}

}
