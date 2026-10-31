package ru.myx.ae3.test.auth;

import java.nio.charset.StandardCharsets;

import ru.myx.ae3.Engine;
import ru.myx.ae3.answer.AbstractReplyException;
import ru.myx.ae3.answer.Reply;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.serve.ServeRequest;
import ru.myx.ae3.serve.SimpleServeRequest;
import ru.myx.auth.AuthUtils;
import ru.myx.util.Base64;

/**
 * @author myx
 *
 */
public class TestAuth {
	
	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		
		Engine.createGuid();
		System.out.println( ">>>> TEST AUTH" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			/** a valid credential resolves and leaves the header */
			final ServeRequest query = new SimpleServeRequest();
			query.setAttribute( "Authorization", "Basic " + Base64.encode( "magicprobe:secret".getBytes( StandardCharsets.UTF_8 ), false ) );
			final BaseObject credentials = AuthUtils.squeezeCredentials( query );
			assert credentials != null : "valid credential must resolve";
			assert "magicprobe".equals( Base.getString( credentials, "login", "" ) ) : "login must resolve";
			assert "secret".equals( Base.getString( credentials, "password", "" ) ) : "password must resolve";
			assert "true magicprobe".equals( Base.getString( query.getAttributes(), "Authorization", "" ) ) : "credential must be cleared from the header";
		}
		{
			/** a malformed credential is a client error, never a server fault */
			final ServeRequest query = new SimpleServeRequest();
			query.setAttribute( "Authorization", "Basic !!!" );
			try {
				final BaseObject credentials = AuthUtils.squeezeCredentials( query );
				throw new AssertionError( "malformed credential must be refused, got: " + credentials );
			} catch (final AbstractReplyException e) {
				assert e.getReply().getCode() == Reply.CD_UNAUTHORIZED : "malformed credential must answer 401, got: " + e.getReply().getCode();
				assert "Invalid credentials".contentEquals( e.getReply().toCharacter().getText() ) : "refusal must carry a plain message, got: " + e.getReply().toCharacter().getText();
			}
		}
		{
			/** a truncated payload takes the same path */
			final ServeRequest query = new SimpleServeRequest();
			query.setAttribute( "Authorization", "Basic abcde" );
			try {
				AuthUtils.squeezeCredentials( query );
				throw new AssertionError( "truncated credential must be refused" );
			} catch (final AbstractReplyException e) {
				assert e.getReply().getCode() == Reply.CD_UNAUTHORIZED : "truncated credential must answer 401, got: " + e.getReply().getCode();
			}
		}
		{
			/** an absent credential is not a fault */
			final ServeRequest query = new SimpleServeRequest();
			assert AuthUtils.squeezeCredentials( query ) == null : "absent credential must resolve to null";
			query.setAttribute( "Authorization", "Basic " );
			assert AuthUtils.squeezeCredentials( query ) == null : "empty payload must resolve to null";
		}
		System.out.println( ">>>> TEST AUTH PASSED" );
	}
}
