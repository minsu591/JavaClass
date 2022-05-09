package co.mia.farm.game.farming;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InFieldVO {
	private int fieldX;
	private int fieldY;
	private int itemId;
	
	public InFieldVO() {}
}


