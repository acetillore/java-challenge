package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Cacheable(value = "employees-cache")
    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        // for cache testing
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Cacheable(value = "employee-cache", key = "#employeeId")
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = Optional.ofNullable(employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found for this id : " + employeeId)));
        // for cache testing
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return optEmp.get();
    }

    @CacheEvict(value="employees-cache", allEntries=true)
    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    @Caching(evict = {
            @CacheEvict(value="employee-cache", key = "#employeeId", allEntries=true),
            @CacheEvict(value="employees-cache", allEntries=true)})
    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    @Caching(evict = {
            @CacheEvict(value="employee-cache", key = "#employeeId", allEntries=true),
            @CacheEvict(value="employees-cache", allEntries=true)})
    public void updateEmployee(Employee employee) {
        // for testing
        employeeRepository.save(employee);
    }
}