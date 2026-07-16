# TODO

## 将旋钮事件分发重构为 Controller 管线

将当前集中在 `KnobUtil.onKnobChangedWithoutEnter` 的旋钮事件处理流程拆分为可组合的 `KnobActionController`。

- 每个 controller 只负责判断是否处理当前事件，并返回明确结果，例如 `Handled`、`Consumed` 或 `NotHandled`。
- 已注册的 controller 按固定优先级依次执行；任一 controller 处理成功后立即停止后续分发。
- 为稳定已有行为，内置 controller 应覆盖：当前 View 事件拦截、显式目标 View 导航、Action Chain 导航和跨页面导航。
- controller 接收统一、只读的 `KnobContext`；页面状态、焦点展示、滚动等副作用由统一协调层执行，避免 controller 任意修改全局状态。
- 优先级应是明确的行为分层，而不是业务方随意填写的整数，防止新增规则意外改变既有导航语义。

## Default Controller 作为最终兜底

提供不加入 controller 列表、也不参与优先级排序的 `defaultController`。

- 只有当所有已注册 controller 均返回 `NotHandled` 时，才执行 `defaultController`。
- 默认职责是最终兜底：按当前页面 View 列表顺序传递焦点；无可用目标时保持当前焦点或执行既有的默认行为。
- 业务方新增 controller 只能在明确处理事件时覆盖默认导航，避免无意改变兜底逻辑。

## 回归测试

为 controller 管线及默认兜底补充单元测试，至少覆盖：

- 已处理的 controller 会中断后续 controller。
- 所有 controller 未处理时才执行 `defaultController`。
- 当前 View 拦截优先于导航规则。
- 显式导航优先于链式和顺序兜底导航。
- 不可见或不可用 View 会被跳过。
