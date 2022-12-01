package first.springboot.springboot.service;

import first.springboot.springboot.entity.Customer;
import first.springboot.springboot.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private static int id = 0;
    private List<Customer> db = new ArrayList<>();

    public List<Customer> getAll() {
        return db.stream().filter(c -> !c.getDeleted()).toList();
    }

    public Customer add(Customer customer) {
        customer.setId(++id);
        customer.setDeleted(false);
        db.add(customer);
        return customer;
    }

    public Customer update(int id, Customer customer)  {
        var foundCustomer = getCustomer(id);
        foundCustomer.setFirstName(customer.getFirstName());
        foundCustomer.setLastName(customer.getLastName());
        foundCustomer.setBirthdate(customer.getBirthdate());
        return foundCustomer;
    }

    public void delete(int id) {
        var foundCustomer = getCustomer(id);
        foundCustomer.setDeleted(true);

    }

    public Customer getCustomer(int id) throws RuntimeException{
        var optional = db.stream().filter(c -> c.getId() == id).findFirst();
        if(optional.isEmpty()) {
            throw new NotFoundException("customer not found");

        }
        return optional.get();

    }
}
