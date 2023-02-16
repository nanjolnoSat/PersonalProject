## HTML DSL
使用kotlin实现html的dsl，个人原因需要生成html代码，所以就写了一套dsl出来。<br/>
主要是实现了标签的功能，css和js都没做。因为我不怎么需要使用css和js。<br/>
标签这方面，我写了一些常用标签。还有很多很多标签和属性都没写，但可以照着我写的代码写新的标签。

### 框架的作用
在说怎么用之前，先说这个框架有什么用。<br/>
这个框架让开发者比较方便的编写html代码。<br/>
主要体现在：
- 不用手写标签，只需要调用方法就能生成标签代码，下面会说怎么用
- 一个标签有哪些属性，属性是什么类型一看便知。如果忘记属性名称，可以查看标签对象有哪些字段

可能有人会说，可以用vue什么的。但我作为一名安卓开发者，主力还是在安卓这边。html这种需求也不是特别多，所以就懒得去学。当然了，如果以后工作需要，自然会去学习。<br/>
但不是特别多也不是完全没有，前前后后写了几个生成文件，每次都是使用OutputStream暴力输出html代码。最近又给自己想了一个需求，索性直接写一套dsl出来，以后就不用暴力写html代码了。<br/>
这套代码虽然本质上还是用OutputStream输出，但至少简化编写html代码这个过程，所以我认为这套代码还是有意义的。至少我自己用起来挺好用的。<br/>

### 框架的使用
先来一段示例代码
```kotlin
html{
    header{
        title("test")
    }
    body{
        a{
            href = "https://www.baidu.com"
            value = "baidu"
        }
        div{
            a{
                href = "https://www.baidu.com"
                text = "baidu"
            }   
        }
        a{
            href = "https://www.baidu.com"
            body = getH2.apply{
                text = "baidu"
            }
        }
        a{
            href = "https://www.baidu.com"
            h2("baidu")
        }
    }
}.toHtmlCode()
```
上面的代码看似有很多重复代码，但写出来的每一行代码都是有意义的，解释一下代码。<br/>
首先，既然是html代码，那就必须以html开头。html下面有header，这个方法的作用就是给html的header标签设置一个header变量。<br/>
header里面有title，对应的是header的title标签。<br/>
再看看body，对应的是body标签。body里面写<b>a</b>之后，就会将a标签添加到body里面。<br/>
而div里面，也可以通过同样的方式添加a标签，这样做不会将a标签添加到body里面。<br/>
再看看3个a标签的代码，如果想要在a标签里面设置纯文本，就调用text设置文本。
如果想在a标签里面设置其他标签，可以使用body=xxx设置一个标签。标签的获取方式是：getXxx这样，就像上面的getH2。
也可以直接调用标签代码设置一个标签，就像第3个a标签的代码一样。
上面的a标签都是通过调用方法获取的，但调用方法就会将该标签添加到HtmlBodyGroup里面，如果想要获取一个标签的实例，就必须调用getXxx。比如getA、getTable等。<br/>
再看看最后的toHtmlCode，该方法会返回一串html代码。所有的标签都必须实现HtmlTag，并实现该方法。上一级的标签会调用下一级的实现，最后形成一个调用链。<br/>
每个标签都做正确的实现，调用之后就可以获取到一串正确的html代码。

#### header
我对header的了解不多，所以就只加了一个title，而且title也只有value这个变量，没有添加属性在里面。<br/>
如果有什么需要，可以自己手动添加，具体参考body的代码。

#### HtmlBodyGroup的使用
上面提了body和div可以使用相同的方式添加a标签，这里解释一下为什么。<br/>
在body里面，有一个HtmlBodyGroup对象。如果一个标签可以存放多个子标签，就可能可以实现这个类，比如HtmlBodyRoot和HtmlBodyDivTag。<br/>
这个类需要提供一个泛型，这个泛型必须是HtmlBody的实现类。实现该接口之后，就有了addHtmlBody这样的功能。<br/>
有了这个功能之后，就可以非常方便的添加一些子标签到该Group标签里面，具体看a的实现代码，实现代码再htmlBodyGeneralDSL文件里面，该文件还有其他实现代码，可以看看<br/>
```kotlin
inline fun HtmlBodyGroup<HtmlBody>.a(action: HtmlBodyATag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    getA.also {
        it.action()
        addHtmlBody(it)
    }
    return this
}
```
可以看到，实现的方式也非常简单，就是实例化一个a标签，然后调用addHtmlBody。

#### HtmlBody
HtmlBody是body的最小单位，所有的类都必须是HtmlBody的实现类，包括HtmlBodyGroup。<br/>
上面可以看到HtmlBodyGroup有一个泛型，为什么要泛型，而不是直接body就行？<br/>
因为我在开发的时候，发现像tr这种标签，它只需要th和td这种标签，如果不限制类型，娜开发者可能就会往该标签里面乱塞一些不相关的标签。<br/>
而th和td是两种类型，所以我就干脆定义了一个HtmlBodyTableThTdTag，这样泛型就可以用这个类。再让th和td实现该类即可。

#### 属性
一个标签里面可能有一个或多个属性，属性的key是固定的，如果每次都手动写key，难免有写错的时候，所以我就写了一个类HtmlBodyAttribute。<br/>
将用到的key都定义成一个常量，并为key进行分类。比如general下面有id、width、height这些，而href则会放到a下面。<br/>
如果一个标签里面有多个属性，手动编写key="value"的代码是很烦人的，所以我就在HtmlTag里面编写了一个getAttributeList方法，返回每个标签所有的属性。<br/>
并为HtmlTag提供了generateHtmlCode的扩展方法，在该方法里面，将调用getAttributeList方法获取属性列表，并转换成属性代码。<br/>
getAttributeList的返回类型是List<Pair<String,Any>>>，first为属性名称，所以是String。second设置为Any是考虑到Int、Double这些类型。<br/>
直接设置为Any，并在转换时调用toString，就不用将second设置为String类型。<br/>
为了开发者不用手动判断String value是否为空字符串，我还提供了String.toPairByStringValue(String)方法。调用对象为属性名称，参数为属性值。<br/>
在该方法里面，将判断value是否为空字符串。如果是，将返回null。否则，将返回Pair对象。

#### toHtmlCode
html代码一般有开标签和闭标签，但不管是开标签或是闭标签，每次都手动写这些代码都是很烦人的。<br/>
所以每个body不但需要实现toHtmlCode，还需要实现getTagString，比如a标签就是a。实现该方法之后，就可以调用代码生成想要的标签代码。<br/>
所有的标签(包括html和body)，都是HtmlTag的实现类，所以我为该类提供了一些常用的扩展方法来生成标签代码。
方法的名称都是generateHtmlCode，具体有哪些参数就自己看吧，也没什么好说的。

#### getXxx
为什么要写getXxx，因为实际使用之后，我发现有些body就是需要其他body，并且只需一个，所以不用继承HtmlBodyGroup，比如a标签。<br/>
所以HtmlBodyGroup.a这套代码是没办法使用的，可能有人会说，那写一个不是Group的同名称扩展方法不就行了。<br/>
实际上，我是试过了，但真正使用的时候，需要将完整的包名写到代码里面，调用该方法是再body里面，而body是一个Group。<br/>
kotlin不知道你是想要调用Group的a方法，还是调用一个最纯粹的方法。所以最后想出了这种方式。

#### 最后看看实现效果
![](https://github.com/nanjolnoSat/PersonalProject/blob/htmlDSL/htmldsl/pic/pic1.png)<br/>
![](https://github.com/nanjolnoSat/PersonalProject/blob/htmlDSL/htmldsl/pic/pic2.png)<br/>
![](https://github.com/nanjolnoSat/PersonalProject/blob/htmlDSL/htmldsl/pic/pic3.png)<br/>
![](https://github.com/nanjolnoSat/PersonalProject/blob/htmlDSL/htmldsl/pic/pic4.png)<br/>
![](https://github.com/nanjolnoSat/PersonalProject/blob/htmlDSL/htmldsl/pic/pic5.png)<br/>
![](https://github.com/nanjolnoSat/PersonalProject/blob/htmlDSL/htmldsl/pic/pic6.png)<br/>
![](https://github.com/nanjolnoSat/PersonalProject/blob/htmlDSL/htmldsl/pic/pic7.png)<br/>
