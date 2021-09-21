package jp.co.axa.apidemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.embedded.tomcat.ConnectorStartFailedException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTests {

	//@Mock
	//SpringApplication mockSpringApp;

	@Test()
	public void contextLoads() {
		try {
			ApiDemoApplication.main(new String[] {});
		} catch (ConnectorStartFailedException se) {
			// ignore
		}
	}

}
