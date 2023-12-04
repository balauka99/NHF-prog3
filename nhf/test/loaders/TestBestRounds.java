package loaders;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBestRounds {
	BestRounds tested;
	String fname = "source/testBestRounds.txt";
	/**
	 * Létrehozunk egy állást a BestRounds-nak
	 */
	@Before
	public void setup() {
		tested = new BestRounds();
		tested.add(new Score("balauka", 10));
		tested.add(new Score("Fanni", 11));
		tested.add(new Score("Sok", 12));
		tested.add(new Score("player4", 10));
		tested.add(new Score("player5", 10));
	}
	/**
	 * Lementjuk az állást, azt visszatöltjük és megnézzük egyezik-e azzal, amit lementettünk
	 */
	@Test
	public void testWriteAndLoad() {
		BestRounds tmp = tested;
		BestRounds.saveBests(tested, fname);
		tested = BestRounds.loadBests(fname);
		Assert.assertEquals("Test size is the same", tmp.size(), tested.size());
		for(int i = 0; i < tmp.size(); i++) {
			Assert.assertEquals("Test name data, testing: " + i + " index", tmp.get(i).getName(), tested.get(i).getName());
			Assert.assertEquals("Test point data, testing: " + i + " index", tmp.get(i).getPoint(), tested.get(i).getPoint());
		}
		Assert.assertEquals("Test last player name is correct", tmp.getCurrent().getName(), tested.getCurrent().getName());
		Assert.assertEquals("Test last player point is correct", tmp.getCurrent().getPoint(), tested.getCurrent().getPoint());
	}
	/**
	 * Teszteljük, az éppen játsuó játékos tényleg az utoljára bekerült
	 */
	@Test
	public void testCurrentPlayer() {
		Score current = new Score("current", 2022);
		tested.add(current);
		Assert.assertTrue("Test current player name", tested.getCurrent().getName().equals(current.getName()));
		Assert.assertEquals("Test current player name", current.getPoint(), tested.getCurrent().getPoint());
	}
}
