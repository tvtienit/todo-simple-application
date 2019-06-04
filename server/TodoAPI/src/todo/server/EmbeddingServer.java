package todo.server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cpu11291
 */
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.FilterMapping;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import todo.handlers.TodoServlet;

public class EmbeddingServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletHandler handler = new ServletHandler();

        FilterHolder filter = new FilterHolder();
        filter.setInitParameter("allowedOrigins", "http://localhost:3000");
        filter.setInitParameter("allowedMethods", "POST,GET,OPTIONS,PUT,DELETE,HEAD");
        filter.setInitParameter("allowedHeaders", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        filter.setInitParameter("preflightMaxAge", "728000");
        filter.setInitParameter("allowCredentials", "true");

        CrossOriginFilter corsFilter = new CrossOriginFilter();
        filter.setFilter(corsFilter);

        FilterMapping filterMapping = createFilterMapping("/*", filter);
        handler.addFilter(filter, filterMapping);

        server.setHandler(handler);

        handler.addServletWithMapping(TodoServlet.class, "/todos");

        server.start();
        server.join();
    }

    private static FilterMapping createFilterMapping(String pathSpec, FilterHolder filterHolder) {
        FilterMapping filterMapping = new FilterMapping();
        filterMapping.setPathSpec(pathSpec);
        filterMapping.setFilterName(filterHolder.getName());
        return filterMapping;
    }
}
