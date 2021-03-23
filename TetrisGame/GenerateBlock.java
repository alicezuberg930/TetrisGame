package TetrisGame;

import java.awt.Color;
import java.util.Random;

public class GenerateBlock {

    private int shape[][];
    private Color color;
    private int x, y;
    private int rotation_shapes[][][];
    private int current_rotation;

    public GenerateBlock(int shape[][], Color c) {
        this.shape = shape;
        color = c;
        InitializeAllRotationShape();
    }

    private void InitializeAllRotationShape() {
        rotation_shapes = new int[4][][];
        int r, c;
        for (int i = 0; i < 4; i++) {
            r = shape[0].length;
            c = shape.length;
            rotation_shapes[i] = new int[r][c];
            for (int rows = 0; rows < r; rows++) {
                for (int columns = 0; columns < c; columns++) {
                    rotation_shapes[i][rows][columns] = shape[c - columns - 1][rows];
                }
            }
            shape = rotation_shapes[i];
        }
    }

    public void rotate() {
        current_rotation++;
        if (current_rotation > 3) {
            current_rotation = 0;
        }
        shape = rotation_shapes[current_rotation];
        if (shape.length == 4) {
            setX(this.getX() + 1);
            setY(this.getY() - 1);
        } else if (shape.length == 1) {
            setX(this.getX() - 1);
            setY(this.getY() + 1);
        }
    }

    public void unrotate() {
        if (current_rotation > 0) {
            current_rotation--;
        } else {
            current_rotation = 3;
        }
        shape = rotation_shapes[current_rotation];
    }

    public void SpawnLocation(int width) {
        Random r = new Random();
        current_rotation = 0;
        shape = rotation_shapes[current_rotation];
        x = r.nextInt(width - getWidth());
        y -= getHeight();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void left() {
        x--;
    }

    public void right() {
        x++;
    }

    public void down() {
        y++;
    }

    public void up() {
        y--;
    }

    public int getBottomEdge() {
        return y + getHeight();
    }

    public int getRightEdge() {
        return x + getWidth();
    }

    public int getLeftEdge() {
        return x;
    }

}
