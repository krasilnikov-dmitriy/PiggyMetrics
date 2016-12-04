package com.piggymetrics.account;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import ru.yandex.qatools.allure.junit.AllureRunListener;

public class AllureTestRunner extends BlockJUnit4ClassRunner {

    public AllureTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public void run(RunNotifier notifier) {
        notifier.addListener(new AllureRunListener());
        notifier.fireTestRunStarted(Description.createSuiteDescription("Tests"));
        super.run(notifier);
        notifier.fireTestRunFinished(new Result());
    }
}