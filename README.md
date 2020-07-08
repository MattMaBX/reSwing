# reSwing — 基于Swing的简易GUI模块

在Python语言中，有很多简单的模块可以快速生成GUI界面而无需进行繁琐的设计（如[EasyGUI](https://easygui.readthedocs.io/en/master/?hmsr=aladdin1e5)），而在Java语言当中想要快速生成简单的GUI图形界面是较为繁琐的。因此仿照Python中EasyGUI的功能，基于Java Swing开发了reSwing模块。

## 已完成
 1. MsgBox：无返回值，仅作消息提醒用，不阻塞进程。
 2. BoolBox：返回boolean，提供Yes/No的选择功能。会阻塞进程直到用户作出反应。
 3. TextBox：返回String[]，提供多个文本框用于获取用户输入。会阻塞进程直到用户作出反应。
 4. ButtonBox：返回String，提供多个按钮，用户点击后会返回按钮的标签。会阻塞进程直到用户作出反应。
 5. ChoiceBox：返回String，提供一个下拉菜单，并返回用户选中的项目。会阻塞进程直到用户作出反应。
 
## 计划列表
 1. ~~ButtonBox：定义多个按钮的文本内容，并返回被用户按下的按钮的文本(String)。~~ 已实现
 2. ~~ChoiceBox：自定义选项列表，返回被用户选中的选项(String)。~~ 已实现


# reSwing — Simple GUI module based on Swing
 
 In the Python language, there are many simple modules that can quickly generate GUI interfaces without cumbersome design(such as [EasyGUI](https://easygui.readthedocs.io/en/master/?hmsr=aladdin1e5)),but In Java language, it is tedious to quickly generate a simple GUI graphical interface. Therefore, imitating the functions of EasyGUI in Python, the reSwing module was developed based on Java Swing.

## Completed
  1. MsgBox: No return value, only used as a message reminder, without blocking the process.
  2. BoolBox: returns boolean, providing Yes/No selection function. Will block the process until the user reacts.
  3. TextBox: Returns String[], providing multiple text boxes for obtaining user input. Will block the process until the user reacts.
  4. ButtonBox: Returns String, provides multiple buttons, and returns the label of the button after the user clicks. Will block the process until the user reacts.
  5. ChoiceBox: Returns String, provides a drop-down menu, and returns the item selected by the user. Will block the process until the user reacts.
 
## Plan List
 1. ~~ButtonBox: Define the text content of multiple buttons and return the text of the button pressed by the user (String).~~ Completed
 2. ~~ChoiceBox: Customize the option list and return the option selected by the user (String).~~ Completed
