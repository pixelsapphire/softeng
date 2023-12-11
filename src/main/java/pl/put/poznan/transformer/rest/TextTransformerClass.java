package pl.put.poznan.transformer.rest;


public class TextTransformerClass {

	private String Transform;
	private boolean Numbers;
	private String Shortcuts;
	private boolean LaTeX;
	private boolean Neighbors;	
	public TextTransformerClass() {
		
	}
	
	public TextTransformerClass(String Transform, boolean Numbers, 
			String Shortcuts, boolean LaTeX, boolean Neighbors) {
		super();
		this.Transform = Transform;
		this.Numbers = Numbers;
		this.Shortcuts = Shortcuts;
		this.LaTeX = LaTeX;
		this.Neighbors = Neighbors;
	}
	
	public String getBasicTransform() {
		return Transform;
	}

	public void setBasicTransform(String Transform) {
		this.Transform = Transform;
	}

	public boolean isNumbers() {
		return Numbers;
	}

	public void setNumbers(boolean Numbers) {
		this.Numbers = Numbers;
	}

	public String getShortcuts() {
		return Shortcuts;
	}

	public void setShortcuts(String Shortcuts) {
		this.Shortcuts = Shortcuts;
	}

	public boolean isLatex() {
		return LaTeX;
	}

	public void setLatex(boolean LaTeX) {
		this.LaTeX = LaTeX;
	}

	public boolean isNeighbors() {
		return Neighbors;
	}

	public void setNeighbors(boolean Neighbors) {
		this.Neighbors = Neighbors;
	}	
}
