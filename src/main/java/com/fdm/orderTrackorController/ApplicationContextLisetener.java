package com.fdm.orderTrackorController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fdm.orderTrackor.Courier;
import com.fdm.orderTrackor.CourierService;
import com.fdm.orderTrackor.CourierStatus;

public class ApplicationContextLisetener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// init couriers
		CourierService courierService = PersistUtil.getCourierService();
		Courier courier1 = new Courier();
		courier1.setStatus(CourierStatus.WAITING);
		Courier courier2 = new Courier();
		courier2.setStatus(CourierStatus.WAITING);
		Courier courier3 = new Courier();
		courier3.setStatus(CourierStatus.WAITING);
		Courier courier4 = new Courier();
		courier4.setStatus(CourierStatus.WAITING);
		Courier courier5 = new Courier();
		courier5.setStatus(CourierStatus.WAITING);
		Courier courier6 = new Courier();
		courier6.setStatus(CourierStatus.WAITING);

		courierService.persistCourier(courier1);
		courierService.persistCourier(courier2);
		courierService.persistCourier(courier3);
		courierService.persistCourier(courier4);
		courierService.persistCourier(courier5);
		courierService.persistCourier(courier6);
		
		List<Courier> couriers = new ArrayList<>();
		
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("couriers", couriers);

	}

	
}
