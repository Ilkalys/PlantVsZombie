package Soleil;

import Resources.Entite;
import Resources.StdDraw;

public class PorteSoleil extends Entite{
	private int bank;

	public PorteSoleil(double x, double y, int bank) {
		super(x,y);
		this.bank = bank;
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
		StdDraw.text(0.9, 0.1, "Soleil : "+bank);
	}
}
