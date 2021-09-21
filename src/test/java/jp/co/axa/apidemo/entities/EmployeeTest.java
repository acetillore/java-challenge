package jp.co.axa.apidemo.entities;

import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {

    private Employee emp;

    @Autowired
    private EmployeeRepository empRepo;

    @Before
    public void setUp() throws Exception {
        // initialize set data
        emp = new Employee();
        emp.setId(1L);
        emp.setName("Uzumaki Naruto");
        emp.setSalary(5000000);
        emp.setDepartment("Ninja");

        // save data directly via Repository save method
        empRepo.save(emp);
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void getId() {
        // execute getId method
        long empId = emp.getId();

        // assert result
        assertThat(empId, is(1L));
    }

    @Test
    public void getName() {
        // execute getName method
        String empName = emp.getName();

        // assert result
        assertThat(empName, is("Uzumaki Naruto"));
    }

    @Test
    public void getSalary() {
        // execute getSalary method
        Integer empSalary = emp.getSalary();

        // assert result
        assertThat(empSalary, is(5000000));
    }

    @Test
    public void getDepartment() {
        // execute getDepartment method
        String empDept = emp.getDepartment();
        assertThat(empDept, is("Ninja"));
    }

    @Test
    public void setId() {
        // create new Employee object
        Employee newEmp = new Employee();

        // execute setId method
        newEmp.setId(2L);

        // assert result
        assertThat(newEmp.getId(), is(2L));
    }

    @Test
    public void setName() {
        // create new Employee object
        Employee newEmp = new Employee();

        // execute setId method
        newEmp.setName("Monkey D. Luffy");

        // assert result
        assertThat(newEmp.getName(), is("Monkey D. Luffy"));
    }

    @Test
    public void setSalary() {
        // create new Employee object
        Employee newEmp = new Employee();

        // execute setId method
        newEmp.setSalary(6000000);

        // assert result
        assertThat(newEmp.getSalary(), is(6000000));
    }

    @Test
    public void setDepartment() {
        // create new Employee object
        Employee newEmp = new Employee();

        // execute setId method
        newEmp.setDepartment("Pirate");

        // assert result
        assertThat(newEmp.getDepartment(), is("Pirate"));
    }
}