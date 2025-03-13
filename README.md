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

