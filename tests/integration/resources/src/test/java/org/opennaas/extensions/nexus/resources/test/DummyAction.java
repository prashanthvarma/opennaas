package org.opennaas.extensions.nexus.resources.test;

import org.opennaas.core.resources.action.Action;
import org.opennaas.core.resources.action.ActionException;
import org.opennaas.core.resources.action.ActionResponse;
import org.opennaas.core.resources.command.Response;
import org.opennaas.core.resources.protocol.IProtocolSessionManager;

public class DummyAction extends Action {

	@Override
	public ActionResponse execute(IProtocolSessionManager protocolSessionManager) throws ActionException {
		ActionResponse actionResponse = new ActionResponse();
		actionResponse.setActionID(getActionID());

		Response response = Response.okResponse("DUMMY");
		actionResponse.addResponse(response);

		return actionResponse;
	}

	@Override
	public boolean checkParams(Object params) throws ActionException {
		return true;
	}

}