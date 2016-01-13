package service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.springabc.domin.Node;
import xyz.springabc.service.NodeServ;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestNode {

	@Autowired
	private NodeServ nodeServ;
	@Test
	public void test() {
		List<Node> nodes=nodeServ.getNodeBySection("技术");
		for(Node n:nodes){
			System.out.println(n.getName());
		}
	}
	
	@Test
	public void testNames() {
		List<String> nodes=nodeServ.getNodeNameBySectionName("技术");
		for(String n:nodes){
			System.out.println(n);
		}
	}

}
