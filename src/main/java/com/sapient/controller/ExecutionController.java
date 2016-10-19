package com.sapient.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sapient.config.AppConfig;
import com.sapient.main.Login;
import com.sapient.model.User;
import com.sapient.service.BrokerService;
import com.sapient.service.BrokerServiceImpl;
import com.sapient.service.UserService;

@Controller
public class ExecutionController {

	@RequestMapping("/views/startStopService")
	public String executionStartStop(HttpServletRequest req) {
		String start = req.getParameter("start");
		System.out.println("username: " + start);
/*		System.out.println("executionStartStop");
		String stop = req.getParameter("stop");
		System.out.println("password: " + stop);*/

		AbstractApplicationContext container = new AnnotationConfigApplicationContext(
				com.sapient.config.AppConfig.class);
		container.registerShutdownHook();
		BrokerService brokerService = (BrokerService) container.getBean("brokerService");

		brokerService.StartExecution();
		
		return "redirect:BrokerMainScreen.jsp";
		
		
		
		/*User user = new User();
		user.setUser_name(uname);
		user.setPassword(pass);
		Login l = new Login();

		String vm = l.checkuser(user);
		if (vm.equals("Valid user"))
			return new ModelAndView("redirect:BrokerMainScreen.jsp", "message", vm);
		else
			return new ModelAndView("redirect:Login.jsp", "message", vm);
*/
	}
}