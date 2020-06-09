#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define width 15
#define height 16
int maze[2 * height + 1][2 * width + 1] = {0};
void initMaze()
{
    int i, j;
    for (i = 0; i <= 2 * height; i++)
    {
        for (j = 0; j <= 2 * width; j++)
        {
            if (i % 2 == 1 && j % 2 == 1)
                maze[i][j] = '?';
            else
                maze[i][j] = 1;
        }
    }
    maze[1][0] = 0;
    maze[1][1] = 0;
    maze[2 * height - 1][2 * width] = 0;
}
void printMaze()
{
    int i, j;
    for (i = 0; i <= 2 * height; i++)
    {
        for (j = 0; j <= 2 * width; j++)
        {
            if (j != 2 * width)
            {
                printf("%d,", maze[i][j]);
            }
            else
            {
                printf("%d", maze[i][j]);
            }
        }
        printf("\n");
    }
}
void createMaze(int x, int y)
{ //(x,y)入口
    int offsetX[4] = {-2, 2, 0, 0};
    int offsetY[4] = {0, 0, -2, 2};
    while (1)
    {
        int j, i, count = 0, a, b;
        for (j = 0; j <= 3; j++)
        {
            if (maze[x + offsetX[j]][y + offsetY[j]] == '?')
                count++;
        }
        if (count <= 0)
            return;
        do
        {
            i = rand() % 4;
            a = x + offsetX[i];
            b = y + offsetY[i];
        } while (!(a > 0 && a != 2 * width && b > 0 && b != 2 * height && maze[a][b] == '?'));
        maze[a][b] = 0;
        maze[x + (a - x) / 2][y + (b - y) / 2] = 0;
        createMaze(a, b);
    }
}
int main()
{
    srand((unsigned int)time(NULL));
    initMaze();
    createMaze(1, 1);
    printMaze();
    return 0;
}