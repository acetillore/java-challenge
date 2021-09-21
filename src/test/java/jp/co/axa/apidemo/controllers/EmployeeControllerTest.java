package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.services.EmployeeServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerTest {

    @Autowired
    EmployeeRepository empRepo;

    // target class to test
    @Autowired
    EmployeeController empCtrller;

    @Mock
    EmployeeService empSvc;

    // initial data set
    Employee empInit;

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

    // testing for getEmployee method
    @Test
    public void test1_getEmployees() {
        // save data directly via Repository save method
        List<Employee> inputList = new ArrayList<Employee>();
        inputList.add(empInit);

        // create other Employee data to add to the list
        Employee othrEmp = new Employee();
        empInit.setId(1L);
        empInit.setName("Uzumaki Naruto");
        empInit.setSalary(5000000);
        empInit.setDepartment("Ninja");

        empRepo.save(empInit);

        // initialize actual result
        List<Employee> actualRes = new ArrayList<Employee>();

        // execute getEmployees method in EmployeeController class
        actualRes = empCtrller.getEmployees();

        // assert if Employee set data are retrieved
        assertThat(actualRes.size(), is(1));
        assertThat(actualRes.get(0).getId(), is(1L));
        assertThat(actualRes.get(0).getName(), is("Uzumaki Naruto"));
        assertThat(actualRes.get(0).getSalary(), is(5000000));
        assertThat(actualRes.get(0).getDepartment(), is("Ninja"));
    }

    @Test
    public void test2_getEmployee() {
        empRepo.save(empInit);

        // initialize actual result
        Employee actualRes = new Employee();

        // execute getEmployee method
        actualRes = empCtrller.getEmployee(1L);

        // assert if employee id 1L is retrieved
        assertThat(actualRes.getId(), is(1L));
    }

    @Test
    public void test3_saveEmployee() {
        // execute saveEmployee method in EmployeeController class
        empCtrller.saveEmployee(empInit);

        // assert if Employee set data are saved
        assertNotNull(empRepo.findById(1L).get());
        assertThat(empRepo.findById(1L).get().getId(), is(1L));
        assertThat(empRepo.findById(1L).get().getName(), is("Uzumaki Naruto"));
        assertThat(empRepo.findById(1L).get().getSalary(), is(5000000));
        assertThat(empRepo.findById(1L).get().getDepartment(), is("Ninja"));
    }

    @Test
    public void test4_updateEmployee() {
        // save data directly via Repository save method
        empRepo.save(empInit);

        // initilize other set data
        Employee empUpdt = new Employee();
        empUpdt.setId(1L);
        empUpdt.setName("Monkey D. Luffy");
        empUpdt.setSalary(6000000);
        empUpdt.setDepartment("Pirate");

        // execute updateEmployee method
        empCtrller.updateEmployee(empUpdt, 1L);

        // assert if Employee 2L data is updated
        assertThat(empRepo.findById(1L).get().getName(), is("Monkey D. Luffy"));
        assertThat(empRepo.findById(1L).get().getSalary(), is(6000000));
        assertThat(empRepo.findById(1L).get().getDepartment(), is("Pirate"));
    }

    @Test
    public void test5_deleteEmployee() {
        // save data directly via Repository save method
        empRepo.save(empInit);

        // execute deleteEmployee method
        empCtrller.deleteEmployee(1L);

        // assert if Employee id 1L is deleted
        assertThat(empRepo.existsById(1L), is(false));
    }

    @Test
    public void test6_setEmployeeService() {
        // mock EmployeeService interface class
        when(empSvc.getEmployee(1L)).thenReturn(empInit);

        // execute setEmployeeService method
        empCtrller.setEmployeeService(empSvc);

        // assert if Employee 1L is get
        assertNotNull(empSvc.getEmployee(1L));
    }

}