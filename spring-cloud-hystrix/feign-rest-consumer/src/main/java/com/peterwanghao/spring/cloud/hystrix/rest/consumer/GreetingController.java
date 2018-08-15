package com.peterwanghao.spring.cloud.hystrix.rest.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**   
 * @ClassName:  GreetingController
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: wanghao
 * @date:   2018年8月15日 下午5:00:42
 * @version V1.0
 * 
 */
@Controller
public class GreetingController {
	@Autowired
	private GreetingClient greetingClient;

	@RequestMapping("/get-greeting/{username}")
	public String getGreeting(Model model, @PathVariable("username") String username) {
		model.addAttribute("greeting", greetingClient.greeting(username));
		return "greeting-view";
	}
}
