package me.coriew.ashakespeareanchat.data;

public enum StyleType {
    NONE("None"),

    // Styles
    SHAKESPEAREAN("Shakespearean", "Convert the text to Shakespearean"),
    ROYAL("Royal", "Convert the text to Royal"),
    SCHOLAR("Scholar", "Convert the text to scholarly"),
    COMPLEX_WRITER("Complex Writer", "Convert the text to use very complex words"),
    POET("Poet", "Convert the text to poet-like"),
    RHYMING("Rhyming", "Convert the text to a continuous rhyme"),
    YODA("Yoda", "Convert the text to Yoda"),
    PIRATE("Pirate", "Convert the text to Pirate"),
    WESTERN("Western", "Convert the text to sound like an Old West cowboy"),
    VALLEY("Valley", "Convert the text to Valley"),
    BABY("Baby", "Convert the text to baby-like"),
    CHILD("Child", "Convert the text to child-like"),
    GOTH("Goth", "Convert the text to goth"),
    DEPRESSING("Depressing", "Convert the text to depressing"),
    VERY_SAD("Very Sad", "Convert the text to very sad"),
    STUTTER("Stutter", "Convert the text to stutter"),
    ROBOTIC("Robotic", "Convert the text to robotic"),
    COMPUTER("Computer", "Convert the text to computer-like"),
    NONSENSE("Nonsense", "Convert the text to nonsense"),
    NEWSCASTER("Newscaster", "Convert the text to sound like a news report"),
    OTHERWORLDLY("Otherworldly", "Convert the text to otherworldly"),

    // Celebrities
    TRUMP("Trump", "Convert the text to Trump-like"),
    ANDREW_TATE("Andrew Tate", "Convert the text to obnoxious Andrew Tate-like"),
    EMINEM("Eminem", "Convert the text to Eminem-like"),
    SNOOP_DOGG("Snoop Dogg", "Convert the text to Snoop Dogg-like"),

    // Corrections
    PROFESSIONAL("Professional", "Convert the text to professional"),
    GRAMMAR("Grammar", "Convert the text to use proper grammar"),
    CONCISE("Concise", "Convert the text to be more concise and to the point"),
    SIMPLETON("Simpleton", "Convert the text to very simple, basic words and structure"),
    ABBREVIATED("Abbreviated", "Convert the text to abbreviated, texting-style language"),
    SARCASTIC("Sarcastic", "Convert the text you're provided to sarcastic, don't reply to it"),
    NO_SWEARING("No Swearing", "Convert the text to remove swearing"),

    CUSTOM("Custom");

    private final String readableName;
    private final String prompt;

    // Constructor
    StyleType(String readableName) {
        this.readableName = readableName;
        this.prompt = "Enter the " + readableName + " text you would like to convert:";
    }

    StyleType(String readableName, String prompt) {
        this.readableName = readableName;
        this.prompt = prompt;
    }

    // Getter for the readable name
    public String getReadableName() {
        return readableName;
    }

    // Getter for the prompt
    public String getPrompt() {
        return prompt;
    }

    public static StyleType fromName(String name) {
        name = name.replace("_", " ");

        for (StyleType type : values()) {
            if (type.readableName.equalsIgnoreCase(name)) {
                return type;
            }
        }

        return null;
    }
}
