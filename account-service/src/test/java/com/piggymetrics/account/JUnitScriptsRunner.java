package com.piggymetrics.account;

/**
 * Created by andrey.smirnov on 05.12.2016.
 */
import com.epam.reportportal.junit.ReportPortalListener;
import com.piggymetrics.account.service.AccountServiceTest;
import org.junit.runner.JUnitCore;
import ru.yandex.qatools.allure.junit.AllureRunListener;

public class JUnitScriptsRunner {
    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.addListener(new ReportPortalListener());
        core.addListener(new AllureRunListener());
        System.out.println(1);
        core.run(AccountServiceTest.class);
        System.out.println(2);
    }
}