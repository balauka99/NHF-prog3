package gameplay;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import menu.MainMenu;

public class TestGamePlay {
	GamePanel tester;
	MainMenu win;
	String testCHARACTER = "viking";
	String testNAME = "testerName";
	/**
	 * Beállítunk egy játékot
	 */
	@Before
	public void setup() {
		win = new MainMenu();
		tester = new GamePanel(testCHARACTER, testNAME);
		win.setTitle("Dungeon Run TEST");
		win.addNewCard(tester, "Game_TEST");
		tester.setMenuWin(win);
		tester.setupGame();
		tester.startMainT();
		win.pack();
		win.changeCard("Game_TEST");
	}
	/**
	 * Megnézzük/Teszteljük, minden alapérték a helyén van, azaz mindennek a Poziciója ott van ahol lennie kell és a kapott értékek is helyesek
	 */
	@Test
	public void testDefaultData() {
		Assert.assertNotNull("Test Player is not null", tester.getPlayer());
		Assert.assertTrue("Test Character name is correct", tester.getPlayer().getCharType().equals(testCHARACTER));
		Assert.assertTrue("Test Player Name is correct", tester.getPlayerName().equals(testNAME));
		Assert.assertFalse("Test current new map is not in pervious maps", tester.isMapInPrevMaps);
		Assert.assertNotNull("Test CollisionChecker is not null", tester.cChecker);
		Assert.assertNotNull("Test ArrayList<OsObject> is not null", tester.objects);
		Assert.assertNotNull("Test ArrayList<Entity> is not null", tester.enemys);
		Assert.assertNotNull("Test UserInterface is not null", tester.ui);
		Assert.assertNotNull("Test TileManager is not null", tester.tileMg);
		Assert.assertNotNull("Test AssetSetter is not null", tester.assSetter);
		Assert.assertNotNull("Test ArrayList<Heart> is not null", tester.player_healt);
		Assert.assertNotNull("Test KeyHandler is not null", tester.getKeyListener());
		Assert.assertNotNull("Test BestRounds playerScore is not null", tester.getPLayersScore());
		Assert.assertEquals("Test Map Status is in a new map state", Map_Status.IN_NEW, tester.map_state);
	}
	@Test
	public void testDefalutPositionsPlayerHealt() {
		Assert.assertEquals("Test player healt first heart is in the right position", 14*tester.tileSize, tester.player_healt.get(0).y);
		Assert.assertEquals("Test player healt first heart is in the right position", 15*tester.tileSize, tester.player_healt.get(0).x);
		
		Assert.assertEquals("Test player healt second heart is in the right position", 14*tester.tileSize, tester.player_healt.get(1).y);
		Assert.assertEquals("Test player healt second heart is in the right position", 16*tester.tileSize, tester.player_healt.get(1).x);
		
		Assert.assertEquals("Test player healt third heart is in the right position", 14*tester.tileSize, tester.player_healt.get(2).y);
		Assert.assertEquals("Test player healt third heart is in the right position", 17*tester.tileSize, tester.player_healt.get(2).x);
		
		Assert.assertEquals("Test player healt fourth heart is in the right position", 14*tester.tileSize, tester.player_healt.get(3).y);
		Assert.assertEquals("Test player healt fourth heart is in the right position", 18*tester.tileSize, tester.player_healt.get(3).x);
		
		Assert.assertEquals("Test player healt fifth heart is in the right position", 14*tester.tileSize, tester.player_healt.get(4).y);
		Assert.assertEquals("Test player healt fifth heart is in the right position", 19*tester.tileSize, tester.player_healt.get(4).x);
	}
	@Test
	public void testSetupGame() {
		tester.setupGame();
		Assert.assertTrue("Test GamePanel has objects", tester.objects.size() > 0);
		Assert.assertTrue("Test GamePanel has enemys", tester.enemys.size() > 0);
	}
}
