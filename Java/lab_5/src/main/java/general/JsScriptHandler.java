package general;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsScriptHandler {
    static ScriptEngineManager manager = new ScriptEngineManager();
    static String PATH = "C:\\Users\\alyona\\Documents\\bonchara\\Java\\lab_5\\src\\main\\js\\funcs.js";
    static Object engine;

    static {
        try {
            engine = manager.getEngineByName("JavaScript").eval(Files.newBufferedReader(Paths.get(PATH), StandardCharsets.UTF_8));
        } catch (ScriptException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    ;

    // read script file
    static Invocable invoke = (Invocable) engine;

    public JsScriptHandler() throws ScriptException, IOException {
    }

    public static void run(String functionName, String... params) throws ScriptException, NoSuchMethodException {
        // call function from script file
        invoke.invokeFunction(functionName, params[0]);
    }


}
