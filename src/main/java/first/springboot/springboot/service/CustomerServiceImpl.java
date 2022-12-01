package first.springboot.springboot.service;

import first.springboot.springboot.entity.Customer;
import first.springboot.springboot.exceptions.NotFoundException;
import first.springboot.springboot.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private static int id = 0;
    private List<Customer> db = new ArrayList<>();

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll() {

        return customerRepository.findAll();
//       return db.stream().filter(c -> !c.getDeleted()).toList();
    }

    @Override
    public Customer add(Customer customer) {
        customer.setId(++id);
        customer.setDeleted(false);
        db.add(customer);
        return customer;
    }

    @Override
    public Customer update(int id, Customer customer)  {
        var foundCustomer = getCustomer(id);
        foundCustomer.setFirstName(customer.getFirstName());
        foundCustomer.setLastName(customer.getLastName());
        foundCustomer.setBirthdate(customer.getBirthdate());
        return foundCustomer;
    }

    @Override
    public void delete(int id) {
        var foundCustomer = getCustomer(id);
        foundCustomer.setDeleted(true);

    }

    @Override
    public Customer getCustomer(int id) throws RuntimeException{
        var optional = db.stream().filter(c -> c.getId() == id).findFirst();
        if(optional.isEmpty()) {
            throw new NotFoundException("customer not found");

        }
        return optional.get();

    }
}
