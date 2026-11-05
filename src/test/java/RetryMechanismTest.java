import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testinium retry mekanizmasini dogrulamak icin kullanilan kontrollu flaky test.
 *
 * <p>Devcluster executor'in container'a {@code CURRENT_RETRY_COUNT} degerini
 * vermesi beklenir: ilk kosumda 0, ilk retry kosumunda 1 olur. Eski executor
 * akislari icin {@code -DretryCount=<n>} system property degeri de desteklenir.</p>
 */
public class RetryMechanismTest {

    @Test
    public void shouldFailOnInitialRunAndPassOnFirstRetry() {
        int retryCount = readCurrentRetryCount();
        String totalRetryCount = readOptionalValue("TOTAL_RETRY_COUNT", "totalRetryCount", "unknown");

        System.out.printf(
                "Retry test is running. currentRetryCount=%d, totalRetryCount=%s, expectedResult=%s%n",
                retryCount,
                totalRetryCount,
                retryCount == 0 ? "FAIL" : "SUCCESS"
        );

        assertTrue(
                retryCount >= 1,
                "Intentional failure on the initial run (CURRENT_RETRY_COUNT=0). "
                        + "The first retry should run with CURRENT_RETRY_COUNT=1 and pass."
        );
    }

    private int readCurrentRetryCount() {
        String value = readOptionalValue("CURRENT_RETRY_COUNT", "currentRetryCount", null);

        // Enterprise executor compatibility: -DretryCount=<current attempt>
        if (value == null) {
            value = System.getProperty("retryCount");
        }

        if (value == null || value.trim().isEmpty()) {
            throw new AssertionError(
                    "Current retry count is missing. The executor must provide "
                            + "CURRENT_RETRY_COUNT or start Maven with -DretryCount=<currentAttempt>."
            );
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new AssertionError(
                    "Current retry count must be an integer, but was: " + value,
                    exception
            );
        }
    }

    private String readOptionalValue(String environmentName, String propertyName, String defaultValue) {
        String value = System.getenv(environmentName);
        if (value == null || value.trim().isEmpty()) {
            value = System.getProperty(propertyName);
        }
        return value == null || value.trim().isEmpty() ? defaultValue : value;
    }
}
