/**
 * 
 */
package com.home.service;

import java.util.Set;
import java.util.logging.Logger;

import com.home.model.User;
import com.home.repository.UserRepository;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * 
 * @author Ahmad Alrefai
 */

@Path("userService")
public class UserService {
	private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

	
	  @Inject
	  private UserRepository userRepository;
	  
	  @Inject
	  private Validator validator;
	  
	 
    @GET
    @Path("getUserCoupled")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCoupled(@QueryParam("vorname") String vorname, @QueryParam("nachname") String nachname) {
        // JSON-Objekt mit den gegebenen Daten
//        String jsonResponse = String.format("{\"Vorname\":\"%s\",\"Nachname\":\"%s\"}", vorname, nachname);
//        return Response.ok(jsonResponse).build();
    	
    	  try {
              User user = userRepository.findByFirstNameAndLastName(vorname, nachname);
              if (user == null) {
                  return Response.status(Response.Status.NOT_FOUND).build();
              }
              return Response.ok(user).build();
          } catch (Exception e) {
              LOGGER.severe("Error finding user: " + e.getMessage());
              return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
          }
      }
    
    @POST
    @Path("getUserDecoupled")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDecoupled(User user) {
        LOGGER.info("Empfangener User: " + user.getFirstName() + " " + user.getLastName());

        User savedUser = userRepository.save(user);
        LOGGER.info("Gespeicherter User: " + savedUser.getFirstName() + " " + savedUser.getLastName());

        return Response.ok(savedUser).build();
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createUser(@Valid User user) {
//        try {
//            userRepository.save(user);
//            return Response.status(Response.Status.CREATED).entity(user).build();
//        } catch (ConstraintViolationException e) {
//            return Response.status(Response.Status.BAD_REQUEST)
//                    .entity(e.getConstraintViolations().stream().map(violation -> violation.getMessage()).toArray())
//                    .build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
//        }
//    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@Valid User user) {
        // Validierung manuell durchführen
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<User> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(sb.toString()).build();
        }

        try {
            userRepository.save(user);
            return Response.status(Response.Status.CREATED).entity(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    
    public User getUserByFullName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

	/**
	 * @param userRepository fuer Junit Test
	 */
	public void setUserRepository(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
	    return "UserService is working";
	}

	/**
	 * @param validator
	 */
	public void setValidator(Validator validator) {
		
		this.validator= validator;
	}

    
    
//    @GET
//    @Path("getUserCoupled/{name}/{email}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getUserCoupled(
//            @PathParam("name") String name, 
//            @PathParam("email") String email){
//        //GET USER CODE
//        
//        return Response.ok().build();
//    }
//    
//    @GET
//    @Path("getUserDecoupled")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getUserDecoupled(@HeaderParam("User") User user){
//        //GET USER CODE
//        
//        return Response.ok().build();
//    }
}