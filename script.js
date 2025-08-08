class Game2048 {
    constructor() {
        this.grid = this.createEmptyGrid();
        this.score = 0;
        this.bestScore = parseInt(localStorage.getItem('bestScore')) || 0;
        this.gameWon = false;
        this.gameOver = false;
        
        this.tileContainer = document.getElementById('tile-container');
        this.scoreElement = document.getElementById('score');
        this.bestScoreElement = document.getElementById('best-score');
        this.gameMessage = document.getElementById('game-message');
        this.messageText = document.getElementById('message-text');
        
        this.updateDisplay();
        this.addRandomTile();
        this.addRandomTile();
        this.updateTiles();
        
        this.bindEvents();
    }
    
    createEmptyGrid() {
        return Array(4).fill().map(() => Array(4).fill(0));
    }
    
    bindEvents() {
        // Keyboard events
        document.addEventListener('keydown', (e) => {
            if (this.gameOver && !['ArrowUp', 'ArrowDown', 'ArrowLeft', 'ArrowRight'].includes(e.key)) {
                return;
            }
            
            switch(e.key) {
                case 'ArrowUp':
                    e.preventDefault();
                    this.move('up');
                    break;
                case 'ArrowDown':
                    e.preventDefault();
                    this.move('down');
                    break;
                case 'ArrowLeft':
                    e.preventDefault();
                    this.move('left');
                    break;
                case 'ArrowRight':
                    e.preventDefault();
                    this.move('right');
                    break;
            }
        });
        
        // Touch events for mobile
        let startX, startY;
        
        this.tileContainer.addEventListener('touchstart', (e) => {
            startX = e.touches[0].clientX;
            startY = e.touches[0].clientY;
        });
        
        this.tileContainer.addEventListener('touchend', (e) => {
            if (!startX || !startY) return;
            
            const endX = e.changedTouches[0].clientX;
            const endY = e.changedTouches[0].clientY;
            
            const diffX = startX - endX;
            const diffY = startY - endY;
            
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (diffX > 0) {
                    this.move('left');
                } else {
                    this.move('right');
                }
            } else {
                if (diffY > 0) {
                    this.move('up');
                } else {
                    this.move('down');
                }
            }
            
            startX = null;
            startY = null;
        });
        
        // Button events
        document.getElementById('new-game-btn').addEventListener('click', () => {
            this.restart();
        });
        
        document.getElementById('try-again-btn').addEventListener('click', () => {
            this.restart();
            this.hideMessage();
        });
    }
    
    addRandomTile() {
        const emptyCells = [];
        
        for (let r = 0; r < 4; r++) {
            for (let c = 0; c < 4; c++) {
                if (this.grid[r][c] === 0) {
                    emptyCells.push({row: r, col: c});
                }
            }
        }
        
        if (emptyCells.length > 0) {
            const randomCell = emptyCells[Math.floor(Math.random() * emptyCells.length)];
            this.grid[randomCell.row][randomCell.col] = Math.random() < 0.9 ? 2 : 4;
        }
    }
    
    move(direction) {
        if (this.gameOver) return;
        
        const previousGrid = this.grid.map(row => [...row]);
        let moved = false;
        
        switch(direction) {
            case 'left':
                moved = this.moveLeft();
                break;
            case 'right':
                moved = this.moveRight();
                break;
            case 'up':
                moved = this.moveUp();
                break;
            case 'down':
                moved = this.moveDown();
                break;
        }
        
        if (moved) {
            this.addRandomTile();
            this.updateTiles();
            this.updateDisplay();
            
            if (this.checkWin() && !this.gameWon) {
                this.gameWon = true;
                this.showMessage('You Win!', 'won');
            } else if (this.checkGameOver()) {
                this.gameOver = true;
                this.showMessage('Game Over!', 'lost');
            }
        }
    }
    
    moveLeft() {
        let moved = false;
        
        for (let r = 0; r < 4; r++) {
            const row = this.grid[r];
            const compressed = this.compress(row);
            const merged = this.merge(compressed);
            const finalRow = this.compress(merged);
            
            if (!this.arraysEqual(row, finalRow)) {
                moved = true;
                this.grid[r] = finalRow;
            }
        }
        
        return moved;
    }
    
    moveRight() {
        this.reverseGrid();
        const moved = this.moveLeft();
        this.reverseGrid();
        return moved;
    }
    
    moveUp() {
        this.transposeGrid();
        const moved = this.moveLeft();
        this.transposeGrid();
        return moved;
    }
    
    moveDown() {
        this.transposeGrid();
        const moved = this.moveRight();
        this.transposeGrid();
        return moved;
    }
    
    compress(row) {
        const newRow = [0, 0, 0, 0];
        let index = 0;
        
        for (let i = 0; i < 4; i++) {
            if (row[i] !== 0) {
                newRow[index] = row[i];
                index++;
            }
        }
        
        return newRow;
    }
    
    merge(row) {
        for (let i = 0; i < 3; i++) {
            if (row[i] !== 0 && row[i] === row[i + 1]) {
                row[i] *= 2;
                this.score += row[i];
                row[i + 1] = 0;
                i++; // Skip the next element
            }
        }
        return row;
    }
    
    reverseGrid() {
        for (let r = 0; r < 4; r++) {
            this.grid[r].reverse();
        }
    }
    
    transposeGrid() {
        const newGrid = this.createEmptyGrid();
        
        for (let r = 0; r < 4; r++) {
            for (let c = 0; c < 4; c++) {
                newGrid[c][r] = this.grid[r][c];
            }
        }
        
        this.grid = newGrid;
    }
    
    arraysEqual(arr1, arr2) {
        return arr1.length === arr2.length && arr1.every((val, i) => val === arr2[i]);
    }
    
    getTilePosition(row, col) {
        return {
            x: col * 97.5 + 'px',
            y: row * 97.5 + 'px'
        };
    }
    
    updateTiles() {
        // Clear existing tiles
        this.tileContainer.innerHTML = '';
        
        // Create new tiles
        for (let r = 0; r < 4; r++) {
            for (let c = 0; c < 4; c++) {
                if (this.grid[r][c] !== 0) {
                    this.createTile(this.grid[r][c], r, c);
                }
            }
        }
    }
    
    createTile(value, row, col) {
        const tile = document.createElement('div');
        tile.className = `tile tile-${value}`;
        tile.textContent = value;
        
        const position = this.getTilePosition(row, col);
        tile.style.left = position.x;
        tile.style.top = position.y;
        
        // Add super class for values > 2048
        if (value > 2048) {
            tile.classList.add('tile-super');
        }
        
        this.tileContainer.appendChild(tile);
    }
    
    updateDisplay() {
        this.scoreElement.textContent = this.score;
        
        if (this.score > this.bestScore) {
            this.bestScore = this.score;
            localStorage.setItem('bestScore', this.bestScore);
        }
        
        this.bestScoreElement.textContent = this.bestScore;
    }
    
    checkWin() {
        for (let r = 0; r < 4; r++) {
            for (let c = 0; c < 4; c++) {
                if (this.grid[r][c] === 2048) {
                    return true;
                }
            }
        }
        return false;
    }
    
    checkGameOver() {
        // Check for empty cells
        for (let r = 0; r < 4; r++) {
            for (let c = 0; c < 4; c++) {
                if (this.grid[r][c] === 0) {
                    return false;
                }
            }
        }
        
        // Check for possible merges
        for (let r = 0; r < 4; r++) {
            for (let c = 0; c < 4; c++) {
                const current = this.grid[r][c];
                
                // Check right neighbor
                if (c < 3 && current === this.grid[r][c + 1]) {
                    return false;
                }
                
                // Check bottom neighbor
                if (r < 3 && current === this.grid[r + 1][c]) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    showMessage(text, type) {
        this.messageText.textContent = text;
        this.messageText.className = `message-text ${type}`;
        this.gameMessage.classList.add('show');
    }
    
    hideMessage() {
        this.gameMessage.classList.remove('show');
    }
    
    restart() {
        this.grid = this.createEmptyGrid();
        this.score = 0;
        this.gameWon = false;
        this.gameOver = false;
        
        this.addRandomTile();
        this.addRandomTile();
        this.updateTiles();
        this.updateDisplay();
    }
}

// Initialize the game when the page loads
document.addEventListener('DOMContentLoaded', () => {
    new Game2048();
});
