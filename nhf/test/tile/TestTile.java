package tile;

import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTile {
	Tile test;
	@Before
	public void setup() {
		test = new Tile();
		try {
			test.setSkin(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")), "wall");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Testteljük a setSkin minden helyesen állít-e be
	 */
	@Test
	public void testSetskin() {
		try {
			test.setSkin(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")), "wall");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertTrue("Test Tile setSkin name", test.getName().equals("wall"));
		Assert.assertNotNull("Test Tile setSkin image is not null", test.getSkin());
		try {
			test.setSkin(ImageIO.read(getClass().getResourceAsStream("/tiles/flore.png")), "flore");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertTrue("Test Tile setSkin name", test.getName().equals("flore"));
		Assert.assertNotNull("Test Tile setSkin image is not null", test.getSkin());
		try {
			test.setSkin(ImageIO.read(getClass().getResourceAsStream("/tiles/lava.png")), "lava");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertTrue("Test Tile setSkin name", test.getName().equals("lava"));
		Assert.assertNotNull("Test Tile setSkin image is not null", test.getSkin());
	}
	/**
	 * Teszteljük, helyesen állítja be a Collisiont, azaz ütközést, vagyis hogy szilárd-e a Tile
	 */
	@Test
	public void testCollisionIsCorretct() {
		test.noCollision();
		Assert.assertFalse("Test noCollision", test.isSolid());
		test.enableCollision();
		Assert.assertTrue("Test enableCollision", test.isSolid());
	}
}
