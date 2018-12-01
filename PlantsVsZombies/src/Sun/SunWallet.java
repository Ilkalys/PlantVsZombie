package Sun;

import java.io.File;

import Resources.Entite;
import Resources.StdDraw;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class SunWallet extends Entite {
	
	private File SpriteFilepath;
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------
	
	private int bank;

	public SunWallet(double x, double y, int bank) {
		super(x,y);
		this.bank = bank;
		SpriteFilepath = new File("sprites\\mob\\sun.png");
	}

	public int getBank() {
		return bank;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}
	
	public void add(int value) {
		bank += value;
	}
	
	public boolean enoughSun(int value) {
		return bank >= value;
	}
	@Override
	public void step() {
		
	}

	@Override
	public void dessine() {
		StdDraw.setFont();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.picture(0.87, 0.1, SpriteFilepath.getAbsolutePath(), 0.04, 0.04);
		StdDraw.text(0.9, 0.1, ""+bank);
	}
}
