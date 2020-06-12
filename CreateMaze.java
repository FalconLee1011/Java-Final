package peekaboo;

import java.util.Random;
public class CreateMaze {
    private final int[] offsetX = {-2, 2, 0, 0};
    private final int[] offsetY = {0, 0, -2, 2};
    private final int h = 56 - 16 + 1, w = 88 - 16 + 1;
    public int[][] maze = new int[h][w];

    public CreateMaze() {
        this.init();
        this.createMaze(8, 8);
        this.maze[8][7] = 0;
        this.maze[this.h - 9][this.w - 7] = 0;
        for (int i = 0; i < this.h; i++) {
            System.out.printf("%d", this.maze[i][0]);
            for (int j = 1; j < this.w; j++)
                System.out.printf(",%d", this.maze[i][j]);
            System.out.println();
        }
    }

    private void init() {
        for (int i = 0; i < h; i++) {
            maze[i] = new int[w];
            for (int j = 0; j < w; j++)
                maze[i][j] = 1;
        }
    }

    private boolean inBound(int x, int y) {
        if (x < 8) return false;
        if (y < 8) return false;
        if (x > this.h - 8) return false;
        if (y > this.w - 8) return false;
        return true;
    }

    private void createMaze(int x, int y) {
        while (true) {
            int count = 0;
            for (int i = 0; i < 4; i++) {
                if (inBound(x + this.offsetX[i], y + this.offsetY[i]) && this.maze[x + this.offsetX[i]][y + this.offsetY[i]] == 1)
                    count++;
            }
            if (count == 0) return;

            Random rand = new Random();
            int r = rand.nextInt(4);
            while (!inBound(x + this.offsetX[r], y + this.offsetY[r]) || this.maze[x + this.offsetX[r]][y + this.offsetY[r]] == 0)
                r = rand.nextInt(4);

            maze[x + offsetX[r]/2][y + offsetY[r]/2] = 0;
            maze[x + offsetX[r]][y + offsetY[r]] = 0;
            this.createMaze(x + offsetX[r], y + offsetY[r]);
        }
    }
}