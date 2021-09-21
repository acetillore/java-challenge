package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import static org.hamcrest.CoreMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeRepository empRepo;

    // target class to test
    @Autowired
    private EmployeeServiceImpl empSvc;

    // initial data set
    private Employee empInit;

    @Before
    public void setUp() throws Exception {
        // initialize set data
        empInit = new Employee();
        empInit.setId(1L);
        empInit.setName("Uzumaki Naruto");
        empInit.setSalary(5000000);
        empInit.setDepartment("Ninja");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test1_setEmployeeRepository() {
        // save data directly via Repository save method
        empRepo.save(empInit);

        // execute setEmployeeService method
        empSvc.setEmployeeRepository(empRepo);

        // assert if Employee 1L is get
        assertNotNull(empSvc.getEmployee(1L));
    }

    @Test
    public void test2_retrieveEmployees() {
        // save data directly via Repository save method
        empRepo.save(empInit);

        // initialize list for actual result
        List<Employee> actualRes = new ArrayList<Employee>();

        // execute retrieveEmployees method
        actualRes = empSvc.retrieveEmployees();

        assertThat(actualRes.get(0).getId(), is(1L));
        assertThat(actualRes.get(0).getName(), is("Uzumaki Naruto"));
        assertThat(actualRes.get(0).getSalary(), is(5000000));
        assertThat(actualRes.get(0).getDepartment(), is("Ninja"));
    }

    @Test
    public void test3_getEmployee() {
        // save data directly via Repository save method
        empRepo.save(empInit);

        // initialize actual result
        Employee actualRes = new Employee();

        // execute getEmployee method
        actualRes = empSvc.getEmployee(1L);

        // assert if employee id 1L is retrieved
        assertThat(actualRes.getId(), is(1L));
    }

    @Test
    public void test4_saveEmployee() {
        // execute saveEmployee method in EmployeeController class
        empSvc.saveEmployee(empInit);

        // assert if Employee set data are saved
        assertNotNull(empRepo.findById(1L).get());
        assertThat(empRepo.findById(1L).get().getId(), is(1L));
        assertThat(empRepo.findById(1L).get().getName(), is("Uzumaki Naruto"));
        assertThat(empRepo.findById(1L).get().getSalary(), is(5000000));
        assertThat(empRepo.findById(1L).get().getDepartment(), is("Ninja"));
    }

    @Test
    public void test5_updateEmployee() {
        // save data directly via Repository save method
        empRepo.save(empInit);

        // initilize other set data
        Employee empUpdt = new Employee();
        empUpdt.setId(1L);
        empUpdt.setName("Monkey D. Luffy");
        empUpdt.setSalary(6000000);
        empUpdt.setDepartment("Pirate");

        // execute updateEmployee method
        empSvc.updateEmployee(empUpdt);

        // assert if Employee 1L data is updated
        assertThat(empRepo.findById(1L).get().getName(), is("Monkey D. Luffy"));
        assertThat(empRepo.findById(1L).get().getSalary(), is(6000000));
        assertThat(empRepo.findById(1L).get().getDepartment(), is("Pirate"));
    }

    @Test
    public void test6_deleteEmployee() {
        // save data directly via Repository save method
        empRepo.save(empInit);

        // execute deleteEmployee method
        empSvc.deleteEmployee(1L);

        // assert if Employee id 1L is deleted
        assertThat(empRepo.existsById(1L), is(false));
    }

}