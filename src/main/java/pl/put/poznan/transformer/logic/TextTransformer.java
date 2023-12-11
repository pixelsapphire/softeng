package pl.put.poznan.transformer.logic;

public abstract class TextTransformer {
	protected String description;

	public String getText() {
		return description;
	}

	public abstract String transform();

}
