// This code was generated by Rory Fitzpatrick using IMS Development Environment (version 1.65 build 3169.24390)
// Copyright (C) 1995-2008 IMS MAXIMS plc. All rights reserved.

package ims.RefMan.forms.changeservicedialog;

import ims.RefMan.vo.ChangeOfServiceVo;
import ims.RefMan.vo.ReferralServiceFullVo;
import ims.core.vo.MemberOfStaffShortVo;
import ims.core.vo.MemberOfStaffShortVoCollection;
import ims.core.vo.PersonName;
import ims.core.vo.ServiceShortVo;
import ims.domain.exceptions.DomainInterfaceException;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.MessageButtons;
import ims.framework.MessageIcon;
import ims.framework.enumerations.DialogResult;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.DateTime;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		initialize();
	}
	
	private void initialize() 
	{
		ReferralServiceFullVo services = domain.getReferralServices();
		if (services != null && services.getReferralServicesIsNotNull())
		{
			for (ServiceShortVo service : services.getReferralServices())
			{
				if (!service.getID_Service().equals(form.getGlobalContext().RefMan.getCurrentServiceLiteVo().getID_Service()))
					form.cmbService().newRow(service,service.getServiceName());
			}
		}
		
		form.cmbOldService().newRow(form.getGlobalContext().RefMan.getCurrentServiceLiteVo(), form.getGlobalContext().RefMan.getCurrentServiceLiteVo().getServiceName().toString());
		form.cmbOldService().setValue(form.getGlobalContext().RefMan.getCurrentServiceLiteVo());
		
		form.dtimChanging().setValue(new DateTime());
		if (domain.getMosUser() != null)
		{
			form.qmbChangingUser().newRow((MemberOfStaffShortVo)domain.getMosUser(), ((MemberOfStaffShortVo)domain.getMosUser()).getName().toString());
			form.qmbChangingUser().setValue((MemberOfStaffShortVo)domain.getMosUser());	
		}
	}

	@Override
	protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException
	{
		ChangeOfServiceVo voCOS = new ChangeOfServiceVo();
		
		voCOS.setCatsReferral(form.getGlobalContext().RefMan.getCatsReferral());
		voCOS.setOriginalService(form.cmbOldService().getValue());
		voCOS.setNewService(form.cmbService().getValue());
		voCOS.setChangingUser(form.qmbChangingUser().getValue() != null ? form.qmbChangingUser().getValue() : null);
		voCOS.setChangingDateTime(form.dtimChanging().getValue() != null ? form.dtimChanging().getValue() : null);
		
		String [] errors = voCOS.validate();
		if (errors != null)
		{
			engine.showErrors(errors);
			return;
		}

		try 
		{
			domain.saveChangeOfServiceVo(voCOS);
		} 
		catch (DomainInterfaceException e) 
		{
			engine.showMessage(e.getMessage());
			return;
		} 
		catch (StaleObjectException e) 
		{
			engine.showMessage(ims.configuration.gen.ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			return;
		}
		
		form.getGlobalContext().RefMan.setCurrentServiceLiteVo(form.cmbService().getValue());
		
		engine.close(DialogResult.OK);
	}
	@Override
	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		engine.close(DialogResult.CANCEL);
	}

	@Override
	protected void onQmbChangingUserTextSubmited(String value) throws PresentationLogicException
	{
		form.qmbChangingUser().clear();
		MemberOfStaffShortVo voMemberOfStaffFilter = new MemberOfStaffShortVo();
		PersonName name = new PersonName();
		name.setSurname(value);
		voMemberOfStaffFilter.setName(name);
		voMemberOfStaffFilter.setIsAppUser(new Boolean(true));
		voMemberOfStaffFilter.setIsActive(Boolean.TRUE);
		MemberOfStaffShortVoCollection voColl = null;
		try 
		{
			voColl = domain.listMembersOfStaff(voMemberOfStaffFilter);
		} 
		catch (DomainInterfaceException e) 
		{
			engine.showMessage(e.getMessage());
			return;
		}
	
		for (int i = 0 ; voColl != null && i < voColl.size() ;i++)
			form.qmbChangingUser().newRow(voColl.get(i),  voColl.get(i).getNameIsNotNull() ? voColl.get(i).getName().toString() : "");

		if (voColl == null || voColl.size() == 0)
		{
			engine.showMessage("No Members of Staff found for you search criteria.", "No Data Found", MessageButtons.OK, MessageIcon.INFORMATION);
			return;
		}
		
		if (voColl.size() == 1)
			form.qmbChangingUser().setValue(voColl.get(0));
		else if (voColl.size() > 1)
			form.qmbChangingUser().showOpened();
	}
}