package com.sergio.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Entry point for all JAX-RS resources.
 * 
 * @author Sergio Verde
 *
 * @see javax.ws.rs.ApplicationPath
 */
@ApplicationPath("/resources")
public class JAXRSConfiguration extends Application {

}