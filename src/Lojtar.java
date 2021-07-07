
public class Lojtar {
   
	private String emri="i papercaktuar";
	
	public Lojtar(String emri){
		if(emri!=null){
			this.emri = emri;
		}
	}
	public String getEmri(){
		return emri;
	}
	public void setEmri(String emri){
		this.emri = emri;
	}
	public void levizGur(Gur pozicioniFillestar, Gur pozicioniPasLevizjes, Gur [][] gur){
		int xPara = pozicioniFillestar.getKoordinataX();
		int yPara = pozicioniFillestar.getKoordinataY();
		int xPas = pozicioniPasLevizjes.getKoordinataX();
		int yPas = pozicioniPasLevizjes.getKoordinataY();
		gur[xPas][yPas].setKoordinataX(xPara);
		gur[xPas][yPas].setKoordinataY(yPara);
		gur[xPara][yPara].setKoordinataX(xPas);
		gur[xPara][yPara].setKoordinataY(yPas);
		gur[xPara][yPara] = pozicioniPasLevizjes;
		gur[xPas][yPas] = pozicioniFillestar;
	}
}
