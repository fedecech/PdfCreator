# Pdf Creator
## Run the program

`java PdfCreator <input_file[default=input.txt]> <output_file[default=output.pdf]>`

## Input format
The input file should be a txt file.
The file can contain `commands` and `paragraph text`. Each different command and paragraph text must be in a new line.

## Commands

- `.large`: makes following text size 20
- `.normal`: makes following text size 10 (default)
- `.bold`: makes following text bold
- `.italics`: makes following text italics
- `.regular`: makes following text regular (weight, font size)
- `.indent <number>` indent following text by adding/removing margin left. (`indentation[px] = amount * 20px`). NOTE: If the current paragraph is not empty a new paragraph is created automatically.
- `.nofill`: set indentation format to no fill (default)
- `.fill`: set indentation format to fill
- `.paragraph`: creates a new paragraph

## Package
The program contains a Lexer which parses the input file into tokens. The tokens are passed to the Parser which creates the document and modifies it accordingly and saves it to file.

## Exceptions
Exceptions are expected only during the compilation process (in `Lexer`), due to wrong inputs (i.e.: not existing commands, wrong commands formats...). They could happen also in case of wrong input/output files.

## Thoughts

There are a few things I would improve:
- The way the Lexer parses the different commands (switch statement). A way to improve it could be to use a look-up table.
- Same thing happens in ApplyStyle (apply method). A way to improve it could be to create subclasses for each style and override the apply method.



