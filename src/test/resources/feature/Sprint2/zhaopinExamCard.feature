Feature: 作为一个登陆的用户,我点击个人中心我的准考证，显示当前所有的准考证,点击一个准考证,显示准考证详细信息

  Scenario: 我的准考证

    Given 作为一个登陆的用户
    When 我点击个人中心我的准考证
    Then 显示当前所有的准考证
    And 点击一个准考证,显示准考证详细信息