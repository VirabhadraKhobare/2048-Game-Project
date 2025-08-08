# 2048 Game Project
A complete 2048 game implementation with both console and web versions, focusing on Data Structures & Algorithms.

## 🎮 **[▶️ PLAY WEB VERSION NOW!](https://virabhadrakhobare.github.io/2048-Game-Project/)**

*Click the link above to play the game instantly in your browser!*

## 🎮 Two Versions Available

### 1. Console Version (Java)
A command-line 2048 game implemented in Java, focusing on DS&A concepts for interviews.

### 2. Web Version (HTML/CSS/JavaScript)
A modern, animated web-based version with smooth animations and touch support.

## 🚀 How to Play

### Console Version
```bash
# Compile all Java files
javac src/*.java

# Run the game
java -cp src Main
```

**Controls:**
- W: Move Up
- S: Move Down  
- A: Move Left
- D: Move Right
- Q: Quit Game

### Web Version
**🌐 Play Online:** **[Launch Game](https://virabhadrakhobare.github.io/2048-Game-Project/)**

**📁 Play Locally:**
1. Open `index.html` in any modern web browser
2. Use arrow keys on desktop or swipe gestures on mobile
3. Click "New Game" to restart

**Controls:**
- Arrow Keys: Move tiles
- Touch/Swipe: Mobile controls
- New Game Button: Restart

## 🏗️ Project Structure

```
2048-puzzels-game/
├── src/                        # Java Console Version
│   ├── Main.java              # Entry point + game loop
│   ├── Board.java             # Game logic and board management
│   ├── MoveDirection.java     # Direction enumeration
│   ├── Utils.java             # Game utilities and helpers
│   └── TestCases.java         # Test cases for validation
├── index.html                 # Web version main page
├── styles.css                 # Web version styling and animations
├── script.js                  # Web version game logic
└── README.md                  # This file
```

## 🧮 Data Structures & Algorithms

### Data Structures Used
- **2D int array**: Grid representation (4x4 matrix)
- **List<int[]>**: Dynamic tracking of empty cells
- **Arrays**: Row manipulation and processing

### Core Algorithms
1. **Movement Algorithm**: Compress → Merge → Compress (O(N²))
2. **Direction Handling**: Matrix transformations (transpose/reverse)
3. **Random Placement**: Uniform sampling from available positions
4. **Game State Detection**: Win/lose condition checking

### Algorithm Complexity
- **Move Operations**: O(N²) where N = 4 (grid size)
- **Empty Cell Detection**: O(N²)
- **Game State Check**: O(N²)
- **Overall**: Efficient for 4x4 grid with room for optimization

## ✨ Features

### Console Version
- ✅ Clean ASCII art display
- ✅ Input validation and error handling
- ✅ Score tracking
- ✅ Play again functionality
- ✅ Comprehensive test cases

### Web Version
- ✅ Smooth tile animations
- ✅ Responsive design (mobile-friendly)
- ✅ Touch gesture support
- ✅ Local storage for best score
- ✅ Win/Game Over overlays
- ✅ Modern UI with visual feedback

## 🎯 Game Rules
1. Use arrow keys/WASD to move tiles
2. When two tiles with the same number touch, they merge
3. Each move adds a new tile (2 or 4) in a random empty spot
4. Goal: Create a tile with the number 2048
5. Game ends when no moves are possible

## 🔧 Technical Implementation

### Java Console Version
- Object-oriented design with separation of concerns
- Robust input handling and validation
- Efficient matrix operations for tile movement
- Comprehensive error handling

### Web Version  
- ES6 JavaScript classes
- CSS animations and transitions
- Touch event handling for mobile
- Local storage integration
- Responsive design principles

## 🧪 Testing
Run the test cases for the console version:
```bash
java -cp src TestCases
```

## 🌐 Browser Support
The web version supports all modern browsers:
- Chrome 60+
- Firefox 60+
- Safari 12+
- Edge 79+

## 📱 Mobile Support
- Touch gestures (swipe to move)
- Responsive layout
- Optimized for both portrait and landscape

---
**Developed as a Data Structures & Algorithms learning project**
