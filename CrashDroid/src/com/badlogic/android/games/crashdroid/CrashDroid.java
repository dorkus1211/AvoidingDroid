package com.badlogic.android.games.crashdroid;

import com.badlogic.android.games.framework.Screen;
import com.badlogic.android.games.framework.impl.AndroidGame;

public class CrashDroid extends AndroidGame{
	
	@Override
	public Screen getStartScreen(){
		return new LoadingScreen(this);
		
	}

}
