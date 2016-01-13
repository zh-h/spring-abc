package helper;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestPatten {
	public Pattern pattern=Pattern.compile("^(?!_)(?!.*?_$)[A-Za-z0-9|_]{3,9}");
	String username="zo_nghu哈哈";
	@Test
	public void test() {
		Matcher matcher=pattern.matcher(username);
		System.out.println(matcher.matches());
	}

}
