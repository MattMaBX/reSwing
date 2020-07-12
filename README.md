# reSwing — 基于Swing的简易GUI模块

在Python语言中，有很多简单的模块可以快速生成GUI界面而无需进行繁琐的设计（如[EasyGUI](https://easygui.readthedocs.io/en/master/?hmsr=aladdin1e5)），而在Java语言当中想要快速生成简单的GUI图形界面是较为繁琐的。因此仿照Python中EasyGUI的功能，基于Java Swing开发了reSwing模块。

## 已完成
 1. MsgBox：无返回值，仅作消息提醒用。默认不阻塞进程，但可以通过参数控制是否阻塞。
 2. BoolBox：返回boolean，提供Yes/No的选择功能。会阻塞进程直到用户作出反应。
 3. TextBox：返回String[]，提供多个文本框用于获取用户输入。会阻塞进程直到用户作出反应。
 4. ButtonBox：返回String，提供多个按钮，用户点击后会返回按钮的标签。会阻塞进程直到用户作出反应。
 5. ChoiceBox：返回String，提供一个下拉菜单，并返回用户选中的项目。会阻塞进程直到用户作出反应。
 6. PasswordBox：返回String\[2\]，提供一个带有文本框（明文）和密码框（密文）的界面，返回文本（String\[0\]）和密码（String\[1\]）。会阻塞进程直到用户作出反应。
 7. DisplayBox：无返回值，与MsgBox类似，用于显示大量文本。默认不阻塞进程，但可以通过参数控制是否阻塞。
 8. FileDisplayBox：无返回值，继承于DisplayBox，用于直接显示文件中的文本。默认不阻塞进程，但可以通过参数控制是否阻塞。
 
## 计划列表
 1. ~~DisplayBox：无返回值，在界面上显示指定的文本（文本量较大）。~~ 已完成
 2. 优化现有代码，编写文档。


# reSwing — Simple GUI module based on Swing
 
 In the Python language, there are many simple modules that can quickly generate GUI interfaces without cumbersome design(such as [EasyGUI](https://easygui.readthedocs.io/en/master/?hmsr=aladdin1e5)),but In Java language, it is tedious to quickly generate a simple GUI graphical interface. Therefore, imitating the functions of EasyGUI in Python, the reSwing module was developed based on Java Swing.

## Completed
  1. MsgBox: No return value, only for message reminder. The process is not blocked by default, but it can be controlled by parameters.
  2. BoolBox: returns boolean, providing Yes/No selection function. Will block the process until the user reacts.
  3. TextBox: Returns String[], providing multiple text boxes for obtaining user input. Will block the process until the user reacts.
  4. ButtonBox: Returns String, provides multiple buttons, and returns the label of the button after the user clicks. Will block the process until the user reacts.
  5. ChoiceBox: Returns String, provides a drop-down menu, and returns the item selected by the user. Will block the process until the user reacts.
  6. PasswordBox: Returns String\[2\], provides an interface with a text box (clear text) and a password box (cipher text), returns text (String\[0\]) and password (String\[1\]). Will block the process until the user reacts.
  7. DisplayBox: No return value, similar to MsgBox, used to display large amounts of text. The process is not blocked by default, but it can be controlled by parameters.
  8. FileDisplayBox: No return value, inherited from DisplayBox, used to directly display the text in the file. The process is not blocked by default, but it can be controlled by parameters.
## Plan List
  1. ~~DisplayBox: No return value, display the specified text on the interface (the amount of text is large).~~ Complete
  2. Optimize existing code and write documentation.
