package com.sovanm.microservice3.microservice3.controller;

import java.util.Collection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="EmployeeSearchService" )//Service Id of EmployeeSerach service
public interface EmployeeServiceProxy {
   @RequestMapping("/employee/find/{id}")
   public EmployeeInfo findById(@PathVariable(value="id") Long id);
   @RequestMapping("/employee/findall")
   public Collection<EmployeeInfo> findAll();
}