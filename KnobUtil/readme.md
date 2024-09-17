## KnobUtil
车机旋钮工实现工具，无需修改大量代码就能实现旋钮功能。<br/>
功能有：
- 一个方法就能收集当前页面所有旋钮的View，无需手动构建View list。
- 参考事件分发机制实现旋钮事件分发机制，在需要接收旋钮事件时返回true，不需要时返回false，精细化控制旋钮事件分发
- 旋钮拦截机制。有了拦截机制，就可以实现Seekbar和RecyclerView的旋钮功能
- 左右旋自动将旋钮焦点自动传递到下一个View
- 左右旋和按下方向键将旋钮焦点切换到目标界面或将旋钮焦点传递给指定的View
- 自动滚动。如果当前View不是在顶部，且外部是ScrollView，会自动滚动将该View滚动至顶部
- 旋钮防抖
- 数秒没有操作时自动隐藏旋钮
- 无需修改onClick代码就可以实现手动点击View自动隐藏该View的旋钮界面
- 根据需要，点击当前View之后自动将旋钮焦点传递给指定的View
- 按下返回键通知当前被旋钮选中的View，并根据需要返回true或false来决定是否拦截返回键

写在前面：我负责的项目大部分代码是Java写的，Kotlin是我入职之后引入的，所以有大量的@JvmStatic代码，如果不需要兼容Java，可以去掉。

### 使用教程
1. 监听MainActivity的onCreate方法，并调用KnobUtil.init方法。
2. 接收旋钮的广播调用KnobUtil.onKnobChanged方法。
3. 监听MainActivity的onBackPressed方法，调用KnobUtil.onBackPressed方法，如果返回true，则return。
4. 监听MainActivity的onDestroy方法，调用KnobUtil.onDestroy方法。
5. 在需要旋钮的页面调用KnobUtil.collectView收集View返回一个viewList，将viewList保存为成员变量。收集时，需要传入rootLayout。
6. 如果是一级页面就调用KnobPageUtil.updateFirstViewList传入一个viewList，还支持二级、三级和弹窗。分别对应：second、third和pop。
7. 如果需要页面跳转，比如FIRST按下右键跳到SECOND，需要调用KnobPageUtil.addNextPageRule将FIRST、KnobAction.RIGHT、SECOND这3个参数传递过去，其他情况同理
8. 如果在FIRST的最后一个右旋需要跳转到SECOND，也可以通过同样的方式添加跳转规则

这里的FIRST、SECOND、THIRD都是我自己定义的字符串，可以自己随便改，只要不一样就行了。POP和其他也一样，都是普通的页面。<br/>
如果需要某个按键按下之后跳到POP，也可以自己添加rule。而且页面类型是可以无限多的，可以根据需要自己调用KnobUtil的updateViewList传入page和viewList。<br/>
比如页面是3*3的九宫格，只要rule设置得当，就可以按照想要的方式跳转。有些app在分屏后跳转逻辑会改变，还能调用clearNextPageRule清空跳转逻辑，再重新添加rule。<br/>
而对于旋钮View，需要实现BaseKnobView接口，具体看下面BaseKnobView的介绍。

### 上面是主要步骤，下面是细节。

### BaseKnobView
layout方面，对于需要旋钮的View，需要实现BaseKnobView接口，有几个方法需要注意。
- getDataHolder：该方法需要实现。该方法需要指定泛型，用于存储数据，没有特殊需求就将泛型指定为KnobDataHolder。需要声明一个成员变量，并将该成员变量返回，而不是直接在该方法new一个。如果kotlin，可以直接返回。
- isInterceptNextKnobAction：该方法需要实现。是否拦截旋钮事件。如果是ImageView，可以返回false。如果是列表型的View，可以返回true。Seekbar相关的，有一个SeekbarKnobView的实现。
- dispatchKnobFocusAction：该方法<b>不</b>需要实现。是否处理旋钮时间，默认实现是返回true。如果一个View被gone掉了，就可以返回false，表示此时不需要接收旋钮事件。如果是ViewGroup有可能gone，可以调用KnobDeepVisibleUtil的isDeepVisible方法。
- onKnobFocusedInner和onClearKnobFocusInner：该方法需要实现。分别是接收到旋钮事件时逻辑的处理和失去旋钮事件逻辑的处理。
- onKnobEnter：该方法需要实现。当旋钮点击时，会调用这个方法，在实现这个方法之后，可以调用performClick方法来调用点击事件。
- onKnobBackPressed：该方法<b>不</b>需要实现。按下返回键时事件的处理，默认返回false。当按下返回键之后，需要做特殊处理，可以在该方法处理并返回true。
- setNextKnobViewId：设置View的6个按键(上下左右左旋右旋)操作后下一个View。比如顺序是abc，将a的右旋设置为c，下次右旋就是将焦点给到c，而不是b。除了左右旋，另外4个也可以在layout文件设置。
- setNextFocusKnobAfterClickId：设置点击之后将旋钮事件分发给哪个View，和上面的方法差不多，只不过这次不是按键，而是onClick。
- getViewController：顾名思义，就是View控制器，返回的对象是KnobViewController。提供了一些控制View的属性，具体写在下面。

#### KnobViewController
- nextKnobViewIdList和nextKnobAfterClickId：都是存储上面提到的两个方法提供的属性，一般不用修改这两个属性。
- isAutoUpdateIndexWhenUserClick：boolean，默认为true。当用手点击一个View之后，是否自动更新该页面旋钮的index。比如index为0，点击第3个之后自动将index更新为3，而不用手动更新。
- isAutoDispatchKnobFocusActionToNextView：boolean，默认为true。为true时，在分发旋钮时，如果下一个View不dispatch旋钮事件，会尝试将旋钮事件分发给其他View，直到找到处理旋钮事件的View。
- isAutoRequestCurrentPage：boolean，默认为true。在用手点击View之后，是否将focusView更新为当前View。比如旋钮在第1页，点了第2页的View，自动将focusView更新为第2页。
- isAutoScrollWhenViewFocused：当View得到焦点时是否自动滚动，默认为true。RecyclerView默认为false，忘了什么要这样做，应该是在开发过程中遇到什么bug。
- actionChainType：KnobActionChainType，默认为NONE。旋钮事件链，有NONE、VERTICAL、HORIZONTAL和FULL。如果viewList所有的View都为HORIZONTAL，拿到left和right两个按键时，就会自动分发给viewList其他View，而不用设置nextLeft和nextRight，优先级低于nextLeft、nextRight。

BaseKnobView的ViewController的每个属性都有扩展属性，可以不调用viewController就可以直接使用，在BaseKnobViewExtensions文件里面。<br/>
如果是Java，则可以调用BaseKnobViewUtil使用，而不是BaseKnobViewExtensionsKt。

### KnobUtil
#### 旋钮事件分发流程(onKnobChangedWithoutEnter方法)
1. 通过currentPage获取上次focusedView
2. 调用isInterceptNextKnobAction方法判断是否拦截旋钮事件，如果该View为空则返回false
3. 如果拦截，就调用该View的onKnobFocused方法处理旋钮事件。否则下一步
4. 根据action判断focusedView的action是否有需要分发的View，如果有就将事件分发给目标View。判断方式是调用目标View的dispatchKnobFocusAction方法
5. 如果上一步没有找到，就获取actionChainType属性判断并寻找是否存在与action相匹配的View。如果存在就将事件分发给该View
6. 如果上面几个步骤都没有将旋钮事件分发出去，就会进入最后的步骤
7. 如果是旋钮left或旋钮right，就会将旋钮事件分发给当前页面viewList的上一个View或下一个View
8. 如果不是或者没有找到可以分发的View，就尝试用当前事件寻找目标page。如果找到，就将事件传递给目标page
9. 如果还是没有找到，就会将事件分发给当上次旋钮焦点的View

所有判断View是否消费都会调用dispatchKnobFocusAction方法，只有返回true，才会调用onKnobFocused方法。<br/>
如果一个View disable，或者gone，可以返回false，表示不消费。此时会跳过该View，继续寻找下一个View，直到找到或到viewList尾部。

#### collectView
该方法的步骤不需要写，只写需要注意的点。在Activity/Fragment的View创建完成之后，将拿到的View作为参数构建viewList。<br/>
View是按照顺序收集的，收集之后，除非指定左右旋的View，否则会根据View的顺序分发旋钮事件。如果不想要指定左右旋的View，可以在layout文件调整View的顺序。<br/>
在构建过程中，会获取旋钮View最近的ScrollView，并记录ScrollView到旋钮View之间所有的View，用于计算滚动时实际的x/y值，从而实现自动滚动。<br/>
ScrollView，垂直的有ScrollView和NestedScrollView可以使用，水平的只有HorizontalScrollView。需要注意的是，水平的只考虑LTR，没有考虑RTL。如果需要，要在tryToScrollIfNeed方法里面做判断并做好坐标计算。<br/>
如果需要使用其他View实现滚动，需要修改isScrollView和tryToScrollIfNeed的代码。RecyclerView本身有scrollToPosition方法，所以这里没有处理，RecyclerView相关的看下面RecyclerView的使用方式。<br/>
在收集View时，还会记录View的nextFocus信息。作用是如果当前View有焦点，此时获取到旋钮事件。如果nextFocus的方向和旋钮的方向一致，并且View不为空，而且该View是BaseKnobView，就会将旋钮事件分发给该View。<br/>
举个例子，比如按下左键，当前旋钮焦点的View的nextFocusLeft的id不为空且不是NO\_ID，如果这个id对应的View是BaseKnobView，就会将旋钮事件分发给该View。<br/>
相关属性有nextFocusLeftId、nextFocusUpId、nextFocusRightId和nextFocusDownId，这4个是和按键相关的，这4个是View本身的属性，可以在layout文件里面设置。<br/>
还有getNextKnobViewId和getNextKnobViewId，这两个是左右旋。这两个是BaseKnobView的方法，不能在layout文件里面指定。如果想在layout文件里面设置，需要使用dataBinding，在BaseKnobView里面，已经提供了dataBinding能够使用的方法。<br/>
nextFocusKnobAfterClickId这个是当点击一个View时，自动请求对应id的View的旋钮焦点。<br/>
需要注意的是，findViewById在这个阶段就已经完成，后续如果重新设置id，需要调用reCollectViewNextFocusView方法来重新findViewById。<br/>
但这样还是太麻烦了，所以可以调用BaseKnobView的setNextKnobViewId传入action和id来设置，nextFocusKnobAfterClickId需要调用BaseKnobView该属性设置，此时也会自动find View。

#### hasAnyKnobFocus
总有一些场景需要手动请求当前页面焦点，此时，如果上一个页面有焦点，在当前页面就必须显示焦点。此时就可以调用这个方法来判断其他页面是否有焦点，只要有某个View有焦点，就会返回true。

#### page
部分方法的参数里面有page变量，类型是String，这个变量的作用是记录当前页面。通过当前页面，可以获取viewList，可以实现页面之间的跳转。在KnobPageUtil里面，定义了FIRST、SECOND、THIRD和POP。
比如在updateViewList方法里面的page是1，在调用requestFocus时，想要请求1的页面焦点，page就传1。

#### index
下面一些方法需要传递View index，所以写在这里。传递index时，除了大于等于0这种表示View坐标的index，还能使用KnobIndex传递INVALIDATE_INDEX和CURRENT_VIEW_INDEX。<br/>
前者表示非法index，后者表示当前view的index，具体作用到特定方法说明。

#### updateViewList
作用：更新page的viewList。<br/>
参数：page和viewList。viewList就是调用collectView方法之后返回的viewList。理论上，也可以自己构建一个viewList，但collectView方法里面还做了收集view以外的任务，所以建议调用collectView方法。<br/>
需要注意的是，在调用该方法之后，上一个页面的焦点会被清除。如果不这样做，会出现当前viewList和显示的旋钮View错位，从而出现不可预测的bug。<br/>
我在开发完这个工具之后，就总是出现一些奇怪的bug。一开始还不知道问题在哪，后来才发现在更新之后没有清空上次的旋钮信息。<br/>
但清空也引发新的问题，清空之后hasAnyKnobFocus会返回false。明明在界面A进入界面B时还有旋钮，怎么到界面B旋钮就不见了，问题就在这，所以写了updateViewListAndRequestFocus方法来解决。<br/>
为什么不在这个方法直接requestFocus？因为我在开发过程中发现这样会总会出现bug，所以就直接去掉，现在只有在需要时才调用updateViewListAndRequestFocus在updateViewList的同时requestFocus。<br/>
不过现在仔细想想，这2个方法还是设计得不好。而当时之所以在去掉requestFocus之后没有重新思考如何修改这个方法，是因为updateViewList这个方法被用到了太多项目里面了。稍微修改一下，都要考虑影响范围，最终只能将就着用。<br/>
所以如果自己有更好的想法，可以自己修改一下这部分代码。而且拆出来也是为了让每个方法的职责单一，而不是一个方法解决很多问题，最好导致大部分方法的职能重叠。提供大量基础方法，再根据需要调用所需的方法。

#### updateViewListAndRequestFocus
作用：功能和updateViewList的功能一样，但多了requestFocus。同级页面切换时，如果新页面的View需要焦点，此时调用requestFocus方法会出现旋钮丢失的bug。在这种情况下，才编写这个方法来解决。<br/>
参数：page、viewList、index和isShowFocus。index必须大于等于0，isShowFocus为是否显示旋钮。举个例子，点击之前没有显示旋钮，在点击之后就不应该显示，这里可以调用hasAnyKnobFocus来获取当前是否有焦点。

#### updateViewIndex
作用：更新page的view index。<br/>
参数：page、index和isShwFocus。index如果传CURRENT\_VIEW\_INDEX，就会获取上次显示旋钮的View，传INVALIDATE_INDEX则什么都不会做。<br/>
一个常见的用法是，用户手动点击了某个View，想要更新view index，就可以调用这个方法。需要注意的是，默认情况下，index会自动更新，除非调用BaseKnobView的viewController禁用自动更新，否则一般不用手动更新。<br/>
另一个用法则是，在RecyclerView里面按下返回键，需要将旋钮焦点传递给顶部的tab，此时就可以调用这个方法，这个我在工作中就用得比较多。

#### requestFocus
作用：请求页面焦点。在这个方法里面，还调用了updateViewIndex。因为在请求页面焦点时，有时还需要顺带更新view index。<br/>
参数：page、index和isShowFocus。index如果传CURRENT\_VIEW\_INDEX，就会获取上次显示旋钮的View。<br/>
常见的用法是，一级界面点击某个View之后显示二级界面。此时二级页面需要焦点，就可以调用这个方法将焦点传递给二级页面。

#### updateEnable
作用：让某个page变得可用或不可用。<br/>
参数：page和enabled。<br/>
常见的用法，设置里面，有一些选项有三级页面。而有些选项只有二级页面。如果设置了三级页面，而切换到只有二级页面的设置项，并且add了在二级页面按旋钮右键跳转到三级页面的rule。<br/>
此时就有一个bug，明明当前设置项没看到三级页面，但按下右键之后旋钮却跑到三级页面，导致当前页面的旋钮不见了。正确的做法是按下右键之后旋钮还是停留在二级页面，此时将三级页面设置为disable就能解决这个问题。

#### addNextPageRule
作用：添加页面跳转逻辑。<br/>
参数：currentPage、action: KnobAction、nextPage。<br/>
currentPage就是当前页面，当在currentPage页面时，如果操作了action，就跳转到nextPage。

#### removeNextPageRule和clearNextPageRule
都是清除页面跳转的rule，可以根据需要清除。

#### addKnobFocusChangedObserver和removeKnobFocusChangedObserver
添加和移除旋钮观察者。常见的用法，播放视频时，会全屏播放。当操作旋钮时，需要显示其他View，此时就可以add一个Observer然后再收到通知后显示其他View。

#### knobAccessibilityDelegate
先说明一下，accessibility本质上是用来辅助盲人使用app的。我认为车机app绝对没有盲人会来使用，所以就占用这个功能。如果开发的应用需要被盲人使用，也可以注释掉。但与之相对的，很多和点击事件的功能也会随之消失。<br/>
KnobUtil里面，很多和点击事件的功能就是借助accessibility来完成的。如果没有借助accessibility，就没办法做到不需要修改onClick代码还能在点击之后隐藏旋钮View。<br/>
这个delegate会实现sendAccessibilityEvent方法并拦截clicked以外的事件。<br/>
将拿到的View尝试转换成BaseKnobView，如果转换失败就return。获取page信息，如果获取不到就return。<br/>
判断page是否等于当前page，如果不相等，就调用当前page的focusedView的onClearKnobFocus，并将当前page更新为该View的page。<br/>
此时不会为当前View获取焦点，只会记录被点击的View。因为手动点击是不需要显示旋钮的，只要操作旋钮时才需要。而操作旋钮则还没到这里，就已经记录了View的信息，所以不会做任何操作。<br/>
如果BaseKnobView设置了点击之后下一个获取旋钮焦点的View，就会调用该View的dispatchKnobFocusAction判断是否消费旋钮事件。如果返回true，就会将旋钮事件传递给该View。<br/>
一个很常见的场景是，有几个tab，点击tab之后显示特定的RecyclerView。此时就能使用这个功能来完成，如果没有用这个功能，也可以在onClick里面主动请求RecyclerView的焦点。

### KnobPageUtil
KnobPageUtil就是对KnobUtil功能的封装，KnobUtil提供的方法比较原始，用起来不是很好用。一开始我也是将封装好的方法写在KnobUtil里面，但后面发现这样做了之后，KnobUtil里面的代码太乱了。<br/>
一个方法往往顺带了一堆重载方法，这些方法又和那些功能性方法混在一起，看代码的时候真的非常头疼，所以才想到把一些不是那么重要的方法用单独一个类放在一起。<br/>

#### 页面类型
在这个类里面，定义了4种页面类型。分别是：FIRST、SECOND、THIRD和POP。

#### updateXxxEnabled
提供了updateFirstEnabled、updateSecondEnabled、updateThirdEnabled和updatePopEnabled方法，这样在设置enable时就可以直接调用这些方法，不需要在设置时还传page。

#### updateXxxViewList
提供了updateFirstViewList、updateSecondViewList、updateThirdViewList和updatePopViewList，这样在更新viewList，才不需要传page。这个方法还会将对应page的enable设置为true。

#### updateXxxViewListAndRequestFocus
这个也有4种page的方法，这个就提供了大量的重载方法，具体可以自己看看。比较实用的一点就是，isShowFocus这个参数，在KnobPageUtil里面，默认会调用KnobPage.hasAnyKnobFocus，而不再需要手动调用。

#### updateXxxViewIndex
这个也有4种page的方法，这个就提供了大量的重载方法，具体可以自己看看。比较实用的一点就是，isShowFocus这个参数，在KnobPageUtil里面，默认会调用KnobPage.hasAnyKnobFocus，而不再需要手动调用。

#### requestXxxFocus
这个也有4种page的方法，这个就提供了大量的重载方法，具体可以自己看看。比较实用的一点就是，isShowFocus这个参数，在KnobPageUtil里面，默认会调用KnobPage.hasAnyKnobFocus，而不再需要手动调用。

### RecyclerView
RecyclerView虽然比较特殊，但在KnobUtil里面，并没有判断一个View是否为RecyclerView并做什么处理，而是只处理BaseKnobView。这样做是为了解耦，否则整个工具就和RecyclerView绑定了。<br/>
现在只有RecyclerView，后续如果需要判断其他特殊View，按照这样的思路，还需要加其他判断，这样做显然是不合理的，所以必须把RecyclerView当BaseKnobView用。<br/>
想要使用RecyclerView，必须使用KnobRecyclerView。在KnobRecyclerView里面，实现了BaseKnobView接口，并重写了必要的方法，adapter使用的是KnobRecyclerAdapter，KnobRecyclerAdapter也是BaseKnobView的实现类，下面重点介绍KnobRecyclerAdapter。

#### 刷新旋钮 
相关变量有：clickedPosition和knobPosition和isShowKnob。<br/>
前2个变量是给onKnobEnter使用的，手动点击时获取当前position赋值给clickedPosition并调用onKnobEnter方法，而使用旋钮时根据实际情况更新knobPosition。<br/>
当onKnobEnter时，就判断clickedPosition是否为-1，如果不是就说明是手动点击，如果是就是旋钮点击。如果是手动点击，就可以设置isShowKnob为false。如果是旋钮，就可以设置isShowKnob为true。<br/>
然后再调用refreshKnobPosition方法通知adapter刷新，在onBindViewHolder方法里面刷新adapter。<br/>
在onBindViewHolder方法里面，判断isShowKnob是否为false，如果是就直接隐藏旋钮。如果不是，就判断knobPosition是否等于position，如果是就显示旋钮，否则隐藏旋钮。<br/>
值得一提的是，在refreshKnobPosition方法里面调用的是notifyItemChanged方法，会传入payload。如果是隐藏旋钮，就传false，否则就传true。<br/>
所以在重写onBindViewHolder方法时，可以重写带有payloads参数的方法，并只更新旋钮View，而不更新其他View。

#### onKnobFocused和onClearKnobFocus
onKnobFocused有一个参数：action: KnobAction，onClearKnobFocus没有参数。<br/>
可以根据左旋和右旋，更新knobPosition，从而实现旋钮刷新。需要注意的是，如果焦点在RecyclerView里面刷新旋钮，会一直调用onClearKnobFocus方法，而不会调用onClearKnobFocus方法，只有RecyclerView失去焦点时，才会调用onClearKnobFocus方法。<br/>
而想让RecyclerView一直获取焦点，则需要重写dispatchKnobFocusAction方法，根据实际情况返回true和false，在不需要旋钮时及时返回false。<br/>
比如knobPosition的值为0，此时左旋，就可以返回false。onKnobFocused和onClearKnobFocus最终会调用onKnobFocusedInner和onClearKnobFocusInner。<br/>
所以一般重写这2个方法就可以了，在onKnobFocusedInner里面，刷新完之后可以调用refreshKnobPosition更新旋钮。在onClearKnobFocusInner，需要设置isShowKnob为false并notify。<br/>
还有一个地方需要注意，isShowKnob在dispatchKnobFocusAction或onKnobFocusedInner记得更新为true。我建议在dispatch里面，当要处理旋钮时设置为true，这样就只会设置一次，在onKnobFocused里面会设置多次。

#### moveKnobPositionStrategyList
监听adapter刷新，当条件符合时，执行对应代码。有一个类MoveKnobPositionAction，有2个参数。分别是condition和action。<br/>
condition的类型是Function0<Boolean>，如果条件符合就返回true。当返回true时，就会执行action，action的类型是Function0<Unit>。<br/>
在KnobRecyclerAdapter里面，提供了autoMoveKnobPositionToEdgeIfNeed方法。该方法的作用是，knobPosition大于等于itemCount，就自动将knobPosition移动到adapter最后一个item。<br/>
这个方法就是向moveKnobPositionStrategyList add一个变量并编写条件和action，需要的时候可以在初始化adapter时调用。

#### KnobHorizontalRecyclerAdapter和KnobVerticalRecyclerAdapter
在KnobRecyclerAdapter里面提供了scrollToKnobPositionIfNeed方法，这两个类就是这个方法的实现。<br/>
一般情况下，adapter想要滚动到对应的位置，只需要调用RecyclerView的scrollToPosition就够了。但考虑到有些人会在RecyclerView外部套ScrollView，所以才提供了这2种实线。<br/>
这2个类已经做好了实现，在开发时直接继承就行。如果觉得区分这2个太麻烦，也可以继承KnobRecyclerAdapter重写scrollToKnobPositionIfNeed方法并调用scrollToPosition方法。

### KnobAction
KnobAction里面有Active这个特殊变量，这个是主动调用onKnobFocused方法。有些时候，需要主动调用onKnobFocused方法，但这个事件不是旋钮产生的，此时如果随意传一个action，可能引发bug，所以就定义这样一个action。<br/>
在KnobUtil里面，有很多地方用到这个action。而在KnobRecyclerAdapter的实现类，也可以在onBackPressed方法里面调用onKnobFocused时传递这个action。<br/>
还有一个地方需要注意，在KnobAction里面有这样一个变量knobActionList，这个是做旋钮key和action的映射。旋钮key的值是我随便写的，因为不同项目旋钮key的值或类型肯定不一样，在拿到这个工具之后，这部分需要修改一下。

### 写在最后
这个工具是有应用到实际的生产环境，很多功能也是不断完善出来的，比如一开始没有dispatchKnobFocusAction这个方法的，后面觉得这样不行才加上去的。<br/>
还有一开始RecyclerView和KnobUtil的耦合度也很高，后面才想办法剥离的，actionChain也是在实际工作中，总是写nextFocus太麻烦了，才想出来的。<br/>
写了这么多，想要说的是，大部分情况下是没有什么问题的，因为我也是不断使用不断发现问题不断解决问题。我还记得很清楚，第一个版本想得很好，写出来之后用到项目上，发现这有问题那有问题，然后才一个一个去解决。<br/>
一开始KnobUtil连300行代码都没有，后面随着功能的增加，膨胀到900行，后面又开始将一些代码剥离出来，才降到现在的600行。