package com.sapient.jms;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.sapient.config.LoggerConfig;
import com.sapient.model.Block;



public class MarshallAndSend {

	private JmsTemplate jmsTemplate;

	private JAXBContext jaxbContext;
	private Marshaller jaxbMarshaller;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws URISyntaxException, Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MarshallAndSend ms= (MarshallAndSend) context
				.getBean("MessageProducer");

		ActiveMQControl mq = new ActiveMQControl();
		mq.startBroker();
		Date current=new Date();
		Block obj=new Block("BUY","GOOG",300L,"MARKET","OPEN",200.0,300.0,100L,current,120.0); 
		Block obj1=new Block("BUY1","GOOG1",300L,"MARKET1","OPEN1",200.0,300.0,100L,current,120.0);
		List<Block> blockList = new ArrayList<Block>();
		blockList.add(obj);
		blockList.add(obj1);
		
		ms.sendExecutedBlock(blockList);
		
		//ms.sendEmployee();
	}
	
	
	public void marshallAndSendBlock(Block block) throws JAXBException{
		String convertedObj = marshal(block);
		getJmsTemplate().convertAndSend("blockQueue", convertedObj);
	}
			
	
	public void sendExecutedBlock(List<Block> blockList) throws JAXBException{
		LoggerConfig logConfig = new LoggerConfig();
		Logger log = logConfig.getLogConfig();
		if(blockList == null){
			log.info("The list is empty. Please send a list of blocks------------");
			System.exit(0);
		}
		
		for(Block iter : blockList){
			log.info("The Block being sent is" + iter);
			System.out.println("The Block being sent is" + iter);
			marshallAndSendBlock(iter);
		}
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	
	public String marshal(Block object) throws JAXBException {
		OutputStream stream = new ByteArrayOutputStream();
		jaxbContext = JAXBContext.newInstance(Block.class);
		jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.marshal(object, stream);

		return stream.toString();
	}

	
}
