package ca.mcgill.epsilon

class GroovyShellService {

    static expose = ['jmx']
    
    def evaluate(String script) {
        try {
            def shell = new GroovyShell(this.class.classLoader)
            shell.evaluate(script)
        } catch (e) {
            e.message
        }
    }
}
