package me.coriew.ashakespeareanchat.data;

public enum ModelType {
    GPT4OMINI("gpt-4o-mini"),
    GPT4O("gpt-4o"),
    CUSTOM("custom");

    private final String apiName;

    ModelType(String apiName) {
        this.apiName = apiName;
    }

    public String getApiName() {
        return apiName;
    }

    public static ModelType fromName(String name) {
        for (ModelType type : values()) {
            if (type.apiName.equalsIgnoreCase(name)) {
                return type;
            }
        }

        return null;
    }
}
