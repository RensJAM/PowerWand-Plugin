# PowerWand-Plugin
# StickLightning

A simple Minecraft Paper plugin that adds special ability sticks with configurable cooldowns and ranges.

## Features

### Lightning Stick

* Strike lightning at the block you are looking at.
* Configurable maximum range.
* Individual player cooldown.

### Freeze Stick

* Freeze entities by disabling their gravity temporarily.
* Snowflake particle effect when freezing.
* Configurable freeze duration and range.
* Individual player cooldown.

---

## Commands

| Command           | Description                 |
| ----------------- | --------------------------- |
| `/lightningstick` | Gives you a Lightning Stick |
| `/freezestick`    | Gives you a Freeze Stick    |

---

## Configuration

Example `config.yml`:

```yaml
lightningRange: 50
freezeRange: 20
cooldownTime: 1
freezeTime: 3
```

### Options

| Setting          | Description                             |
| ---------------- | --------------------------------------- |
| `lightningRange` | Maximum lightning targeting distance    |
| `freezeRange`    | Maximum freeze targeting distance       |
| `cooldownTime`   | Cooldown between ability uses (seconds) |
| `freezeTime`     | Duration of freeze effect (seconds)     |

---

## Requirements

* Minecraft Paper Server
* Java 21+
* Minecraft verision 26.1.2+

---

## Installation

1. Download the latest release.
2. Place the plugin `.jar` inside your server's `plugins` folder.
3. Start or restart the server.
4. Configure the plugin through `config.yml`.

---

## Planned Features

* Knockback Stick
* Improved freeze mechanics
* Additional particle effects
* More configurable abilities

---

## Author

Created by RensJam as a learning project while exploring Java and Paper plugin development.
