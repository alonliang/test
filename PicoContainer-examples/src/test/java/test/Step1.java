package test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class Step1 {

    private Page baiduPage;

    public Step1(Page page) {
        baiduPage = page;
    }

    @Given("^打开百度$")
    public void 打开百度() throws Throwable{
        baiduPage.openPage();
    }


    @When("^在输入框中，输入关键字\"([^\"]*)\"，然后点击搜索按钮$")
    public void 在输入框中输入关键字然后点击搜索按钮(String arg0) throws Throwable {
        baiduPage.search(arg0);
    }

}
