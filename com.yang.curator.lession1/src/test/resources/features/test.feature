Feature: 验证Curator基本功能
  Scenario: 测试curator一些基本场景
    Given 创建curator客户端
    When 增删改查一些基本操作
    Then 关闭zookeeper

  Scenario: 异步回调的一些基本场景
    Given 创建curator客户端
    When 异步回调增删改查一些基本操作
    Then 关闭zookeeper

  Scenario: 事件监听场景
    Given 创建curator客户端
    When 添加事件监听获取监听数据
    Then 关闭zookeeper