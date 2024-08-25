package me.coriew.ashakespeareanchat.data;

import org.bukkit.configuration.file.FileConfiguration;

public class Configuration {
    private StyleType _style;
    private String _prompt;
    private ModelType _model;
    private String _auth;
    private String _model_url;

    public Configuration() {
        _style = StyleType.NONE;
        _prompt = "";
        _model = ModelType.GPT4OMINI;
        _auth = "";
        _model_url = "";
    }

    public StyleType getStyle() {
        return _style;
    }

    public void setStyle(StyleType style) {
        _style = style;
    }

    /**
     * Gets the prompt for the custom style type
     * @return The prompt
     */
    public String getPrompt() {
        return _prompt;
    }

    /**
     * Sets the prompt for the custom style type
     * @param prompt The prompt
     */
    public void setPrompt(String prompt) {
        _prompt = prompt;
    }

    public ModelType getModel() {
        return _model;
    }

    public void setModel(ModelType model) {
        _model = model;
    }

    public String getAuth() {
        return _auth;
    }

    public void setAuth(String auth) {
        _auth = auth;
    }

    /**
     * Gets the URL for the custom model
     * @return The URL
     */
    public String getModelUrl() {
        return _model_url;
    }

    /**
     * Sets the URL for the custom model
     * @param model_url The URL
     */
    public void setModelUrl(String model_url) {
        _model_url = model_url;
    }

    /**
     * Parses the configuration file and loads the values into the configuration object
     * @param config The configuration file
     */
    public void loadFrom(FileConfiguration config) {
         _style = StyleType.fromName(config.getString("style"));
         _prompt = config.getString("prompt");
         _model = ModelType.fromName(config.getString("model"));
         _auth = config.getString("auth");
         _model_url = config.getString("model_url");
    }

    /**
     * Saves the configuration object to the configuration file
     * @param config The configuration file
     */
    public void writeTo(FileConfiguration config) {
        if (_style != null) config.set("style", _style.getReadableName());
        else config.set("style", StyleType.SHAKESPEAREAN.getReadableName());

        config.set("prompt", _prompt);

        if (_model != null) config.set("model", _model.getApiName());
        else config.set("model", ModelType.GPT4OMINI.getApiName());

        config.set("auth", _auth);
        config.set("model_url", _model_url);
    }

    /**
     * Parses the configuration file and loads the values into a new configuration object
     * @param config The configuration file
     */
    public static Configuration fromConfig(FileConfiguration config) {
        Configuration configuration = new Configuration();
        configuration.loadFrom(config);
        return configuration;
    }
}
