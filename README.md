# mc-spigot-template

This is a template for creating a Spigot plugin for Minecraft.

## Installation

1. Download the latest release from the [releases page](https://github.com/jkm-mc/mc-myplugin/releases).
2. Place the downloaded JAR file in your server's `plugins` directory.

## Configuration

_Add information about plugin configuration here._

## Commands

_Add information about plugin commands here._

- `/somecommand` - Command description.

## Development

You can run `mvn clean package` to build the plugin JAR file. The JAR file will be placed in the `target` directory.

The version in `pom.xml` will be automatically applied to your `plugin.yml`. Changes to `pom.xml` will result in a Github Release being published.