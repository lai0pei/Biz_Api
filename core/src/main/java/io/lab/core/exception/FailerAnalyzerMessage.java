package io.lab.core.exception;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class FailerAnalyzerMessage extends AbstractFailureAnalyzer<Throwable> {
    @Override
    protected @Nullable FailureAnalysis analyze(Throwable rootFailure, Throwable cause) {

        if (cause.getClass().getName().contains("PortInUseException")) {
            return null;
        }

        String description = "An unexpected error occurred during startup: " + cause.getMessage();
        String action = cause.getClass().getName();

        return new FailureAnalysis(description, action, cause);
    }
}
