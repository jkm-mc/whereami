# whereami

This Spigot plugin allows you to define zones in your world and then query them to find out where you are.

Zones are defined in the `config.yml` file, and can be rectangular or polygonal.

![Example: In a zone](https://github.com/jkm-mc/whereami/blob/master/examples/spawn.png?raw=true)
![Example: Not in a zone](https://github.com/jkm-mc/whereami/blob/master/examples/no-zone.png?raw=true)

## Installation

1. Download the latest release from the [releases page](https://github.com/jkm-mc/whereami/releases).
2. Place the downloaded JAR file in your server's `plugins` directory.

## Configuration

You can define zones in the `config.yml` file.

The format is as follows:

```yaml
zones:
    "Your Zone Name":
        # zone vertex data (see below)
    "Your Other Zone":
        # zone vertex data (see below)
```

Your vertex data can be either rectangular or polygonal, see below for details on each.

### Rectangular Zones

Rectangular zones are defined by two points, the top-left and bottom-right corners of the rectangle.

```yaml
zones:
    "Your Rectangular Zone":
        x1: -200 # Top left X
        z1: -200 # Top left Z
        x2: 200  # Bottom right X
        z2: 200  # Bottom right Z
```

### Polygonal Zones

A polygonal zone is defined by a list of points, which are the vertices of the polygon.

The vertices are defined as a list of X and Z coordinates, and must be in clockwise order.

```yaml
zones:
    "Your Polygonal Zone":
        vertices:
            - - 300 # point 1 x
              - 300 # point 1 z
            - - 400 # point 2 x
              - 300 # point 2 z
            - - 550 # point 3 x
              - 450 # point 3 z
```

## Commands

- `/whereami` - Displays the name of the zone you are currently in.

## Caveats

I hope to solve these in future.

- Overlapping zones are not handled
- Zones are not restricted to a single world