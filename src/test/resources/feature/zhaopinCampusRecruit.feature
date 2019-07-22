Feature: 招聘App校园招聘申请

  Scenario: 招聘App校园招聘申请成功申请岗位 作为一个已登录的用户，我点击干部人事管理岗位，跳转招聘职位详情页面，点击申请该岗位按钮，探出确认提示信息：是否确认申请昆明局-人力资源管理专员？ 我已阅读并同意[申请岗位须知]钩选框，点击确认，提示申请成功（已投递 2/5），申请该岗位按钮变成已申请。
    Given 作为一个已登录的用户
    When 我点击自动控制班作业员岗位
    Then 跳转招聘职位详情页面
    When 点击申请该岗位按钮
    Then 探出确认提示信息：是否确认申请昆明局-自动控制班作业员？
    And 我已阅读并同意[申请岗位须知]钩选框
    And 点击确认，提示申请成功（已投递 2/5），申请该岗位按钮变成已申请。


  Scenario:招聘App校园招聘申请取消申请岗位 作为一个已登录的用户，我点击干部人事管理岗位，跳转招聘职位详情页面，点击申请该岗位按钮，探出确认提示信息：是否确认申请昆明局-人力资源管理专员？ 我已阅读并同意[申请岗位须知]钩选框，点击取消，返回招聘职位详情页面。
    Given 作为一个已登录的用户
    When 我点击自动控制班作业员岗位
    Then 跳转招聘职位详情页面
    When 点击申请该岗位按钮
    Then 探出确认提示信息：是否确认申请昆明局-自动控制班作业员？
    And 我已阅读并同意[申请岗位须知]钩选框
    And 点击取消，返回招聘职位详情页面。

  Scenario: 招聘App校园招聘申请申请职位不超过5个  作为一个已登录的用户（已申请5个岗位），我点击干部人事管理岗位，跳转招聘职位详情页面，点击申请该岗位按钮，探出确认提示信息：是否确认申请昆明局-人力资源管理专员？ 我已阅读并同意[申请岗位须知]钩选框，点击确认，提示“申请岗位已超过5个”，当前页面。
    Given 作为一个已登录的用户（已申请5个岗位）
    When 我点击自动控制班作业员岗位
    Then 跳转招聘职位详情页面
    When 点击申请该岗位按钮
    Then 探出确认提示信息：是否确认申请昆明局-自动控制班作业员？
    And 我已阅读并同意[申请岗位须知]钩选框
    And 点击确认，提示“申请岗位已超过5个”，当前页面

