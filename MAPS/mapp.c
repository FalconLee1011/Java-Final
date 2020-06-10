#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define width 29
#define height 12
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
void printMaze(FILE *fp_w)
{
    int i, j;
    for (i = 1; i <= 7; i++)
    {
        for (j = 0; j <= 2 * width; j++)
        {
            if (j != 2 * width)
            {
                fprintf(fp_w, "1,", maze[i][j]);
            }
            else
            {
                fprintf(fp_w, "1", maze[i][j]);
            }
        }
        fprintf(fp_w, "\n");
    }
    for (i = 0; i <= 2 * height; i++)
    {
        for (j = 0; j <= 2 * width; j++)
        {
            if (j != 2 * width)
            {
                fprintf(fp_w, "%d,", maze[i][j]);
            }
            else
            {
                fprintf(fp_w,"%d", maze[i][j]);
            }
        }
        fprintf(fp_w,"\n");
    }
    for (i = 1; i <= 7; i++)
    {
        for (j = 0; j <= 2 * width; j++)
        {
            if (j != 2 * width)
            {
                fprintf(fp_w, "1,", maze[i][j]);
            }
            else
            {
                fprintf(fp_w, "1", maze[i][j]);
            }
        }
        fprintf(fp_w, "\n");
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
    FILE *fp_w = fopen("maze2.txt", "w");
    if (fp_w == NULL)
        return -1;
    printMaze(fp_w);
    fclose(fp_w);
    return 0;
}