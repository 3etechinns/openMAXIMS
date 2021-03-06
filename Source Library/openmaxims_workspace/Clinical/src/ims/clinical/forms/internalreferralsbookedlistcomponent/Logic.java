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
// This code was generated by Mara Iroaie using IMS Development Environment (version 1.80 build 5127.24028)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.

package ims.clinical.forms.internalreferralsbookedlistcomponent;
import java.util.ArrayList;
import java.util.Comparator;

import ims.clinical.forms.internalreferralsbookedlistcomponent.GenForm.grdReferralsRow;
import ims.clinical.forms.internalreferralsbookedlistcomponent.GenForm.grpAppointmentDetailsEnumeration;
import ims.clinical.vo.ClinicalReferralForOutpatientBookingWorklistVo;
import ims.clinical.vo.ClinicalReferralForOutpatientBookingWorklistVoCollection;
import ims.clinical.vo.OutpatientBookingWorklistSearchCriteriaVo;
import ims.clinical.vo.ServiceShortForClinicalInternalReferralsVoCollection;
import ims.configuration.gen.ConfigFlag;
import ims.core.vo.HcpLiteVoCollection;
import ims.core.vo.PatientShort;
import ims.core.vo.lookups.InternalReferralStatus;
import ims.core.vo.lookups.PatIdType;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.MessageButtons;
import ims.framework.MessageIcon;
import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.FormMode;
import ims.framework.enumerations.SortOrder;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.Color;
import ims.framework.utils.Date;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;
	
	private static final int APPOINTMENT_BOOKED_AND_REQUIRED 	= 2;
	private static final int APPOINTMENT_REQUIRED 				= 1;
	private static final int APPOINTMENT_BOOKED					= 0;
	
	private static final int COLUMN_DOB				= 5;
	private static final int COLUMN_REFFERINGHCP 	= 1;
	private static final int COLUMN_PATIENTNAME		= 4;
	private static final int COLUMN_REFTO			= 7;
	private static final int COLUMN_IMMAGE			= 8;
	@Override
	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
	}
	
	public void initialize() 
	{	
		initializeSearchCriteriaAndForm();
		if (form.getGlobalContext().Clinical.getOutpatientBookingWorklistSearchCriteriaIsNotNull())
		{
			open();		
		}		
	}
	
	private void initializeSearchCriteriaAndForm()
	{
		PatIdType dispIdType = PatIdType.getNegativeInstance(ims.configuration.gen.ConfigFlag.UI.DISPLAY_PATID_TYPE.getValue());
		form.grdReferrals().setcolNHSCaption(dispIdType.getIItemText());
		
		form.grpAppointmentDetails().setValue(grpAppointmentDetailsEnumeration.rdoRequiringAppointment);
		
		form.lblTotlRecords().setValue("0");
		
		form.getGlobalContext().Core.setPatientShort(null);
		
		form.imbSearch().setAsDefaultButton();
		
		form.getLocalContext().setlcInternalReferralSettings(domain.getInternalReferralSettingsConfig());
		
		form.dteTo().setValue(new Date());
		
		Date fromDate = new Date();

		if (form.getLocalContext().getlcInternalReferralSettingsIsNotNull() && form.getLocalContext().getlcInternalReferralSettings().getSearchNumberOfDaysIsNotNull())
		{
			form.dteFrom().setValue(fromDate.addDay(-form.getLocalContext().getlcInternalReferralSettings().getSearchNumberOfDays()));
		}
		else
			form.dteFrom().setValue(fromDate.addDay(-7));
	}

	private void open()
	{
		if (form.getGlobalContext().Clinical.getOutpatientBookingWorklistSearchCriteriaIsNotNull())
		{
			populateSearchCriteriaFromData(form.getGlobalContext().Clinical.getOutpatientBookingWorklistSearchCriteria());
		}
		
		if (search())
		{
			form.getGlobalContext().Clinical.setOutpatientBookingWorklistSearchCriteria(getSearchCriteria());
		}
		
		updateControlState();
		
	}

	private void populateSearchCriteriaFromData(OutpatientBookingWorklistSearchCriteriaVo outpatientBookingWorklistSearchCriteria) 
	{
		setAppointmentStatus(outpatientBookingWorklistSearchCriteria.getRequiredAppointment());
		
		if (outpatientBookingWorklistSearchCriteria.getServiceIsNotNull())
		{
			form.qmbService().newRow(outpatientBookingWorklistSearchCriteria.getService(), outpatientBookingWorklistSearchCriteria.getService().getServiceName());
		}
		
		if (outpatientBookingWorklistSearchCriteria.getHCPIsNotNull())
		{
			form.qmbHCP().newRow(outpatientBookingWorklistSearchCriteria.getHCP(), outpatientBookingWorklistSearchCriteria.getHCP().getMos().getIMosName());
		}
		
		form.qmbService().setValue(outpatientBookingWorklistSearchCriteria.getService());
		form.qmbHCP().setValue(outpatientBookingWorklistSearchCriteria.getHCP());
		form.dteFrom().setValue(outpatientBookingWorklistSearchCriteria.getFromDate());
		form.dteTo().setValue(outpatientBookingWorklistSearchCriteria.getToDate());	
	}


	private void setAppointmentStatus(Integer requiredAppointment) 
	{
		switch (requiredAppointment)
		{
		case APPOINTMENT_BOOKED:
			form.grpAppointmentDetails().setValue(grpAppointmentDetailsEnumeration.rdoAppointmentBooked);
			break;
			
		case APPOINTMENT_REQUIRED:
			form.grpAppointmentDetails().setValue(grpAppointmentDetailsEnumeration.rdoRequiringAppointment);
			break;
			
		case APPOINTMENT_BOOKED_AND_REQUIRED:
			form.grpAppointmentDetails().setValue(grpAppointmentDetailsEnumeration.rdoBothRequiredandBooked);
			break;
		}		
	}

	private void searchServices(String text) 
	{
		form.qmbService().clear();
		
		if (text == null || text.length() == 0)
			return;
		
		ServiceShortForClinicalInternalReferralsVoCollection servicesList = domain.listServices(text);
		
		if (servicesList != null)
		{
			for (int i = 0; i < servicesList.size(); i++)
			{
				form.qmbService().newRow(servicesList.get(i), servicesList.get(i).getServiceName());
			}
		}
		
		if (text != null && text.length() > 0)		
			form.qmbService().showOpened();
	}

	@Override
	protected void onFormDialogClosed(ims.framework.FormName formName, ims.framework.enumerations.DialogResult result) throws ims.framework.exceptions.PresentationLogicException
	{
		form.getGlobalContext().Clinical.setClinicalReferralRef(null);
		search();
		updateControlState();
	}
	
	@Override
	protected void onImbClearClick() throws ims.framework.exceptions.PresentationLogicException
	{
		clearSearchCriteriaAndResults();
		clearContextVariables();
		form.getGlobalContext().Clinical.setOutpatientBookingWorklistSearchCriteria(null);
		updateControlState();
	}
	
	private void clearContextVariables() 
	{
		form.getGlobalContext().Core.setPatientShort(null);
		form.getLocalContext().setlcSelectedReferral(null);
	}

	private void clearSearchCriteriaAndResults() 
	{
		form.grpAppointmentDetails().setValue(grpAppointmentDetailsEnumeration.rdoRequiringAppointment);	
		form.qmbService().clear();
		form.qmbHCP().clear();
		form.dteFrom().setValue(null);
		form.dteTo().setValue(null);
		form.grdReferrals().getRows().clear();	
		form.lblTotlRecords().setValue("0");
	}

	@Override
	protected void onImbSearchClick() throws ims.framework.exceptions.PresentationLogicException
	{	
		clearContextVariables();
		
		if(search())
		{
			form.getGlobalContext().Clinical.setOutpatientBookingWorklistSearchCriteria(getSearchCriteria());
		}
		
		updateControlState();
	}
	
	private void updateControlState() 
	{
		Boolean displayBookedAppt = false;
		
		if(form.grdReferrals().getValue() != null && form.grdReferrals().getValue().getCurrentReferralStatusIsNotNull() &&
		   form.grdReferrals().getValue().getCurrentReferralStatus().getReferralStatusIsNotNull() &&
		   Boolean.TRUE.equals(form.grdReferrals().getValue().getCurrentReferralStatus().getAppointmentRequired()) &&
		   !Boolean.TRUE.equals(form.grdReferrals().getValue().getCurrentReferralStatus().getAppointmentBooked()))
				displayBookedAppt = true;
		
		//form.getContextMenus().Clinical.getClinicalReferralBookListVIEWItem().setVisible(form.grdReferrals().getValue() != null);
		//form.getContextMenus().Clinical.getClinicalReferralBookListADMIN_EVENTItem().setVisible(form.grdReferrals().getValue() != null);
		
		//form.getContextMenus().Clinical.getClinicalReferralBookListAPPT_BOOKEDItem().setVisible(displayBookedAppt);
	}

	private OutpatientBookingWorklistSearchCriteriaVo getSearchCriteria() 
	{
		OutpatientBookingWorklistSearchCriteriaVo searchCriteria = new OutpatientBookingWorklistSearchCriteriaVo();
		
		searchCriteria.setRequiredAppointment(getAppointmentStatus());
		searchCriteria.setService(form.qmbService().getValue());
		searchCriteria.setHCP(form.qmbHCP().getValue());
		searchCriteria.setFromDate(form.dteFrom().getValue());
		searchCriteria.setToDate(form.dteTo().getValue());
		
		searchCriteria.setReferralStatus(InternalReferralStatus.ACCEPTED);
	
		return searchCriteria;
	}

	private Integer getAppointmentStatus()
	{
		grpAppointmentDetailsEnumeration searchType = form.grpAppointmentDetails().getValue();
		
		if (grpAppointmentDetailsEnumeration.rdoRequiringAppointment.equals(searchType))
		{
			return APPOINTMENT_REQUIRED;
		}
		
		if (grpAppointmentDetailsEnumeration.rdoAppointmentBooked.equals(searchType))
		{
			return APPOINTMENT_BOOKED;
		}
		
		if (grpAppointmentDetailsEnumeration.rdoBothRequiredandBooked.equals(searchType))
		{
			return APPOINTMENT_BOOKED_AND_REQUIRED;
		}
		
		return null;
	}

	private boolean search()
	{	
		if(isSearchCriteriaValid())
		{			
			ClinicalReferralForOutpatientBookingWorklistVoCollection referralsList = domain.listReferrals(getSearchCriteria());
					
			if (referralsList == null || referralsList.size() == 0)
			{
				engine.showMessage("No records matching your search criteria were found!", "No results",MessageButtons.OK, MessageIcon.INFORMATION);
				clearContextVariables();
				form.grdReferrals().getRows().clear();
				form.lblTotlRecords().setValue("0");
				return true;
			}
				
			referralsList.sort(new referralIsUrgentReferralComparator());
			populateScreenFromData(referralsList);
				
			return true;
		}
		
		return false;
	}

	private void populateScreenFromData(ClinicalReferralForOutpatientBookingWorklistVoCollection referralsList)
	{
		form.grdReferrals().getRows().clear();
		
		for(int i = 0; i < referralsList.size(); i++)
		{
			if (referralsList.get(i) == null)
				continue;
			
			addNewReferralRow(referralsList.get(i));				
		}
		
		form.grdReferrals().setValue(form.getLocalContext().getlcSelectedReferral());
		
		if (form.grdReferrals().getValue() == null)
			form.getGlobalContext().Core.setPatientShort(null);
		
		StringBuilder sb = new StringBuilder();
		sb.append(referralsList.size());
		form.lblTotlRecords().setValue(sb.toString());
	}

	private void addNewReferralRow(ClinicalReferralForOutpatientBookingWorklistVo referral)
	{
		grdReferralsRow newReferralRow = form.grdReferrals().getRows().newRow();
		
		newReferralRow.setcolReferralDate(referral.getDateDecisionToRefer() != null ? referral.getDateDecisionToRefer() : null);
		newReferralRow.setCellcolReferralDateTooltip(newReferralRow.getcolReferralDate() != null ? newReferralRow.getcolReferralDate().toString() : null);
		
		Color urgentReferralBackgroundColour = form.getLocalContext().getlcInternalReferralSettingsIsNotNull() ? form.getLocalContext().getlcInternalReferralSettings().getUrgentReferralBackgroundColour() : null;
		if (Boolean.TRUE.equals(referral.getUrgentReferral()))
		{
			newReferralRow.setcolUrgent("Yes");	
			newReferralRow.setBackColor(urgentReferralBackgroundColour);
		}
		else if (Boolean.FALSE.equals(referral.getUrgentReferral()))
			newReferralRow.setcolUrgent("No");
		else
			newReferralRow.setcolUrgent(null);
		
		newReferralRow.setCellcolUrgentTooltip(newReferralRow.getcolUrgent());
		
		newReferralRow.setcolNHS(referral.getPatient() != null && referral.getPatient().getDisplayId() != null ? referral.getPatient().getDisplayId().getValue() : null);
		newReferralRow.setCellcolNHSTooltip(newReferralRow.getcolNHS());
		
		newReferralRow.setcolName(referral.getPatient() != null && referral.getPatient().getName() != null ? referral.getPatient().getName().toString() : null);
		newReferralRow.setCellcolNameTooltip(newReferralRow.getcolName());	
		
		newReferralRow.setcolDOB(referral.getPatient() != null && referral.getPatient().getDob() != null ? referral.getPatient().getDob().toString() : null);
		newReferralRow.setCellcolDOBTooltip(newReferralRow.getcolDOB());
		
		newReferralRow.setcolReferringHCP(referral.getReferringHCP() != null && referral.getReferringHCP().getName() != null ? referral.getReferringHCP().getName().toString() : null);
		newReferralRow.setCellcolReferringHCPTooltip(newReferralRow.getcolReferringHCP());
		
		newReferralRow.setcolRequestToService(referral.getReferToService() != null && referral.getReferToService().getService() != null ? referral.getReferToService().getService().getServiceName() : null);
		newReferralRow.setCellcolRequestToServiceTooltip(newReferralRow.getcolRequestToService());
		
		newReferralRow.setcolRefTo(referral.getReferToHCP() != null && referral.getReferToHCP().getName() != null ? referral.getReferToHCP().getName().toString() : null);
		newReferralRow.setCellcolRefToTooltip(newReferralRow.getcolRefTo());
		
		newReferralRow.setcolStatus(referral.getCurrentReferralStatus() != null &&  referral.getCurrentReferralStatus().getReferralStatus() != null ? referral.getCurrentReferralStatus().getReferralStatus().getText() : null);
		newReferralRow.setCellcolStatusTooltip(newReferralRow.getcolStatus());	
		
		if (referral.getCurrentReferralStatusIsNotNull() && Boolean.TRUE.equals(referral.getCurrentReferralStatus().getAppointmentBooked()))
		{
			newReferralRow.setcolAppt(form.getImages().Core.Calendar16);
			newReferralRow.setCellcolApptTooltip("Appointment Booked");
		}
		
		if (referral.getCurrentReferralStatusIsNotNull() && Boolean.TRUE.equals(referral.getCurrentReferralStatus().getAppointmentRequired()))
		{
			newReferralRow.setcolAppt(form.getImages().Scheduling.session_16);
			newReferralRow.setCellcolApptTooltip("Requiring Appointment");
		}
		
		newReferralRow.setValue(referral);
	}

	private boolean isSearchCriteriaValid() 
	{
		ArrayList<String> errorList = new ArrayList<String>();
		
		if (form.dteFrom().getValue() == null)
		{
			errorList.add("Referral Date From is mandatory");
		}
		
		if (form.dteTo().getValue() == null)
		{
			errorList.add("Referral Date To is mandatory");
		}
		
		if (form.dteFrom().getValue() != null && form.dteTo().getValue()!= null && form.dteFrom().getValue().isGreaterThan(form.dteTo().getValue()))
		{
			errorList.add("Referral Date From cannot be greater than Referral Date To");
		}
		
		if (errorList != null && errorList.size() > 0)
		{
			engine.showErrors(errorList.toArray(new String[errorList.size()]));
			clearContextVariables();
			form.grdReferrals().getRows().clear();
			form.lblTotlRecords().setValue("0");
			return false;
		}
		
		return true;
	}

	@Override
	protected void onContextMenuItemClick(int menuItemID, ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
	{
		switch(menuItemID)
		{
			case GenForm.ContextMenus.ClinicalNamespace.ClinicalReferralBookList.VIEW:
				viewPatientReferral();
			break;
				
			case GenForm.ContextMenus.ClinicalNamespace.ClinicalReferralBookList.ADMIN_EVENT:
				viewAdminEvent();
			break;
			
			case GenForm.ContextMenus.ClinicalNamespace.ClinicalReferralBookList.APPT_BOOKED:
				engine.showMessage("Confirm appointment for " + getPatientName() + " has been Booked", "Warning", MessageButtons.YESNO);
			break;
		}
		
		updateControlState();
	}
	
	private void viewAdminEvent() 
	{
		form.getGlobalContext().Clinical.setClinicalReferralRef(form.grdReferrals().getValue());
		engine.open(form.getForms().Clinical.InternalReferralAdminEventDialog);	
	}

	private void viewPatientReferral() 
	{
			form.getGlobalContext().Clinical.setClinicalReferralRef(form.grdReferrals().getValue());	
			engine.open(form.getForms().Clinical.InternalReferralDialog, new Object[] {FormMode.VIEW, null, null});
	}

	private void markReferralAsAppointmentBooked() 
	{	
		if (form.getLocalContext().getlcSelectedReferralIsNotNull())
		{
			if (updateReferral())
			{
				if(search())
				{
					form.getGlobalContext().Clinical.setOutpatientBookingWorklistSearchCriteria(getSearchCriteria());
				}
			}
		}
	}

	private boolean updateReferral()
	{
		try
		{
			form.getLocalContext().setlcSelectedReferral(domain.markReferralAsAppointmentBooked(form.getLocalContext().getlcSelectedReferral()));
		}catch (StaleObjectException e)
		{
			e.printStackTrace();
			engine.showMessage(ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			if(search())
			{
				form.getGlobalContext().Clinical.setOutpatientBookingWorklistSearchCriteria(getSearchCriteria());
			}
			return false;
		}
		return true;
	}

	private String getPatientName() 
	{
		String patientName = " ";
		
		if (form.getLocalContext().getlcSelectedReferralIsNotNull() && form.getLocalContext().getlcSelectedReferral().getPatientIsNotNull() &&
			form.getLocalContext().getlcSelectedReferral().getPatient().getNameIsNotNull())
			patientName = form.getLocalContext().getlcSelectedReferral().getPatient().getName().toString();
		
		return patientName;
	}

	@Override
	protected void onMessageBoxClosed(int messageBoxId, ims.framework.enumerations.DialogResult result) throws ims.framework.exceptions.PresentationLogicException
	{
		if (DialogResult.YES.equals(result))
			markReferralAsAppointmentBooked();	
		
		updateControlState();
	}

	@Override
	protected void onGrdReferralsSelectionChanged() throws PresentationLogicException 
	{
		if (form.grdReferrals().getValue() != null && form.grdReferrals().getValue().getPatient() != null)
		{
			form.getGlobalContext().Core.setPatientShort(new PatientShort(form.grdReferrals().getValue().getPatient().getID_Patient(), form.grdReferrals().getValue().getPatient().getVersion_Patient()));
		}
		
		form.getLocalContext().setlcSelectedReferral(form.grdReferrals().getValue());
		updateControlState();
		
		viewPatientReferral();
	}
	
	@Override
	protected void onGrdReferralsGridHeaderClicked(int column) throws PresentationLogicException
	{
		sortOrder(column);
		if (form.getLocalContext().getlcSelectedReferralIsNotNull())
			form.grdReferrals().setValue(form.getLocalContext().getlcSelectedReferral());
		updateControlState();
	}

	private void sortOrder(int column)
	{
		ClinicalReferralForOutpatientBookingWorklistVoCollection referralList = form.grdReferrals().getValues();
		
		if (referralList == null || referralList.size() == 0)
			return ;
		
		if (column == COLUMN_DOB)
		{
			form.getLocalContext().setlcDOBSortOrder(SortOrder.ASCENDING.equals(form.getLocalContext().getlcDOBSortOrder())? SortOrder.DESCENDING : SortOrder.ASCENDING);
			referralList.sort(new InternalReferralsPatientDOBComparator(form.getLocalContext().getlcDOBSortOrder()));
		}
		
		if (column == COLUMN_REFFERINGHCP)
		{
			form.getLocalContext().setlcRefferingHCPSortOrder(SortOrder.ASCENDING.equals(form.getLocalContext().getlcRefferingHCPSortOrder())? SortOrder.DESCENDING : SortOrder.ASCENDING);
			referralList.sort(new InternalReferralsReferringHCPComparator(form.getLocalContext().getlcRefferingHCPSortOrder()));
		}
		
		if (column == COLUMN_REFTO)
		{
			form.getLocalContext().setlcRefToSortOrder(SortOrder.ASCENDING.equals(form.getLocalContext().getlcRefToSortOrder())? SortOrder.DESCENDING : SortOrder.ASCENDING);
			referralList.sort(new InternalReferralsRefToComparator(form.getLocalContext().getlcRefToSortOrder()));
		}
		
		if (column == COLUMN_PATIENTNAME)
		{
			form.getLocalContext().setlcPatientNameSortOrder(SortOrder.ASCENDING.equals(form.getLocalContext().getlcPatientNameSortOrder())? SortOrder.DESCENDING : SortOrder.ASCENDING);
			referralList.sort(new InternalReferralsPatientNameComparator(form.getLocalContext().getlcPatientNameSortOrder()));
		}
		
		if (column == COLUMN_IMMAGE)
		{
			form.getLocalContext().setlcImageSortOrder(SortOrder.ASCENDING.equals(form.getLocalContext().getlcImageSortOrder())? SortOrder.DESCENDING : SortOrder.ASCENDING);
			referralList.sort(new InternalReferralsImagComparator(form.getLocalContext().getlcImageSortOrder()));
		}
		
		populateScreenFromData(referralList);		
	}
	
	public class referralIsUrgentReferralComparator implements Comparator<ClinicalReferralForOutpatientBookingWorklistVo>
	{
		public int compare(ClinicalReferralForOutpatientBookingWorklistVo o1,ClinicalReferralForOutpatientBookingWorklistVo o2)
		{		
			int retVal = 0;
			if (retVal == 0)
			{
				if(o1.getUrgentReferral() == null && o2.getUrgentReferral() != null)
					return -1;
				if(o1.getUrgentReferral() != null && o2.getUrgentReferral() == null)
					return 1;
				if(o1.getUrgentReferral() != null && o2.getUrgentReferral() != null)
					retVal = o1.getUrgentReferral().compareTo(o2.getUrgentReferral())*-1;
			}
			if (retVal == 0)
			{
				if(o1.getDateDecisionToRefer() == null && o2.getDateDecisionToRefer() != null)
					return -1;
				if(o1.getDateDecisionToRefer() != null && o2.getDateDecisionToRefer() == null)
					return 1;
				if(o1.getDateDecisionToRefer() != null && o2.getDateDecisionToRefer() != null)
					retVal = o1.getDateDecisionToRefer().compareTo(o2.getDateDecisionToRefer())*-1;
			}
			return retVal;
			
		}
	}
	
	public class InternalReferralsPatientDOBComparator implements Comparator<ClinicalReferralForOutpatientBookingWorklistVo>
	{
		private int order = 1;
		
		public InternalReferralsPatientDOBComparator()
		{
			order = 1;
		}
		
		public InternalReferralsPatientDOBComparator(SortOrder order)
		{
			this.order = SortOrder.DESCENDING.equals(order) ? -1 : 1;
		}
		
		public int compare(ClinicalReferralForOutpatientBookingWorklistVo o1, ClinicalReferralForOutpatientBookingWorklistVo o2)
		{
			if(o1 != null && o1.getPatient().getDobIsNotNull() && o2 != null && o2.getPatient().getDobIsNotNull())
				return order*o1.getPatient().getDob().compareTo(o2.getPatient().getDob());
			
			if (o1 == null || !o1.getPatient().getDobIsNotNull())
				return -1 * order;
			
			if (o2 == null || !o2.getPatient().getDobIsNotNull())
				return order;
			
			return 0;
		}
	}

	public class InternalReferralsReferringHCPComparator implements Comparator<ClinicalReferralForOutpatientBookingWorklistVo>
	{
		private int order = 1;
		
		public InternalReferralsReferringHCPComparator()
		{
			order = 1;
		}
		
		public InternalReferralsReferringHCPComparator(SortOrder order)
		{
			this.order = SortOrder.DESCENDING.equals(order) ? -1 : 1;
		}
		
		public int compare(ClinicalReferralForOutpatientBookingWorklistVo o1, ClinicalReferralForOutpatientBookingWorklistVo o2)
		{
			if(o1 != null && o1.getReferringHCPIsNotNull() && o1.getReferringHCP().getMosIsNotNull() && o1.getReferringHCP().getMos().getNameIsNotNull() && 
			   o2 != null && o2.getReferringHCPIsNotNull() && o2.getReferringHCP().getMosIsNotNull() && o2.getReferringHCP().getMos().getNameIsNotNull())
				return order* o1.getReferringHCP().getMos().getName().compareTo( o2.getReferringHCP().getMos().getName());
			
			if (o1 == null || !o1.getReferringHCPIsNotNull() || !o1.getReferringHCP().getMosIsNotNull() || !o1.getReferringHCP().getMos().getNameIsNotNull())
				return -1 * order;
			
			if (o2 == null || !o2.getReferringHCPIsNotNull() || !o2.getReferringHCP().getMosIsNotNull() || !o2.getReferringHCP().getMos().getNameIsNotNull())
				return order;
			
			return 0;
		}
	}
	
	public class InternalReferralsRefToComparator implements Comparator<ClinicalReferralForOutpatientBookingWorklistVo>
	{
		private int order = 1;
		
		public InternalReferralsRefToComparator()
		{
			order = 1;
		}
		
		public InternalReferralsRefToComparator(SortOrder order)
		{
			this.order = SortOrder.DESCENDING.equals(order) ? -1 : 1;
		}
		
		public int compare(ClinicalReferralForOutpatientBookingWorklistVo o1, ClinicalReferralForOutpatientBookingWorklistVo o2)
		{
			if(o1 != null && o1.getReferToHCPIsNotNull() && o1.getReferToHCP().getMosIsNotNull() && o1.getReferToHCP().getMos().getNameIsNotNull() && 
			   o2 != null && o2.getReferToHCPIsNotNull() && o2.getReferToHCP().getMosIsNotNull() && o2.getReferToHCP().getMos().getNameIsNotNull())
				return order* o1.getReferToHCP().getMos().getName().compareTo( o2.getReferToHCP().getMos().getName());
			
			if (o1 == null || !o1.getReferToHCPIsNotNull() || !o1.getReferToHCP().getMosIsNotNull() || !o1.getReferToHCP().getMos().getNameIsNotNull())
				return -1 * order;
			
			if (o2 == null || !o2.getReferToHCPIsNotNull() || !o2.getReferToHCP().getMosIsNotNull() || !o2.getReferToHCP().getMos().getNameIsNotNull())
				return order;
			
			return 0;
		}
	}
	
	public class InternalReferralsPatientNameComparator implements Comparator<ClinicalReferralForOutpatientBookingWorklistVo>
	{
		private int order = 1;
		
		public InternalReferralsPatientNameComparator()
		{
			order = 1;
		}
		
		public InternalReferralsPatientNameComparator(SortOrder order)
		{
			this.order = SortOrder.DESCENDING.equals(order) ? -1 : 1;
		}
		
		public int compare(ClinicalReferralForOutpatientBookingWorklistVo o1, ClinicalReferralForOutpatientBookingWorklistVo o2)
		{
			if(o1 != null && o1.getPatientIsNotNull() && o1.getPatient().getNameIsNotNull() && 
			   o2 != null && o2.getPatientIsNotNull() && o2.getPatient().getNameIsNotNull())
				return order* o1.getPatient().getName().compareTo(o2.getPatient().getName());
			
			if (o1 == null || !o1.getPatientIsNotNull() || !o1.getPatient().getNameIsNotNull())
				return -1 * order;
			
			if (o2 == null || !o2.getPatientIsNotNull() || !o2.getPatient().getNameIsNotNull())
				return order;
			
			return 0;
		}
	}
	
	public class InternalReferralsImagComparator implements Comparator<ClinicalReferralForOutpatientBookingWorklistVo>
	{
		private int order = 1;
		
		public InternalReferralsImagComparator()
		{
			order = 1;
		}
		
		public InternalReferralsImagComparator(SortOrder order)
		{
			this.order = SortOrder.DESCENDING.equals(order) ? -1 : 1;
		}
		
		public int compare(ClinicalReferralForOutpatientBookingWorklistVo o1, ClinicalReferralForOutpatientBookingWorklistVo o2)
		{
			if (o1 != null && o1.getCurrentReferralStatusIsNotNull() && o2 != null && o2.getCurrentReferralStatusIsNotNull())
			{
				Integer val1, val2;
				
				if (Boolean.TRUE.equals(o1.getCurrentReferralStatus().getAppointmentBooked()) && !Boolean.TRUE.equals(o1.getCurrentReferralStatus().getAppointmentRequired()))
				{
					val1 = 1;
				}
				else if (!Boolean.TRUE.equals(o1.getCurrentReferralStatus().getAppointmentBooked()) && Boolean.TRUE.equals(o1.getCurrentReferralStatus().getAppointmentRequired()))
				{
					val1 = 2;
				}
				else 
					val1 = 0;
				
				if (Boolean.TRUE.equals(o2.getCurrentReferralStatus().getAppointmentBooked()) && !Boolean.TRUE.equals(o2.getCurrentReferralStatus().getAppointmentRequired()))
				{
					val2 = 1;
				}
				else if (!Boolean.TRUE.equals(o2.getCurrentReferralStatus().getAppointmentBooked()) && Boolean.TRUE.equals(o2.getCurrentReferralStatus().getAppointmentRequired()))
				{
					val2 = 2;
				}
				else 
					val2 = 0;
				
				if (val1 != 0 && val2 != 0)
				{
					return val1.compareTo(val2) * order;
				}

				if (val1 != 0 && val2 == 0)
				{
					return order;
				}

				if (val2 != 0 && val1 == 0)
				{
					return -1 * order;
				}	
			}
		
			return 0;
		}
	}
		
	@Override
	protected void onQmbHCPTextSubmited(String value)throws PresentationLogicException 
	{
		form.qmbHCP().setFocus();
		searchHCP(value);		
	}

	private void searchHCP(String text) 
	{
		form.qmbHCP().clear();
		
		if (text == null || text.length() == 0)
			return;
		
		HcpLiteVoCollection hcpList = domain.listHCP(text);
		
		if (hcpList != null)
		{

			for (int i = 0; i < hcpList.size(); i++)
			{
				form.qmbHCP().newRow(hcpList.get(i), hcpList.get(i).getMos().getIMosName());
			}
		}
		
		if (text != null && text.length() > 0)
			form.qmbHCP().showOpened();
		
	}

	@Override
	protected void onQmbServiceTextSubmited(String value) throws PresentationLogicException 
	{
		form.qmbService().setFocus();
		searchServices(value);
	}
}
