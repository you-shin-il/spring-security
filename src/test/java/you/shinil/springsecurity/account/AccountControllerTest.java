package you.shinil.springsecurity.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	AccountService accountService;

	@Test
	@Transactional
	public void login_success1() throws Exception {
		String username = "shinil";
		String password = "2222";
		Account user = this.createUser(username, password);

		mockMvc.perform(formLogin().user(user.getUsername()).password(password))
				.andExpect(authenticated());
	}

	@Test
	@Transactional
	public void login_success2() throws Exception {
		String username = "shinil";
		String password = "1111";
		Account user = this.createUser(username, password);

		mockMvc.perform(formLogin().user(user.getUsername()).password(password))
				.andExpect(authenticated());
	}

	@Test
	@Transactional
	public void login_fail() throws Exception {
		String username = "keesun";
		String password = "1111";
		Account user = this.createUser(username, password);

		mockMvc.perform(formLogin().user(user.getUsername()).password("1234"))
				.andExpect(unauthenticated());
	}

	private Account createUser(String username, String password) {
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setRole("ADMIN");
		accountService.createNew(account);
		return account;
	}
}