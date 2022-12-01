package first.springboot.springboot.controller;

import first.springboot.springboot.entity.Customer;
import first.springboot.springboot.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping ()
    public List<Customer> getAll() {

        return customerService.getAll();
    }

    @GetMapping ("/{id}")
    public Customer getById(@PathVariable int id) throws Exception {
        return customerService.getCustomer(id);
    }



    @PostMapping ()
    public Customer add(@RequestBody  Customer customer) {
        return customerService.add(customer);

    }

    @PutMapping ("/{id}")
    public Customer update(@RequestBody Customer customer, @PathVariable int id) {
        return customerService.update(id, customer);

    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable int id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
