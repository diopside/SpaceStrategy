package graphics.systemview;

import graphics.FadeButton;

import java.util.ArrayList;

public class EmptyButtonWindow extends ButtonWindow {

	public EmptyButtonWindow(int x, int y) {
		super(x, y, new ArrayList<FadeButton>());
	}

}
