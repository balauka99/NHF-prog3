package loaders;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestScore {
	String name = "player name";
	int point = 10;
	Score tested;
	/**
	 * Beállít egy játékost és pontját
	 */
	@Before
	public void setup() {
		tested = new Score(name, point);
	}
	@Test
	public void testKonstruktorAndGetterSetter() {
		Assert.assertTrue("Test name is correct", tested.getName().equals(name));
		Assert.assertEquals("Test point is correct", point, tested.getPoint());
		String newPlayer = "new player";
		
		tested.setName(newPlayer);
		Assert.assertTrue("Test Name Setter", tested.getName().equals(newPlayer));
		
		int newPoint = 20;
		tested.setPoint(newPoint);
		Assert.assertEquals(newPoint, tested.getPoint());
	}
}