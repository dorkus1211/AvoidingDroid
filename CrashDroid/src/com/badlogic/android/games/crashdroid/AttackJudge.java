package com.badlogic.android.games.crashdroid;

import android.graphics.Rect;

public class AttackJudge {

	public AttackJudge() {

	}

	public static boolean rectangleAttack(Rect rectA, Rect rectB) {
		// if (rectA.contains(rectB))
		// return true;
		// else
		// return false;
		if ((rectB.left < rectA.right) && (rectB.right > rectA.left)
				&& (rectB.top < rectA.bottom) && (rectB.bottom > rectA.top))
			return true;
		else
			return false;

		// if (Math.abs(rectB.right - rectA.right) < Math.abs(rectB.right
		// - rectB.left)
		// && Math.abs(rectB.top - rectA.top) < (rectB.top - rectB.bottom))
		// return true;
		// else
		// return false;

	}

	public static boolean rectangleAttack(int x1, int y1, int width1,
			int height1, int x2, int y2, int width2, int height2) {
		if ((x2 < x1 + width1) && (x2 + width2 > x1) && (y2 < y1 + height1)
				&& (y2 + height2 > y1))
			return true;
		else
			return false;

	}
}
