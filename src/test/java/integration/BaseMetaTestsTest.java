package integration;

import nl.tudelft.cse1110.andy.result.MetaTestsResult;
import org.assertj.core.api.Condition;

public abstract class BaseMetaTestsTest extends IntegrationTestBase {

    public static Condition<? super MetaTestsResult> passedMetaTest(String metaTestName) {
        return new Condition<>() {
            @Override
            public boolean matches(MetaTestsResult value) {
                return value.getMetaTestResults().stream()
                        .anyMatch(m -> m.getName().equals(metaTestName) && m.succeeded());
            }
        };
    }

    public static Condition<? super MetaTestsResult> failedMetaTest(String metaTestName) {
        return new Condition<>() {
            @Override
            public boolean matches(MetaTestsResult value) {
                return value.getMetaTestResults().stream()
                        .anyMatch(m -> m.getName().equals(metaTestName) && !m.succeeded());
            }
        };
    }
}
