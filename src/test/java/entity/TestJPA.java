package entity;

import static org.junit.Assert.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class TestJPA {

	@Test
	public void test() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("test");
	}

}
