# wakDuper - A Minecraft Fabric Mod (v1.21.1)

**wakDuper** is a Minecraft Fabric mod designed for exploring dupe exploits in multiplayer environments. This mod provides players with a set of tools that manipulate the GUI and server-client packet interactions, often used in dupe glitch hunting.

## Features

### 1. Soft Close
- **Functionality**: Closes your current GUI without sending a `CloseHandledScreen` packet to the server.
- **Usage**: Many small dupe glitches exploit this behavior as the server loses track of the GUI state, leading to potential duplication exploits.

### 2. De-sync
- **Functionality**: Closes the GUI server-side but keeps it open client-side.
- **Usage**: While this feature is available, it is generally useless in practical scenarios.

### 3. Send Packets (True/False)
- **Functionality**: Toggles whether the client should send `ClickSlot` and `ButtonClick` packets to the server.
- **Usage**: When disabled, all GUI interactions remain client-side and are not transmitted to the server, preventing any server-side processing of those actions.

### 4. Delay Packets (True/False)
- **Functionality**: Stores `ClickSlot` and `ButtonClick` packets in memory, delaying their transmission to the server until manually toggled back.
- **Usage**: This acts as a "blink" for click slots, allowing players to manipulate and sequence GUI actions in a way often used for dupe exploits.

### 5. Save UI
- **Functionality**: Saves your current GUI to memory, allowing restoration by pressing the 'V' key (configurable in Keybinds).
- **Usage**: This allows for actions like placing blocks while remaining in the GUI, as long as the GUI is not closed or replaced.

### 6. Leave & Send Packets
- **Functionality**: Works in tandem with "Delay Packets". It sends all delayed packets at once and immediately disconnects the player from the server.
- **Usage**: Can create potential race conditions on non-vanilla servers, useful for exploiting certain server responses.

### 7. Sync Id
- **Functionality**: Tracks which GUI your player is currently interacting with.
- **Usage**: Essential for understanding how the game associates GUI interactions with the player.

### 8. Revision
- **Functionality**: Tracks the number of clicks made within a GUI.
- **Usage**: Helps the server monitor and verify player actions in GUIs, though it may provide insight into dupe possibilities.

### 9. GUI Chat
- **Functionality**: Allows players to send chat messages or commands without leaving the GUI.
- **Usage**: Handy for sending pre-prepared commands while still interacting with a GUI.

### 10. Get Name
- **Functionality**: Automatically prints the current UI's name in chat and copies it to the clipboard.
- **Usage**: Useful for identifying GUI objects when working on exploits.

### 11. PaperMC Dupe (v1.20.6 - v1.21.1)
- **Functionality**: Exploits a known dupe method involving books and quills on PaperMC servers.
- **Usage**: Limited to specific server versions, this dupe method may still work under certain configurations.

## Installation
1. Download the latest version of the wakDuper mod from [Releases](https://github.com/me-jndildap/wakDuper/releases).
2. Place the `.jar` file into your `mods` folder within your Minecraft installation.
3. Run Minecraft with the Fabric loader and enjoy!

## Contributing
We welcome contributions and suggestions! Feel free to open an issue or submit a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE](https://github.com/me-jndildap/wakDuper?tab=License-1-ov-file) file for details.

## Links
- Minecraft Protocol Information: [wiki.vg/Protocol](https://wiki.vg/Protocol)

