package entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestEntity {
	Entity tested;
	/**
	 * Alap beállítások egy Entity-nek
	 */
	@Before
	public void setup() {
		tested = new Entity();
		tested.setSpeed(4);
		tested.setX(100);
		tested.setY(100);
	}
	/**
	 * Teszteljük sebződik-e egy entitás
	 */
	@Test
	public void testDamaged() {
		int defHealt = tested.getHealt();
		tested.damaged(10);
		Assert.assertNotEquals("Test damaged()",defHealt, tested.getHealt());
	}
	/**
	 * Teszteljük, ha többel sebződik, mint amennyi léetereje van, akkor meghal-e
	 */
	@Test
	public void testDieWhenNoHealt() {
		int defHealt = tested.getHealt();
		tested.damaged(defHealt+defHealt);
		Assert.assertTrue("Test die when no healt" ,tested.isDead());
	}
	/**
	 * Teszteljük, ha meghívjuk a halál metódust, tényleg meghal-e
	 */
	@Test
	public void testDie() {
		tested.die();
		Assert.assertTrue("Test isDead()", tested.isDead());
	}
	/**
	 * Teszteljük, amikor jobbra megy jobb is néz, és váltoik a poziciója, ballra szintén
	 */
	@Test
	public void testMoveToPoint() {
		int lastX = tested.getX();
		int lastY = tested.getY();
		/**
		 * Jobbra megyünk
		 */
		tested.moveToPoint(110, 110);
		Assert.assertNotEquals("Move right, x koordinate", lastX, tested.getX());
		Assert.assertNotEquals("Move right, y koordinate", lastY, tested.getY());
		Assert.assertEquals("Move right, move_dir", "right", tested.move_dir);
		/**
		 * Ballra megyünk
		 */
		lastX = tested.getX();
		lastY = tested.getY();
		tested.moveToPoint(90, 90);
		Assert.assertNotEquals("Move left, x koordinate", lastX, tested.getX());
		Assert.assertNotEquals("Move left, y koordinate", lastY, tested.getY());
		Assert.assertEquals("Move left, move_dir", "left", tested.move_dir);
	}
}