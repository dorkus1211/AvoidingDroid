package com.badlogic.android.games.crashdroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.android.games.framework.Graphics;

public class PoliceCar extends Car {
	List<RightPolice> rightPcar = new ArrayList<RightPolice>();
	List<LeftPolice> leftPcar = new ArrayList<LeftPolice>();
	Random random;
	Droid droid;

	public PoliceCar(Droid droid) {
		this.droid = droid;
		random = new Random();
		rightPcar.add(new RightPolice(random.nextInt(2)));
		rightPcar.add(new RightPolice(random.nextInt(2) + 2));
		leftPcar.add(new LeftPolice(random.nextInt(2)));
		leftPcar.add(new LeftPolice(random.nextInt(2)+2));
	}
	
	public void update(){
		int rightLen = rightPcar.size();
		for(int i=0;i<rightLen;i++)
			rightPcar.get(i).move();
		int leftLen = leftPcar.size();
		for(int i=0;i<leftLen;i++)
			leftPcar.get(i).move();
	}
	
	public boolean attackPolice(){
		int rightLen = rightPcar.size();
		int leftLen = leftPcar.size();
		int Width = Assets.policecar.getWidth();
		int Height = Assets.policecar.getHeight();
		int droidWidth = Assets.droid.getWidth();
		int droidHeight = Assets.droid.getHeight();
		
		for(int i=0;i<rightLen;i++){
			if(AttackJudge.rectangleAttack(rightPcar.get(i).x,rightPcar.get(i).y,Width,Height,(int)droid.x,(int)droid.y,droidWidth,droidHeight))
				return true;	}
		for(int i=0;i<leftLen;i++){
			if(AttackJudge.rectangleAttack(leftPcar.get(i).x,leftPcar.get(i).y,Width,Height,(int)droid.x,(int)droid.y,droidWidth,droidHeight))
				return true;	}
		return false;
	}
	
	public void Clear(){
		//rightPcar.clear();
		//leftPcar.clear();
		//rightPcar.add(new RightPolice(random.nextInt(2)));
		//rightPcar.add(new RightPolice(random.nextInt(2) + 2));
		//leftPcar.add(new LeftPolice(random.nextInt(2)));
		//leftPcar.add(new LeftPolice(random.nextInt(2)+2));
		rightPcar.get(0).clear(random.nextInt(2));
		rightPcar.get(1).clear(random.nextInt(2)+2);
		leftPcar.get(0).clear(random.nextInt(2));
		leftPcar.get(1).clear(random.nextInt(2)+2);	
	}
	
	public void present(Graphics g){
		int rightLen = rightPcar.size();
		for(int i=0;i<rightLen;i++){
			RightPolice rightPolice = rightPcar.get(i);
			g.drawPixmap(Assets.policecar, rightPolice.x, rightPolice.y);}
		
		int leftLen = leftPcar.size();
		for(int i=0;i<leftLen;i++){
			LeftPolice leftPolice = leftPcar.get(i);
			g.drawPixmap(Assets.policecar, leftPolice.x, leftPolice.y);}
	}
	
	public boolean end(){
		if(rightPcar.get(1).end())
			return true;
		else
			return false;
	}
	
	public void dispose() {
		rightPcar = null;
		leftPcar = null;

    }
	
		
}
