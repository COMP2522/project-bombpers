# Draft for the JSON data structure

**Tentative**

## The Plan
Have a JSON file that stores the state of the game so the player can return 
and play from where they left the game.

## What We Need
- Player heath
- Player Position
- Player Weapon
- Type of Bullet for the weapon
- Number of enemies
- Types of the enemies
- Wave number
- number of enemies remaining corresponding to the wave number the player is on.
- Maybe more to add later...
```
{ //an example of what it may look like
    "player":{
            "health" : "100",
            "xPosition": "22",
            "yPosition": "33",   
    },
    "weapon":{
            "Projectile": "Fast"
    }
}...
```

