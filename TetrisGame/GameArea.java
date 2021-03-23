package TetrisGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class GameArea extends JPanel {

    private int ColorCheck, BlockCheck;
    public static int GridCellSize, GridColumns, GridRows;
    private GenerateBlock b;
    public Color background[][];
    private Color AllColors[];
    private int AllBlocks[][][];

    public GameArea(int columns) {
        this.setBorder(new MatteBorder(1, 1, 1, 1, Color.blue));
        this.setBounds(0, 0, 400, 680);
        this.setBackground(new Color(212, 196, 242));
        GridColumns = columns;
        GridCellSize = this.getBounds().width / GridColumns;
        GridRows = this.getBounds().height / GridCellSize;
        AllColors = new Color[]{
            new Color(89, 109, 212),
            new Color(208, 67, 67),
            new Color(208, 88, 208),
            new Color(66, 205, 170),
            new Color(210, 210, 65),
            new Color(253, 126, 63),
            new Color(221, 184, 75),
            new Color(128, 171, 214),
            new Color(59, 59, 171),
            new Color(236, 62, 19),
            new Color(17, 232, 232)};
        AllBlocks = new int[][][]{
            {
                {1, 1},
                {1, 1}
            },
            {
                {1, 0},
                {1, 0},
                {1, 1}
            },
            {
                {0, 1},
                {0, 1},
                {1, 1}
            },
            {
                {1, 1, 1, 1}
            },
            {
                {1, 1, 0},
                {0, 1, 1}
            },
            {
                {0, 1, 1},
                {1, 1, 0}
            },
            {
                {0, 1, 0},
                {1, 1, 1}
            }
        };
        background = new Color[GridRows][GridColumns];
        SpawnBlock();
    }

    public void InitializeBackground() {
        background = new Color[GridRows][GridColumns];
    }

    public GenerateBlock SpawnBlock() {
        Random r = new Random();
        int BlockPosition = r.nextInt(AllBlocks.length);
        int ColorPosition = r.nextInt(AllColors.length);
        if (ColorPosition == ColorCheck) {
            if (ColorPosition < AllColors.length - 1) {
                ColorPosition++;
            } else {
                ColorPosition = 0;
            }
        }
        if (BlockPosition == BlockCheck) {
            if (BlockPosition < AllBlocks.length - 1) {
                BlockPosition++;
            } else {
                BlockPosition = 0;
            }
        }
        ColorCheck = ColorPosition;
        BlockCheck = BlockPosition;
        b = new GenerateBlock(AllBlocks[BlockPosition], AllColors[ColorPosition]);
        b.SpawnLocation(GridColumns);
        return b;
    }

    public boolean CheckBounds() {
        if (b.getY() <= 0) {
            b = null;
            return true;
        }
        return false;
    }

    public boolean CheckLeft() {
        if (b.getLeftEdge() <= 0) {
            return false;
        }
        for (int rows = 0; rows < b.getHeight(); rows++) {
            for (int columns = 0; columns < b.getWidth(); columns++) {
                if (b.getShape()[rows][columns] == 1) {
                    int x = columns + b.getX() - 1;
                    int y = rows + b.getY();
                    if (y < 0) {
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    public void MoveBlockLeft() {
        if (b == null) {
            return;
        }
        if (!CheckLeft()) {
            return;
        }
        b.left();
        repaint();
    }

    public boolean CheckRight() {
        if (b.getRightEdge() == GridColumns) {
            return false;
        }
        for (int rows = 0; rows < b.getHeight(); rows++) {
            for (int columns = b.getWidth() - 1; columns >= 0; columns--) {
                if (b.getShape()[rows][columns] == 1) {
                    int x = columns + b.getX() + 1;
                    int y = rows + b.getY();
                    if (y < 0) {
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    public void MoveBlockRight() {
        if (b == null) {
            return;
        }
        if (!CheckRight()) {
            return;
        }
        b.right();
        repaint();
    }

    public boolean CheckBottom() {
        if (b.getBottomEdge() == GridRows) {
            return false;
        }
        for (int columns = 0; columns < b.getWidth(); columns++) {
            for (int rows = b.getHeight() - 1; rows >= 0; rows--) {
                if (b.getShape()[rows][columns] == 1) {
                    int x = columns + b.getX();
                    int y = rows + b.getY() + 1;
                    if (y < 0) {
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    public boolean MoveBlockDown() {
        if (b == null) {
            return false;
        }
        if (!CheckBottom()) {
            return false;
        }
        b.down();
        repaint();
        return true;
    }

    /* Hack Code */
    public void MoveBlockUp() {
        b.up();
        repaint();
    }

    /* Hack Code */
    public void RotateBlock() {
        if (b == null) {
            return;
        }
        b.rotate();
        if (b.getRightEdge() >= GridColumns) {
            b.setX(GridColumns - b.getWidth());
        }
        if (b.getLeftEdge() < 0) {
            b.setX(0);
        }
        if (b.getBottomEdge() >= GridRows) {
            b.setY(GridRows - b.getHeight());
        }
//        for (int row = 0; row < b.getShape().length; row++) {
//            for (int col = 0; col < b.getShape()[row].length; col++) {
//                if (b.getShape()[row][col] != 0) {
//                    if (background[b.getY() + row][b.getX() + col] != null) {
//                        b.unrotate();
//                        break;
//                    }
//                }
//            }
//        }
        repaint();
    }

    public void MoveBlockToBackground() {
        int xPos, yPos;
        for (int rows = 0; rows < b.getHeight(); rows++) {
            for (int columns = 0; columns < b.getWidth(); columns++) {
                if (b.getShape()[rows][columns] == 1) {
                    xPos = b.getX();
                    yPos = b.getY();
                    if (b.getShape()[rows][columns] == 1) {
                        background[rows + yPos][columns + xPos] = b.getColor();
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//      DrawMap(g); 
        DrawBackground(g);
        DrawBlock(g);
    }

    private void DrawBlock(Graphics g) {
        int x, y;
        for (int rows = 0; rows < b.getHeight(); rows++) {
            for (int columns = 0; columns < b.getWidth(); columns++) {
                if (b.getShape()[rows][columns] == 1) {
                    x = (b.getX() + columns) * GridCellSize;
                    y = (b.getY() + rows) * GridCellSize;
                    DrawGridSquare(x, y, b.getColor(), g);
                }
            }
        }
    }

    private void DrawBackground(Graphics g) {
        Color color;
        for (int rows = 0; rows < GridRows; rows++) {
            for (int columns = 0; columns < GridColumns; columns++) {
                color = background[rows][columns];
                if (color != null) {
                    int x = columns * GridCellSize;
                    int y = rows * GridCellSize;
                    DrawGridSquare(x, y, color, g);
                }
            }
        }
    }

    public static void DrawGridSquare(int x, int y, Color c, Graphics g) {
        g.setColor(c);
        g.fillRect(x, y, GridCellSize, GridCellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, GridCellSize, GridCellSize);
    }

    private void DrawMap(Graphics g) {
        for (int rows = 0; rows < GridRows; rows++) {
            for (int columns = 0; columns < GridColumns; columns++) {
                g.drawRect(columns * GridCellSize, rows * GridCellSize, GridCellSize, GridCellSize);
            }
        }
    }

    public int ClearFullLine() {
        int LineFilled, level = 0;
        for (int rows = GridRows - 1; rows >= 0; rows--) {
            LineFilled = 1;
            for (int columns = 0; columns < GridColumns; columns++) {
                if (background[rows][columns] == null) {
                    LineFilled = 0;
                    break;
                }
            }
            if (LineFilled == 1) {
                DeleteRows(rows);
                MoveRowsDown(rows);
                repaint();
                rows++;
                level++;
            }
        }
        return level;
    }

    private void MoveRowsDown(int r) {
        for (int rows = r; rows > 0; rows--) {
            for (int columns = 0; columns < GridColumns; columns++) {
                background[rows][columns] = background[rows - 1][columns];
            }
        }
    }

    private void DeleteRows(int r) {
        for (int i = 0; i < GridColumns; i++) {
            background[r][i] = null;
        }
    }
}
