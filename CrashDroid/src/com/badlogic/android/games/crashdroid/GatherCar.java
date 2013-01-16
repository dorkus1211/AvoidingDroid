package com.badlogic.android.games.crashdroid;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.android.games.framework.Graphics;

public class GatherCar {
	List<Car> Cars = new ArrayList<Car>();// 0�`2�Ԗڂ͍��Ԑ��̎� 3�`5�Ԗڂ͉E�Ԑ��̎�
	Droid droid;

	public GatherCar(Droid droid) {
		addCar(3, 3);
		this.droid = droid;
	}

	public void updateCar() {
		CarsChanged();
		advanceCar();
	}

	public void presentCar(Graphics g) {
		

		
		int len = Cars.size();
		for (int i = 0; i < len; i++) {// �Ԃ�`��
			Car car = Cars.get(i);
			g.drawPixmap(Assets.car, car.x, car.y);
		}
	}
	
	
	//�h���C�h����ƎԂ��ڐG���Ă��邩��Ԃ��B
	public boolean attackCar(){
		int len = Cars.size();
		int carWidth = Assets.car.getWidth();
		int carHeight = Assets.car.getHeight();
		int droidWidth = Assets.droid.getWidth()-40;
		int droidHeight = Assets.droid.getHeight()-20;
		for(int i=0;i<len;i++){
			if(AttackJudge.rectangleAttack(Cars.get(i).x,Cars.get(i).y,carWidth,carHeight,(int)droid.x+20,(int)droid.y+20,droidWidth,droidHeight))
				return true;			
		}
		return false;
	}
	

	// Car�̃��X�g�ɎԂ̃I�u�W�F�N�g�𐶐��B
	private void addCar(int leftFigure, int RightFigure) {
		int len = leftFigure + RightFigure;
		for (int i = 0; i < len; i++) {
			if (i >= 0 && i <= leftFigure - 1)
				Cars.add(new LeftCar());
			else
				Cars.add(new RightCar());
		}
	}

	public void advanceCar() {
		int len = Cars.size();
		for (int i = 0; i <len; i++) { // �Ԃ�i�܂���B
			Cars.get(i).update();
		}
	}

	// �Ԃ����ׂď��������Ȃ��悤�ɂ���B
	public void initFalse() {
		int len = Cars.size();
		for (int i = 0; i < len; i++) {
			Cars.get(i).Init = false;
		}
	}

	// �Ԃ����ׂď���������悤�ɂ���B
	public void initTrue() {
		int len = Cars.size();
		for (int i = 0; i < len; i++) {
			Cars.get(i).Init = true;
		}
	}

	// �Ԃ���ʊO�ɂ��邩������s���B
	public boolean CarOut() {
		int num = 0;
		int CarHeight = Assets.car.getHeight();
		int len = Cars.size();
		for (int i = 0; i < len; i++) {
			int y = Cars.get(i).y;
			if (y + CarHeight - 1 < 0 || y> Settings.Height)
				num++;
		}

		if (num == len)
			return true;
		else
			return false;

	}

	//
	private void CarsChanged() {
		Car car0 = Cars.get(0);
		Car car1 = Cars.get(1);
		Car car2 = Cars.get(2);
		Car car3 = Cars.get(3);
		Car car4 = Cars.get(4);
		Car car5 = Cars.get(5);

		while (OverlapCar(car0, car1))
			car0.init();
		while (OverlapCar(car0, car2) || OverlapCar(car1, car2))
			car2.init();

		while (OverlapCar(car3, car4))
			car3.init();
		while (OverlapCar(car3, car5) || OverlapCar(car4, car5))
			car5.init();

		//while (OverlapCar(Cars.get(0), Cars.get(1)))
		//	Cars.get(0).init();
		//while (OverlapCar(Cars.get(0), Cars.get(2))
		//		|| OverlapCar(Cars.get(1), Cars.get(2)))
		//	Cars.get(2).init();

		//while (OverlapCar(Cars.get(3), Cars.get(4)))
		//	Cars.get(3).init();
		//while (OverlapCar(Cars.get(3), Cars.get(5))
		//		|| OverlapCar(Cars.get(4), Cars.get(5)))
		//	Cars.get(5).init();
	}
	

	private boolean OverlapCar(Car car1, Car car2) {
		if (Math.abs(car1.x - car2.x) <= 50 && Math.abs(car1.y - car2.y) <= 90)
			return true;
		else
			return false;
	}
	
	public void dispose() {
		Cars = null;
    }

}
