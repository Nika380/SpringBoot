package first.springboot.springboot.controller;

import first.springboot.springboot.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    private static int id = 0;
    private List<Customer> db = new ArrayList<>();

    @GetMapping ("/customers")
    public List<Customer> getAll() {
        return db.stream().filter(c -> !c.getDeleted()).toList();
    }

    @GetMapping ("/customers/{id}")
    public ResponseEntity<Customer> getById(@PathVariable int id) {
        var optional = db.stream().filter(c -> c.getId() == id).findFirst();
        if(optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var foundCustomer = optional.get();
        return ResponseEntity.ok(optional.get());
    }


    @PostMapping ("/customers")
    public Customer add(@RequestBody  Customer customer) {
        customer.setId(++id);
        customer.setDeleted(false);
        db.add(customer);
        return customer;
    }

    @PutMapping ("/customers/{id}")
    public ResponseEntity<Customer> update(@RequestBody Customer customer, @PathVariable int id) {
        var optional = db.stream().filter(c -> c.getId() == id).findFirst();
        if(optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var foundCustomer = optional.get();
        foundCustomer.setFirstName(customer.getFirstName());
        foundCustomer.setLastName(customer.getLastName());
        foundCustomer.setBirthdate(customer.getBirthdate());
        return ResponseEntity.ok(foundCustomer);
    }

    @DeleteMapping ("/customers/{id}")
    public ResponseEntity<Customer> delete(@PathVariable int id) {
        var optional = db.stream().filter(c -> c.getId() == id).findFirst();
        if(optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var foundCustomer = optional.get();
        foundCustomer.setDeleted(true);
        return ResponseEntity.noContent().build();

    }
}
