package entity;

import java.awt.Rectangle;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import error.OwnError;

public class TestPlayer {
	Player viking;
	Player robot;
	/**
	 * Beállítja a két elérhető karaktert
	 * @throws IOException 
	 * @throws NullPointerException 
	 * @throws IllegalArgumentException 
	 */
	@Before
	public void setup() throws OwnError {
		viking = new Player(null, null, "viking");
		robot = new Player(null, null, "robot");
	}
	/**
	 * Teszteljük létrejön a két karakter helyesen
	 */
	@Test
	public void testKonstruktor() {
		/**
		 * Default értékek TEST
		 */
		Assert.assertEquals("Test Player hitbox is correct", new Rectangle(8, 16, 32, 32), viking.hitbox);
		Assert.assertEquals("Test Default x position", 432, viking.getX());
		Assert.assertEquals("Test Default y position", 576, viking.getY());
		Assert.assertEquals("Test Player speed is correct", 4, viking.getSpeed());
		Assert.assertNotNull("Test Player has skin", viking.sheet);
		/**
		 * Viking TEST
		 */
		Assert.assertTrue("Test viking Character Type", viking.getCharType().equals("viking"));
		Assert.assertEquals("Test viking healt is correct", 1001, viking.getHealt());
		Assert.assertEquals("Test viking healt is full", viking.getMaxHealt(), viking.getHealt());
		Assert.assertTrue("Test viking weapon Type is correct", viking.getWeaponType().equals("ground_attack"));
		/**
		 * Robot TEST
		 */
		Assert.assertTrue("Test robot Character Type", robot.getCharType().equals("robot"));
		Assert.assertEquals("Test robot healt is correct", 501, robot.getHealt());
		Assert.assertEquals("Test robot healt is full", robot.getMaxHealt(), robot.getHealt());
		Assert.assertTrue("Test robot weapon Type is correct", robot.getWeaponType().equals("wave_attack"));
	}
	/**
	 * Teszteljük tényleg gyogyul-e a Player
	 */
	@Test
	public void testHeal() {
		viking.damaged(100);
		int lastHp = viking.getHealt();
		viking.heal(100);
		Assert.assertNotEquals("Test Player is healed",lastHp, viking.getHealt());
	}
}