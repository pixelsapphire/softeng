package pl.put.poznan.transformer.logic;

public class TextClass extends TextTransformer{

    public TextClass(String str){
        this.descr = str;
    }

    @Override
    public String transform() {
        return descr;
    }

}
