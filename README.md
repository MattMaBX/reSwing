# reSwing — 基于Swing的简易GUI模块

在Python语言中，有很多简单的模块可以快速生成GUI界面而无需进行繁琐的设计（如[EasyGUI](https://easygui.readthedocs.io/en/master/?hmsr=aladdin1e5)），而在Java语言当中想要快速生成简单的GUI图形界面是较为繁琐的。因此仿照Python中EasyGUI的功能，基于Java Swing开发了reSwing模块。

## 已完成
 1. MsgBox：无返回值，仅作消息提醒用，不阻塞进程。
 2. BoolBox：返回boolean，提供Yes/No的选择功能，会阻塞进程直到用户作出反应。
 3. TextBox：返回String[]，提供多个文本框用于获取用户输入，会阻塞进程直到用户作出反应。
 
## 计划列表
 1. ButtonBox：定义多个按钮的文本内容，并返回被用户按下的按钮的文本(String)。
 2. ChoiceBox：自定义选项列表，返回被用户选中的选项(String)。

 
