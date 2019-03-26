package com.atguigu.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;

@RestController
public class DeptController {
	@Autowired
	private DeptService service;
	
	/**
	 * 服务发现接口
	 */
	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return service.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return service.get(id);
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return service.list();
	}
	
	 @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	  public Object discovery()
	  {
	    List<String> list = client.getServices(); //判断eureka中的微服务有多少个,有哪一些
	    System.out.println("**********" + list);

	    List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT"); //MICROSERVICECLOUD-DEPT的名字
	    for (ServiceInstance element : srvList) {
	    	//getServiceId 微服务的id  getHost主机名字  getPort端口  getUri地址
	     System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
	         + element.getUri());
	    }
	    return this.client;
	  }

}
