package Restlet;

import java.rmi.RemoteException;

import org.json.JSONArray;
import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Representation;
import org.restlet.resource.Resource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;

import com.storytron.enginecommon.SessionLogoutException;

public class PropsUnknownToProtagonistResource extends Resource {
/*
 * Resource {sessionID}/PropsUnknownToProtagonist
GET: public String[] getPropsUnknownToProtagonist(String sessionID)
		throws RemoteException, SessionLogoutException;
 */
	private String sessionID;

	public PropsUnknownToProtagonistResource(Context context, Request request, Response response) {
		super(context, request, response);
		
		this.sessionID = (String) request.getAttributes().get("sessionID");
		//getVariants().add(new Variant(MediaType.TEXT_PLAIN));
		getVariants().add(new Variant(MediaType.APPLICATION_JSON));
	}
	public boolean allowPut() {
		return false;
	}
	
	public boolean allowPost() {
		return false;
	}
	
	public boolean  allowDelete() {
		return false;
	}
	
	 public boolean setModifiable() {
		 return false;
	 }
	 
	 public boolean setReadable() {
		 return true;
	 }	
	 public Representation represent(Variant variant) throws ResourceException {
		 Representation result = null;

		 try {
			 System.out.println("GET PropsUnknownToProtagonist");
			 String[] props  = ServerData.janus.getPropsUnknownToProtagonist(sessionID);
			 JSONArray jsProps = new JSONArray();
			 for (String s: props)
				 jsProps.put(s);
			 result = new JsonRepresentation(jsProps);
		 } catch (RemoteException e) {
			 getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
		 } catch (SessionLogoutException e) {
			 getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
		 } catch (Exception e) {
			 getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
		 }
		 System.out.println("DONE");
		 return result;
	 }
	 
/*	 private Representation representError(Variant variant, ErrorMessage em) throws ResourceException {
		 Representation result = null;
		 if (variant.getMediaType().equals(MediaType.APPLICATION_JSON)) {
			 result = new JsonRepresentation(em.toJSON());
		 } else {
			 result = new StringRepresentation(em.toString());
		 }
		 return result;
	 }*/
	 
	 protected Representation representError(MediaType type, ErrorMessage em) throws ResourceException {
		 Representation result = null;
		 if (type.equals(MediaType.APPLICATION_JSON)) {
			 result = new JsonRepresentation(em.toJSON());
		 } else {
			 result = new StringRepresentation(em.toString());
		 }
		 return result;
	 }
	 		 				
}
