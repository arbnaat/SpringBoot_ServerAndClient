package com.sovanm.microservice2.microservice2.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

@RestController
@RequestMapping(path = "/dashboard")
public class MicroserviceTwoController {

	@Autowired
	Environment environment;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private EurekaClient eurekaClient;

	/*
	 * @Value("${service.employyesearch.serviceId}") private String
	 * employeeSearchServiceId;
	 */
	@RequestMapping("/{myself}")
	public EmployeeInfo findme(@PathVariable Long myself) {
		Application application = eurekaClient.getApplication("EMPLOYEESEARCHSERVICE");
		InstanceInfo instanceInfo = application.getInstances().get(0);
		String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "employee/find/" + myself;
		System.out.println("URL" + url);
		EmployeeInfo emp = restTemplate.getForObject(url, EmployeeInfo.class);
		System.out.println("RESPONSE " + emp);

		return emp;
//		return null;
	}

	@RequestMapping("/peers")
	public Collection<EmployeeInfo> findPeers() {
		Application application = eurekaClient.getApplication("EMPLOYEESEARCHSERVICE");
		InstanceInfo instanceInfo = application.getInstances().get(0);
		String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "employee/findall";
		System.out.println("URL" + url);
		Collection<EmployeeInfo> list = restTemplate.getForObject(url, Collection.class);
		System.out.println("RESPONSE " + list);
		return list;
	}

	public String test() {
		String port = environment.getProperty("local.server.port");
		return "Microservice 1 running in :" + port;
	}

}
