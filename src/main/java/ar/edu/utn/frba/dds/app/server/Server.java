package ar.edu.utn.frba.dds.app.server;

import ar.edu.utn.frba.dds.app.exceptions.TemplateEngineException;
import ar.edu.utn.frba.dds.app.server.initializer.Initializer;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import io.javalin.rendering.JavalinRenderer;

import java.io.IOException;
import java.util.function.Consumer;

public class Server {
    private static Javalin app = null;

    public static Javalin app() {
        if(app == null)
            throw new RuntimeException("App no inicializada");
        return app;
    }

    public static void init() {
        if(app == null) {
            initTemplateEngine();
            int port = Integer.parseInt(System.getProperty("port","8080"));
            app = Javalin.create(config()).start(port);
            Router.init();
            Initializer.init();
        }
    }

    private static Consumer<JavalinConfig> config() {
        return config -> config.staticFiles.add(staticFileConfig -> {
            staticFileConfig.hostedPath = "/";
            staticFileConfig.directory = "/public";
        });
    }

    private static void initTemplateEngine() {
        JavalinRenderer.register(
                (path, model, context) -> {
                    Handlebars handlebars = new Handlebars();
                    Template template;
                    try {
                        template = handlebars.compile(
                                "templates/" + path.replace(".hbs",""));
                        return template.apply(model);
                    } catch (IOException e) {
                        context.status(HttpStatus.NOT_FOUND);
                        throw new TemplateEngineException("No se encuentra la página indicada...");
                    }
                }, ".hbs" // Extensión del archivo de template
        );
    }
}
