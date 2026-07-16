# KnobUtil 演进建议

这些建议用于未来有时间时逐步改进。目标是提升可维护性、可测试性和生命周期边界的清晰度，不是推翻已在项目中验证过的现有行为。

## 优先级 1：补充核心回归测试

旋钮导航包含大量状态与路径组合，不能仅依靠手动安装 APK 验证。优先为现有行为补充测试，再进行重构。

建议覆盖：

- 当前控件拦截旋钮事件时，焦点不应切换。
- 显式 `nextKnobViewId` 优先于 Action Chain 与顺序导航。
- 不可见、禁用或拒绝分发事件的 View 会被跳过。
- 到达 View 列表边界时的行为符合预期。
- 页面切换会清理旧页面焦点，并能按策略恢复新页面焦点。
- 返回键由当前 View 消费与不消费时的结果。
- 自动隐藏旋钮焦点、用户点击 View 后的焦点更新行为。
- 单 Activity + 多 Fragment，以及两个 Activity 往返切换的场景。

若短期难以直接对 Android View 写单测，可先提取纯导航决策逻辑，使用假的 View 节点和页面状态进行测试。

## 优先级 2：明确宿主与生命周期边界

当前 `KnobUtil` 是全局对象，早期默认适用于单 Activity、多 Fragment。若要支持多 Activity，需要明确状态属于谁、何时清理、何时恢复。

可选方向：

1. 明确声明只支持单 Activity；在 README 写出这一前提。
2. 支持多 Activity：每个 Activity 拥有独立的导航会话（例如 `KnobSession` / `KnobNavigator`），页面、焦点、自动隐藏任务和观察者都归属于该会话。

即使暂不实现多 Activity，也应把它纳入回归测试或明确的限制说明，避免调用方误以为全局状态天然可跨宿主工作。

## 优先级 3：拆分职责，但保持兼容 API

不建议一次性重写 `KnobUtil`。可以维持现有静态入口，内部逐步委托给独立组件：

- `KnobPageRegistry`：保存 page、View 列表、当前 index 和历史焦点。
- `KnobActionDispatcher`：依次执行旋钮事件 controller，并协调最终处理结果。
- `KnobFocusCoordinator`：更新焦点显示、通知 observer、处理点击后的焦点转移。
- `KnobScrollCoordinator`：处理 ScrollView / NestedScrollView / HorizontalScrollView 的自动滚动。
- `KnobAutoHideController`：管理防抖、延迟隐藏与任务取消。

这样可让 `KnobUtil` 保持为门面（facade）：负责初始化和转发调用，但不再承载全部实现细节。

## 优先级 4：事件分发 Controller 化

具体方案见 [TODO.md](TODO.md)。核心原则：

- 已注册 controller 按固定行为层级运行。
- controller 只匹配事件并产出结果，避免直接修改全局状态。
- 未处理的事件最终交给不参与排序的 `defaultController` 兜底。

建议先保持当前处理顺序不变，以 controller 形式复刻现有逻辑；测试稳定后再考虑新增能力。

## 优先级 5：默认页面工具与核心能力分层

当前 `KnobPageUtil` 的 `FIRST`、`SECOND`、`THIRD`、`POP` 是为了开箱即用提供的默认实现，保留这一便利性是有价值的。

建议将其重命名为 `DefaultKnobPageUtil`，明确它是可选的便捷层，而不是框架唯一的页面模型：

- 核心 `KnobUtil` 继续支持任意 `String` page key 和任意数量页面。
- `DefaultKnobPageUtil` 提供四类常见页面的快捷方法。
- 复杂业务可以直接使用核心 API，或自行提供项目级页面工具。

由于库需要兼容 Java，page key 保持 `String` 是合理取舍；业务项目应集中定义页面常量，避免字符串字面量散落。

## 优先级 6：文档与示例

- README 增加最小可运行示例：初始化、收集 View、注册页面、接收旋钮广播、销毁清理。
- 画出或列出事件分发优先级，说明“拦截、显式导航、链式导航、顺序兜底、跨页面导航”的关系。
- 说明 View 实现 `BaseKnobView` 时各回调的职责及返回值含义。
- 明确当前支持的滚动容器与限制，例如水平滚动尚未处理 RTL。
- 记录线程要求：所有 View 和焦点操作需在主线程执行（若这是实现约束）。

## 可暂缓的事项

- 为了形式上的整洁而删除 Java 兼容 API 或 `@JvmStatic`。
- 将全部 page key 改为 Kotlin value class：这会降低 Java 调用便利性。
- 为所有可能的 UI 控件预先实现适配。应由真实需求驱动新增适配。
