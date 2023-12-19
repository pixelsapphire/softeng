### Diagram Logiki Serwera -pl.put.poznan.transformer.server.logic
```mermaid
classDiagram
  class TextTransformer{
    protected String text;
    public @NotNull String getText();
    public abstract @NotNull String transform();
  }
  click TextTransformer href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/logic/TextTransformer.java"
  class TextTransformerDecorator["TextTransformerDecorator : TextTransformer"] {
    +protected TextTransformer textToTransform;
    +public TextTransformerDecorator(@NotNull TextTransformer textToTransform);
    +public abstract @NotNull String description();
  }
  click TextTransformerDecorator href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/logic/TextTransformerDecorator.java"
  class CaseTransform["transform.CaseTransform : TextTransformerDecorator"] {
    +private final Type typeOfTransform;
    +public CaseTransform(@NotNull TextTransformer textToTransform, @NotNull Type typeOfTransform);
    ~public @NotNull String transform();
    ~public @NotNull String description(); 
    +private @NotNull String caseTransformation(@NotNull String text);
    +public enum Type;
  }
  click CaseTransform href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/logic/transform/CaseTransform.java"
class Enum["public enum Type"]{
    +public static Type fromName(@NotNull String name);
    <<enumeration>>
    UPPER
    LOWER
    CAPITALIZE
    SENTECE_CAPITALIZE
    IDENTITY
}
class DuplicatesRemovalTransform["transform.DuplicatesRemovalTransform : TextTransformerDecorator"] {
  +private final boolean removeAllow;
  +public DuplicatesRemovalTransform(@NotNull TextTransformer textToTransform, boolean removingAllowed);
  ~public @NotNull String transform();
  ~public @NotNull String description();
  +private @NotNull String removeAdjacentDuplicates(@NotNull String input);
}
click DuplicatesRemovalTransform href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/logic/transform/DuplicatesRemovalTransform.java"
class LaTeXEscapesTransform["transform.LaTeXEscapesTransform : TextTransformerDecorator"]{
  +private static final Map<Character, String> latexCharacterMappings;
  +private final boolean latexCharactersAllowed;
  +public LaTeXEscapesTransform(@NotNull TextTransformer textToTransform, boolean latexCharactersAllowed);
  ~public @NotNull String transform();
  ~public @NotNull String description();
  +private @NotNull String addLaTeXEscapes();
}
click LaTeXEscapesTransform href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/logic/transform/LaTeXEscapesTransform.java"
class NumberExpansionTransform["NumberExpansionTransform : TextTransformerDecorator"]{
  +private static final Map<Integer, String> DIGITS;
  +private static final Map<Integer, String> TEENS;
  +private static final Map<Integer, String> TENS;
  +private static final Map<Integer, String> HUNDREDS;
  +private static final PluralVariant thousands;
  +private static final PluralVariant millions;
  +private static final PluralVariant billions;
  +private static final PluralVariant trillions;
  +private static final PluralVariant quadrillions;
  +private static final PluralVariant quintillions;
  +private final boolean numberExpandAllowed;
  +public NumberExpansionTransform(@NotNull TextTransformer textToTransform, boolean numberExpandAllowed);
  +private static @NotNull String numberInWords(long number);
  ~public @NotNull String transform();
  ~public @NotNull String description();
  +private @NotNull String expandNumbers(@NotNull String s);
}
click NumberExpansionTransform href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/logic/transform/NumberExpansionTransform.java"
class ReverseTransform["ReverseTransform : TextTransformerDecorator"]{
  +private final boolean inverseAllow;
  +public ReverseTransform(@NotNull TextTransformer textToTransform, boolean reversalAllowed);
  ~public @NotNull String transform();
  ~public @NotNull String description();
  +private @NotNull String revertString(@NotNull String input);
}
click ReverseTransform href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/logic/transform/ReverseTransform.java"
class ShortcutTransform["ShortcutTransform : TextTransformerDecorator"]{
  +private final Type shortcutType;
  +private final Map<String, String> shortcutMap;
  +public ShortcutTransform(@NotNull TextTransformer textToTransform, @NotNull Type shortcutType);
  +private Map<String, String> initializeShortcutMap();
  ~public @NotNull String transform();
  ~public @NotNull String description();
  +public @NotNull String applyShortcutModification(@NotNull String inputText);
  +public enum Type;
}
click ShortcutTransform href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/logic/transform/ShortcutTransform.java"
class Enum1["public enum Type"]{
    +public static Type fromName(@NotNull String name);
    <<enumeration>>
    EXPAND
    COMPRESS
    IDENTITY
}
class TransformerBase["TransformerBase : TextTransformer"]{
    +public TransformerBase(@NotNull String str);
    ~public @NotNull String transform();
}
click TransformerBase href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/logic/TransformerBase.java"
class TransformsRegister {
    private static final List<TextTransformerDecorator> transforms;
    public static void register(@NotNull TextTransformerDecorator transform);
    public static @NotNull @UnmodifiableView List<TextTransformerDecorator> getTransforms()
}
click TransformsRegister href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/logic/TransformsRegister.java"
  TransformsRegister "1" --> "*" TextTransformerDecorator
  ShortcutTransform .. Enum1
  TextTransformer <|-- TransformerBase
  TextTransformerDecorator <|-- ShortcutTransform
  TextTransformerDecorator <|-- ReverseTransform
  TextTransformerDecorator <|-- NumberExpansionTransform
  TextTransformerDecorator <|-- LaTeXEscapesTransform
  TextTransformerDecorator <|-- DuplicatesRemovalTransform
  CaseTransform .. Enum
  TextTransformer <|-- TextTransformerDecorator
  TextTransformerDecorator <|-- CaseTransform
```
### Diagram Klas Rest dla Serwera -pl.put.poznan.transformer.server.rest
```mermaid
classDiagram
    class ResponseBody {
        private final String message, error;
        private ResponseBody(@Nullable String data, @Nullable String errorMessage);
        public static @NotNull ResponseBody text(@NotNull String message);
        public static @NotNull ResponseBody raw(@NotNull String raw);
        public static @NotNull ResponseBody error(@NotNull String error);
        public String toString();
    }
    click ResponseBody href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/rest/ResponseBody.java"
    class TextTransformerController{
        private static final Logger logger;
        //@RequestMapping(method = RequestMethod.GET, produces = "application/json")
        public String helpRequest(@PathVariable @NotNull String serviceName);
        //@RequestMapping(method = RequestMethod.POST, produces = "application/json")
        public @NotNull String transformRequest(@NotNull @PathVariable String serviceName, @Nullable @RequestBody TextTransformerRequestBody transforms);
        private @NotNull String performTransformation(@NotNull @RequestBody TextTransformerRequestBody transforms);
        private @NotNull String help();
    }
    click TextTransformerController href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/rest/TextTransformerController.java"
    class logic["import pl.put.poznan.transformer.server.logic.*"]{
        Class TextTransformer;
        Class TransformerBase;
        Class TransformsRegister;
        Classes transform.*;
    }
    click logic href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/logic"
    class util{
        Class JSONFieldDescription;
    }
    click util href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/util/JSONFieldDescription.java"
    class TextTransformerRequestBody{
        private String text;
        private String caseTransform;
        private boolean numbers;
        private boolean reverse;
        private String shortcuts;
        private boolean latex;
        private boolean neighbors;
        public TextTransformerRequestBody(String text, String caseTransform, boolean numbers, boolean reverse, String shortcuts, boolean latex, boolean neighbors);
        public String getText();
        public void setText(String text);
        public @NotNull String getCaseTransform();
        public void setCaseTransform(@NotNull String transform);
        public boolean isReverse();
        public void setReverse(boolean reverse);
        public boolean isNumbers();
        public void setNumbers(boolean numbers);
        public @NotNull String getShortcuts();
        public void setShortcuts(@NotNull String shortcuts);
        public boolean isLatex();
        public void setLatex(boolean latex);
        public boolean isNeighbors();
        public void setNeighbors(boolean neighbors);
    }
    click TextTransformerRequestBody href "https://github.com/RubyNaxela/softeng/blob/main/Server/src/main/java/pl/put/poznan/transformer/server/rest/TextTransformerRequestBody.java"
      TextTransformerRequestBody .. TextTransformerController
      util .. TextTransformerController
      logic .. TextTransformerController
      ResponseBody .. TextTransformerController
```
