## HTML DSL
使用kotlin实现html的dsl，个人原因需要生成html代码，所以就随手写了一套dsl出来。<br/>
主要是实现了标签的功能，css做了特别简单的实现，js没做。因为我还要花时间学习准备找工作，而且我只需要生成很多标签代码及一点点css代码，所以没有对css和js进行深入开发。<br/>
标签这方面，我写了一些常用标签。还有很多很多标签和属性都没写，但可以照着我写的代码写新的标签。

### 框架的作用
在说怎么用之前，先说这个框架有什么用。<br/>
这个框架让开发者比较方便的编写html代码。<br/>
主要体现在：
- 不用手写标签，只需要调用方法就能生成标签代码，下面会说怎么用
- 一个标签有哪些属性，属性是什么类型一看便知。如果忘记属性名称，可以查看标签对象有哪些字段

可能有人会说，可以用vue什么的。但我作为一名安卓开发者，主力还是在安卓这边。html这种需求也不是特别多，所以就懒得去学。<br/>
但不是特别多也不是完全没有，前前后后写了几个生成文件，每次都是使用OutputStream暴力输出html代码。最近又给自己想了一个需求，索性直接写一套dsl出来，以后就不用暴力写html代码了。<br/>
这套代码虽然本质上还是用OutputStream输出，但至少简化编写html代码这个过程，所以我认为这套代码还是有意义的。至少我自己用起来挺好用的。<br/>
这套代码算上开发和优化，总共开发了十几个小时，反而是我自己的需求才开发了3、4个小时。

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
                value = "baidu"
            }   
        }
        a{
            href = "https://www.baidu.com"
            valueBody = getH2.apply{
                value = "baidu"
            }
        }
    }
}.toHtmlCode()
```
上面的代码看似有很多重复代码，但写出来的每一行代码都是有意义的，解释一下代码。<br/>
首先，既然是html代码，那就必须以html开头。html下面有header，这个方法的作用就是给html的header标签设置一个header变量。<br/>
header里面有title，对应的是header的title标签。<br/>
再看看body，对应的是body标签。body里面写<b>a</b>之后，就会将a标签添加到body里面。<br/>
而div里面，也可以通过同样的方式添加a标签，这样做不会将a标签添加到body里面。<br/>
再看看最下面的a标签，这里的value和上面的value不一样。上面都是一些文本，所以直接给value这个变量设值就可以了。<br/>
而如果想要设置一个标签，则需要使用valueBody。valueBody的类型是HtmlBody，必须提供一个body，不能一个字符串。<br/>
上面的a标签都是通过调用方法获取的，但调用方法就会将该标签添加到HtmlBodyGroup里面，如果想要获取一个标签的实例，就必须调用getXxx。比如getA、getTable等。<br/>
此时，如果调用h2()，则会将h2设置到valueBody里面<b>并</b>将该h2标签添加到body里面，这点需要注意一下。当然了，这样做运行不会报错。<br/>
再看看最后的toHtmlCode，该方法会返回一串html代码。所有的标签都必须实现HtmlTag，并实现该方法。上一级的标签会调用下一级的实现，最后形成一个调用链。<br/>
每个标签都做正确的实现，调用之后就可以获取到一串正确的html代码。

#### header
我对header的了解不多，所以就只加了一个title，而且title也只有value这个变量，没有添加属性在里面。<br/>
如果有什么需要，可以自己手动添加，具体参考body的代码。

#### HtmlBodyGroup的使用
上面提了body和div可以使用相同的方式添加a标签，这里解释一下为什么。<br/>
在body里面，有一个HtmlBodyGroup对象。如果一个标签可以存放多个子标签，就可能可以实现这个接口，比如HtmlBodyRoot和HtmlBodyDivTag。<br/>
为什么说可能可以，因为有一些是不可以的。比如table标签，table是有多个子标签，但只有thead、tbody和tfoot，所以就没必要实现这个接口，改为实现HtmlBody。<br/>
这个接口需要提供一个泛型，这个泛型必须是HtmlBody的实现类。实现该接口之后，就有了addHtmlBody这样的功能。<br/>
有了这个功能之后，就可以非常方便的添加一些子标签到该Group标签里面，具体看a的实现代码，实现代码再htmlBodyGeneralDSL文件里面，该文件还有其他实现代码，可以看看<br/>
```kotlin
inline fun HtmlBodyGroup<HtmlBody>.a(anchorAction: HtmlBodyATag.() -> Unit): HtmlBodyATag {
    return getA.also {
        it.anchorAction()
        addHtmlBody(it)
    }
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
将用到的key都定义成一个常量，并为key进行分类。比如general的注释下面有id、width、height这些，而href则会放到a的注释下面。<br/>
如果一个标签里面有多个属性，手动编写key="value"的代码是很烦人的，所以我编写了一个generateAttributeString方法，该方法的参数是一个可变参数的Pair对象。<br/>
并且Pair对象可以为空，在该方法里面，会调用filterNotNull方法过滤掉，所以不用担心空指针的问题。<br/>
传入Pair对象时需要注意，first是key，second是value。key的类型是String，value的类型是Any，所以可以传int等类型的值。<br/>
而一些常用的key，如id。我会编写一个接口，并提供一个获取Pair的方法，这样就不用手动编写生成的代码了。<br/>
上面提到Pair可为空，为什么要这样设计？因为这样在传值就方便了很多，比如某个字符串不是空字符串时，才需要生成一个属性，就可以这样做。<br/>
value.takeIf{&nbsp;it.isNotEmpty()&nbsp;}?.let{&nbsp;"key"&nbsp;to&nbsp;it&nbsp;}，这样判断之后，如果value是空字符串，就会传入一个null。

#### toHtmlCode
html代码一般有开标签和闭标签，但不管是开标签或是闭标签，每次都手动写这些代码都是很烦人的。<br/>
所以每个body不但需要实现toHtmlCode，还需要实现getTagString，比如a标签就是a。实现该方法之后，就可以调用代码生成想要的标签代码。<br/>
所有的标签(包括html和body)，都是HtmlTag的实现类，所以我为该类提供了一些常用的扩展方法来生成标签代码。
闭标签相对简单，无需在标签中间设置值，所以方法没有那么多。
- generateCloseTagCode()
- generateCloseTagCode(attributeCodeAction: () -> String)
- generateCloseTagCodeWithStringBuilder(attributeCodeAction: StringBuilder.() -> Unit)

第一个没什么好说的，什么参数都不需要。第二个则需要一个字符串，这个可以调用上面提到的generateAttributeString方法，就不用写太多代码。<br/>
如果觉得前面2个都不好用，那就用第3个，用StringBuilder自己拼接。<br/><br/>
开标签就有点麻烦，因为有2个参数，再加上有不同的参数类型，玩排列组合可以有很多种组合，不过我也不会真的去玩排列组合，所以最后只提供了3个方法。
- generateValueCode(value: () -> String)
- generateValueCodeWithStringBuilder(valueCodeAction: StringBuilder.() -> Unit)
- generateValueAndAttributeCode(action: HtmlValueAttributeHelper.() -> Unit)

前2个没什么好说的，重点是第3个。该对象里面有attributeString和valueString，可以给这两个字段设值。<br/>
设完之后，该方法就会获取这2个字段的值，并添加到字符串里面。<br/>
最后来看看div的toHtmlCode的实现
```kotlin
val attribute = generateAttributeString(
    getIdAttribute()
)
return if (bodyList.isEmpty()) {
    generateCloseTagCode {attribute}
} else {
    generateValueAndAttributeCode {
        attributeString = attribute
        valueString = bodyList.bodyListToString()
    }
}

fun <T: HtmlBody> MutableList<T>.bodyListToString() = joinToString("") {it.toHtmlCode()}
```

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
