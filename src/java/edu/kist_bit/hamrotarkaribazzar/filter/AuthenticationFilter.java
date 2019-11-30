/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist_bit.hamrotarkaribazzar.filter;

import edu.kist_bit.hamrotarkaribazzar.entity.Users;
import edu.kist_bit.hamrotarkaribazzar.services.UsersJpaController;
import edu.kist_bit.hamrotarkaribazzar.services.exceptions.NonexistentEntityException;
import edu.kist_bit.hamrotarkaribazzar.utils.BCrypt;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"},
    initParams = @WebInitParam(name = "avoids-url", value = "/login.jsp,"
        + ".css,"
        + ".js,"
        + "/font/,"
        + "/images/,"
        + "/index.jsp,"
        + "/signup,"
        + "/register,"
        + "/adminsignup,"
        + "/manageuser,"
        + "/updateuser,"
        + "/deleteuser,"
        + "/singleproduct"))
public class AuthenticationFilter implements Filter {

    private static final boolean debug = false;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private ArrayList<String> urlList;

    public AuthenticationFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("AuthenticationFilter:doFilter()");
        }

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getServletPath();
        boolean allowedRequest = false;
        
        for(String s: urlList){
            if(url.contains(s)){
                allowedRequest = true;
                break;
            }
        }
        Throwable problem = null;

        if (!allowedRequest) {
            HttpSession session = req.getSession(false);
            if ((null == session || session.getAttribute("loggedInUser") == null) && url.equalsIgnoreCase("/dashboard")) {
                if (req.getMethod().equalsIgnoreCase("POST")) {
                    try {
                        if(checkLogin(req, resp)){
                            HttpSession currentSession =req.getSession(false);
                            Users u = (Users) currentSession.getAttribute("loggedInUser");
                            if(u.getType().equalsIgnoreCase("admin")){
                                req.getRequestDispatcher("/WEB-INF/admin/admindashboard.jsp").forward(request,response);
                                return;
                            }else{
                            chain.doFilter(request, response);
                            return;
                            }
                        }else{
                            req.setAttribute("errorMsg", "Invalid Username and Password");
                            req.getRequestDispatcher("login.jsp").forward(request, response);
                            //resp.sendRedirect("login.jsp");
                            return;
                        }
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    resp.sendRedirect("login.jsp");
                    return;
                }
            } else if (null == session || session.getAttribute("loggedInUser") == null) {
                resp.sendRedirect("index.jsp");
                return;
            }else{
                HttpSession currentSession = req.getSession(false);
                Users u = (Users) currentSession.getAttribute("loggedInUser");
                if (u.getType().equalsIgnoreCase("admin")) {
                    req.getRequestDispatcher("/WEB-INF/admin/admindashboard.jsp").forward(request, response);
                    return;
                }
            }
        }

        chain.doFilter(request, response);
        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        String urls = filterConfig.getInitParameter("avoids-url");
        StringTokenizer token = new StringTokenizer(urls, ",");
        urlList = new ArrayList<>();
        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());
        }
        if (filterConfig != null) {
            if (debug) {
                log("AuthenticationFilter: Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenticationFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthenticationFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());

    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    private boolean checkLogin(HttpServletRequest req, HttpServletResponse resp)throws IOException,ServletException, NonexistentEntityException {
        EntityManagerFactory emf = (EntityManagerFactory) req.getServletContext().getAttribute("HamroTarikarBazzaremf");
        boolean isUserLoggedIn = false;
        Users user = null;
        UsersJpaController userJpaController = new UsersJpaController(emf);
        try {
            user = userJpaController.checkLogin(req.getParameter("username"));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(user != null){
            //if(BCrypt.checkpw(req.getParameter("password"),user.getPassword())){
            if(req.getParameter("password").equals(user.getPassword())){
                isUserLoggedIn = true;
                HttpSession session = req.getSession();
                session.setAttribute("loggedInUser", user);
            }
        }
        return isUserLoggedIn;
    }

    /**
     * This request wrapper class extends the support class
     * HttpServletRequestWrapper, which implements all the methods in the
     * HttpServletRequest interface, as delegations to the wrapped request. You
     * only need to override the methods that you need to change. You can get
     * access to the wrapped request using the method getRequest()
     */
    class RequestWrapper extends HttpServletRequestWrapper {

        public RequestWrapper(HttpServletRequest request) {
            super(request);
        }

        // You might, for example, wish to add a setParameter() method. To do this
        // you must also override the getParameter, getParameterValues, getParameterMap,
        // and getParameterNames methods.
        protected Hashtable localParams = null;

        public void setParameter(String name, String[] values) {
            if (debug) {
                System.out.println("AuthenticationFilter::setParameter(" + name + "=" + values + ")" + " localParams = " + localParams);
            }

            if (localParams == null) {
                localParams = new Hashtable();
                // Copy the parameters from the underlying request.
                Map wrappedParams = getRequest().getParameterMap();
                Set keySet = wrappedParams.keySet();
                for (Iterator it = keySet.iterator(); it.hasNext();) {
                    Object key = it.next();
                    Object value = wrappedParams.get(key);
                    localParams.put(key, value);
                }
            }
            localParams.put(name, values);
        }

        @Override
        public String getParameter(String name) {
            if (debug) {
                System.out.println("AuthenticationFilter::getParameter(" + name + ") localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameter(name);
            }
            Object val = localParams.get(name);
            if (val instanceof String) {
                return (String) val;
            }
            if (val instanceof String[]) {
                String[] values = (String[]) val;
                return values[0];
            }
            return (val == null ? null : val.toString());
        }

        @Override
        public String[] getParameterValues(String name) {
            if (debug) {
                System.out.println("AuthenticationFilter::getParameterValues(" + name + ") localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterValues(name);
            }
            return (String[]) localParams.get(name);
        }

        @Override
        public Enumeration getParameterNames() {
            if (debug) {
                System.out.println("AuthenticationFilter::getParameterNames() localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterNames();
            }
            return localParams.keys();
        }

        @Override
        public Map getParameterMap() {
            if (debug) {
                System.out.println("AuthenticationFilter::getParameterMap() localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterMap();
            }
            return localParams;
        }
    }

    /**
     * This response wrapper class extends the support class
     * HttpServletResponseWrapper, which implements all the methods in the
     * HttpServletResponse interface, as delegations to the wrapped response.
     * You only need to override the methods that you need to change. You can
     * get access to the wrapped response using the method getResponse()
     */
    class ResponseWrapper extends HttpServletResponseWrapper {

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        // You might, for example, wish to know what cookies were set on the response
        // as it went throught the filter chain. Since HttpServletRequest doesn't
        // have a get cookies method, we will need to store them locally as they
        // are being set.
        /*
	protected Vector cookies = null;
	
	// Create a new method that doesn't exist in HttpServletResponse
	public Enumeration getCookies() {
		if (cookies == null)
		    cookies = new Vector();
		return cookies.elements();
	}
	
	// Override this method from HttpServletResponse to keep track
	// of cookies locally as well as in the wrapped response.
	public void addCookie (Cookie cookie) {
		if (cookies == null)
		    cookies = new Vector();
		cookies.add(cookie);
		((HttpServletResponse)getResponse()).addCookie(cookie);
	}
         */
    }

}
