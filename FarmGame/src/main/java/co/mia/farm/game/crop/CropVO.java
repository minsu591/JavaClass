package co.mia.farm.game.crop;

import lombok.Data;

@Data
public class CropVO {
	private String cropName;
	private int cropSell;
	private int cropPur;
	private int cropTime;
	private int cropExp;
	private int cropLevel;
	private String cropExplain;

}
