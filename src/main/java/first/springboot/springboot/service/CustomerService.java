package first.springboot.springboot.service;

import first.springboot.springboot.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    Customer add(Customer customer);
    Customer update(int id, Customer customer);
    void delete(int id);
    Customer getCustomer(int id);
}
