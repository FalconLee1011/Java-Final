# Java-Final

* peekaboo.Pkb__Test

## 選單介面 package peekaboo.menu;
* PkbMenuFrame -- 
* PkbMenuButton -

## 角色 package peekaboo.role;
* PkbHuman ------
    + import peekaboo.props.*;
    + import peekaboo.*;
* PkbGhost ------
    + import peekaboo.props.*;
    + import peekaboo.*;

## package peekaboo;
* CreateMaze -----
* InitMap -------
* BackgroundImage 
* Music
* KeyListener --- 
* PkbTimer ------ 倒數計時
    + import peekaboo.props.*;
* GameFrame ----- 
    + import peekaboo.props.*;
    + import peekaboo.role.*;

## 道具 package peekaboo.props;
* PkbFlyingRock - 撿起來的地板
    + import peekaboo.props.*;
    + import peekaboo.role.PkbHuman;
    
* Enery --------- 以下class的父類
* Door ---------- 任意門
* Hole ---------- 坑洞
* Barrier ------- 障礙物
* Bewitch ------- 迷惑
* Turtle -------- 烏龜
* Shoe ---------- 跑鞋
* Stone --------- 地板