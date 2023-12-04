package objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import error.OwnError;
import object.OsObject;

public class TestOsObject {
	OsObject tested;
	@Before
	public void setup() {
		tested = new OsObject();
	}
	/**
	 * Teszteljük tényleg használjuk mikor meghívjuk a helyes metódusokat
	 */
	@Test
	public void testUse() {
		tested.useObject();
		Assert.assertTrue("Test Object is truely used", tested.isObjectUsed());
		tested.reUseObject();
		Assert.assertFalse("Test Object is truely reused", tested.isObjectUsed());
	}
	@Test
	public void testDestroy() {
		Assert.assertFalse("Test Object not destroyed in default", tested.isDestroyed());
		tested.destroy();
		Assert.assertTrue("Test Object is destroyed", tested.isDestroyed());
	}
	/**
	 * Teszteljük, ha nincs megvalósítva OwnError-t dob
	 * @throws Exception
	 */
	@Test (expected=OwnError.class)
	public void testReLoad() throws Exception {
		tested.reLoad();
	}
	@Test
	public void testSetGet() {
		String name = new String("tester");
		String name2 = new String("tester2");
		tested.setName(name);
		Assert.assertTrue("Test name is correct", tested.getName().equals(name));
		tested.setName(name2);
		Assert.assertTrue("Test name is correct", tested.getName().equals(name2));
	}
}
