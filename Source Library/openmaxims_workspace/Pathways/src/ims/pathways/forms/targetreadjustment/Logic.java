//#############################################################################
//#                                                                           #
//#  Copyright (C) <2015>  <IMS MAXIMS>                                       #
//#                                                                           #
//#  This program is free software: you can redistribute it and/or modify     #
//#  it under the terms of the GNU Affero General Public License as           #
//#  published by the Free Software Foundation, either version 3 of the       #
//#  License, or (at your option) any later version.                          # 
//#                                                                           #
//#  This program is distributed in the hope that it will be useful,          #
//#  but WITHOUT ANY WARRANTY; without even the implied warranty of           #
//#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            #
//#  GNU Affero General Public License for more details.                      #
//#                                                                           #
//#  You should have received a copy of the GNU Affero General Public License #
//#  along with this program.  If not, see <http://www.gnu.org/licenses/>.    #
//#                                                                           #
//#  IMS MAXIMS provides absolutely NO GUARANTEE OF THE CLINICAL SAFTEY of    #
//#  this program.  Users of this software do so entirely at their own risk.  #
//#  IMS MAXIMS only ensures the Clinical Safety of unaltered run-time        #
//#  software that it builds, deploys and maintains.                          #
//#                                                                           #
//#############################################################################
//#EOH
// This code was generated by Catalin Tomozei using IMS Development Environment (version 1.70 build 3300.22643)
// Copyright (C) 1995-2009 IMS MAXIMS plc. All rights reserved.

package ims.pathways.forms.targetreadjustment;

import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.interfaces.IAppUser;
import ims.framework.utils.DateTime;
import ims.pathways.forms.targetreadjustment.GenForm.grdReadjustmentsRow;
import ims.pathways.vo.TargetReadjustmentVo;
import ims.pathways.vo.TargetReadjustmentVoCollection;
import ims.pathways.vo.enums.TargetReadjustmentsAction;
import ims.vo.SystemInformation;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		form.setMode(FormMode.VIEW);
		populateScreen(form.grdReadjustments().getSelectedRow() != null ? form.grdReadjustments().getSelectedRow().getValue() : null);
		// WDEV-18410 
		form.getLocalContext().setEditedRecord(form.grdReadjustments().getSelectedRow() != null ? form.grdReadjustments().getSelectedRow().getValue() : null);
	}
	@Override
	protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException
	{
		// WDEV-18410 
		TargetReadjustmentVo vo = populateDataFromScreen((TargetReadjustmentVo) (form.getLocalContext().getEditedRecordIsNotNull() ? form.getLocalContext().getEditedRecord().clone() : null));
		
		SystemInformation sysInfo = new SystemInformation();
		sysInfo.setCreationDateTime(form.ctnDetails().dtimDate().getValue());
		sysInfo.setCreationUser(form.ctnDetails().lblUserEdit().getValue());
		vo.setSystemInformation(sysInfo);
				
		String[] errors = vo.validate();
		if(errors != null && errors.length > 0)
		{
			engine.showErrors(errors);	
			return;
		}
		
		TargetReadjustmentVoCollection targetReadjustmentsCollection = form.getGlobalContext().Pathways.getTargetReadjustments();
		
		if (targetReadjustmentsCollection == null)
		{
			targetReadjustmentsCollection = new TargetReadjustmentVoCollection();
		}
		
		//WDEV-18410 
		if (vo.getID_TargetReadjustmentIsNotNull())
		{
			for(int i = 0; i < targetReadjustmentsCollection.size(); i++)
			{
				if (targetReadjustmentsCollection.get(i).getID_TargetReadjustment().equals(vo.getID_TargetReadjustment()))
				{
					targetReadjustmentsCollection.remove(i);
					targetReadjustmentsCollection.add(vo);
				}
			}
		}
		else
			targetReadjustmentsCollection.add(vo);
		
		form.getGlobalContext().Pathways.setTargetReadjustments(targetReadjustmentsCollection);
		engine.close(DialogResult.OK);
	}
	@Override
	protected void onBtnEditClick() throws ims.framework.exceptions.PresentationLogicException
	{
		form.setMode(FormMode.EDIT);
		form.ctnDetails().setCollapsed(false);
		form.ctnDetails().setCaption("Edit Readjustment");
		
		TargetReadjustmentVo vo = form.grdReadjustments().getSelectedRow().getValue();
		SystemInformation sysInfo = vo.getSystemInformation();
		populateScreenFromData(vo);
		
		if (sysInfo != null)
		{
			form.ctnDetails().dtimDate().setValue(sysInfo.getCreationDateTime() != null ? sysInfo.getCreationDateTime() : null);
			form.ctnDetails().lblUserEdit().setValue(sysInfo.getCreationUser() != null ? sysInfo.getCreationUser() : null);
		}
		
	}
	@Override
	protected void onBtnNewClick() throws ims.framework.exceptions.PresentationLogicException
	{
		form.setMode(FormMode.EDIT);
		
		form.ctnDetails().setCollapsed(false);
		form.ctnDetails().setCaption("New Readjustment");

		form.getLocalContext().setEditedRecord(null);
		clearInstanceDetails();
		
		form.ctnDetails().lblUserEdit().setValue(getRecordingUser());
		form.ctnDetails().dtimDate().setValue(new DateTime());
	}
	
	private String getRecordingUser()
	{		
		String user = null;
		Object usr = engine.getLoggedInUser();
		if (usr != null) {
			user = ((IAppUser)usr).getUsername();
			if (user == null)
				return null;
		}		
		
		return user;
	}	
	
	@Override
	protected void onFormOpen(Object[] args) throws PresentationLogicException 
	{
		form.setMode(FormMode.VIEW);
		form.ctnDetails().setCollapsed(true);		
		updateEditButtonStatus();
		populateGrid(form.getGlobalContext().Pathways.getTargetReadjustments());
		
		if (form.getGlobalContext().Pathways.getTargetReadjustmentsActionIsNotNull())
		{
			if (form.getGlobalContext().Pathways.getTargetReadjustmentsAction().equals(TargetReadjustmentsAction.ADDREADJUSTMENT))
			{
				onBtnNewClick();
			}
		}
	}
	
	private void populateGrid(TargetReadjustmentVoCollection targetReadjustments) 
	{
		TargetReadjustmentVoCollection coll = form.getGlobalContext().Pathways.getTargetReadjustments();
		
		if (coll == null || 
				(coll != null && 
						coll.size() == 0))
		return;
				
		for (int i = 0; i < coll.size(); i++) 
		{
			TargetReadjustmentVo vo = coll.get(i);
			grdReadjustmentsRow row = form.grdReadjustments().getRows().newRow();
			row.setNoOfDays(vo.getNoOfDaysIsNotNull() ? vo.getNoOfDays().toString() : null);
			row.setNotes(vo.getReasonIsNotNull() ?  vo.getReason() : null);
			row.setReason(vo.getTargetReadjustmentIsNotNull() ? vo.getTargetReadjustment().getText() : null);
			row.setDate(vo.getSystemInformationIsNotNull() ? vo.getSystemInformation().getCreationDateTime() != null ? vo.getSystemInformation().getCreationDateTime().toString() : null : null);
			row.setUser(vo.getSystemInformationIsNotNull() ? vo.getSystemInformation().getCreationUser() : null);
			row.setValue(vo);
		}		
	}
	private void updateEditButtonStatus() 
	{
		form.btnEdit().setVisible(form.grdReadjustments().getSelectedRow() != null);		
	}
	@Override
	protected void onFormModeChanged() 
	{
		form.ctnDetails().setCollapsed(form.getMode().equals(FormMode.VIEW));				
		boolean isRowSelected = form.grdReadjustments().getSelectedRow() != null ? true : false;
		
		if (form.getMode().equals(FormMode.EDIT)) {			
				form.btnEdit().setVisible(false);
		}
		else {
			form.btnEdit().setVisible(isRowSelected);
		}
	}
	@Override
	protected void onBtnCloseClick() throws PresentationLogicException 
	{
		engine.close(DialogResult.CANCEL);		
	}
	@Override
	protected void onGrdReadjustmentsSelectionChanged()
			throws PresentationLogicException 
	{
		boolean isRowSelected = form.grdReadjustments().getSelectedRow() != null;
		
		form.ctnDetails().setCollapsed(!isRowSelected);
		form.btnEdit().setVisible(isRowSelected);
		form.ctnDetails().setCaption("Edit Readjustment");
		TargetReadjustmentVo rowValue = form.grdReadjustments().getSelectedRow().getValue();
		form.getLocalContext().setEditedRecord(rowValue);
		
		populateScreen(rowValue);		
	}
	
	private void populateScreen(TargetReadjustmentVo rowValue) 
	{
		clearInstanceDetails();
		form.ctnDetails().setCaption(rowValue == null ? " " : "Edit Readjustment");
		if (rowValue == null)
			return;
		
		form.ctnDetails().setCollapsed(false);
		
		form.ctnDetails().intNoOfDays().setValue(rowValue.getNoOfDays());
		form.ctnDetails().txtReason().setValue(rowValue.getReason());
		form.ctnDetails().cmbReadjustments().setValue(rowValue.getTargetReadjustment());
		form.ctnDetails().dtimDate().setValue(rowValue.getSystemInformationIsNotNull() ? rowValue.getSystemInformation().getCreationDateTime() : null);
		form.ctnDetails().lblUserEdit().setValue(rowValue.getSystemInformationIsNotNull() ? rowValue.getSystemInformation().getCreationUser() : null);
	}
	
	private void clearInstanceDetails() 
	{
		form.ctnDetails().intNoOfDays().setValue(null);
		form.ctnDetails().txtReason().setValue(null);
		form.ctnDetails().cmbReadjustments().setValue(null);
		form.ctnDetails().dtimDate().setValue(null);	
		form.ctnDetails().lblUserEdit().setValue(null);		
	}	
}
