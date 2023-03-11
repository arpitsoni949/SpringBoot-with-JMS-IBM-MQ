package com.example.ibmmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableJms
@RequestMapping("/demo/")
public class TestController {
	
	  @Autowired
	  private JmsTemplate jmsTemplate;

	  @RequestMapping("send")
	  public String sendMessage(){
	      try{
	    	  String msg="IBM MQ integration testing with spring boot";
	          jmsTemplate.convertAndSend("DEV.QUEUE.1", msg);
	          System.out.println("Message Sent :"+msg);
	          return "OK";
	      }catch(JmsException ex){
	          ex.printStackTrace();
	          return "FAIL";
	      }
	  }
	  
	  @RequestMapping("recv")
	  public String receiveMessage(){
		  String msgRecevd=null;
	      try{
	    	  for(int i=0;i<5;i++) {
	    		  msgRecevd=jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
		    	  System.out.println("Message Received :"+ msgRecevd);
	    	  }
	          return msgRecevd;
	          
	      }catch(JmsException ex){
	          ex.printStackTrace();
	          return "FAIL";
	      }
	  }
	  
}
