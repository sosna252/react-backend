package pw.react.backend.reactbackend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message, int line) {
        super(message);
        StackTraceElement[] trace = new StackTraceElement[] {
                new StackTraceElement("controller", "checkUser",
                        "MyController", line),
        };
        setStackTrace(trace);
    }
}
