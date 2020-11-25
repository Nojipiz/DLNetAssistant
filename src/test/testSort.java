package test;

import org.junit.Test;
import org.python.antlr.base.mod;
import org.python.core.*;

public class testSort {

    private PythonCompiler compiler = new PythonCompiler() {
        @Override
        public PythonCodeBundle compile(mod mod, String s, String s1, boolean b, boolean b1, CompilerFlags compilerFlags) throws Exception {
            return null;
        }
    };

    @Test
    public void test(){
        PyObject xd = new PyObject();
        Py.print(xd);
    }
}
