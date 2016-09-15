package entity;

import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJPA {

	@Test
	public void test() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("test");
	}

}
