# **reSwing — 基于Swing的简易GUI模块**

## 一、项目简介
本项目的设计灵感来源于Python语言中的 [EasyGUI](https://easygui.readthedocs.io/en/master/?hmsr=aladdin1e5) 模块，可以通过简单的一行语句实现GUI界面的生成。而在Java语言中如果需要使用简单的图形界面来完成交互，则需要大量代码。reSwing的目的就是实现在Java中使用简单的1~2行代码来完成简单图形界面的生成。

## 二、功能说明及使用方法

### 1. MsgBox类

MsgBox类用于生成一个简单的消息弹窗及一个按钮，**不返回任何值**。类中仅包括一个构造方法，调用构造方法后直接初始化并显示窗口，**通过参数决定是否阻塞线程（默认不阻塞）**。构造方法参数包括：
- String massage：弹窗显示的文字内容（不可省略，除非不传入任何参数）。
- String title：窗口标题（省略后默认值为MsgBox）。
- String button_text：按钮文本（省略后默认值为Get）。
- boolean wait：是否阻塞线程（省略后默认值为false）。

可调用不带参数的构造方法MsgBox( )来初始化一个示例窗口。

### 2. BoolBox类

BoolBox类用于生成一个带有消息提示及两个按钮的窗口，第一个按钮被选中时**返回boolean true**，第二个按钮被选中或用户关闭窗口时**返回boollean false**。使用构造方法初始化对象，**在调用getResult( )方法时显示窗口并阻塞线程**。构造方法参数包括：
- String massage：弹窗显示的文字内容（不可省略，除非不传入任何参数）。
- String title：窗口标题（省略后默认值为BoolBox）。
- String yes_text：确认按钮文本（只能与no_text同时省略，省略后默认值为Yes），点击后返回boolean true。
- String no_text：取消按钮文本（只能与yes_text同时省略，省略后默认值为No），点击后返回boolean false。

可调用不带参数的构造方法BoolBox( )初始化一个示例对象，并对其调用getResult( )方法来显示示例窗口。

### 3. TextBox类

TextBox类用于生成一个带有一个或多个文本框的窗口。使用构造方法初始化对象，**在调用getResult( )方法时显示窗口并阻塞线程，返回值为每条用户输入内容组成的Stirng[ ]数组**，直到用户点击确认按钮或关闭窗口。构造方法参数包括：
- String[ ] label：文本框标题组成的String数组。生成窗口中的文本框数量与该数组中标题的数量相同且顺序一致，最终返回的数组中元素顺序与该数组一致（该参数不可省略，除非不传入任何参数）。
- String title：窗口标题（省略后默认值为TextBox）。

可调用不带参数的构造方法TextBox( )初始化一个示例对象，并对其调用getResult( )方法来显示示例窗口。

### 4. PasswordBox类

PasswordBox类用于生成一个登陆界面，界面中用户名为明文显示，密码为密文显示。使用构造方法初始化对象，**在调用getResult( )方法时显示窗口并阻塞线程，直到用户点击确认按钮或关闭窗口。返回值为仅包含两个元素的String[ ]数组**，第一个元素为用户输入的用户名，第二个元素用户输入的为密码。构造方法参数包括：
- String text_label：明文文本框标签（只能与password_label同时省略，省略后默认值为User Name）。
- String password_label：密文文本框标签（只能与text_label同时省略，省略后默认值为Password）。
- String title：窗口标题（省略后默认值为PasswordBox）。

可调用不带参数的构造方法PasswordBox( )初始化一个示例对象，并对其调用getResult( )方法来显示示例窗口。

### 5. ChoiceBox类
ChoiceBox类用于生成一个带有下拉菜单的界面供用户选择，使用构造方法初始化对象，**在调用getResult( )方法时显示窗口并阻塞线程，直到用户点击确认按钮或关闭窗口，并返回被选择的项目(String)。**构造方法参数包括：
- String label：下拉菜单的标签（不可省略，除非不传入任何参数）。
- String[ ] items：下拉菜单中的所有选项（不可省略，除非不传入任何参数）。
- String title：窗口标题（省略后默认值为ChoiceBox）。

可调用不带参数的构造方法ChoiceBox( )初始化一个示例对象，并对其调用getResult( )方法来显示示例窗口。

### 6. ButtonBox类
ButtonBox类用于生成一个包含提示信息及多个按钮的窗口，使用构造方法初始化对象，**在调用getResult( )方法时显示窗口并阻塞线程，直到用户点击按钮或关闭窗口，并返回被选择的项目(String)。**构造方法参数包括：
- String massage：提示信息文本（不可省略，除非不传入任何参数）。
- String[ ] text：所有按钮的文本（不可省略，除非不传入任何参数）。
- String title：窗口标题（省略后默认值为ButtonBox）。

可调用不带参数的构造方法ButtonBox( )初始化一个示例对象，并对其调用getResult( )方法来显示示例窗口。

### 7. DisplayBox类
DisplayBox类与MsgBox相似，但可以显示更大量的文本，具有自动换行的特性，**且不返回任何值**。类中仅包括一个构造方法，调用构造方法后直接初始化并显示窗口，**通过参数决定是否阻塞线程（默认不阻塞）**。构造方法参数包括：
- String source：窗口显示的文字内容（不可省略，除非不传入任何参数）。
- String title：窗口标题（省略后默认值为MsgBox）。
- int rows ：显示区域的行数（只能与columns一起省略，省略后默认值为10）。
- int columns：显示区域的列数（只能与rows一起省略，省略后默认值为30）。
- boolean wait：是否阻塞线程（省略后默认值为false）。

可调用不带参数的构造方法DisplayBox( )初始化一个示例窗口。

### 8. FileDisplayBox类 （继承于DisplayBox）
FileDisplayBox类继承于DisplayBox类，唯一的区别是**显示文本的来源变为本地文件**，而不是String变量。类中仅包括一个构造方法，调用构造方法后直接初始化并显示窗口，**通过参数决定是否阻塞线程（默认不阻塞）**。构造方法参数包括：
- String source：用于显示的文件名（**包含文件扩展名**。该参数不可省略，除非不传入任何参数）。
- String title：窗口标题（省略后默认值为MsgBox）。
- int rows ：显示区域的行数（只能与columns一起省略，省略后默认值为10）。
- int columns：显示区域的列数（只能与rows一起省略，省略后默认值为30）。
- boolean wait：是否阻塞线程（省略后默认值为false）。

可调用不带参数的构造方法FileDisplayBox( )初始化一个示例窗口。

# **reSwing — Simple GUI module based on Swing**
## 1. Project Introduction
The design inspiration for this project comes from the [EasyGUI](https://easygui.readthedocs.io/en/master/?hmsr=aladdin1e5) module in the Python language, which can generate a GUI interface with a simple one-line statement. If you need to use a simple graphical interface to complete the interaction in the Java language, you need a lot of code. The purpose of reSwing is to realize the generation of a simple graphical interface using simple 1-2 lines of code in Java.

## 2. Function description and usage

### 1. MsgBox class

The MsgBox class is used to generate a simple message popup and a button, **does not return any value**. There is only one constructor in the class. After calling the constructor, initialize and display the window directly. **Parameter determines whether to block the thread (not blocked by default)**. Constructor parameters include:
- String massage: The text content displayed in the pop-up window (cannot be omitted unless no parameters are passed in).
- String title: window title (the default value is MsgBox when omitted).
- String button_text: Button text (the default value is Get if omitted).
- boolean wait: Whether to block the thread (the default value is false if omitted).

You can call the constructor MsgBox() with no parameters to initialize a sample window.

### 2. BoolBox class

The BoolBox class is used to generate a window with a message prompt and two buttons. When the first button is selected **returns boolean true**, when the second button is selected or the user closes the window **returns boollean false** . Use the constructor to initialize the object, **display the window and block the thread when calling the getResult() method**. Constructor parameters include:
- String massage: The text content displayed in the pop-up window (cannot be omitted unless no parameters are passed in).
- String title: window title (the default value is BoolBox when omitted).
- String yes_text: Confirm button text (can only be omitted at the same time as no_text, the default value is Yes after being omitted), and returns boolean true after clicking.
- String no_text: Cancel button text (can only be omitted at the same time as yes_text, the default value is No after being omitted), and boolean false is returned after clicking.

You can call the BoolBox() constructor without parameters to initialize a sample object and call the getResult() method on it to display the sample window.

### 3. TextBox class

The TextBox class is used to generate a window with one or more text boxes. Use the construction method to initialize the object, **display the window and block the thread when calling the getResult() method, and the return value is a Stirng[] array composed of each user input** until the user clicks the confirm button or closes the window. Constructor parameters include:
- String[] label: String array composed of text box titles. The number of text boxes in the generated window is the same as the number of titles in the array and the order is the same. The order of the elements in the final returned array is the same as the array (this parameter cannot be omitted unless no parameters are passed in).
- String title: window title (default value is TextBox after omitting).

You can call the TextBox() constructor without parameters to initialize a sample object and call the getResult() method on it to display the sample window.

### 4. PasswordBox class

The PasswordBox class is used to generate a login interface where the user name is displayed in plain text and the password is displayed in cipher text. Use the constructor to initialize the object, **Display the window and block the thread when the getResult() method is called, until the user clicks the confirm button or closes the window. The return value is a String[] array containing only two elements**, the first element is the username entered by the user, and the second element is the password entered by the user. Constructor parameters include:
- String text_label: clear text box label (can only be omitted at the same time as password_label, the default value is User Name after being omitted).
-  String password_label: cipher text label (can only be omitted at the same time as text_label, the default value is Password after being omitted).
- String title: window title (the default value is PasswordBox after being omitted).

You can call the construction method PasswordBox() without parameters to initialize a sample object and call the getResult() method on it to display the sample window.

### 5. ChoiceBox class
The ChoiceBox class is used to generate an interface with a drop-down menu for the user to choose, initialize the object using the constructor, **display the window and block the thread when the getResult() method is called, until the user clicks the confirm button or closes the window, and returns to the selected Items (string).** Construction method parameters include:
- String label: the label of the drop-down menu (cannot be omitted unless no parameters are passed in).
- String[] items: All options in the drop-down menu (cannot be omitted unless no parameters are passed in).
- String title: window title (the default value is ChoiceBox after being omitted).

You can call the ChoiceBox() constructor without parameters to initialize a sample object and call the getResult() method on it to display the sample window.

### 6. ButtonBox class
The ButtonBox class is used to generate a window containing prompt information and multiple buttons. The constructor is used to initialize the object. **When calling the getResult() method, the window is displayed and the thread is blocked until the user clicks the button or closes the window, and returns to the selected Item (String).** Construction method parameters include:
- String massage: prompt message text (cannot be omitted unless no parameters are passed in).
- String[] text: The text of all buttons (cannot be omitted unless no parameters are passed in).
- String title: Window title (the default value is ButtonBox when omitted).

You can call the ButtonBox() constructor without parameters to initialize a sample object and call the getResult() method on it to display the sample window.

### 7. DisplayBox class
The DisplayBox class is similar to MsgBox, but it can display a larger amount of text, has the feature of word wrap, **and does not return any value**. There is only one constructor in the class. After calling the constructor, initialize and display the window directly. **Parameter determines whether to block the thread (not blocked by default)**. Constructor parameters include:
- String source: The text content displayed in the window (cannot be omitted unless no parameters are passed in).
- String title: window title (the default value is MsgBox when omitted).
- int rows: the number of rows in the display area (can only be omitted with columns, the default value is 10 after being omitted).
- int columns: the number of columns in the display area (can only be omitted with rows, the default value is 30 after being omitted).
- boolean wait: Whether to block the thread (the default value is false if omitted).

You can call the DisplayBox() constructor without parameters to initialize a sample window.

### 8. FileDisplayBox class (extends DisplayBox)
The FileDisplayBox class inherits from the DisplayBox class, the only difference is that the source of the displayed text becomes a local file, not a String variable. There is only one constructor in the class. After calling the constructor, initialize and display the window directly. **Parameter determines whether to block the thread (not blocked by default)**. Constructor parameters include:
- String source: The file name used for display (**includes the file extension**. This parameter cannot be omitted unless no parameters are passed in).
- String title: window title (the default value is MsgBox when omitted).
- int rows: the number of rows in the display area (can only be omitted with columns, the default value is 10 after being omitted).
- int columns: the number of columns in the display area (can only be omitted with rows, the default value is 30 after being omitted).
- boolean wait: Whether to block the thread (the default value is false if omitted).

You can call the FileDisplayBox() constructor without parameters to initialize a sample window.