package pl.put.poznan.transformer.logic;

public abstract class TextTransformer {
	protected String descr = "valueATT";

	public String getText() {
		return descr;
	}

	public abstract String transform();

}
