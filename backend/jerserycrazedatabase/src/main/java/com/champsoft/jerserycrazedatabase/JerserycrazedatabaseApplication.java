package com.champsoft.jerserycrazedatabase;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Customer;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Jersey;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Order;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.OrderItem;
import com.champsoft.jerserycrazedatabase.dataaccess.repository.CustomerRepository;
import com.champsoft.jerserycrazedatabase.dataaccess.repository.JerseyRepository;
import com.champsoft.jerserycrazedatabase.dataaccess.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JerserycrazedatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(JerserycrazedatabaseApplication.class, args);
	}

	@Bean
	CommandLineRunner seedData(JerseyRepository jerseyRepo,
							   CustomerRepository customerRepo,
							   OrderRepository orderRepo) {
		return args -> {
			// Seed Jerseys (10+)
			if (jerseyRepo.count() == 0) {
				List<Jersey> jerseys = new ArrayList<>();
				String[] clubs = {"Real Madrid","Barcelona","Arsenal","Liverpool","Man City"};
				String[] types = {"Home","Away"};
				String[] sizes = {"S","M","L","XL"};

				int idGen = 1;
				for (String club : clubs) {
					for (String type : types) {
						Jersey j = new Jersey();
						j.setName(club + " " + type + " Jersey 25/26 #" + idGen);
						j.setClub(club);
						j.setType(type);
						j.setSize(sizes[idGen % sizes.length]);
						j.setPrice(new BigDecimal("150.00").add(new BigDecimal(idGen)));
						j.setInStock(true);
						j.setDescription(type + " kit");
						j.setImageLink("https://example.com/" + club.toLowerCase().replace(" ", "-") + "-" + type.toLowerCase() + ".png");
						jerseys.add(j);
						idGen++;
					}
				}
				jerseyRepo.saveAll(jerseys);
			}

			// Seed Customers (10+)
			if (customerRepo.count() == 0) {
				List<Customer> customers = new ArrayList<>();
				for (int i = 1; i <= 10; i++) {
					Customer c = new Customer();
					c.setFirstName("User" + i);
					c.setLastName("Test" + i);
					c.setEmail("user" + i + "@example.com");
					c.setAddress("Street " + i + ", Montreal, QC");
					c.setPassword("pass" + i);
					customers.add(c);
				}
				customerRepo.saveAll(customers);
			}

			// Seed a few Orders with items, if you have Order and OrderItem mapped
			if (orderRepo.count() == 0 && customerRepo.count() > 0 && jerseyRepo.count() > 1) {
				Customer c1 = customerRepo.findAll().get(0);
				Customer c2 = customerRepo.findAll().get(1);
				Jersey j1 = jerseyRepo.findAll().get(0);
				Jersey j2 = jerseyRepo.findAll().get(1);

				Order o1 = new Order();
				o1.setCustomer(c1);
				o1.setOrderDate(LocalDate.now());
				o1.setStatus("PENDING");

				OrderItem oi11 = new OrderItem();
				oi11.setOrder(o1);
				oi11.setJersey(j1);
				oi11.setQuantity(2);
				oi11.setUnitPrice(j1.getPrice());

				OrderItem oi12 = new OrderItem();
				oi12.setOrder(o1);
				oi12.setJersey(j2);
				oi12.setQuantity(1);
				oi12.setUnitPrice(j2.getPrice());

				List<OrderItem> items1 = new ArrayList<>();
				items1.add(oi11);
				items1.add(oi12);
				o1.setOrderItems(items1);
				o1.setTotalAmount(
						oi11.getUnitPrice().multiply(new BigDecimal(oi11.getQuantity()))
								.add(oi12.getUnitPrice().multiply(new BigDecimal(oi12.getQuantity())))
				);

				Order o2 = new Order();
				o2.setCustomer(c2);
				o2.setOrderDate(LocalDate.now());
				o2.setStatus("PENDING");

				OrderItem oi21 = new OrderItem();
				oi21.setOrder(o2);
				oi21.setJersey(j2);
				oi21.setQuantity(3);
				oi21.setUnitPrice(j2.getPrice());

				List<OrderItem> items2 = new ArrayList<>();
				items2.add(oi21);
				o2.setOrderItems(items2);
				o2.setTotalAmount(
						oi21.getUnitPrice().multiply(new BigDecimal(oi21.getQuantity()))
				);

				orderRepo.saveAll(List.of(o1, o2));
			}
		};
	}
}
