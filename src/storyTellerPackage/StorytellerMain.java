package storyTellerPackage;

/** 
 * This class exists for the solely purpose of initializing some system 
 * properties before any other classes are loaded.
 * <p>
 * Turning antialiasing on requires this properties to be set in this context.
 * */
public final class StorytellerMain {

	public static void main(String[] args) {
		System.setProperty("apple.awt.antialiasing", "on");
		System.setProperty("apple.awt.textantialiasing", "on");
		Storyteller.launch(args);
	}

}
