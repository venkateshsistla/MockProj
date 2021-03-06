package tradingApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mock.project.config.AppConfig;
import com.mock.project.model.Order;
import com.mock.project.model.User;
import com.mock.project.service.OrderService;
import com.mock.project.service.PMServices;


@Controller
public class PMController {

	@RequestMapping("/views/CreateOrder")
	public ModelAndView addCreateOrder(@ModelAttribute("order") Order d, HttpServletRequest request){

		User u = (User)request.getSession().getAttribute("user");
		System.out.println("ADD CREATE ORDER, I AM CHECKING SESSIONS USER PERSISTENCE: " + u.toString());
		String traderName = null;
		String portfolioName = null;
		traderName = request.getParameter("traderName");
		portfolioName = request.getParameter("portfolioName");
		d.setStatus("New");
		System.out.println(d);
		System.out.println("TraderName = " + traderName );
		System.out.println("PortfolioName = " + portfolioName );
		AbstractApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
		container.registerShutdownHook();
		PMServices pmservice=(PMServices) container.getBean("PMService");
		Long traderId;
		Long portfolioId;
		traderId = pmservice.getTraderId(traderName);
		portfolioId = pmservice.getPortfolioId(portfolioName);
		d.setTraderId(traderId);
		d.setPortfolioId(portfolioId);
		OrderService orderService = container.getBean(OrderService.class);		
		d.setPmId(u.getId());
		orderService.addOrder(d);
		container.close();
		ModelAndView view = new ModelAndView("redirect:CreateTrade.jsp");
		return view;
	}
	
	
@RequestMapping(value = "/EditOrderPM", method = RequestMethod.POST)
public ModelAndView editorder(@ModelAttribute("order") Order d) {
	    
	//Edit order code
	
	   return new ModelAndView("Error");
}

@RequestMapping(value = "/AmmendOrder", method = RequestMethod.POST)
public ModelAndView ammendorder(@ModelAttribute("order") Order d) {

	//Ammend order code
	return new ModelAndView("Error");
}

/*@RequestMapping(value = "views/ammendTable", method = RequestMethod.GET)
public ModelAndView methodToAmmendTable(HttpServletRequest req) 
{
	Long id = req.getParameter(name)
	AbstractApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
	container.registerShutdownHook();
	PMServices pms = container.getBean(PMServices.class);
	Order p = new Order();
	p = pms.findByPrimaryKey(d.getOrderId());
	ModelAndView model = new ModelAndView("EditOrder");
	model.addObject("Order",p);
	return model;
}  */


@RequestMapping(value = "views/DeleteOrder", method = RequestMethod.POST)
public ModelAndView deleteorder(HttpServletRequest req) {
	
	       AbstractApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
	       container.registerShutdownHook();
	       PMServices pmservice = container.getBean(PMServices.class);
	       String out=req.getParameter("message");
	       System.out.println("out:"+out);
			Long idl = Long.parseLong(out);
			System.out.println("controller"+idl);
	    pmservice.findOrderForUpdate(idl);
	    ModelAndView view = new ModelAndView("PendingOrder");
	    container.close();
	    return view;
	}



@RequestMapping(value = "/ViewHistory", method = RequestMethod.POST)
public ModelAndView viewhistory(@ModelAttribute("order") Order d) {
	//view history code
	return new ModelAndView("Error");
}

@RequestMapping(value = "/ViewSpecificTrade", method = RequestMethod.POST)
public ModelAndView viewspecifictrade(@ModelAttribute("order") Order d) {

	//view specific trade code
	return new ModelAndView("Error");
}


@RequestMapping(value="views/ViewOrderBlotter", method=RequestMethod.GET)
public ModelAndView UpdateOrder(HttpServletRequest req){
		AbstractApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
container.registerShutdownHook();
    PMServices pmsendtotrader = (PMServices) container.getBean("PMService");
  	List<Order> p=new ArrayList<Order>();  
  	User user = (User) req.getSession().getAttribute("user");
  	Long pmId =user.getId();
  	p=pmsendtotrader.displayForPMAfterSend(pmId);
  
  for(Order l: p){

  	}
  ModelAndView model = new ModelAndView("OrderBlotter");
	model.addObject("Orders",p);
container.close();
	return model;
  	
}


@RequestMapping(value="views/SendToTrader", method=RequestMethod.POST)
public ModelAndView UpdateStatus(HttpServletRequest req){
	AbstractApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
	container.registerShutdownHook();
	   
	PMServices pmservice=(PMServices) container.getBean("PMService"); 
	

	 List<Long> orderId=new ArrayList<Long>();
	 List<String> orderStatus=new ArrayList<String>();
		String[] out=req.getParameterValues("data");
		String[] ids=out[0].split(",");
	   
		for(String order:ids)
		{
			orderId.add(Long.parseLong(order));
			
		}
		for(Long order:orderId)
		{
			System.out.println(order);
			
		}
		
		pmservice.updateStatus(orderId);
	
		  	List<Order> p=new ArrayList<Order>();
		  
		  	User user = (User) req.getSession().getAttribute("user");
		  	Long pmId =user.getId();
		  	p=pmservice.displayForPMAfterSend(pmId);

		 for(Order l: p){
	
		  	System.out.println(l);
		  	}
		  ModelAndView model = new ModelAndView("OrderBlotter");
			model.addObject("Orders",p);
		container.close();
			return model;
  	
}

@RequestMapping(value = "views/ViewPendingOrder", method = RequestMethod.GET)
public ModelAndView viewpendingorder(@ModelAttribute("order") Order d, HttpServletRequest req) {
       AbstractApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
    container.registerShutdownHook();
    PMServices pmsendtotrader = (PMServices) container.getBean("PMService");
       List<Order> p=new ArrayList<Order>();
       User user = (User) req.getSession().getAttribute("user");
       Long pmId =user.getId();
       p=pmsendtotrader.displayPendingForPM(pmId);
       System.out.println("p:"+p);
       List<Order> q=new ArrayList<Order>();
       for(Order l: p){
              String stat = l.getStatus();
              if(stat.equalsIgnoreCase("OPEN"))
                     q.add(l);
             
       }
       ModelAndView model = new ModelAndView("PendingOrder");
       model.addObject("Orders",q);
       return model;
}

@RequestMapping(value="views/fetchTraderList", method=RequestMethod.POST)
public void getTraderList(HttpServletRequest request,HttpServletResponse response) throws IOException{
	AbstractApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
	container.registerShutdownHook();   
	PMServices pmservice=(PMServices) container.getBean("PMService"); 
	List<String> traderNameList = new ArrayList<String>();
	traderNameList = pmservice.getTraderNameList();
	String toSend = "";
	for(String t : traderNameList) {
		toSend += t;
		toSend += ",";
	}
	PrintWriter out = response.getWriter();
	out.print(toSend);
	out.close();
}



}