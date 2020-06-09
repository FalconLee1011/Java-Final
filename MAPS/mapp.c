#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define width 10
#define height 5
char maze[2 * height + 1][2 * width + 1]={0};
void initMaze(){
int i,j;
for(i=0;i<=2height;i++){
for(j=0;j<=2width;j++){
if (i%21&&j%21)maze[i][j]=’?’;
else maze[i][j]=’#’;
}
}
maze[1][0]=’ ‘;
maze[1][1]=’ ‘;
maze[2height-1][2width]=’ ‘;
}
void printMaze(){
int i,j;
for(i=0;i<=2height;i++){
for(j=0;j<=2width;j++){
printf("%c",maze[i][j]);
}
printf("\n");
}
}
void createMaze(int x, int y){//(x,y)入口
int offsetX[4] = {-2, 2, 0, 0};
int offsetY[4] = {0, 0, -2, 2};
while(1){
int j,i,count=0,a,b;
for(j=0;j<=3;j++){
if(maze[x+offsetX[j]][y+offsetY[j]]’?’)count++;
}
if(count<=0)return;
do{
i=rand()%4;
a=x+offsetX[i];
b=y+offsetY[i];
}while(!(a>0&&a!=2width&&b>0&&b!=2height&&maze[a][b]’?’));
maze[a][b]=’ ‘;
maze[x+(a-x)/2][y+(b-y)/2]=’ ';
createMaze(a,b);
}
}
int main(){
srand((unsigned int)time(NULL));
initMaze();
createMaze(1, 1);
printMaze();
return 0;
}