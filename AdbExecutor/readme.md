必须设置adb环境变量，只在windows上测试过，没有mac电脑，所以没有测试mac上能否正常运行。

用法：
```kotlin
adb shell input tap 100 withY 100
adb.shell{
    input tap 100 withY 100
    input swipe 100 withY 100 lastPoint 200 withY 200 swipeTime 100
}
```
两种方式都可以运行，看自己喜欢哪一种。<br/>
由于受到kotlin中缀表达式的语法限制，所以tap之后的xy值只能使用withY这种语法连接起来。<br/><br/>
可能有人会说，这种方式和直接使用批处理命令有什么区别？<br/>
还是有区别的，批处理命令写代码什么提示都没有，声明和引用变量都没静态语言方便。<br/>
想要处理多个命令时，一个bat文件的代码也会爆炸，虽然可以拆成多个bat文件。<br/>
而如果用kotlin，就能将不同的任务放到不同的class文件，想要查看class代码也是非常方便。<br/><br/>
目前我只添加了我自己需要的命令，如果还需要其他命令，可以自行添加。
