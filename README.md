# Minecraft-UHC-GAMES

Single-instance Minecraft UHC Games project from the 2017/2018 era. The repository contains reverse-engineered game-mode work around UHC-style match flow, arena management, player state, commands, and network-era gameplay systems.

## What It Does

- Runs a standalone UHC Games match on one Minecraft server instance.
- Manages arenas, map rotation, player state, game start/end flow, and active match lifecycle.
- Provides commands for bounties, kills, leaderboards, maintenance, map rotation, player lists, sponsors, stats, wagers, speed, spoofing, start/end, and debugging.
- Includes managers for players, arenas, bounties, maps, points, spectators, sponsors, and voting.

## Repository Layout

- `src/Commands/` - player, host, stats, bounty, sponsor, and admin commands.
- `src/dev/tjxjnoobie/sg/commands/` - core game command flow.
- `src/dev/tjxjnoobie/sg/managers/` - arena, player, map, point, spectator, sponsor, and voting managers.
- `src/dev/tjxjnoobie/sg/other/` - supporting region/cuboid utilities.

## Tech Stack

- Java
- Bukkit/Spigot-style plugin APIs
- Single-instance minigame server architecture

## Status

Archived historical project. It is retained as reference material for older reverse-engineered UHC/minigame architecture.
