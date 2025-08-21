# How to Compile a LaTeX Book on Mac/Linux

## 1. Install LaTeX (if not installed)
### Mac (via MacTeX)
```bash
brew install mactex
```
### Linux (Ubuntu/Debian-based)
```bash
sudo apt install texlive-full
```

## 2. Navigate to Your Project Directory
```bash
cd /path/to/your/book
```

## 3. Compile the Main `.tex` File
Use `pdflatex` or `xelatex` (if using fonts or Unicode characters):
```bash
pdflatex main.tex
```
or  
```bash
xelatex main.tex
```

## 4. Run Bibliography (if applicable)
If you use `bibtex`:
```bash
bibtex main
pdflatex main.tex  # Run again to update references
pdflatex main.tex
```

## 5. Automate Compilation (Recommended)
Instead of running multiple commands manually, use:
```bash
latexmk -pdf main.tex
```
This automatically handles dependencies.

## 6. (Optional) Configuring JetBrains profiles
If you want to configure a profile for your task in an IDE such as one of the JetBrains products, follow these steps
### a. Choose a profile name
### b. Execute option choose `Execute script`
Put the following cmd in the `Script text`
```bash
latexmk -pdf main; open main.pdf; exit
```
### c. For `Working directory`
Put the folder containing main `.tex` file.
### d. Check the box `Execute in the terminal`
