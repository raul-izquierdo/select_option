# select_option

## Overview

This project provides a simple way to display a list of options to the user and allow them to select one interactively from the console. The core functionality is provided by the method `OptionsSelector.showOptions`, which is designed to work with lists of strings representing the available options.

## The method OptionsSelector.showOptions

### Purpose
`OptionsSelector.showOptions` displays a list of options in the console and prompts the user to select one. It returns the index of the selected option.

### Usage
```java
List<String> options = Arrays.asList("Option 1", "Option 2", "Option 3");

int selectedIndex = OptionsSelector.showOptions(options);

System.out.println("You selected: " + options.get(selectedIndex));
```

### How Options Are Presented

When `OptionsSelector.showOptions` is executed, the user sees a prompt at the top of the console:

```
(type to filter or use arrows ↑/↓): <your filter text>
```

Below the prompt, the options are displayed interactively:
- The currently selected option is highlighted and marked with a `> ` prefix.
- As the user types, the list is filtered in real time to show only matching options (case and accent insensitive).
- The user can use the up/down arrow keys to move the selection.
- The matching part of each option is visually highlighted.


Example display (no filter):
```
(type to filter or use arrows ↑/↓):
> 01. Temperature
  03. Editor I
  04. Validations
  05. Editor II. Undo
  07. Encrypt
  09. Visitor
  12. JHotDraw
```

Example display (with filter "edi"):
```
(type to filter or use arrows ↑/↓): edi
> 03. Editor I
  05. Editor II. Undo
```

If the user presses the down arrow key, the selection moves down:
```
(type to filter or use arrows ↑/↓): edi
  03. Editor I
> 05. Editor II. Undo
```


## License
See LICENSE file for details.

Raúl Izquierdo Castanedo (raul@uniovi.es)
