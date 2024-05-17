package com.booking.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.booking.models.Service;

public class ServiceRepository {
	public  List<Service> getAllService() {
		
		List<Service> serviceList = new ArrayList<>();

        
        Service service1 = Service.builder().serviceId("Serv-01").serviceName("Potong Rambut").price(75_000).build();
        Service service2 = Service.builder().serviceId("Serv-02").serviceName("Styling Rambut").price(125_000).build();
        Service service3 = Service.builder().serviceId("Serv-03").serviceName("Pewarnaan Rambut").price(500_000).build();
        Service service4 = Service.builder().serviceId("Serv-04").serviceName("Rebondingt").price(60_000).build();
        Service service5 = Service.builder().serviceId("Serv-05").serviceName("Mengeriting Rambut").price(70_000).build();
        
        serviceList.addAll(Arrays.asList(service1, service2, service3, service4, service5));

		return serviceList;
	}
}
