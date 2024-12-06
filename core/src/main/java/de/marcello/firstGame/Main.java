package de.marcello.firstGame;

import com.badlogic.gdx.Game;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {


	public static Main INSTANCE;
	
	public Main() {
		INSTANCE = this;
	}
	
    @Override
    public void create() {
    	setScreen(new GameScreen());
    }


}
