package co.mia.farm.game.farming;

import lombok.Data;

@Data
public class ThreadFieldVO {
	private int x;
	private int y;
	private long startSec;
	private int wantSec;
}
