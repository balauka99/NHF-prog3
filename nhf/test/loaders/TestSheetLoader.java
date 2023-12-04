package loaders;

import org.junit.Test;
import error.OwnError;

public class TestSheetLoader {
	SheetLoader sheet;
	@Test (expected=OwnError.class)
	public void testThrowIllegalArgumentException() throws Exception {
		sheet = new SheetLoader("nemjo_file_name", 10, 10, 20, 20);
	}
	@Test (expected=NullPointerException.class)
	public void testThrowNullPointerException() throws Exception {
		sheet = new SheetLoader("/save_file/testBestRounds.txt", 10, 10, 20, 20);
	}
}
