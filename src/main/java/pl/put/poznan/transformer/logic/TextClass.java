package pl.put.poznan.transformer.logic;

public class TextClass extends TextTransformer{

    public TextClass(String str){
        this.description = str;
    }

    @Override
    public String transform() {
        return description;
    }

}
