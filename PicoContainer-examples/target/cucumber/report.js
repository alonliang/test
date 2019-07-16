$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("case.feature");
formatter.feature({
  "line": 2,
  "name": "搜索",
  "description": "",
  "id": "搜索",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@搜索"
    }
  ]
});
formatter.before({
  "duration": 4047009320,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "在百度页面搜索关键字",
  "description": "",
  "id": "搜索;在百度页面搜索关键字",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "打开百度",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "在输入框中，输入关键字\"自动化测试\"，然后点击搜索按钮",
  "keyword": "When "
});
formatter.match({
  "location": "Step1.打开百度()"
});
formatter.result({
  "duration": 2519819843,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "自动化测试",
      "offset": 12
    }
  ],
  "location": "Step1.在输入框中输入关键字然后点击搜索按钮(String)"
});
formatter.result({
  "duration": 2357957547,
  "status": "passed"
});
formatter.after({
  "duration": 426622,
  "error_message": "cucumber.runtime.CucumberException: When a hook declares an argument it must be of type cucumber.api.Scenario. public void test.Hooks.embedScreenshot(gherkin.formatter.model.Step)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:52)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:223)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:211)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:205)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:91)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:309)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:93)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:37)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:309)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:98)\n\tat org.junit.runner.JUnitCore.run(JUnitCore.java:160)\n\tat com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)\n\tat com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)\n\tat com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)\n\tat com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)\n",
  "status": "failed"
});
});