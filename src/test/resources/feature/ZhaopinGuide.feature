Feature: 招聘App应聘指南

  Scenario: 作为一个未登录的用户，我点击应聘指南按钮，跳转应聘指南页面，默认显示应聘须知栏以及内容，我点击常见问题栏，显示常见问题详细内容，我点击投递规则栏，显示投递规则详细内容，我点击返回，跳转首页
    Given 作为一个未登录的用户
    When 我点击应聘指南按钮，跳转应聘指南页面
    Then 我点击常见问题栏，显示常见问题详细内容
    And 我点击投递规则栏，显示投递规则详细内容，我点击返回，跳转首页
