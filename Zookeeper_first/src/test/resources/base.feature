Feature: 验证Gray获取数据的功能
  Scenario: 验证初始化数据已经准备好
    Given 添加初始化数据
    When 启动zkregistry获取数据
    Then 验证结果

  Scenario:验证增加数据
    Given 启动zkregistry获取数据
    When 不断增加数据
    Then 验证结果