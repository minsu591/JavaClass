package co.mia.farm.game.farming;

import java.util.ArrayList;
import java.util.List;

import co.mia.farm.game.print.ConsolePrintService;

public class ForFarmingThread {
	private FarmingMenu fm = new FarmingMenu();
	private ThreadFieldVO tf;
	public static List<ThreadFieldVO> timeFieldList = new ArrayList<ThreadFieldVO>();

	public void farmingThread(int sec) {
		resetTime(); // 이전 값들 지우기
		InFieldVO myField = fm.checkMyField(); // 농장필드 정보 가져옴
		Runnable mf = new MultiFarmingThread(sec, myField);
		Thread th = new Thread(mf);
		tf = new ThreadFieldVO();
		tf.setX(myField.getFieldX());
		tf.setY(myField.getFieldY());
		tf.setStartSec(System.currentTimeMillis());
		tf.setWantSec(sec);
		timeFieldList.add(tf);
		th.start();

	}

	public int remainTime() {
		for (ThreadFieldVO tf : timeFieldList) {
			if (ConsolePrintService.userX == tf.getX() && ConsolePrintService.userY == tf.getY()) {
				int leftTime = tf.getWantSec() - (int) ((System.currentTimeMillis() - tf.getStartSec()) / 1000);
				if (leftTime > 0) {
					return leftTime;
				}
			}
		}
		return 0;
	}

	private void resetTime() {
		InFieldVO f = fm.checkMyField();
		List<InFieldVO> flist = new ArrayList<InFieldVO>();
		for(int i = 0; i< timeFieldList.size();i++) {
			for(int j =0; j<flist.size();j++) {
				if(timeFieldList.get(i).getX()==flist.get(j).getFieldX() && timeFieldList.get(i).getY()==flist.get(j).getFieldY()) {
					if(flist.get(j).getItemId()==0) {
						timeFieldList.remove(i);
					}
				}
			}
		}

	}

}
