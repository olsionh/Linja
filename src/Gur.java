import java.awt.*;
import java.awt.Dimension;
import javax.swing.JButton;

public class Gur extends JButton{
   
	private static final long serialVersionUID =1L;
	private Color ngjyra;
	private int koordinataX, koordinataY;
	
	public Gur(Color ngjyraQelizes, int koordinataX, int koordinataY){
		super();
		if(ngjyraQelizes == null)
			ngjyra = Color.WHITE;
		this.ngjyra = ngjyraQelizes;
		this.koordinataX = koordinataX;
		this.koordinataY = koordinataY;
		
		this.setBackground(ngjyra);
		this.setFocusable(false);
	}
	public Gur(int koordinataX, int koordinataY){
		this(Color.WHITE, koordinataX, koordinataY);
		setEnabled(false);
	}
	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}
		if(obj == null){
			return false;
		}
		if(!(obj instanceof Gur)){
			return false;
		}
		Gur other = (Gur)obj;
		if(koordinataX != other.koordinataX){
			return false;
		}
		if(koordinataY != other.koordinataY){
			return false;
		}
		if(ngjyra == null){
			if(other.ngjyra != null){
				return false;
			}
			}
		else if(!ngjyra.equals(other.ngjyra)){
			return false;
		}
		return true;
	}
	@Override
	public String toString(){
		return "Gur [ngjyra="+ ngjyra +",koordinataX="+koordinataX+",koordinataY="+koordinataY+"]";
	}
	public Color getNgjyra(){
		return ngjyra;
	}
	public void setNgjyra(Color ngjyra){
		if(ngjyra!= null){
			this.ngjyra = ngjyra;
			this.setBackground(this.ngjyra);
			repaint();
		}
	}
	public int getKoordinataX(){
		return koordinataX;
	}
	public void setKoordinataX(int koordinataX){
		this.koordinataX = koordinataX;
	}
	public int getKoordinataY(){
		return koordinataY;
	}
	public void setKoordinataY(int koordinataY){
		this.koordinataY = koordinataY;
	}
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(50,50);
	}
	
}
