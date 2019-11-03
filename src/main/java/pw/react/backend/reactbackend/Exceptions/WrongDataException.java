package pw.react.backend.reactbackend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class WrongDataException extends RuntimeException {
    public WrongDataException(String message) {
        super(message);
        StackTraceElement[] trace = new StackTraceElement[] {
                new StackTraceElement("controller", "save",
                        "MyController", 24),
        };
        setStackTrace(trace);
    }
}
