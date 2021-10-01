package jp.co.internous.ecsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.ecsite.model.dao.UserRepository;
import jp.co.internous.ecsite.model.entity.User;
import jp.co.internouse.ecsite.model.form.UsersForm;

@Controller 
@RequestMapping("/ecsite/admin/user")
public class UserController {
	@Autowired 
	private UserRepository userRepos;
	
	@RequestMapping("/") 
	public String userIndex() {
		return "register_user";
	}
	
	@RequestMapping("/duplicatedUserName")
	@ResponseBody
	public boolean duplicatedUserName(@RequestBody UsersForm f) {

		int count = userRepos.findCountByUserName(f.getUserName());
		return count > 0;
	}
	@RequestMapping("/addUsers")
	public String addUsersApi(UsersForm usersForm) {
		
		User users = new User();
		users.setUserName(usersForm.getUserName());
		users.setPassword(usersForm.getPassword());
		users.setFullName(usersForm.getFullName());
		userRepos.saveAndFlush(users);
		
		return "adminindex";
	}

	}

