# AShakespeareanChat
A Minecraft Java Spigot plugin that transforms chat messages into Shakespearean or other styles of English.

## Features
### Current Features
- Transform chat messages into various styles of English
  - `Shakespearean` - People talk like Shakespeare
  - `Royal` - People talk like the Queen
  - `Scholar` - People talk in a scholarly manner
  - `Complex Writer` - People use very complex words and structures
  - `Poet` - People talk like a poet
  - `Rhyming` - People talk in continuous rhymes
  - `Yoda` - People talk like Yoda
  - `Pirate` - People talk like a pirate
  - `Western` - People talk like Old West cowboys
  - `Valley` - People talk like people from the valley
  - `Baby` - People talk like a baby
  - `Child` - People talk like a child
  - `Goth` - People talk in a goth style
  - `Depressing` - People talk in a depressing tone
  - `Very Sad` - People talk in a very sad manner
  - `Stutter` - People talk with a stutter
  - `Robotic` - People talk like a robot
  - `Computer` - People talk in a computer-like manner
  - `Nonsense` - People talk in a nonsensical way
  - `Newscaster` - People talk like a news reporter
  - `Otherworldly` - People talk in an otherworldly or mystical manner
  - `Trump` - People talk like Trump
  - `Andrew Tate` - People talk in an Andrew Tate-like manner
  - `Eminem` - People talk like Eminem
  - `Snoop Dogg` - People talk like Snoop Dogg
  - `Professional` - Converts text to a professional tone
  - `Grammar` - Ensures proper grammar usage
  - `Concise` - Makes the text more concise and to the point
  - `Simpleton` - Simplifies the text to very basic words and structure
  - `Abbreviated` - Converts the text to abbreviated, texting-style language
  - `Sarcastic` - Converts the text to a sarcastic tone
  - `No Swearing` - Removes any swearing from the text
- Configurable settings
  - Use custom model for transforming messages
  - Define own style using a prompt

### Later Features
- Per player style

## Commands
### /asccurrent
Check the current style of chat \
usage: `/asccurrent`\
permission: `asc.current`
### /ascchange
Change the style of chat\
usage: `/ascchange <style>`\
permission: `asc.change`
### /ascconfig
View the plugin configuration\
usage: `/ascconfig`\
permission: `asc.config`
### /ascreload
Reload the plugin\
usage: `/ascreload`\
permission: `asc.reload`

## Configuration
```yaml
# TYPES:
# none - No style conversion
# shakespearean - People talk like Shakespeare
# royal - People talk like the Queen
# scholar - People talk in a scholarly manner
# complex_writer - People use very complex words and structures
# poet - People talk like a poet
# rhyming - People talk in continuous rhymes
# yoda - People talk like Yoda
# pirate - People talk like a pirate
# western - People talk like Old West cowboys
# valley - People talk like people from the valley
# baby - People talk like a baby
# child - People talk like a child
# goth - People talk in a goth style
# depressing - People talk in a depressing tone
# very_sad - People talk in a very sad manner
# stutter - People talk with a stutter
# robotic - People talk like a robot
# computer - People talk in a computer-like manner
# nonsense - People talk in a nonsensical way
# newscaster - People talk like a news reporter
# otherworldly - People talk in an otherworldly or mystical manner
# trump - People talk like Trump
# andrew_tate - People talk in an obnoxious Andrew Tate-like manner
# eminem - People talk like Eminem
# snoop_dogg - People talk like Snoop Dogg
# professional - Converts text to a professional tone
# grammar - Ensures proper grammar usage
# concise - Makes the text more concise and to the point
# simpleton - Simplifies the text to very basic words and structure
# abbreviated - Converts the text to abbreviated, texting-style language
# sarcastic - Converts the text to a sarcastic tone
# no_swearing - Removes any swearing from the text
# custom - You can define your own prompt
style: SHAKESPEAREAN

# If you used the CUSTOM conversion, you can define your own prompt here
prompt: "Convert text to shakespearean"

# MODEL:
# gpt-4o-mini - (RECOMMENDED) a small, fast, and cheaper model produced by OpenAI
# gpt-4o - gpt-4o, a large, powerful, and expensive model produced by OpenAI
# custom - You can define your own model
model: gpt-4o-mini

# This is the authorisation key for the API
# This could be an API key, a username and password, or any other form of authorisation
# An API key might look like this: "#sk-...jGQA" for OpenAI
auth: "YOUR_AUTHORISATION_KEY"

# If you used the CUSTOM model, you can define your own model here
# The route will need to accept a POST request with the following JSON body:
# {
#   "prompt": "The system to convert the text to (e.g. shakespearean)",
#   "message": "The text to convert"
# }
# And return a JSON object with the following structure:
# {
#   "message": "The converted text"
# }
# The model_url should be the URL of the route
# For example, "https://api.openai.com/v1/chat/completions" for OpenAI
model_url: "YOUR_MODEL_URL"
```

## Contributing
Any contributions are welcome! Just fork the repository, make your changes, and create a pull request. If you have any questions, feel free to ask.
